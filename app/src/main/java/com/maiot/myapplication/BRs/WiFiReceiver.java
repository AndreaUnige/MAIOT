package com.maiot.myapplication.BRs;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.maiot.myapplication.interfaces.WiFiScanCompleted;

import java.util.List;

public class WiFiReceiver extends BroadcastReceiver {

    private final String TAG = "WiFiReceiver";
    private WifiManager wifiManager = null;
    private WiFiScanCompleted wiFiScanCompleted = null;

    public WiFiReceiver(WifiManager wifiManager, WiFiScanCompleted wiFiScanCompleted) {
        this.wifiManager = wifiManager;
        this.wiFiScanCompleted = wiFiScanCompleted;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");

        @SuppressLint("MissingPermission")
        List<ScanResult> wifiScan = wifiManager.getScanResults();

        String res = "";
        res += "<b>Numero reti trovate: " + wifiScan.size() + "</b><br><br>";

        for (int i = 0; i < wifiScan.size(); i++) {
            res += "<b>SSID: </b>" + wifiScan.get(i).SSID + "<br>";
            res += "<b>MAC: </b>" + wifiScan.get(i).BSSID + "<br>";
            res += "<b>RSSI: </b>" + wifiScan.get(i).level + "<br><br>";
        }

        Log.i(TAG, res);
        wiFiScanCompleted.onWifiScanCompleted(res);
    }
}
