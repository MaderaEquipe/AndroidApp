package com.reinc.madera;

import android.Manifest;
import android.annotation.SuppressLint;
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

import org.json.JSONException;
import org.json.JSONObject;

public class ProjetDetails extends AppCompatActivity {


    TextView labelPlanView = null;
    TextView datePlanView = null;
    TextView clientView = null;
    TextView utilisateurView = null;

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

                Intent intent = new Intent(ProjetDetails.this, ProjetDetails.class);
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
                myFd.addVariable("IdProjet", projetId);
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
        loadProjetData(projetId);

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

        labelPlanView = (TextView) findViewById(R.id.projetLabelPlan);
        datePlanView = (TextView) findViewById(R.id.projetDatePlan);
        clientView = (TextView) findViewById(R.id.clientName);
        utilisateurView = (TextView) findViewById(R.id.userName);
    }

    @Override
    protected void onPause() {
        super.onPause();

        TextView labelPlanView = null;
        TextView datePlanView = null;
        TextView clientView = null;
        TextView utilisateurView = null;

    }

    private void loadProjetData(String clientId) {
        new FileDownloader(ProjetDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);

                    String labelPlan = jsonObj.getString("labelPlan");
                    labelPlanView = (TextView) findViewById(R.id.projetLabelPlan);
                    labelPlanView.setText(labelPlan);

                    String datePlan = jsonObj.getString("datePlan");
                    datePlanView = (TextView) findViewById(R.id.projetDatePlan);
                    datePlanView.setText(datePlan);

                    String client = jsonObj.getString("client");
                    clientView = (TextView) findViewById(R.id.clientName);
                    clientView.setText(client);


                    String utilisateur = jsonObj.getString("utilisateur");
                    utilisateurView = (TextView) findViewById(R.id.userName);
                    utilisateurView.setText(utilisateur);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("https://api-madera.herokuapp.com/api/projet/" + projetId);
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
        myFd.addVariable("IdProjet", projetId);
        //myFd.addVariable( "IdClient", ((EditText) findViewById(R.id.NNom)).getText().toString());

        myFd.execute("https://api-madera.herokuapp.com/api/projet/");
        Toast.makeText(this, "Le projet à bien été supprime", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (ProjetDetails.this, ListeProjets.class);
        intent.putExtra(PROJET_ID, String.valueOf(projetId));
        startActivity(intent);

    }

}




