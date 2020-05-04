package com.reinc.madera;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NouveauPlan extends AppCompatActivity {
    Button mur, sol, balcon, porte, fenetre, toit;
    EditText edLabelPlan, edDatePlan, edUtilisateur, edClient;
    ArrayList<String> clientList;
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String user1 = "Administrator";
    private Location mLocation;
    int red = Color.parseColor("#CA2222");
    int green = Color.parseColor("#008577");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_plan);


        mur = (Button) findViewById(R.id.murT);
        sol = (Button) findViewById(R.id.solT);
        balcon = (Button) findViewById(R.id.balconT);
        toit = (Button) findViewById(R.id.toitT);
        fenetre = (Button) findViewById(R.id.fenetreT);
        porte = (Button) findViewById(R.id.porteT);


        mur.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       mur.setBackgroundColor(red);
                                       sol.setBackgroundColor(green);
                                       balcon.setBackgroundColor(green);
                                       toit.setBackgroundColor(green);
                                       fenetre.setBackgroundColor(green);
                                       porte.setBackgroundColor(green);
                                   }
                               }
        );
        sol.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       mur.setBackgroundColor(green);
                                       sol.setBackgroundColor(red);
                                       balcon.setBackgroundColor(green);
                                       toit.setBackgroundColor(green);
                                       fenetre.setBackgroundColor(green);
                                       porte.setBackgroundColor(green);
                                   }
                               }
        );
        balcon.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       mur.setBackgroundColor(green);
                                       sol.setBackgroundColor(green);
                                       balcon.setBackgroundColor(red);
                                       toit.setBackgroundColor(green);
                                       fenetre.setBackgroundColor(green);
                                       porte.setBackgroundColor(green);
                                   }
                               }
        );
        toit.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       mur.setBackgroundColor(green);
                                       sol.setBackgroundColor(green);
                                       balcon.setBackgroundColor(green);
                                       toit.setBackgroundColor(red);
                                       fenetre.setBackgroundColor(green);
                                       porte.setBackgroundColor(green);
                                   }
                               }
        );
        porte.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       mur.setBackgroundColor(green);
                                       sol.setBackgroundColor(green);
                                       balcon.setBackgroundColor(green);
                                       toit.setBackgroundColor(green);
                                       fenetre.setBackgroundColor(green);
                                       porte.setBackgroundColor(red);
                                   }
                               }
        );
        fenetre.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       mur.setBackgroundColor(green);
                                       sol.setBackgroundColor(green);
                                       balcon.setBackgroundColor(green);
                                       toit.setBackgroundColor(green);
                                       fenetre.setBackgroundColor(red);
                                       porte.setBackgroundColor(green);
                                   }
                               }
        );


    }

    public void sendPostData(View view) {

        FileDownloader myFd = new FileDownloader(NouveauPlan.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(NouveauPlan.this, result, Toast.LENGTH_LONG).show();
            }
        };
        myFd.setMethod("POST");

        myFd.addVariable("labelClient", ((EditText) findViewById(R.id.NLabelPlan)).getText().toString());
        myFd.addVariable("datePlan", (currentDate));
        myFd.addVariable("utilisateur", (user1));
        myFd.addVariable("client", ((EditText) findViewById(R.id.NClient)).getText().toString());
        Log.i("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", String.valueOf(myFd));
        myFd.execute("https://api-madera.herokuapp.com/api/projets/");

        Intent intent = new Intent(NouveauPlan.this, MenuActivity.class);
        Toast.makeText(NouveauPlan.this,
                "Projet ajouté", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}

