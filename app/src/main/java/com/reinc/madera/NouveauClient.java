package com.reinc.madera;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NouveauClient extends AppCompatActivity {
    Button btnN;
    EditText edNom, edPrenom, edAdresse, edCodeP, edVille, edTel, edMail, edSociete;


    private Location mLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_client);


        btnN = (Button) findViewById(R.id.btnN);
        edNom = (EditText) findViewById(R.id.NNom);
        edPrenom = (EditText) findViewById(R.id.NPrenom);
        edAdresse = (EditText) findViewById(R.id.NAdresse);
        edCodeP = (EditText) findViewById(R.id.NCodeP);
        edVille = (EditText) findViewById(R.id.NVille);
        edTel = (EditText) findViewById(R.id.NTel);
        edMail = (EditText) findViewById(R.id.NMail);

    }

    public void sendPostData(View view) {

        FileDownloader myFd = new FileDownloader(NouveauClient.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(NouveauClient.this, result, Toast.LENGTH_LONG).show();
            }
        };
        myFd.setMethod("POST");

        myFd.addVariable( "nomClient", ((EditText) findViewById(R.id.NNom)).getText().toString());
        myFd.addVariable( "prenomClient", ((EditText) findViewById(R.id.NPrenom)).getText().toString());
        myFd.addVariable( "adresseClient", ((EditText) findViewById(R.id.NAdresse)).getText().toString());
        myFd.addVariable( "cpClient", ((EditText) findViewById(R.id.NCodeP)).getText().toString());
        myFd.addVariable( "villeClient", ((EditText) findViewById(R.id.NVille)).getText().toString());
        myFd.addVariable("emailClient", ((EditText) findViewById(R.id.NMail)).getText().toString());
        myFd.addVariable( "telClient", ((EditText) findViewById(R.id.NTel)).getText().toString());
        Log.i("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", String.valueOf(myFd));
        myFd.execute("https://api-madera.herokuapp.com/api/clients");

        Intent intent = new Intent(NouveauClient.this, ListeClients.class);
        Toast.makeText(NouveauClient.this,
                "Client ajouté", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


}


