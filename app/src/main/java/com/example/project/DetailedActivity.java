package com.example.project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {

    TextView name;
    TextView location;
    TextView size;
    TextView aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        name = findViewById(R.id.d_river);
        location = findViewById(R.id.d_location);
        size = findViewById(R.id.d_size);
        aux = findViewById(R.id.d_aux);

        Intent intent = getIntent();
        if (intent != null){
            String nameString = intent.getStringExtra("name");
            String locationString = intent.getStringExtra("location");
            String sizeString = intent.getStringExtra("size");
            String auxString = intent.getStringExtra("aux");

            bindData(nameString, locationString, sizeString, auxString);
        }
    }

    private void bindData(String nameString, String locationString, String sizeString, String auxString){

        int auxLength = countCountries(auxString);

        name.setText(String.format("Namn: %s", nameString));
        location.setText(String.format("Anslutning: %s", locationString));
        size.setText(String.format("Längd: %skm", sizeString));
        aux.setText(String.format("Länder(%d): %s", auxLength, auxString));
    }

    private int countCountries(String countries){
        return countries.split(",").length;
    }
}
