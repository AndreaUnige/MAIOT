package com.maiot.esempioaccelerometro.sensore;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

import com.maiot.esempioaccelerometro.MainActivity;
import com.maiot.esempioaccelerometro.interfacce.IMyAccelerometer;

public class MyAccelerometer implements SensorEventListener {

    private final String TAG = "MyAccelerometer";

    private SensorManager sensorManager = null;
    private Sensor accelerometer = null;

    private IMyAccelerometer iMyAccelerometer = null;

    public MyAccelerometer(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        this.iMyAccelerometer = (IMyAccelerometer) context;
    }

    public void start() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.i(TAG, "onSensorChanged");

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        iMyAccelerometer.onNewAccelerometerValuesAvailable(x, y, z);
        Log.i(TAG, "X: " + x + " Y: " + y + " Z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.i(TAG, "onAccuracyChanged");
    }
}
