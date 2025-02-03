package com.andrea.neuralnetimagerecognition;

import android.content.Context;
import android.graphics.Bitmap;

import com.andrea.neuralnetimagerecognition.misc.Constants;
import com.andrea.neuralnetimagerecognition.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;


// Open the 'model.tflite' to see a basic example from which this code is taken
public class ImageProcess {

    private final Context context;
    private final Bitmap theImageToProcess;

    private Bitmap squaredImage;


    private Model model;
    private TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(Constants.NEURAL_NET_SIZE, DataType.FLOAT32);
    private ByteBuffer byteBuffer;


    private float[] modelConfidenceValues;
    private float maxConfidenceValue = 0.0f;
    private int maxConfidenceIndex = 0;

    public ImageProcess(Context context, Bitmap theImageToProcess) {
        this.context = context;
        this.theImageToProcess = theImageToProcess;

        try {
            model = Model.newInstance(context);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int nBytesPerImage = getNumBytePerImage();
        byteBuffer = ByteBuffer.allocateDirect(nBytesPerImage);
        byteBuffer.order(ByteOrder.nativeOrder());
    }

    public int getMaxConfidenceIndex() {
        return maxConfidenceIndex;
    }

    public float getMaxConfidenceValue() {
        return maxConfidenceValue;
    }

    public float[] getModelConfidenceValues() {
        return modelConfidenceValues;
    }


    public void run() {
        preProcess();
        process();
        postProcess();
    }


    private void preProcess() {
        byteBuffer.clear();
        squaredImage = Bitmap.createScaledBitmap(theImageToProcess, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, false);
        extractRGB_addToByteBuffer();
        inputFeature0.loadBuffer(byteBuffer);
    }

    private void process() {
        Model.Outputs outputs = model.process(inputFeature0);
        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

        modelConfidenceValues = outputFeature0.getFloatArray();
    }

    private void postProcess() {
        compute_ConfidenceMax_ConfidenceArgMax();
        model.close();
    }





    private int getNumBytePerImage () {
        int nPixelsPerImage = Constants.IMAGE_SIZE * Constants.IMAGE_SIZE;
        int nFloatsPerImage = nPixelsPerImage * Constants.NUM_RGB_CHANNELS;
        int nBytesPerImage = nFloatsPerImage * Constants.NUM_BYTE_PER_FLOAT;

        return  nBytesPerImage;
    }

    private void extractRGB_addToByteBuffer() {
        for (int i = 0; i < Constants.IMAGE_SIZE; i++) {
            for (int j = 0; j < Constants.IMAGE_SIZE; j++) {
                int rgbPixelValue = squaredImage.getPixel(i, j); // RGB
                byteBuffer.putFloat( extractRed(rgbPixelValue) );
                byteBuffer.putFloat( extractGreen(rgbPixelValue) );
                byteBuffer.putFloat( extractBlue(rgbPixelValue) );
            }
        }
    }

    private int extractBlue(int rgbPixelValue) {
        return applyBitmask(rgbPixelValue);
    }

    private int extractGreen(int rgbPixelValue) {
        return applyBitmask(rgbPixelValue >> 8);
    }

    private int extractRed(int rgbPixelValue) {
        return applyBitmask(rgbPixelValue >> 16);
    }

    private int applyBitmask (int rgbPixelValue) {
        return rgbPixelValue & 0xFF;
    }


    private void compute_ConfidenceMax_ConfidenceArgMax() {
        for (int i = 0; i < modelConfidenceValues.length; i++)
            if (modelConfidenceValues[i] > maxConfidenceValue) {
                maxConfidenceIndex = i;
                maxConfidenceValue = modelConfidenceValues[i];
            }
    }


}
