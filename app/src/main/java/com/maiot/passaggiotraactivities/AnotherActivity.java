package com.maiot.passaggiotraactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnotherActivity extends AppCompatActivity {

    private final String TAG = "AnotherActivity";

    private TextView tvMessageReceived = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity);

        tvMessageReceived = findViewById(R.id.tvMessageReceived);

        Intent i = getIntent();
        String messageReceived = i.getStringExtra( getString(R.string.LABEL_FOR_ACTIVITY) );
        tvMessageReceived.setText(messageReceived);
    }
}
