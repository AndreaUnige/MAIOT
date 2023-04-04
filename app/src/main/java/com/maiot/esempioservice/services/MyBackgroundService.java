package com.maiot.esempioservice.services;

import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.maiot.esempioservice.tasks.MusicPlayer;

public class MyBackgroundService extends Service {

    private final String TAG = "MyBackgroundService";
    public static boolean isRunning = false;

    private MusicPlayer musicPlayer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service - onCreate");
        musicPlayer = new MusicPlayer(this, RingtoneManager.TYPE_NOTIFICATION);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service - onStartCommand");
        isRunning = true;
        musicPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service - onDestroy");
        isRunning = false;

        musicPlayer.stop();
    }
}
