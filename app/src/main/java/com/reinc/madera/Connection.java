package com.reinc.madera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Connection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Button btnConnection;
        btnConnection = (Button) findViewById(R.id.connection);




        btnConnection.setOnClickListener(new View.OnClickListener() {

                                          public void onClick(View v) {

                                              Intent intent = new Intent(Connection.this, MenuActivity.class);
                                              startActivity(intent);


                                          }
                                      }
        );

    }
}

