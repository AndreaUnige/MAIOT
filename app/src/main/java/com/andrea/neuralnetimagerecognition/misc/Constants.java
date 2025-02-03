package com.andrea.neuralnetimagerecognition.misc;

import java.util.HashMap;

public class Constants {

    public static final int CAMERA_PERMISSION = 1001;


    public static final int NUM_BYTE_PER_FLOAT = 4;
    public static final int IMAGE_SIZE = 32;
    public static final int NUM_RGB_CHANNELS = 3;


    public static final String MODEL_CLASS__APPLE = "Apple";
    public static final String MODEL_CLASS__BANANA = "Banana";
    public static final String MODEL_CLASS__ORANGE = "Orange";

    public static final String[] MODEL_CLASSES = new String[] {MODEL_CLASS__APPLE, MODEL_CLASS__BANANA, MODEL_CLASS__ORANGE};

    public static final int[] NEURAL_NET_SIZE = new int[]{1, 32, 32, 3}; // This should be same size as the NN created. See the colab code
}
