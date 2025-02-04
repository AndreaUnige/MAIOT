package com.maiot.accelerometerexample.sensore;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.maiot.accelerometerexample.interfaces.IDataAvailable;
import com.maiot.accelerometerexample.interfaces.IMySensor;

public class MyAccelerometer implements SensorEventListener, IMySensor {

    private final String TAG = "MyAccelerometer";

    private SensorManager sensorManager = null;
    private Sensor accelerometer = null;

    private IDataAvailable iDataAvailable = null;


    public MyAccelerometer(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        else
            Log.i(TAG, "Sensor ACCELEROMETER not available !");

        this.iDataAvailable = (IDataAvailable) context;
    }

    @Override
    public void start() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void stop() {
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i(TAG, "onSensorChanged");

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        iDataAvailable.onNewAccelerometerDataAvailable(x, y, z);

        Log.i(TAG, "X: " + x + " - Y: " + y + " - Z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i(TAG, "onAccuracyChanged - Accuracy: " + accuracy);
    }
}
