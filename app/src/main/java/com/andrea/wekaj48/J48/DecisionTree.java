package com.andrea.wekaj48.J48;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.andrea.wekaj48.interfaces.IMachineLearningAlgorithm;
import com.andrea.wekaj48.interfaces.IPrediction;
import com.andrea.wekaj48.misc.Constants;

import java.io.InputStream;

import weka.classifiers.trees.J48;
import weka.core.SerializationHelper;

public class DecisionTree implements IMachineLearningAlgorithm {

    private J48 myClassifier = null;  // Change here for another classifier!
    private Context context;

    private Data data;
    private IPrediction iPrediction;

    private String thePrediction;

    public DecisionTree(Context context) {
        this.context = context;
        this.iPrediction = (IPrediction) context;
    }

    @Override
    public void loadModel () {
        try {
            InputStream is = context.getAssets().open(Constants.MODEL);
            this.myClassifier = (J48) SerializationHelper.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void predict (double[] theFeatures) {
        data = new Data();
        data.createInstance(theFeatures);

        new Thread( () -> {
            classify();

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post( () -> iPrediction.onPredictionAvailable(thePrediction));
        }).start();
    }


    private void classify() {
        double result;
        try {
            result = myClassifier.classifyInstance(data.getTheInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        thePrediction = data.getTheDataset().classAttribute().value((int) result);
        Log.i("WEKA_RESULT", "La predizione è: " + thePrediction);
    }


}
