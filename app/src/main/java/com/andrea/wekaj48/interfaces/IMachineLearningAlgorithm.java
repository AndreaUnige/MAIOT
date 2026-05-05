package com.andrea.wekaj48.interfaces;

public interface IMachineLearningAlgorithm {
    public void loadModel ();
    public void predict (double[] theFeatures);
}
