package com.maiot.esempioviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private EditText etNome = null;
    private EditText etCognome = null;

    private Button bttOK = null;
    private TextView tvNomeCognome = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etCognome = findViewById(R.id.etCognome);

        bttOK = findViewById(R.id.bttOK);
        tvNomeCognome = findViewById(R.id.tvNomeCognome);

        bttOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Bottone OK premuto");

                String nome = etNome.getText().toString();
                String cognome = etCognome.getText().toString();

                String nomeCognome = "Nome: " + nome + " Cognome: " + cognome;
                Log.i(TAG, nomeCognome);

                Toast.makeText(getApplicationContext(), nomeCognome, Toast.LENGTH_LONG).show();
                tvNomeCognome.setText(nomeCognome);
            }
        });
    }
}