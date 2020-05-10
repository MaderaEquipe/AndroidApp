package com.reinc.madera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProjetDetails extends AppCompatActivity {

    TextView nom = null;
    TextView date = null;
    TextView client = null;
    TextView commercial = null;


    Button btnMail = null;
    Button btnTelB = null;
    Button btnGo = null;
    Button btnModif;
    Button btnSup;

    private Location mLocation;
    String projetId;

    static public String PROJET_ID = "com.reinc.madera.IdProjet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_details);
        btnModif = (Button) findViewById(R.id.button4);
        btnSup = (Button) findViewById(R.id.button5);
        btnModif.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(ProjetDetails.this, ListeProjets.class);
                startActivity(intent);


            }
        });

        btnSup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FileDownloader myFd = new FileDownloader(ProjetDetails.this) {
                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);

                        //Toast.makeText(ClientDetails.this, result, Toast.LENGTH_LONG).show();
                    }
                };

                myFd.setMethod("DELETE");
                myFd.addVariable("id", projetId);
                // myFd.addVariable( "IdClient", ((EditText) findViewById(R.id.NNom)).getText().toString());
                Log.i("LLLLLLLLLLLL", projetId);
                myFd.execute("https://api-madera.herokuapp.com/api/projets/" + projetId);

                Intent intent = new Intent(ProjetDetails.this, MenuActivity.class);
                startActivity(intent);
                Toast.makeText(ProjetDetails.this,
                        "Projet supprimé", Toast.LENGTH_SHORT).show();
            }
        });


        Intent itt = getIntent();
        projetId = itt.getStringExtra(ProjetDetails.PROJET_ID);
        loadClientData(projetId);

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                mLocation = location;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Activer les permissions pour le GPS !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        nom = (TextView) findViewById(R.id.nom);
        date = (TextView) findViewById(R.id.date);
        client = (TextView) findViewById(R.id.client);
        commercial = (TextView) findViewById(R.id.commercial);


        btnMail = (Button) findViewById(R.id.btnMail);
        btnTelB = (Button) findViewById(R.id.btnTelB);
        btnGo = (Button) findViewById(R.id.btnGo);
    }

    @Override
    protected void onPause() {
        super.onPause();

        TextView nom = null;
        TextView date = null;
        TextView client = null;
        TextView commercial = null;

        btnMail.setOnClickListener(null);
        btnMail = null;
        btnTelB.setOnClickListener(null);
        btnTelB = null;
        btnGo.setOnClickListener(null);
        btnGo = null;
    }

    private void loadClientData(String projetId) {
        new FileDownloader(ProjetDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);

                    String clientNom = jsonObj.getString("labelPlan");
                    nom = (TextView) findViewById(R.id.nom);
                    nom.setText(clientNom);

                    String clientPrenom = jsonObj.getString("datePlan");
                    date = (TextView) findViewById(R.id.date);
                    date.setText(clientPrenom);

                    String clientAdresse = jsonObj.getString("client");
                    client = (TextView) findViewById(R.id.client);
                    client.setText(clientAdresse);


                    String clientCodeP = jsonObj.getString("utilisateur");
                    commercial = (TextView) findViewById(R.id.commercial);
                    commercial.setText(clientCodeP);


                    // Obtenir les coordonnées GPS du client, pour calculer la distance
                    new FileDownloader(ProjetDetails.this) {
                        @Override
                        protected void onPostExecute(String result) {
                            super.onPostExecute(result);

                            JSONObject jsonObj;
                            try {
                                jsonObj = new JSONObject(result);
                                JSONArray gResults = jsonObj.getJSONArray("results");
                                JSONObject gFirstResult = gResults.getJSONObject(0);
                                JSONObject gGeometry = gFirstResult.getJSONObject("geometry");
                                JSONObject gLocation = gGeometry.getJSONObject("location");
                                String gLat = String.valueOf(gLocation.getDouble("lat"));
                                String gLng = String.valueOf(gLocation.getDouble("lng"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };//.execute("https://maps.googleapis.com/maps/api/geocode/json?address="+clientAdresse+"&key="+R.string.GoogleApiKey);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("https://api-madera.herokuapp.com/api/projets/" + projetId);
    }


    public void delete(){
        FileDownloader myFd = new FileDownloader(ProjetDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                //Toast.makeText(ClientDetails.this, result, Toast.LENGTH_LONG).show();
            }
        };
        myFd.setMethod("DELETE");
        myFd.addVariable("id", projetId);
        //myFd.addVariable( "IdClient", ((EditText) findViewById(R.id.NNom)).getText().toString());

        myFd.execute("https://api-madera.herokuapp.com/api/projets/");
        Toast.makeText(this, "Le projet a bien été supprimeé", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (ProjetDetails.this, ListeProjets.class);
        intent.putExtra(PROJET_ID, String.valueOf(projetId));
        startActivity(intent);

    }

}





