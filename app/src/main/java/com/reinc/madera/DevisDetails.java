package com.reinc.madera;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DevisDetails extends AppCompatActivity {
    static public String DEVIS_ID = "com.reinc.madera.IdDevis";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.devis);

    }
}