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
    EditText et3, et4, et0 ;
    TextView liste, cmpte, tv3, tv4, tv0, unite1, unite2, unite3,unite4 ;
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
    String m= "m";


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
        tv0 = (TextView)findViewById(R.id.textViewQuantiter);
        tv3 = (TextView)findViewById(R.id.textViewQuantiter3);
        tv4 = (TextView)findViewById(R.id.textViewQuantiter4);
        et3 = (EditText)findViewById(R.id.fenetreProfondeur4);
        et4 = (EditText)findViewById(R.id.fenetreLargeur4);
        et0 = (EditText)findViewById(R.id.fenetreLongueur4);

        unite1 = (TextView) findViewById(R.id.unite1);
        unite2 = (TextView) findViewById(R.id.unite2);
        unite3 = (TextView) findViewById(R.id.unite3);
        unite4 = (TextView) findViewById(R.id.unite4);

        unite1.setText(m);
        unite2.setText(m);
        unite3.setText(m);
        unite4.setText(m);



        mur.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       mur.setBackgroundColor(red);
                                       sol.setBackgroundColor(green);
                                       balcon.setBackgroundColor(green);
                                       toit.setBackgroundColor(green);
                                       fenetre.setBackgroundColor(green);
                                       porte.setBackgroundColor(green);
                                       tv0.setVisibility(View.VISIBLE);
                                       tv3.setVisibility(View.VISIBLE);
                                       tv4.setVisibility(View.VISIBLE);
                                       et0.setVisibility(View.VISIBLE);
                                       et3.setVisibility(View.VISIBLE);
                                       et4.setVisibility(View.VISIBLE);
                                       unite2.setVisibility(View.VISIBLE);
                                       unite3.setVisibility(View.VISIBLE);
                                       unite4.setVisibility(View.VISIBLE);

                                       unite1.setText(m);
                                       unite2.setText(m);
                                       unite3.setText(m);
                                       unite4.setText(m);

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
                                       tv0.setVisibility(View.GONE);
                                       tv3.setVisibility(View.GONE);
                                       tv4.setVisibility(View.GONE);
                                       et0.setVisibility(View.GONE);
                                       et3.setVisibility(View.GONE);
                                       et4.setVisibility(View.GONE);
                                       unite2.setVisibility(View.GONE);
                                       unite3.setVisibility(View.GONE);
                                       unite4.setVisibility(View.GONE);

                                       unite1.setText("m2");
                                       unite2.setText("m2");
                                       unite3.setText("m2");
                                       unite4.setText("m2");

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
                                          tv0.setVisibility(View.VISIBLE);
                                          tv3.setVisibility(View.VISIBLE);
                                          tv4.setVisibility(View.VISIBLE);
                                          et0.setVisibility(View.VISIBLE);
                                          et3.setVisibility(View.VISIBLE);
                                          et4.setVisibility(View.VISIBLE);
                                          unite2.setVisibility(View.VISIBLE);
                                          unite3.setVisibility(View.VISIBLE);
                                          unite4.setVisibility(View.VISIBLE);

                                          unite1.setText("cm");
                                          unite2.setText("cm");
                                          unite3.setText("cm");
                                          unite4.setText("cm");

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
                                        tv0.setVisibility(View.VISIBLE);
                                        tv3.setVisibility(View.VISIBLE);
                                        tv4.setVisibility(View.VISIBLE);
                                        et0.setVisibility(View.VISIBLE);
                                        et3.setVisibility(View.VISIBLE);
                                        et4.setVisibility(View.VISIBLE);

                                        tv0.setVisibility(View.GONE);
                                        tv3.setVisibility(View.GONE);
                                        tv4.setVisibility(View.GONE);
                                        et0.setVisibility(View.GONE);
                                        et3.setVisibility(View.GONE);
                                        et4.setVisibility(View.GONE);
                                        unite2.setVisibility(View.GONE);
                                        unite3.setVisibility(View.GONE);
                                        unite4.setVisibility(View.GONE);

                                        unite1.setText("m2");
                                        unite2.setText("m2");
                                        unite3.setText("m2");
                                        unite4.setText("m2");

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
                                         tv0.setVisibility(View.VISIBLE);
                                         tv3.setVisibility(View.VISIBLE);
                                         tv4.setVisibility(View.VISIBLE);
                                         et0.setVisibility(View.VISIBLE);
                                         et3.setVisibility(View.VISIBLE);
                                         et4.setVisibility(View.VISIBLE);
                                         unite2.setVisibility(View.VISIBLE);
                                         unite3.setVisibility(View.VISIBLE);
                                         unite4.setVisibility(View.VISIBLE);
                                         unite1.setText("cm");
                                         unite2.setText("cm");
                                         unite3.setText("cm");
                                         unite4.setText("cm");
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
                                           tv0.setVisibility(View.VISIBLE);
                                           tv3.setVisibility(View.VISIBLE);
                                           tv4.setVisibility(View.VISIBLE);
                                           et0.setVisibility(View.VISIBLE);
                                           et3.setVisibility(View.VISIBLE);
                                           et4.setVisibility(View.VISIBLE);
                                           unite2.setVisibility(View.VISIBLE);
                                           unite3.setVisibility(View.VISIBLE);
                                           unite4.setVisibility(View.VISIBLE);
                                           unite1.setText("cm");
                                           unite2.setText("cm");
                                           unite3.setText("cm");
                                           unite4.setText("cm");
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

        totalTTC = (int) (totalHT * 1.2);
        String stringTotalTTC = String.valueOf(totalTTC);
        String stringTotalHT = String.valueOf(totalHT);



    }


    public void sendPostData(View view) {

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

        Intent intent = new Intent(NouveauPlan.this, ListeDevis.class);
        Toast.makeText(NouveauPlan.this,
                "Devis ajouté", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


}

