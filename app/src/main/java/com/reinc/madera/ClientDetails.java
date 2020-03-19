package com.reinc.madera;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ClientDetails extends AppCompatActivity {

    static public String CLIENT_ID = "com.reinc.madera.IdClient";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients);

    }
}