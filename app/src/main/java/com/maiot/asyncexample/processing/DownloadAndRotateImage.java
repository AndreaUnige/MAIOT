package com.maiot.asyncexample.processing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Looper;

import com.maiot.asyncexample.interfaces.IDownload;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadAndRotateImage implements Runnable {

    private String urlSingleImage;
    private IDownload iDownload;

    private Bitmap bitmap;

    private final Matrix matrix = new Matrix();
    private final int NUMBER_OF_ROTATIONS = 100;

    public DownloadAndRotateImage(String urlSingleImage, IDownload iDownload) {
        this.urlSingleImage = urlSingleImage;
        this.iDownload = iDownload;

        matrix.postRotate(90);
    }


    @Override
    public void run() {
        download_tryCatch();
        rotateIt(NUMBER_OF_ROTATIONS);

        Handler handler = new Handler();
        handler.post(() -> iDownload.onDownloadDone(bitmap, urlSingleImage));
    }

    private void download_tryCatch() {
        try {
            download();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void download() throws IOException {
        URL url = new URL(urlSingleImage);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        bitmap = BitmapFactory.decodeStream(input);
    }

    private void rotateIt(int numberOfRotations) {
        for (int i = 0; i < numberOfRotations; i++)
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
