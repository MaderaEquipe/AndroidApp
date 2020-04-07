package com.reinc.madera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnProjets, btnClients, btnDevis, newProject, projects, clients, newClient, devis;
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


        btnClients.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           newClient.setVisibility(View.VISIBLE);
                                           clients.setVisibility(View.VISIBLE);
                                           newProject.setVisibility(View.INVISIBLE);
                                           projects.setVisibility(View.INVISIBLE);
                                           devis.setVisibility(View.INVISIBLE);
                                       }
                                   }
        );
        btnProjets.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             newProject.setVisibility(View.VISIBLE);
                                             projects.setVisibility(View.VISIBLE);
                                             newClient.setVisibility(View.INVISIBLE);
                                             clients.setVisibility(View.INVISIBLE);
                                             devis.setVisibility(View.INVISIBLE);
                                         }
                                     }
        );
        btnDevis.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {
                                              newProject.setVisibility(View.INVISIBLE);
                                              projects.setVisibility(View.INVISIBLE);
                                              newClient.setVisibility(View.INVISIBLE);
                                              clients.setVisibility(View.INVISIBLE);
                                              devis.setVisibility(View.VISIBLE);
                                          }
                                      }
        );
        newProject.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(MenuActivity.this, Projet.class);
                                            startActivity(intent);
                                        }
                                    }
        );
        projects.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(MenuActivity.this, ProjectsList.class);
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

