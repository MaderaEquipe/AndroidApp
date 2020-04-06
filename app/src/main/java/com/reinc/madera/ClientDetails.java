package com.reinc.madera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
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

public class ClientDetails extends AppCompatActivity {

    TextView clientNameView = null;
    TextView clientFirstnameView = null;
    TextView clientAdresse1View = null;
    TextView clientAdresse2View = null;
    TextView clientCodePView = null;
    TextView clientVilleView = null;
    TextView clientTelBView = null;
    TextView clientMailView = null;
    TextView clientSocieteView =null;

    Button btnMail = null;
    Button btnTelB = null;
    Button btnAddress = null;
    Button btnTelM = null;
    Button btnGo = null;
    Button btnModif;
    Button btnSup;

    private Location mLocation;
    String clientId;

    static public String CLIENT_ID = "com.reinc.madera.IdClient";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);
        btnModif = (Button) findViewById(R.id.button4);
        btnSup = (Button) findViewById(R.id.button5);
        btnModif.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(ClientDetails.this, ClientDetails.class);
                startActivity(intent);


            }
        });

        btnSup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FileDownloader myFd = new FileDownloader(ClientDetails.this) {
                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);

                        //Toast.makeText(ClientDetails.this, result, Toast.LENGTH_LONG).show();
                    }
                };

                myFd.setMethod("DELETE");
                myFd.addVariable("IdClient", clientId);
                // myFd.addVariable( "IdClient", ((EditText) findViewById(R.id.NNom)).getText().toString());
                Log.i("LLLLLLLLLLLL", clientId);
                myFd.execute("https://api-madera.herokuapp.com/api/clients/" + clientId);

                Intent intent = new Intent(ClientDetails.this, MenuActivity.class);
                startActivity(intent);
                Toast.makeText(ClientDetails.this,
                        "Client supprimer", Toast.LENGTH_SHORT).show();
            }
        });


        Intent itt = getIntent();
        clientId = itt.getStringExtra(ClientDetails.CLIENT_ID);
        loadClientData(clientId);

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

        clientNameView = (TextView) findViewById(R.id.clientName);
        clientFirstnameView = (TextView) findViewById(R.id.clientFirstName);
        clientAdresse2View = (TextView) findViewById(R.id.clientAddress1);
        clientCodePView = (TextView) findViewById(R.id.clientCodeP);
        clientVilleView = (TextView) findViewById(R.id.clientVille);
        clientTelBView = (TextView) findViewById(R.id.clientTelB);
        clientMailView = (TextView) findViewById(R.id.clientMail);


        btnMail = (Button) findViewById(R.id.btnMail);
        btnMail.setOnClickListener(openMail);
        btnTelB = (Button) findViewById(R.id.btnTelB);
        btnTelB.setOnClickListener(openTel);
        btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(openNavigation);
    }

    @Override
    protected void onPause() {
        super.onPause();

        TextView clientNameView = null;
        TextView clientFirstnameView = null;
        TextView clientAdresse1View = null;
        TextView clientAdresse2View = null;
        TextView clientCodePView = null;
        TextView clientVilleView = null;
        TextView clientTelBView = null;
        TextView clientTelMView = null;
        TextView clientMailView = null;
        TextView clientBudgetView = null;
        TextView clientSocieteView =null;

        btnMail.setOnClickListener(null);
        btnMail = null;
        btnTelB.setOnClickListener(null);
        btnTelB = null;
        //      btnTelM.setOnClickListener(null);
        //      btnTelM = null;
        //btnAddress.setOnClickListener(null);
        //   btnAddress = null;
        btnGo.setOnClickListener(null);
        btnGo = null;
    }

    private void loadClientData(String clientId) {
        new FileDownloader(ClientDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);

                    String clientNom = jsonObj.getString("nomClient");
                    clientNameView = (TextView) findViewById(R.id.clientName);
                    clientNameView.setText(clientNom);

                    String clientPrenom = jsonObj.getString("prenomClient");
                    clientFirstnameView = (TextView) findViewById(R.id.clientFirstName);
                    clientFirstnameView.setText(clientPrenom);

                    String clientAdresse = jsonObj.getString("adresseClient");
                    clientAdresse1View = (TextView) findViewById(R.id.clientAddress1);
                    clientAdresse1View.setText(clientAdresse);


                    String clientCodeP = jsonObj.getString("cpClient");
                    clientCodePView = (TextView) findViewById(R.id.clientCodeP);
                    clientCodePView.setText(clientCodeP);

                    String clientVille = jsonObj.getString("villeClient");
                    clientFirstnameView = (TextView) findViewById(R.id.clientVille);
                    clientFirstnameView.setText(clientVille);


                    String clientMail = jsonObj.getString("emailClient");
                    clientAdresse2View = (TextView) findViewById(R.id.clientMail);
                    clientAdresse2View.setText(clientMail);

                    String clientTelB = jsonObj.getString("telClient");
                    clientAdresse1View = (TextView) findViewById(R.id.clientTelB);
                    clientAdresse1View.setText(clientTelB);

                    // Obtenir les coordonnées GPS du client, pour calculer la distance
                    new FileDownloader(ClientDetails.this) {
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
        }.execute("https://api-madera.herokuapp.com/api/clients/" + clientId);
    }


    View.OnClickListener openAddress = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String curAddress = clientAdresse1View.getText().toString();

            new FileDownloader(ClientDetails.this) {
                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);

                    String clientAddress = clientAdresse2View.getText().toString();
                    //    String clientSocietyName = clientSocietyView.getText().toString();

                    JSONObject jsonObj;
                    try {
                        jsonObj = new JSONObject(result);
                        JSONArray gResults = jsonObj.getJSONArray("results");
                        JSONObject gFirstResult = gResults.getJSONObject(0);
                        JSONObject gGeometry = gFirstResult.getJSONObject("geometry");
                        JSONObject gLocation = gGeometry.getJSONObject("location");

                        String gLat = String.valueOf(gLocation.getDouble("lat"));
                        String gLng = String.valueOf(gLocation.getDouble("lng"));

                        // Advanced usage : https://developers.google.com/maps/documentation/android-api/intents?hl=fr
                        Intent ittMaps = new Intent();
                        ittMaps.setAction(Intent.ACTION_VIEW);
                        ittMaps.setData(Uri.parse("geo:0,0?q=" + gLat + "," + gLng + "(" + clientNameView + ")"));
                        ittMaps.setPackage("com.google.android.apps.maps");

                        if (ittMaps.resolveActivity(getPackageManager()) != null) {
                            startActivity(ittMaps);
                        } else {
                            Toast.makeText(ClientDetails.this,
                                    "Impossible d'ouvrir l'application Maps", Toast.LENGTH_SHORT).show();
                            // Ouverture par le navigateur
                            Intent ittBrowserMap = new Intent();
                            ittBrowserMap.setAction(Intent.ACTION_VIEW);
                            ittBrowserMap.setData(Uri.parse("https://www.google.fr/maps/place/" + clientAddress));
                            startActivity(ittBrowserMap);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }.execute("https://maps.googleapis.com/maps/api/geocode/json?address=" + curAddress + "&key=" + R.string.GoogleApiKey);
        }
    };
    View.OnClickListener openNavigation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String curAddress = clientAdresse1View.getText().toString();

            // Advanced usage : https://developers.google.com/maps/documentation/android-api/intents?hl=fr
            Intent ittMaps = new Intent();
            ittMaps.setAction(Intent.ACTION_VIEW);
            ittMaps.setData(Uri.parse("google.navigation:q=" + curAddress));
            ittMaps.setPackage("com.google.android.apps.maps");

            if (ittMaps.resolveActivity(getPackageManager()) != null) {
                startActivity(ittMaps);
            } else {
                Toast.makeText(ClientDetails.this,
                        "Impossible d'ouvrir l'application Navigation", Toast.LENGTH_SHORT).show();
                // Ouverture par le navigateur
                Intent ittBrowserMap = new Intent();
                ittBrowserMap.setAction(Intent.ACTION_VIEW);
                ittBrowserMap.setData(Uri.parse("https://www.google.fr/maps/place/" + curAddress));
                startActivity(ittBrowserMap);
            }
        }
    };
    View.OnClickListener openTel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String curTel = clientTelBView.getText().toString();

            Intent ittTel = new Intent();
            ittTel.setAction(Intent.ACTION_DIAL);
            ittTel.setData(Uri.parse("tel:" + curTel));

            if (ittTel.resolveActivity(getPackageManager()) != null) {
                startActivity(ittTel);
            } else {
                Toast.makeText(ClientDetails.this,
                        "Impossible d'ouvrir l'application Telephone", Toast.LENGTH_SHORT).show();
            }
        }
    };
    View.OnClickListener openMail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String curMail = clientMailView.getText().toString();

            Intent ittMail = new Intent();
            ittMail.setAction(Intent.ACTION_SENDTO);
            ittMail.setData(Uri.parse("mailto:" + curMail));

            if (ittMail.resolveActivity(getPackageManager()) != null) {
                startActivity(ittMail);
            } else {
                Toast.makeText(ClientDetails.this,
                        "Impossible d'ouvrir l'application Mail", Toast.LENGTH_SHORT).show();
            }
        }
    };
}

  /*  public void delete(){
        FileDownloader myFd = new FileDownloader(ClientDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                //Toast.makeText(ClientDetails.this, result, Toast.LENGTH_LONG).show();
            }
        };
        myFd.setMethod("DELETE");
        myFd.addVariable("IdClient", clientId);
        //myFd.addVariable( "IdClient", ((EditText) findViewById(R.id.NNom)).getText().toString());

        myFd.execute("http://www.e-manager.fr/api/client");
        Toast.makeText(this, "Le client à bien été supprime", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (ClientDetails.this, ListeClient.class);
                intent.putExtra(CLIENT_ID, String.valueOf(clientId));
                startActivity(intent);

    }
}



*/