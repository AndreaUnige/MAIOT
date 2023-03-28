package com.maiot.esempioaccelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.maiot.esempioaccelerometro.interfacce.IMyAccelerometer;
import com.maiot.esempioaccelerometro.sensore.MyAccelerometer;

public class MainActivity extends AppCompatActivity implements IMyAccelerometer {

    private final String TAG = "MainActivity";

    private Button bttStart = null, bttStop = null;
    private TextView tvX = null, tvY = null, tvZ = null;

    private MyAccelerometer myAccelerometer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttStart = findViewById(R.id.bttStart);
        bttStop = findViewById(R.id.bttStop);

        tvX = findViewById(R.id.tvX);
        tvY = findViewById(R.id.tvY);
        tvZ = findViewById(R.id.tvZ);

        myAccelerometer = new MyAccelerometer(this);
        bttStart.setOnClickListener( (v) -> {
            myAccelerometer.start();
        });

        bttStop.setOnClickListener( (v) -> {
            myAccelerometer.stop();
        });
    }

    @Override
    public void onNewAccelerometerValuesAvailable(float x, float y, float z) {
        tvX.setText("X: " + x);
        tvY.setText("X: " + y);
        tvZ.setText("X: " + z);
    }
}