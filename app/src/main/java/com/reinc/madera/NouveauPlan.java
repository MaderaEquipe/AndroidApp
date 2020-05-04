package com.reinc.madera;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NouveauPlan extends AppCompatActivity {
    Button mur, sol, balcon, porte, fenetre, toit, ok;
    EditText edLabelPlan, edDatePlan, edUtilisateur, edClient;
    TextView liste, cmpte;
    ArrayList<String> clientList;
    String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    String user1 = "Administrator";
    String encours = "En attente de validation";
    private Location mLocation;
    int red = Color.parseColor("#CA2222");
    int green = Color.parseColor("#008577");
    int compteur = 0;
    int max = 32000;
    int min = 23000;
    int range = max - min + 1;
    int rand = (int)(Math.random() * range) + min;
    int totalTTC = 0;
    int totalHT = 0;
    String module = "Module Mur, Prix : " + rand + "€.";


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
        ok = (Button) findViewById(R.id.ok);
        liste = (TextView)findViewById(R.id.liste);
        cmpte = (TextView)findViewById(R.id.module);


        mur.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       mur.setBackgroundColor(red);
                                       sol.setBackgroundColor(green);
                                       balcon.setBackgroundColor(green);
                                       toit.setBackgroundColor(green);
                                       fenetre.setBackgroundColor(green);
                                       porte.setBackgroundColor(green);
                                       int rand = (int)(Math.random() * range) + min;
                                       module = module + " Module Mur, Prix : " + rand + "€." ;
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
                                       int rand = (int)(Math.random() * range) + min;
                                       module = module + " Module Sol, Prix : " + rand + "€."  ;
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
                                          int rand = (int)(Math.random() * range) + min;
                                          module = module +" Module Balcon, Prix : " + rand + "€." ;
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
                                        int rand = (int)(Math.random() * range) + min;
                                        module = module +" Module Toit, Prix : " + rand + "€." ;
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
                                         int rand = (int)(Math.random() * range) + min;
                                         module = module + " Module Porte,  Prix : " +rand + "€." ;
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
                                           int rand = (int)(Math.random() * range) + min;
                                           module = module +" Module Fenêtre, Prix :" + rand + "€." ;
                                       }
                                   }
        );

        ok.setOnClickListener(new View.OnClickListener() {
                                  public void onClick(View v) {
                                      liste.setText(module);
                                      compteur ++;
                                      totalHT = totalHT + rand;
                                      cmpte.setText("Modules : " + compteur);
                                  }
                              }
        );



    }


    public void sendPostDataDevis(View view) {

        totalTTC = (int) (totalHT * 1.2);
        FileDownloader myFd = new FileDownloader(NouveauPlan.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }
        };
        myFd.setMethod("POST");
        myFd.addVariable("nomDevis", (encours));
        myFd.addVariable("etatDevis", (encours));
        myFd.addVariable("totalHT", String.valueOf(totalHT));
        myFd.addVariable("totalTTC", String.valueOf(totalTTC));
        myFd.addVariable("tauxRemise", String.valueOf(0));
        myFd.addVariable("dateCreation", (currentDate));
        myFd.addVariable("projet", (encours));
        myFd.execute("https://api-madera.herokuapp.com/api/devis/");

        Intent intent = new Intent(NouveauPlan.this, ListeDevis.class);
        Toast.makeText(NouveauPlan.this,
                "Plan ajouté", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


}

