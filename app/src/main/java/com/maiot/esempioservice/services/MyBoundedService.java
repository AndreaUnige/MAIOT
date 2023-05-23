package com.maiot.esempioservice.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.maiot.esempioservice.tasks.ThreadBackTask;

public class MyBoundedService extends Service {
    private IBinder binder = new LocalBinder();
    private ThreadBackTask mBackTask = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    public void startTask () {
        mBackTask = new ThreadBackTask(true);
        mBackTask.start();
    }

    public int getStatus () {
        return mBackTask.getStatus();
    }

    public class LocalBinder extends Binder {
        public MyBoundedService getServiceInstance() {
            return MyBoundedService.this;
        }
    }
}
