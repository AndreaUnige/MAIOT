package com.maiot.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.maiot.myapplication.BRs.WiFiReceiver;
import com.maiot.myapplication.interfaces.WiFiScanCompleted;

public class MainActivity extends AppCompatActivity implements WiFiScanCompleted {

    private final String TAG = "MainActivity";
    private Button bttStartScan = null;
    private TextView tvWifiScan = null;

    private WifiManager wifiManager = null;
    private WiFiReceiver wiFiReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttStartScan = findViewById(R.id.bttStartScan);
        tvWifiScan = findViewById(R.id.tvWifiScan);

        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wiFiReceiver = new WiFiReceiver(wifiManager, this);

        registerReceiver(wiFiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        bttStartScan.setOnClickListener( (v) -> {
            if (!wifiManager.isWifiEnabled()) {
                Toast.makeText(getApplicationContext(), "Wifi spengo. Lo accendo..", Toast.LENGTH_LONG).show();
                wifiManager.setWifiEnabled(true);
            }

            wifiManager.startScan();
            Log.i(TAG, "Wifi scan avviata");

            tvWifiScan.setText("Scansione in corso...");
        });

    }

    @Override
    public void onWifiScanCompleted(String wifiResultAsHtml) {
        tvWifiScan.setText(Html.fromHtml(wifiResultAsHtml));
    }
}