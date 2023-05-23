package com.maiot.esempioservice.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.maiot.esempioservice.R;
import com.maiot.esempioservice.tasks.MusicPlayer;

import java.net.MulticastSocket;

public class MyForegroundService extends Service {

    private final String TAG = "MyForegroundService";
    public static boolean isRunning = false;

    private final String NOTIFICATION_CHANNEL_ID = "MyForeroundService_ID";
    private MusicPlayer musicPlayer = null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service - onCreate");
        musicPlayer = new MusicPlayer(this, RingtoneManager.TYPE_ALARM);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service - onStartCommand");
        addNotification();
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


    private void addNotification() {
        NotificationChannel channel = new NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_ID,
                NotificationManager.IMPORTANCE_LOW
        );
        getSystemService(NotificationManager.class).createNotificationChannel(channel);

        Notification.Builder notification = new Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentText("MyForegroundService is running")
                .setContentTitle("Service is active")
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_background);

        startForeground(1001, notification.build());
    }

}
