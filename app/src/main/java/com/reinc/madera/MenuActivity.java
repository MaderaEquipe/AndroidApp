package com.reinc.madera;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnLcli, btnLfrais, btnNc, btnNf, btnParam, btnP;
    static public String CLIENT_ID = "com.reinc.myapplication.clientId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

     /*   btnLcli = (Button) findViewById(R.id.buttonlisteclient);
        btnLfrais = (Button) findViewById(R.id.buttonlistefrais);



        btnLcli.setOnClickListener(new View.OnClickListener() {

                                       public void onClick(View v) {

                                           Intent intent = new Intent(MenuActivity.this, Client.class);
                                           startActivity(intent);


                                       }
                                   }
        );
        btnLfrais.setOnClickListener(new View.OnClickListener() {

                                         public void onClick(View v) {

                                             Intent intent = new Intent(MenuActivity.this, Client.class);
                                             startActivity(intent);


                                         }
                                     }
        ); */
    }
}

