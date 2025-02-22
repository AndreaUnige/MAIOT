package com.andrea.moviessuggestor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.andrea.moviessuggestor.JSON.MyJsonGenerator;
import com.andrea.moviessuggestor.R;
import com.andrea.moviessuggestor.interfaces.IRequestStatus;
import com.andrea.moviessuggestor.network.MyHttpRequest;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements IRequestStatus {

    private CheckBox cbAction, cbAdult, cbAdventure, cbAnimation, cbBiography, cbComedy, cbCrime,
            cbDocumentary, cbDrama, cbFamily, cbFantasy, cbGameShow, cbHistory, cbHorror, cbMusic,
            cbMusical, cbMystery, cbNews, cbRealityTV, cbRomance, cbSciFi, cbShort, cbSport,
            cbTalkShow, cbThriller, cbWar, cbWestern;

    private Button bttSubmit;
    private EditText etK;

    private HashMap<String, Integer> movieFeatures;
    private MyHttpRequest myHttpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHttpRequest = new MyHttpRequest(this);
        movieFeatures = new HashMap<>();
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
        cbAdult = findViewById(R.id.cbAdult);
        cbAdventure = findViewById(R.id.cbAdventure);
        cbAnimation = findViewById(R.id.cbAnimation);
        cbBiography = findViewById(R.id.cbBiography);
        cbComedy = findViewById(R.id.cbComedy);
        cbCrime = findViewById(R.id.cbCrime);
        cbDocumentary = findViewById(R.id.cbDocumentary);
        cbDrama = findViewById(R.id.cbDrama);
        cbFamily = findViewById(R.id.cbFamily);
        cbFantasy = findViewById(R.id.cbFantasy);
        cbGameShow = findViewById(R.id.cbGameShow);
        cbHistory = findViewById(R.id.cbHistory);
        cbHorror = findViewById(R.id.cbHorror);
        cbMusic = findViewById(R.id.cbMusic);
        cbMusical = findViewById(R.id.cbMusical);
        cbMystery = findViewById(R.id.cbMystery);
        cbNews = findViewById(R.id.cbNews);
        cbRealityTV = findViewById(R.id.cbRealityTV);
        cbRomance = findViewById(R.id.cbRomance);
        cbSciFi = findViewById(R.id.cbSciFi);
        cbShort = findViewById(R.id.cbShort);
        cbSport = findViewById(R.id.cbSport);
        cbTalkShow = findViewById(R.id.cbTalkShow);
        cbThriller = findViewById(R.id.cbThriller);
        cbWar = findViewById(R.id.cbWar);
        cbWestern = findViewById(R.id.cbWestern);
    }

    private void initHashMap() {
        movieFeatures.put("Action", 0);
        movieFeatures.put("Adult", 0);
        movieFeatures.put("Adventure", 0);
        movieFeatures.put("Animation", 0);
        movieFeatures.put("Biography", 0);
        movieFeatures.put("Comedy", 0);
        movieFeatures.put("Crime", 0);
        movieFeatures.put("Documentary", 0);
        movieFeatures.put("Drama", 0);
        movieFeatures.put("Family", 0);
        movieFeatures.put("Fantasy", 0);
        movieFeatures.put("Game-Show", 0);
        movieFeatures.put("History", 0);
        movieFeatures.put("Horror", 0);
        movieFeatures.put("Music", 0);
        movieFeatures.put("Musical", 0);
        movieFeatures.put("Mystery", 0);
        movieFeatures.put("News", 0);
        movieFeatures.put("Reality-TV", 0);
        movieFeatures.put("Romance", 0);
        movieFeatures.put("Sci-Fi", 0);
        movieFeatures.put("Short", 0);
        movieFeatures.put("Sport", 0);
        movieFeatures.put("Talk-Show", 0);
        movieFeatures.put("Thriller", 0);
        movieFeatures.put("War", 0);
        movieFeatures.put("Western", 0);
    }

    private void handleCheckboxesListeners() {
        cbAction.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbAction.setChecked(isChecked);
            movieFeatures.put("Action", isChecked ? 1 : 0);
        });

        cbAdult.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbAdult.setChecked(isChecked);
            movieFeatures.put("Adult", isChecked ? 1 : 0);
        });

        cbAdventure.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbAdventure.setChecked(isChecked);
            movieFeatures.put("Adventure", isChecked ? 1 : 0);
        });

        cbAnimation.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbAnimation.setChecked(isChecked);
            movieFeatures.put("Animation", isChecked ? 1 : 0);
        });

        cbBiography.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbBiography.setChecked(isChecked);
            movieFeatures.put("Biography", isChecked ? 1 : 0);
        });

        cbComedy.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbComedy.setChecked(isChecked);
            movieFeatures.put("Comedy", isChecked ? 1 : 0);
        });

        cbCrime.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbCrime.setChecked(isChecked);
            movieFeatures.put("Crime", isChecked ? 1 : 0);
        });

        cbDocumentary.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbDocumentary.setChecked(isChecked);
            movieFeatures.put("Documentary", isChecked ? 1 : 0);
        });

        cbDrama.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbDrama.setChecked(isChecked);
            movieFeatures.put("Drama", isChecked ? 1 : 0);
        });

        cbFamily.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbFamily.setChecked(isChecked);
            movieFeatures.put("Family", isChecked ? 1 : 0);
        });

        cbFantasy.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbFantasy.setChecked(isChecked);
            movieFeatures.put("Fantasy", isChecked ? 1 : 0);
        });

        cbGameShow.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbGameShow.setChecked(isChecked);
            movieFeatures.put("Game-Show", isChecked ? 1 : 0);
        });

        cbHistory.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbHistory.setChecked(isChecked);
            movieFeatures.put("History", isChecked ? 1 : 0);
        });

        cbHorror.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbHorror.setChecked(isChecked);
            movieFeatures.put("Horror", isChecked ? 1 : 0);
        });

        cbMusic.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbMusic.setChecked(isChecked);
            movieFeatures.put("Music", isChecked ? 1 : 0);
        });

        cbMusical.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbMusical.setChecked(isChecked);
            movieFeatures.put("Musical", isChecked ? 1 : 0);
        });

        cbMystery.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbMystery.setChecked(isChecked);
            movieFeatures.put("Mystery", isChecked ? 1 : 0);
        });

        cbNews.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbNews.setChecked(isChecked);
            movieFeatures.put("News", isChecked ? 1 : 0);
        });

        cbRealityTV.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbRealityTV.setChecked(isChecked);
            movieFeatures.put("Reality-TV", isChecked ? 1 : 0);
        });

        cbRomance.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbRomance.setChecked(isChecked);
            movieFeatures.put("Action", isChecked ? 1 : 0);
        });

        cbSciFi.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbSciFi.setChecked(isChecked);
            movieFeatures.put("Sci-Fi", isChecked ? 1 : 0);
        });

        cbShort.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbShort.setChecked(isChecked);
            movieFeatures.put("Short", isChecked ? 1 : 0);
        });

        cbSport.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbSport.setChecked(isChecked);
            movieFeatures.put("Sport", isChecked ? 1 : 0);
        });

        cbTalkShow.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbTalkShow.setChecked(isChecked);
            movieFeatures.put("Talk-Show", isChecked ? 1 : 0);
        });

        cbThriller.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbThriller.setChecked(isChecked);
            movieFeatures.put("Thriller", isChecked ? 1 : 0);
        });

        cbWar.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbWar.setChecked(isChecked);
            movieFeatures.put("War", isChecked ? 1 : 0);
        });
        cbWestern.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            cbWestern.setChecked(isChecked);
            movieFeatures.put("Western", isChecked ? 1 : 0);
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