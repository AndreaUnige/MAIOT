package com.maiot.esempiomqtt;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

import interfaces.IMySensor;

public class MyAccelometer implements SensorEventListener, IMySensor {

    private final String TAG = "MyAccelometer";

    private Context activity = null;

    private ArrayList<Float> x = null;
    private ArrayList<Float> y = null;
    private ArrayList<Float> z = null;

    private ArrayList<Float[]> accValues = null;

    private SensorEventListener sensorEventListener = null;
    private Sensor accelerometer = null;
    private SensorManager sensorManager = null;


    public MyAccelometer(Activity activity) {
        this.activity = activity;

        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
        this.z = new ArrayList<>();

        this.accValues = new ArrayList<>();

        sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void start() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void stop() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    public String toMyString() {
        String accelerometerValuesToString = "";

        if (accValues.size() > 0) {
            Iterator<Float[]> accIterator = accValues.iterator();
            while(accIterator.hasNext()) {
                Float[] singleSample3Axis = accIterator.next();
                accelerometerValuesToString += singleSampleToString(singleSample3Axis) + ";";
            }
        }
        return accelerometerValuesToString;
    }

    private String singleSampleToString(Float [] singleSample3Axis) {
        return singleSample3Axis[0] + ", " + singleSample3Axis[1] + ", " + singleSample3Axis[2];
    }

    @Override
    public void clearData() {
        this.x.clear();
        this.y.clear();
        this.z.clear();

        this.accValues.clear();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        this.x.add(x);
        this.y.add(y);
        this.z.add(z);

        this.accValues.add(new Float[] {x, y, z});
        Log.i(TAG, singleSampleToString(new Float[] {x, y, z}));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }
}
