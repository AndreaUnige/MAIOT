package com.maiot.esempioservice.tasks;

import android.util.Log;

public class ThreadBackTask {

    private final String TAG = "ThreadBackTask";

    private Thread myThread = null;
    private int status = 0;
    private volatile boolean shouldStop;
    private boolean isVerbose = false;

    public ThreadBackTask (boolean isVerbose) {
        this.isVerbose = isVerbose;
        initThread();
    }

    public void start () {
        Log.i(TAG, "Back task started");
        status = 0;
        myThread.start();
    }

    public void stop () {
        Log.i(TAG, "Back task stopped");
        shouldStop = true;
    }

    public int getStatus () {
        return status;
    }

    private void initThread () {
        myThread = new Thread( () -> {
            while (true) {
                if (shouldStop)
                    break;
                doTheTask();
            }
        });
    }

    private void doTheTask () {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        status++;
        doLog(TAG, "BackTask is running. Status: " + status);
    }

    private void doLog(String TAG, String message) {
        if (isVerbose)
            Log.i(TAG, message);
    }
}
