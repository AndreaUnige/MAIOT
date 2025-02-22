package com.andrea.moviessuggestor.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyJsonGenerator {

    private HashMap<String, Integer> movieFeatures;
    private int k;

    private JSONObject json = new JSONObject();

    public MyJsonGenerator(HashMap<String, Integer> movieFeatures, int k) {
        this.movieFeatures = movieFeatures;
        this.k = k;
    }

    public void generateJson() {
        insertK();
        insertMovieFeatures();
    }

    public JSONObject getJson() {
        return json;
    }

    private void insertK(){
        try {
            json.put("k", k);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertMovieFeatures(){
        JSONObject singleFeature = new JSONObject();

        for (Map.Entry<String, Integer> feature : movieFeatures.entrySet()) {
            String key = feature.getKey();
            Integer value = feature.getValue();

            try {
                singleFeature.put(key, value);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            json.put("movieToRecognize", singleFeature);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
