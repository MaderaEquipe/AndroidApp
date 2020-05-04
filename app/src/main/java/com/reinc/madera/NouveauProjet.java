package com.reinc.madera;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NouveauProjet extends AppCompatActivity{
        Button btnN;
        EditText edLabelPlan, edDatePlan, edUtilisateur, edClient;
        Spinner spinner;
        ArrayList<String> clientList;
        private Location mLocation;
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String user1 = "Administrator";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nouveau_projet);


            btnN = (Button) findViewById(R.id.btnN);
            edLabelPlan = (EditText) findViewById(R.id.NLabelPlan);
            edClient = (EditText) findViewById(R.id.NClient);

        }

    public void sendPostData(View view) {

        FileDownloader myFd = new FileDownloader(NouveauProjet.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }
        };
        myFd.setMethod("POST");

            myFd.addVariable( "labelClient", ((EditText) findViewById(R.id.NLabelPlan)).getText().toString());
            myFd.addVariable( "datePlan", (currentDate));
            myFd.addVariable( "utilisateur", (user1));
            myFd.addVariable( "client", ((EditText) findViewById(R.id.NClient)).getText().toString());
            myFd.execute("https://api-madera.herokuapp.com/api/projets/");

            Intent intent = new Intent(NouveauProjet.this, NouveauPlan.class);
            Toast.makeText(NouveauProjet.this,
                    "Projet ajouté", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

 }

