package com.andrea.neuralnetimagerecognition;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
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

import com.andrea.neuralnetimagerecognition.interfaces.IRecognition;

import java.io.IOException;

// CAMERA WITH registerForActivityResult -> https://www.youtube.com/watch?v=JMdHMMEO8ZQ&ab_channel=MSCode009
// YOUTUBE VIDEOS -> https://www.youtube.com/watch?v=yV9nrRIC_R0&ab_channel=IJApps
//                -> https://www.youtube.com/watch?v=ba42uYJd8nc&ab_channel=IJApps
// COLAB NEURAL NET -> https://colab.research.google.com/drive/1XHNNYwDYYoaJaQVCy1uIeKqErXTYpwLu?usp=sharing

public class MainActivity extends AppCompatActivity implements IRecognition {

    private final static String TAG = "MainActivity";

    private Button bttTakePicture, bttGallery;
    private ImageView ivImage;
    private TextView tvClassification;

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
                result -> {
                    if ((result.getResultCode() != RESULT_OK) || (result.getData() == null ))
                        return;

                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");

                    ivImage.setImageBitmap(bitmap);
                    recognize(bitmap);
                }
        );

        arlFromGallery = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                pictureURI -> {
                    if (pictureURI == null)
                        return;

                    ContentResolver contentResolver = getContentResolver();
                    try {
                        Bitmap bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, pictureURI),
                                (imageDecoder, imageInfo, source1) -> imageDecoder.setMutableRequired(true));

                        ivImage.setImageBitmap(bitmap);
                        recognize(bitmap);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        bttTakePicture.setOnClickListener( (v) -> {
                arlTakePhoto.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        });


        bttGallery.setOnClickListener( (v) -> {
            arlFromGallery.launch("image/*");
        });

    }

    private void initViews() {
        bttTakePicture = findViewById(R.id.bttTakePicture);
        bttGallery = findViewById(R.id.bttGallery);

        ivImage = findViewById(R.id.ivImage);
        tvClassification = findViewById(R.id.tvClassification);
    }


    private void recognize(Bitmap bitmap) {
        imageProcess = new ImageProcess(this, bitmap);
        imageProcess.run();
    }


    @Override
    public void onRecognitionDone() {
        float maxConfidence = imageProcess.getMaxConfidenceValue();
        int argMaxConfidence = imageProcess.getMaxConfidenceIndex();

        String result = "Classification: " + Constants.MODEL_CLASSES[argMaxConfidence];
        result += "\n";
        result += "Confidence: " + maxConfidence;

        tvClassification.setText(result);
    }

 }