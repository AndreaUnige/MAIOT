package com.maiot.asyncexample.processing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.maiot.asyncexample.interfaces.IDownload;

public class InBackgroundTask implements IDownload {

    private final String TAG = "BackTask";
    private IDownload iDownload;

    public InBackgroundTask(Activity activity)
    {
        Log.i(TAG, "BackTask()");
        this.iDownload = (IDownload) activity;
    }

    public void doSequentialDownload(String[] urlImagesToDownload) {
        Log.i(TAG, "doSequentialDownload()");

        // On Pre Execution
        onPreExecute();

        new Thread(() -> {
            for (String urlSingleImage : urlImagesToDownload)
                new DownloadAndRotateImage(urlSingleImage, this).run();
        }).start();
}

    public void doParallelDownload(String[] urlImagesToDownload) {
        Log.i(TAG, "doDownload()");

        // On Pre Execution
        onPreExecute();

        for (String urlSingleImage : urlImagesToDownload)
            new Thread(new DownloadAndRotateImage(urlSingleImage, this)).start();
    }

    private void onPreExecute() {
        Log.i(TAG, " onPreExecute()");
    }

    @Override
    public void onDownloadDone(Bitmap bitmap, String urlDone) {
        iDownload.onDownloadDone(bitmap, urlDone);
    }
}
