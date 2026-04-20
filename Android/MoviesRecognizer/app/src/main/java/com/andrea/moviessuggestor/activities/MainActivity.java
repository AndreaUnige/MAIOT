package com.andrea.moviessuggestor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.andrea.moviessuggestor.JSON.MyJsonGenerator;
import com.andrea.moviessuggestor.R;
import com.andrea.moviessuggestor.interfaces.IRequestStatus;
import com.andrea.moviessuggestor.network.MyHttpRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements IRequestStatus {

    private CheckBox cbAction, cbFantasy, cbHistory, cbHorror, cbMusic, cbSport, cbWar, cbWestern;

    private Button bttSubmit;
    private EditText etK;

    private Map<String, Integer> movieFeatures;
    private MyHttpRequest myHttpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHttpRequest = new MyHttpRequest(this);
        movieFeatures = new LinkedHashMap<>();
        initHashMap();

        initCheckBoxes();
        handleCheckboxesListeners();

        etK = findViewById(R.id.etKvalue);

        bttSubmit = findViewById(R.id.bttSubmit);
        bttSubmit.setOnClickListener( (v) -> {

            MyJsonGenerator myJsonGenerator = new MyJsonGenerator(movieFeatures, getK());
            myJsonGenerator.generateJson();

            myHttpRequest.doRequest(myJsonGenerator.getJson());
        });
    }


    private void initCheckBoxes() {
        cbAction = findViewById(R.id.cbAction);
        cbFantasy = findViewById(R.id.cbFantasy);
        cbHistory = findViewById(R.id.cbHistory);
        cbHorror = findViewById(R.id.cbHorror);
        cbMusic = findViewById(R.id.cbMusic);
        cbSport = findViewById(R.id.cbSport);
        cbWar = findViewById(R.id.cbWar);
        cbWestern = findViewById(R.id.cbWestern);
    }

    private void initHashMap() {
        movieFeatures.put("Action", 0);
        movieFeatures.put("Fantasy", 0);
        movieFeatures.put("History", 0);
        movieFeatures.put("Horror", 0);
        movieFeatures.put("Music", 0);
        movieFeatures.put("Sport", 0);
        movieFeatures.put("War", 0);
        movieFeatures.put("Western", 0);
    }

    private void handleCheckboxesListeners() {
        handleCB(cbAction, "Action");
        handleCB(cbFantasy, "Fantasy");
        handleCB(cbHistory, "History");
        handleCB(cbHorror, "Horror");
        handleCB(cbMusic, "Music");
        handleCB(cbSport, "Sport");
        handleCB(cbWar, "War");
        handleCB(cbWestern, "Western");
    }

    private void handleCB(CheckBox cb, String cbName) {
        cb.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cb.setChecked(isChecked);
            movieFeatures.put(cbName, isChecked ? 1 : 0);
        });
    }

    private int getK() {
        return Integer.parseInt( etK.getText().toString() );
    }

    @Override
    public void onResultAvailable(String result) {
        Intent intent = new Intent(getString(R.string.ACTION_LAUNCH_ACTIVITY));
        intent.putExtra(getString(R.string.RESULT_LABEL), result);

        startActivity(intent);
    }
}