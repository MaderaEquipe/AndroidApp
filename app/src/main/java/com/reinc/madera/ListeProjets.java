package com.reinc.madera;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import static com.reinc.madera.ProjetDetails.PROJET_ID;

public class ListeProjets extends AppCompatActivity {
    Button btnGetJson = null;
    TextView tvFruits = null;
    ListView lvClients = null;
    private ListView projetsListView;
    private ProjetsAdapter projetsAdapter;
    private ArrayList<Projet> projetsList;
    View.OnClickListener getJson = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            updateListe();

        }

    };

    public void updateListe() {
        new FileDownloader(ListeProjets.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                projetsList = new ArrayList<>();

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);
                    JSONArray projet = jsonObj.getJSONArray("hydra:member");

                    int len = projet.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject curProjet = projet.getJSONObject(i);

                        String projetId = curProjet.getString("id");
                        String labelPlan = curProjet.getString("labelPlan");
                        String datePlan = curProjet.getString("datePlan");
                        String utilisateur = curProjet.getString("utilisateur");
                        String client = curProjet.getString("client");

                        Projet newProjet = new Projet(projetId, labelPlan, datePlan, utilisateur, client);
                        projetsList.add(newProjet);


                    }

                    projetsListView = (ListView) findViewById(R.id.lvClients); // la ListView précédemment créée (XML)
                    projetsAdapter = new ProjetsAdapter(
                            ListeProjets.this,
                            R.layout.projet_list_template,
                            projetsList
                    );
                    projetsListView.setAdapter(projetsAdapter);

                    projetsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            Projet selectedProjet = (Projet) adapterView.getItemAtPosition(position);
                            String projetId = selectedProjet.getId();

                            Log.i("XXXXXXXX",projetId);
                            Intent intent = new Intent(ListeProjets.this, ProjetDetails.class);
                            intent.putExtra(PROJET_ID, String.valueOf(projetId));
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("https://api-madera.herokuapp.com/api/projets");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_projets);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // btnGetJson = (Button) findViewById(R.id.btnGetJson);
        // btnGetJson.setOnClickListener(getJson);

        // tvFruits = (TextView) findViewById(R.id.tvFruits);
        ListView lvClients = (ListView) findViewById(R.id.lvClients);
        updateListe();
    }

    @Override
    protected void onPause() {
        super.onPause();

//        btnGetJson.setOnClickListener(null);
        btnGetJson = null;
        tvFruits = null;
        ListView lvClients = null;
    }


}

