package com.andrea.wekaj48.J48;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

public class Data {

    private Attribute classes = null;
    private ArrayList<Attribute> features = null;

    private Instances dataset = null;
    private DenseInstance theInstance = null;

    public Data() {
        createClasses();
        createAttributes();
        createData();
    }

    public void createInstance (double[] theFeatures) {
        theInstance = new DenseInstance(1.0, theFeatures);
        theInstance.setDataset(dataset);
    }

    public DenseInstance getTheInstance() {
        return theInstance;
    }

    public Instances getTheDataset() {
        return dataset;
    }



    private void createClasses () {
        ArrayList<String> singleClasses = new ArrayList<>();
        singleClasses.add(Constants.IRIS_SETOSA);
        singleClasses.add(Constants.IRIS_VERSICOLOR);
        singleClasses.add(Constants.IRIS_VIRGINICA);

        classes = new Attribute(Constants.CLASS, singleClasses);
    }

    private void createAttributes () {
        features = new ArrayList<>();

        features.add(new Attribute(Constants.SEPAL_LENGTH));
        features.add(new Attribute(Constants.SEPAL_WIDTH));
        features.add(new Attribute(Constants.PETAL_LENGTH));
        features.add(new Attribute(Constants.PETAL_WIDTH));

        features.add(classes);
    }

    private void createData() {
        dataset = new Instances(Constants.IRIS_DATA, features, 0);
        dataset.setClassIndex(dataset.numAttributes() - 1);
    }
}
