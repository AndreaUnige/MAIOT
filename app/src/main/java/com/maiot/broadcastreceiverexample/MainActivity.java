package com.maiot.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.maiot.broadcastreceiverexample.br.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private EditText etText = null;
    private Button bttOK = null;

    private BroadcastReceiver br = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);
        bttOK = findViewById(R.id.bttOK);

        // Polimorfismo
        br = new MyBroadcastReceiver(this);
        IntentFilter myIntentFilter = new IntentFilter(getString(R.string.MY_INTENT_ACTION));
        IntentFilter airplaneModeIntentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        // https://developer.android.com/about/versions/oreo/android-8.0-migration#rbr
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(br, myIntentFilter, RECEIVER_EXPORTED);
            registerReceiver(br, airplaneModeIntentFilter, RECEIVER_EXPORTED);
        }

        bttOK.setOnClickListener( v -> {
            String text = etText.getText().toString();

            Intent intent = new Intent(getString(R.string.MY_INTENT_ACTION));
            intent.putExtra(getString(R.string.MY_INTENT_LABEL), text);

            sendBroadcast(intent);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(br);
    }
}