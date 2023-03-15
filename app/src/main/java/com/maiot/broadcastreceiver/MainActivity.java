package com.maiot.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.maiot.broadcastreceiver.BRs.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private EditText etTesto = null;
    private Button bttOk = null;
    private TextView tvMessageFromBr = null;

    private BroadcastReceiver br = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTesto = findViewById(R.id.etTesto);
        bttOk = findViewById(R.id.bttOK);
        tvMessageFromBr = findViewById(R.id.tvMessageFromBR);

        // Polimorfismo sulla classe padre BroadcastReceiver
        br = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(getString(R.string.ACTION_BR_A1));
        registerReceiver(br, intentFilter);

        bttOk.setOnClickListener( (v) -> {
            String text = etTesto.getText().toString();
            Intent intent = new Intent(getString(R.string.ACTION_BR_A1));
            intent.putExtra(getString(R.string.LABEL_MESSAGGIO), text);
            sendBroadcast(intent);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(br);
    }
}