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

import static com.reinc.madera.ClientDetails.CLIENT_ID;

public class ListeClients extends AppCompatActivity {


    Button btnGetJson = null;
    TextView tvFruits = null;
    ListView lvClients = null;
    private ListView clientsListView;
    private ClientsAdapter clientAdapter;
    private ArrayList<Client> clientsList;
    View.OnClickListener getJson = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            updateListe();

        }

    };

    public void updateListe() {
        new FileDownloader(ListeClients.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                clientsList = new ArrayList<>();

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);
                    JSONArray users = jsonObj.getJSONArray("hydra:member");

                    int len = users.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject curUser = users.getJSONObject(i);

                        String clientId = curUser.getString("id");
                        String clientNom = curUser.getString("nomClient");
                        String clientPrenom = curUser.getString("prenomClient");
                        String clientAdresse = curUser.getString("adresseClient");
                        String clientCodeP = curUser.getString("cpClient");
                        String clientVille = curUser.getString("villeClient");
                        String clientMail = curUser.getString("emailClient");
                        String clientTel = curUser.getString("telClient");


                        Client newClient = new Client(clientNom, clientPrenom, clientAdresse, clientCodeP, clientVille,clientTel, clientMail, clientId);
                        clientsList.add(newClient);


                    }

                    clientsListView = (ListView) findViewById(R.id.lvClients); // la ListView précédemment créée (XML)
                    clientAdapter = new ClientsAdapter(
                            ListeClients.this,
                            R.layout.client_list_template,
                            clientsList
                    );
                    clientsListView.setAdapter(clientAdapter);

                    clientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            Client selectedClient = (Client) adapterView.getItemAtPosition(position);
                            String clientId = selectedClient.getClientId();

                            Log.i("XXXXXXXX",clientId);
                            Intent intent = new Intent(ListeClients.this, ClientDetails.class);
                            intent.putExtra(CLIENT_ID, String.valueOf(clientId));
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("https://api-madera.herokuapp.com/api/clients");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_clients);
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