package com.reinc.madera;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NouveauProjet extends AppCompatActivity{
        Button btnN;
        EditText edLabelPlan, edDatePlan, edUtilisateur, edClient;
        Spinner spinner;
        ArrayList<String> clientList;
        private Location mLocation;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nouveau_projet);


            btnN = (Button) findViewById(R.id.btnN);
            edLabelPlan = (EditText) findViewById(R.id.NNom);
            edDatePlan = (EditText) findViewById(R.id.NDatePlan);
            edUtilisateur = (EditText) findViewById(R.id.NUtilisateur);
            edClient = (EditText) findViewById(R.id.NClient);
            spinner = (Spinner) findViewById(R.id.spinner);

        }

        public void sendPostData(View view) {

            FileDownloader myFd = new FileDownloader(com.reinc.madera.NouveauProjet.this) {
                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);

                    Toast.makeText(com.reinc.madera.NouveauProjet.this, result, Toast.LENGTH_LONG).show();
                }
            };
            myFd.setMethod("POST");

            myFd.addVariable( "labelClient", ((EditText) findViewById(R.id.NNom)).getText().toString());
            myFd.addVariable( "datePlan", ((EditText) findViewById(R.id.NDatePlan)).getText().toString());
            myFd.addVariable( "utilisateur", ((EditText) findViewById(R.id.NUtilisateur)).getText().toString());
            myFd.addVariable( "client", ((EditText) findViewById(R.id.NClient)).getText().toString());
            Log.i("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", String.valueOf(myFd));
            myFd.execute("https://api-madera.herokuapp.com/api/projets/");

            Intent intent = new Intent(com.reinc.madera.NouveauProjet.this, ListeClients.class);
            Toast.makeText(com.reinc.madera.NouveauProjet.this,
                    "Projet ajouté", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

 }

