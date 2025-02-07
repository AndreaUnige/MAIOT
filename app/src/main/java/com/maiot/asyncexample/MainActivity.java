package com.maiot.asyncexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.maiot.asyncexample.interfaces.IDownload;

public class MainActivity extends AppCompatActivity implements IDownload {

    TextView tvOverallTime, tvStatus;
    Button bttSequential, bttParallel;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9;

    BackTask myBackTask = null;

    long startTimeInMillis, endTimeInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        myBackTask = new BackTask(this);

        bttSequential.setOnClickListener(v -> {
            clearAllImages();
            tvStatus.setText("Download in process...");
            bttSequential.setEnabled(false);
            bttParallel.setEnabled(false);

            startTimeInMillis = System.currentTimeMillis();
            myBackTask.doSequentialDownload(ImagesURLs.URLs);
        });


        bttParallel.setOnClickListener(v -> {
            clearAllImages();
            tvStatus.setText("Download in process...");
            bttSequential.setEnabled(false);
            bttParallel.setEnabled(false);

            startTimeInMillis = System.currentTimeMillis();
            myBackTask.doParallelDownload(ImagesURLs.URLs);
        });

    }

    @Override
    public void onDownloadDone(Bitmap bitmap, String urlDone) {
        int imageIndex = ImagesURLs.getIndex(urlDone);
        setTheImage(bitmap, imageIndex);

        if (allImagesHaveBeenDownloaded()) {
            tvStatus.setText("ALL DONE !");

            endTimeInMillis = System.currentTimeMillis();
            tvOverallTime.setText("Overall time [s]: " + (float)(endTimeInMillis - startTimeInMillis) / (float)1000);

            bttSequential.setEnabled(true);
            bttParallel.setEnabled(true);
        }
    }






    private void initViews() {
        tvStatus = findViewById(R.id.tvStatus);
        tvOverallTime = findViewById(R.id.tvOverallTime);

        bttSequential = findViewById(R.id.bttSequential);
        bttParallel = findViewById(R.id.bttParrallel);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);
        iv7 = findViewById(R.id.iv7);
        iv8 = findViewById(R.id.iv8);
        iv9 = findViewById(R.id.iv9);
    }

    private void setTheImage(Bitmap bitmap, int imageIndex) {
        switch (imageIndex) {
            case 0:
                iv1.setImageBitmap(bitmap);
                break;

            case 1:
                iv2.setImageBitmap(bitmap);
                break;

            case 2:
                iv3.setImageBitmap(bitmap);
                break;

            case 3:
                iv4.setImageBitmap(bitmap);
                break;

            case 4:
                iv5.setImageBitmap(bitmap);
                break;

            case 5:
                iv6.setImageBitmap(bitmap);
                break;

            case 6:
                iv7.setImageBitmap(bitmap);
                break;

            case 7:
                iv8.setImageBitmap(bitmap);
                break;

            case 8:
                iv9.setImageBitmap(bitmap);
                break;
        }
    }

    private void clearAllImages() {
        iv1.setImageDrawable(null);
        iv2.setImageDrawable(null);
        iv3.setImageDrawable(null);
        iv4.setImageDrawable(null);
        iv5.setImageDrawable(null);
        iv6.setImageDrawable(null);
        iv7.setImageDrawable(null);
        iv8.setImageDrawable(null);
        iv9.setImageDrawable(null);
    }

    private boolean allImagesHaveBeenDownloaded() {
        return (
                (iv1.getDrawable() != null) &&
                (iv2.getDrawable() != null) &&
                (iv3.getDrawable() != null) &&
                (iv4.getDrawable() != null) &&
                (iv5.getDrawable() != null) &&
                (iv6.getDrawable() != null) &&
                (iv7.getDrawable() != null) &&
                (iv8.getDrawable() != null) &&
                (iv9.getDrawable() != null)
        );

    }
}