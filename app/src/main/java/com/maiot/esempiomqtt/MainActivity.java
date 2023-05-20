package com.maiot.esempiomqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Button bttPublisher = null, bttSubscriber = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttPublisher = findViewById(R.id.bttPublisher);
        bttPublisher.setOnClickListener( (v) -> {
            Intent i = new Intent("com.maiot.esempiomqtt.activities.Publisher");
            startActivity(i);
        });

        bttSubscriber = findViewById(R.id.bttSubscriber);
        bttSubscriber.setOnClickListener( (v) -> {
            Intent i = new Intent("com.maiot.esempiomqtt.activities.Subscriber");
            startActivity(i);
        });
    }
}