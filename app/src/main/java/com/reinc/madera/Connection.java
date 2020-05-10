package com.reinc.madera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Connection extends AppCompatActivity {
    EditText login, mdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Button btnConnection;
        btnConnection = (Button) findViewById(R.id.connection);
        login = (EditText) findViewById(R.id.login);
        mdp = (EditText) findViewById(R.id.mdp);




        btnConnection.setOnClickListener(new View.OnClickListener() {

                                          public void onClick(View v) {
                                              if (login.getText().toString().equals("administrator") &&
                                                      mdp.getText().toString().equals("admin")) {
                                                  Toast.makeText(getApplicationContext(),
                                                          "Redirecting...", Toast.LENGTH_SHORT).show();

                                                  Intent intent = new Intent(Connection.this, MenuActivity.class);
                                                  startActivity(intent);

                                              }else{
                                                  Toast.makeText(getApplicationContext(), "Login ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }
        );

    }
}

