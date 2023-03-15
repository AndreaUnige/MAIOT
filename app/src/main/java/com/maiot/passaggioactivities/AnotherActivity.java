package com.maiot.passaggioactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnotherActivity extends AppCompatActivity {

    private final String TAG = "AnotherActivity";

    private TextView tvMessagereceived = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity);

        tvMessagereceived = findViewById(R.id.tvMessageReceived);

        Intent intent = getIntent();
        String message = intent.getStringExtra(getString(R.string.LABEL_MESSAGE));
        tvMessagereceived.setText(message);
    }
}
