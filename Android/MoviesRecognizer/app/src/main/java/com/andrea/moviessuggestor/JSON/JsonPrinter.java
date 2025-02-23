package com.andrea.moviessuggestor.JSON;

import com.andrea.moviessuggestor.interfaces.IServerResponsePrinter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonPrinter implements IServerResponsePrinter {

    private JSONObject jsToBePrinted;

    public JsonPrinter(String toBePrintedAsString) {
        convertToJson(toBePrintedAsString);
    }



    // {"data": [{"title": "The Rickey Smiley Show (2012) {The Reconciliation (#2.9)}", "score": 0.0}, {"title": "Ninja Babes from Space (2007)", "score": 0.0}, {"title": "No Chill (2017)", "score": 0.0}, {"title": "Nneka the Uber Driver (2016)", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {Together Again (#6.1)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The Society Entertainer (#4.4)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The Pay-Off (#3.4)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The New Broom (#8.2)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The Great Cat Robbery (#7.1)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The Epidemic (#5.7)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The Beauty Queen Affair (#1.3)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {Ted at the Helm (#6.2)}", "score": 0.0}, {"title": "High Spirits with Shirley Ghostman (2005) {Fat (#1.4)}", "score": 0.0}, {"title": "High Society (1995)", "score": 0.0}, {"title": "High Heels, Low Standards (2013) {Stranger Danger (#1.1)}", "score": 0.0}, {"title": "Big and Slim (2014)", "score": 0.0}, {"title": "Cuckoo (2012/II) {Potato Party (#2.2)}", "score": 0.0}, {"title": "Cuckoo (2012/II) {Mums Group (#3.3)}", "score": 0.0}, {"title": "Cuckoo (2012/II) {Ken on E (#1.3)}", "score": 0.0}, {"title": "Cuckoo (2012/II) {Grandfathers Cat (#1.4)}", "score": 0.0}]}
    @Override
    public String toHtmlFormattedString() {
        String htmlFormatted = "";

        JSONArray data = getData();
        for (int i = 0 ; i < data.length(); i++) {
            JSONObject singleMovie = getSingleMovie(data, i);
            htmlFormatted += getSingleMovieHtmlFormatted(singleMovie);
        }

        return htmlFormatted;
    }






    private void convertToJson(String toBePrintedAsString) {
        try {
            this.jsToBePrinted = new JSONObject(toBePrintedAsString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject getSingleMovie(JSONArray array, int index) {
        try {
            return array.getJSONObject(index);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private String getSingleMovieHtmlFormatted(JSONObject singleMovie) {
        String title = getTitle( singleMovie );
        String score = getScore( singleMovie );

        return "<b>Title: </b>" + title + "<br>" + "<b>Score: </b>" + score + "<br><br>";
    }

    private JSONArray getData() {
        try {
            return (JSONArray) this.jsToBePrinted.get("data");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTitle(JSONObject obj) {
        return getValue(obj, "title");
    }

    private String getScore(JSONObject obj) {
        return getValue(obj, "score");
    }

    private String getValue(JSONObject obj, String key) {
        try {
            return obj.get(key).toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }




}
