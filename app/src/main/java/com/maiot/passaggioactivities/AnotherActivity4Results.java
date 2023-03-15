package com.maiot.passaggioactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AnotherActivity4Results extends AppCompatActivity {

    private EditText etResult = null;
    private Button bttOK = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity_for_results);

        etResult = findViewById(R.id.etMessageResult);
        bttOK = findViewById(R.id.bttOK);

        Intent intent = getIntent();
        String text = intent.getStringExtra(getString(R.string.LABEL_MESSAGE_FOR_RESULTS));
        etResult.setText(text);

        bttOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etResult.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(getString(R.string.LABEL_MESSAGE_RETURN), text);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });





    }
}
