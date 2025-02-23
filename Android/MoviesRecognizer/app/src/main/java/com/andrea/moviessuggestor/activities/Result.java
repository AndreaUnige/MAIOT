package com.andrea.moviessuggestor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.andrea.moviessuggestor.JSON.JsonPrinter;
import com.andrea.moviessuggestor.R;
import com.andrea.moviessuggestor.interfaces.IServerResponsePrinter;

public class Result extends AppCompatActivity {

    private TextView tvResult;
    private IServerResponsePrinter iServerResponsePrinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tvResult);

        Intent intent = getIntent();
        String messageReceived = intent.getStringExtra( getString(R.string.RESULT_LABEL) );

        iServerResponsePrinter = new JsonPrinter(messageReceived);
        String messageAsHtmlFormattedString = iServerResponsePrinter.toHtmlFormattedString();

        tvResult.setText(Html.fromHtml(messageAsHtmlFormattedString, Html.FROM_HTML_MODE_LEGACY));
    }
}