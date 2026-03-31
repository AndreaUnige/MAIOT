package com.maiot.accelerometerexample;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;

import com.maiot.accelerometerexample.interfaces.IDataAvailable;
import com.maiot.accelerometerexample.interfaces.IMySensor;
import com.maiot.accelerometerexample.plot.Plotter;
import com.maiot.accelerometerexample.sensore.MyAccelerometer;

public class MainActivity extends AppCompatActivity implements IDataAvailable {

    private final String TAG = "MainActivity";
    private final int ROUND_N_DIGITS = 3;

    private Button bttStart = null, bttStop = null;
    private TextView tvX = null, tvY = null, tvZ = null;

    // private MyAccelerometer myAccelerometer = null;  // First, not optimal
    private IMySensor myAccelerometer = null;  // OK!

    private Plotter plotter;

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

        GraphView graph = findViewById(R.id.graph);
        plotter = new Plotter(graph);




        bttStart.setOnClickListener( (v) -> {
            myAccelerometer.start();
        });

        bttStop.setOnClickListener((v) -> {
            myAccelerometer.stop();
        });
    }


    @Override
    public void onNewAccelerometerDataAvailable(float x, float y, float z) {
        tvX.setText("X: " + roundToNDecimal(ROUND_N_DIGITS, x));
        tvY.setText("Y: " + roundToNDecimal(ROUND_N_DIGITS, y));
        tvZ.setText("Z: " + roundToNDecimal(ROUND_N_DIGITS, z));

        plotter.addData(x, Plotter.X_AXIS);
        plotter.addData(y, Plotter.Y_AXIS);
        plotter.addData(z, Plotter.Z_AXIS);
    }

    private String roundToNDecimal(int nDecimals, float toRound) {
        return String.format("%."+nDecimals+"f", toRound);
    }


}