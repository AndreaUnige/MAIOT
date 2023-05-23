package com.maiot.esempioservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.maiot.esempioservice.services.MyBackgroundService;
import com.maiot.esempioservice.services.MyBoundedService;
import com.maiot.esempioservice.services.MyForegroundService;
import com.maiot.esempioservice.services.MyServiceConnection;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private Button bttStartForeground = null, bttStopForeground = null;
    private Button bttStartBackground = null, bttStopBackground = null;
    private Button bttBindService = null, bttGetBoundedServiceStatus = null;

    private TextView tvBoundedServiceStatus = null;
    private MyServiceConnection serviceConnection = new MyServiceConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startForegroundService();
        stopForegroundService();

        startBackgroundService();
        stopBackgroundService();

        startBoundedService();
        updateBoundedServiceStatus();
    }

    private void startForegroundService() {
        bttStartForeground = findViewById(R.id.bttStartForeground);
        bttStartForeground.setOnClickListener((v) -> {
            Log.i(TAG, "Start FOREGROUND service!");

            if (!MyForegroundService.isRunning) {
                startForegroundService(new Intent(this, MyForegroundService.class));
            }
            else{
                Log.i(TAG, "MyForeground service already running");
            }
        });
    }

    private void stopForegroundService() {
        bttStopForeground = findViewById(R.id.bttStopForeground);
        bttStopForeground.setOnClickListener((v) -> {
            Log.i(TAG, "Stop FOREGROUND service!");
            stopService(new Intent(this, MyForegroundService.class));
        });
    }

    private void startBackgroundService () {
        bttStartBackground = findViewById(R.id.bttStartBackground);
        bttStartBackground.setOnClickListener( (v) -> {
            if (!MyBackgroundService.isRunning){
                startService(new Intent(this, MyBackgroundService.class));
            }
            else {
                Log.i(TAG, "MybackgroundService already running");
            }
        });
    }

    private void stopBackgroundService () {
        bttStopBackground = findViewById(R.id.bttStopBackground);
        bttStopBackground.setOnClickListener( (v) -> {
            stopService(new Intent(this, MyBackgroundService.class));
        });
    }

    private void startBoundedService () {
        bttBindService = findViewById(R.id.bttConnectBoundedService);
        bttBindService.setOnClickListener( (v) -> {
            Intent intent = new Intent(this, MyBoundedService.class);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        });
    }

    private void updateBoundedServiceStatus () {
        bttGetBoundedServiceStatus = findViewById(R.id.bttGetServiceStatus);
        tvBoundedServiceStatus = findViewById(R.id.tvBoundedServiceStatus);

        bttGetBoundedServiceStatus.setOnClickListener( (v) -> {
            int status = serviceConnection.getServiceStatus();
            Log.i(TAG, "Service status: " + status);
            tvBoundedServiceStatus.setText("Service status: " + status);
        });
    }
}