package com.reinc.madera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnProjets, btnClients, btnDevis, newProject, projects, clients, newClient, devis;
    LinearLayout LayoutProjet, LayoutClient, LayoutDevis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnProjets = (Button) findViewById(R.id.projets);
        btnClients = (Button) findViewById(R.id.clients);
        btnDevis = (Button) findViewById(R.id.devis);
        newProject  = (Button)findViewById(R.id.newProjet);
        projects = (Button)findViewById(R.id.voirProjet);
        newClient  = (Button)findViewById(R.id.newClient);
        clients = (Button)findViewById(R.id.voirClient);
        devis = (Button)findViewById(R.id.voirDevis);
        LayoutProjet = (LinearLayout)findViewById(R.id.LayoutProjet);
        LayoutClient = (LinearLayout)findViewById(R.id.LayoutClient);
        LayoutDevis = (LinearLayout)findViewById(R.id.LayoutDevis);

        btnClients.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           LayoutClient.setVisibility(View.VISIBLE);
                                           LayoutDevis.setVisibility(View.GONE);
                                           LayoutProjet.setVisibility(View.GONE);
                                       }
                                   }
        );
        btnProjets.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             LayoutClient.setVisibility(View.GONE);
                                             LayoutDevis.setVisibility(View.GONE);
                                             LayoutProjet.setVisibility(View.VISIBLE);
                                         }
                                     }
        );
        btnDevis.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {
                                              LayoutClient.setVisibility(View.GONE);
                                              LayoutDevis.setVisibility(View.VISIBLE);
                                              LayoutProjet.setVisibility(View.GONE);
                                          }
                                      }
        );
        newProject.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(MenuActivity.this, NouveauProjet.class);
                                            startActivity(intent);
                                        }
                                    }
        );
        projects.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(MenuActivity.this, ListeProjets.class);
                                            startActivity(intent);
                                        }
                                    }
        );
        newClient.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(MenuActivity.this, NouveauClient.class);
                                              startActivity(intent);
                                          }
                                      }
        );
        clients.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(MenuActivity.this, ListeClients.class);
                                            startActivity(intent);
                                        }
                                    }
        );
        devis.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MenuActivity.this, ListeDevis.class);
                                           startActivity(intent);
                                       }
                                   }
        );
    }
}

