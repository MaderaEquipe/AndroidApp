package com.reinc.madera;

import android.content.Context;
import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

public class DevisDetails extends AppCompatActivity {


    TextView etatDevisView = null;
    TextView totalHTView = null;
    TextView totalTTCView = null;
    TextView tauxRemiseView = null;
    TextView creationDateView = null;
    TextView projetView = null;
    TextView nomDevisView = null;


    Button btnModif;
    Button btnSup;

    private Location mLocation;
    String devisId;

    static public String DEVIS_ID = "com.reinc.madera.IdDevis";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devis_details);

        btnModif = (Button) findViewById(R.id.button4);
        btnSup = (Button) findViewById(R.id.button5);
        btnModif.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FileDownloader myFd = new FileDownloader(DevisDetails.this) {
                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);

                        //Toast.makeText(ClientDetails.this, result, Toast.LENGTH_LONG).show();
                    }
                };

                myFd.setMethod("PUT");
                myFd.addVariable("IdDevis", devisId);
                myFd.execute("https://api-madera.herokuapp.com/api/devis/" + devisId);

                Intent intent = new Intent(DevisDetails.this, MenuActivity.class);
                startActivity(intent);
                Toast.makeText(DevisDetails.this,
                        "Devis validé", Toast.LENGTH_SHORT).show();
            }
        });

        btnSup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FileDownloader myFd = new FileDownloader(DevisDetails.this) {
                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);

                        //Toast.makeText(ClientDetails.this, result, Toast.LENGTH_LONG).show();
                    }
                };

                myFd.setMethod("DELETE");
                myFd.addVariable("IdDevis", devisId);
                // myFd.addVariable( "IdClient", ((EditText) findViewById(R.id.NNom)).getText().toString());
                Log.i("LLLLLLLLLLLL", devisId);
                myFd.execute("https://api-madera.herokuapp.com/api/devis/" + devisId);

                Intent intent = new Intent(DevisDetails.this, MenuActivity.class);
                startActivity(intent);
                Toast.makeText(DevisDetails.this,
                        "Devis supprimer", Toast.LENGTH_SHORT).show();
            }
        });


        Intent itt = getIntent();
        devisId = itt.getStringExtra(DevisDetails.DEVIS_ID);
        loadDevisData(devisId);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        nomDevisView = (TextView) findViewById(R.id.devisNom);
        etatDevisView = (TextView) findViewById(R.id.clientName);
        totalHTView = (TextView) findViewById(R.id.clientFirstName);
        tauxRemiseView = (TextView) findViewById(R.id.clientAddress1);
        creationDateView = (TextView) findViewById(R.id.clientCodeP);
        projetView = (TextView) findViewById(R.id.clientVille);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TextView nomDevisView = null;
        TextView etatDevisView = null;
        TextView totalHTView = null;
        TextView totalTTCView = null;
        TextView tauxRemiseView = null;
        TextView creationDateView = null;
        TextView projetView = null;
    }

    private void loadDevisData(String clientId) {
        new FileDownloader(DevisDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);
                    String nomDevis = jsonObj.getString("nomDevis");
                    nomDevisView = (TextView) findViewById(R.id.devisNom);
                    nomDevisView.setText(nomDevis);

                    String etatDevis = jsonObj.getString("etatDevis");
                    etatDevisView = (TextView) findViewById(R.id.etatDevis);
                    etatDevisView.setText(etatDevis);

                    String totalHT = jsonObj.getString("totalHT");
                    totalHTView = (TextView) findViewById(R.id.totalHT);
                    totalHTView.setText(totalHT);

                    String totalTTC = jsonObj.getString("totalTTC");
                    totalTTCView = (TextView) findViewById(R.id.totalTTC);
                    totalTTCView.setText(totalTTC);


                    String tauxRemise = jsonObj.getString("tauxRemise");
                    tauxRemiseView = (TextView) findViewById(R.id.tauxRemise);
                    tauxRemiseView.setText(tauxRemise);

                    String creationDate = jsonObj.getString("dateCreation");
                    creationDateView = (TextView) findViewById(R.id.creationDate);
                    creationDateView.setText(creationDate);


                    String projet = jsonObj.getString("projet");
                    projetView = (TextView) findViewById(R.id.projet);
                    projetView.setText(projet);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("https://api-madera.herokuapp.com/api/devis/" + devisId);
    }


    public void delete(){
        FileDownloader myFd = new FileDownloader(DevisDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                //Toast.makeText(ClientDetails.this, result, Toast.LENGTH_LONG).show();
            }
        };
        myFd.setMethod("DELETE");
        myFd.addVariable("IdDevis", devisId);
        //myFd.addVariable( "IdClient", ((EditText) findViewById(R.id.NNom)).getText().toString());

        myFd.execute("https://api-madera.herokuapp.com/api/devis/");
        Toast.makeText(this, "Le devis à bien été supprime", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (DevisDetails.this, ListeDevis.class);
        intent.putExtra(DEVIS_ID, String.valueOf(devisId));
        startActivity(intent);

    }

}




