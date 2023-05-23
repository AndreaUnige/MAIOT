package com.maiot.esempioservice.services;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class MyServiceConnection implements ServiceConnection {
    private final String TAG = "MyServiceConnection";
    private MyBoundedService mBoundedService = null;

    public MyServiceConnection () {}

    private void startServiceTask() {
        if (mBoundedService != null) {
            Log.i(TAG, "Service task started!");
            mBoundedService.startTask();
        }
    }

    public int getServiceStatus () {
        return mBoundedService.getStatus();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i(TAG, "onServiceConnected");
        MyBoundedService.LocalBinder binderBridge = (MyBoundedService.LocalBinder) iBinder;
        mBoundedService = binderBridge.getServiceInstance();

        startServiceTask();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(TAG, "onServiceDisconnected");
        mBoundedService = null;
    }
}
