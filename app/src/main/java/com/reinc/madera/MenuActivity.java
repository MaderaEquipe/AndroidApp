package com.reinc.madera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnProjets, btnClients, btnDevis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnProjets = (Button) findViewById(R.id.projets);
        btnClients = (Button) findViewById(R.id.clients);
        btnDevis = (Button) findViewById(R.id.devis);



        btnClients.setOnClickListener(new View.OnClickListener() {

                                       public void onClick(View v) {

                                           Intent intent = new Intent(MenuActivity.this, Client.class);
                                           startActivity(intent);


                                       }
                                   }
        );
        btnProjets.setOnClickListener(new View.OnClickListener() {

                                         public void onClick(View v) {

                                             Intent intent = new Intent(MenuActivity.this, Projet.class);
                                             startActivity(intent);


                                         }
                                     }
        );
        btnDevis.setOnClickListener(new View.OnClickListener() {

                                          public void onClick(View v) {

                                              Intent intent = new Intent(MenuActivity.this, Devis.class);
                                              startActivity(intent);


                                          }
                                      }
        );
    }
}

