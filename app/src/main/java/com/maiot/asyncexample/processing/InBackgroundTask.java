package com.maiot.asyncexample.processing;

import android.util.Log;
import com.maiot.asyncexample.interfaces.IDownload;
import com.maiot.asyncexample.misc.SingleImage;

public class InBackgroundTask {

    private final String TAG = "BackTask";
    private IDownload iDownload;

    public InBackgroundTask(IDownload iDownload)
    {
        Log.i(TAG, "BackTask()");
        this.iDownload = iDownload;
    }

    private IDownload myCallback = (bitmap, imageDone) -> {
        Log.i("Download", "Finished: " + imageDone);
        iDownload.onDownloadDone(bitmap, imageDone);
    };

    public void doSequentialDownload(SingleImage[] imagesToDownload) {
        Log.i(TAG, "doSequentialDownload()");

        // On Pre Execution
        onPreExecute();

        new Thread(() -> {
            for (SingleImage singleImage : imagesToDownload)
                new DownloadAndRotateImage(singleImage, myCallback).run();
        }).start();
    }

    public void doParallelDownload(SingleImage[] imagesToDownload) {
        Log.i(TAG, "doDownload()");

        // On Pre Execution
        onPreExecute();

        for (SingleImage singleImage : imagesToDownload)
            new Thread(new DownloadAndRotateImage(singleImage, myCallback)).start();
    }

    private void onPreExecute() {
        Log.i(TAG, " onPreExecute()");
    }
}
