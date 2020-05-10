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

import static com.reinc.madera.DevisDetails.DEVIS_ID;

public class ListeDevis extends AppCompatActivity {


    Button btnGetJson = null;
    TextView tvFruits = null;
    ListView lvClients = null;
    private ListView devisListView;
    private DevisAdapter devisAdapter;
    private ArrayList<Devis> devisList;
    View.OnClickListener getJson = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            updateListe();

        }

    };

    public void updateListe() {
        new FileDownloader(ListeDevis.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                devisList = new ArrayList<>();

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);
                    JSONArray devis = jsonObj.getJSONArray("hydra:member");

                    int len = devis.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject curDevis = devis.getJSONObject(i);

                        String devisId = curDevis.getString("id");
                        String nomDevis = curDevis.getString("nomDevis");
                        String etatDevis = curDevis.getString("etatDevis");
                        String totalHT = curDevis.getString("totalHT");
                        String totalTTC = curDevis.getString("totalTTC");
                        String tauxRemise = curDevis.getString("tauxRemise");
                        String creationDate = curDevis.getString("dateCreation");
                        String projet = curDevis.getString("idProjet");

                        Devis newDevis = new Devis(devisId, nomDevis, etatDevis, totalHT, totalTTC, tauxRemise, creationDate, projet);
                        devisList.add(newDevis);


                    }

                    devisListView = (ListView) findViewById(R.id.lvClients); // la ListView précédemment créée (XML)
                    devisAdapter = new DevisAdapter(
                            ListeDevis.this,
                            R.layout.devis_list_template,
                            devisList
                    );
                    devisListView.setAdapter(devisAdapter);

                    devisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            Devis selectedDevis = (Devis) adapterView.getItemAtPosition(position);
                            String devisId = selectedDevis.getIdDevis();

                            Log.i("XXXXXXXX",devisId);
                            Intent intent = new Intent(ListeDevis.this, DevisDetails.class);
                            intent.putExtra(DEVIS_ID, String.valueOf(devisId));
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("https://api-madera.herokuapp.com/api/devis");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_devis);
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