package com.maiot.passaggiotraactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.security.interfaces.RSAKey;

public class AnotherActivityForResults extends AppCompatActivity {

    private final String TAG = "AnotherActivityForResults";

    private EditText etMessaggio = null;
    private Button bttOK = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_for_results);

        etMessaggio = findViewById(R.id.etMessageResult);
        bttOK = findViewById(R.id.bttOK);

        Intent i = getIntent();
        String text = i.getStringExtra( getString(R.string.LABEL_FOR_ACTIVITY_4_RESULT) );
        etMessaggio.setText(text);

        bttOK.setOnClickListener( v -> {
            String textModified = etMessaggio.getText().toString();

            Intent backIntent = new Intent();
            backIntent.putExtra( getString(R.string.LABEL_FOR_BACK_PROPAGATION), textModified);
            setResult(Activity.RESULT_OK, backIntent);
            finish();
        });

    }
}