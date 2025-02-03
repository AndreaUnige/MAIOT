package com.andrea.neuralnetimagerecognition;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.andrea.neuralnetimagerecognition.misc.Constants;

import java.io.IOException;

// CAMERA WITH registerForActivityResult -> https://www.youtube.com/watch?v=JMdHMMEO8ZQ&ab_channel=MSCode009
// YOUTUBE VIDEO -> https://www.youtube.com/watch?v=yV9nrRIC_R0&ab_channel=IJApps
// COLAB NEURAL NET -> https://colab.research.google.com/drive/1XHNNYwDYYoaJaQVCy1uIeKqErXTYpwLu?usp=sharing


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private Button bttTakePicture, bttGallery;
    private ImageView ivImage;
    private TextView tvClassification, tvDetails;

    private ActivityResultLauncher<Intent> arlTakePhoto;
    private ActivityResultLauncher<String> arlFromGallery;

    private ImageProcess imageProcess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        arlTakePhoto = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if ((result.getResultCode() != RESULT_OK) || (result.getData() == null ))
                            return;

                        Bundle bundle = result.getData().getExtras();
                        Bitmap bitmap = (Bitmap) bundle.get("data");

                        setBitmapToImageView(bitmap);
                        recognize(bitmap);                    }
                }
        );

        arlFromGallery = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri pictureURI) {
                        if (pictureURI == null)
                            return;

                        ContentResolver contentResolver = getContentResolver();
                        try {
                            Bitmap bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, pictureURI),
                                    (imageDecoder, imageInfo, source1) -> imageDecoder.setMutableRequired(true));

                            setBitmapToImageView(bitmap);
                            recognize(bitmap);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );


        bttTakePicture.setOnClickListener( (v) -> {
            if (cameraPermissionGranted())
                arlTakePhoto.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            else
                requestPermissions(new String[] {Manifest.permission.CAMERA}, Constants.CAMERA_PERMISSION);
        });


        bttGallery.setOnClickListener( (v) -> {
            arlFromGallery.launch("image/*");
        });

    }


    private boolean cameraPermissionGranted() {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void initViews() {
        bttTakePicture = findViewById(R.id.bttTakePicture);
        bttGallery = findViewById(R.id.bttGallery);

        ivImage = findViewById(R.id.ivImage);

        tvClassification = findViewById(R.id.tvClassification);
        tvDetails = findViewById(R.id.tvDetails);
    }



    private void setBitmapToImageView(Bitmap bitmap) {
        ivImage.setImageBitmap(bitmap);
    }

    private void recognize(Bitmap bitmap) {
        imageProcess = new ImageProcess(getApplicationContext(), bitmap);
        imageProcess.run();

        postResultToScreen();

    }

    private void postResultToScreen() {
        float maxConfidence = imageProcess.getMaxConfidenceValue();
        int argMaxConfidence = imageProcess.getMaxConfidenceIndex();

        String result = "Classification: " + Constants.MODEL_CLASSES[argMaxConfidence];
        result += "\n";
        result += "Confidence: " + maxConfidence;

        tvClassification.setText(result);
    }

}