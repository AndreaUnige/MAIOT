package com.maiot.passaggioactivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private EditText etMessaggio = null;
    private Button bttStartActivity = null, bttStartActivity4Results = null;

    private final int ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessaggio = findViewById(R.id.etMessaggio);
        buttonStartActivity();
        buttonStartActivityForResults();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String text = data.getStringExtra(getString(R.string.LABEL_MESSAGE_RETURN));
                etMessaggio.setText(text);
            }
            else
                Log.i(TAG, "Result code ERRATO");
        }
        else
            Log.i(TAG, "Request code differente");

    }



    private void buttonStartActivity() {
        bttStartActivity = findViewById(R.id.bttStartActivity);
        bttStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etMessaggio.getText().toString();

                Intent intent = new Intent(getString(R.string.LAUNCH_ACTIVITY));
                intent.putExtra(getString(R.string.LABEL_MESSAGE), text);
                startActivity(intent);
            }
        });
    }

    private void buttonStartActivityForResults() {
        bttStartActivity4Results = findViewById(R.id.bttStartActivity4Results);
        bttStartActivity4Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etMessaggio.getText().toString();

                Intent intent = new Intent(getString(R.string.LAUNCH_ACTIVITY_FOR_RESULTS));
                intent.putExtra(getString(R.string.LABEL_MESSAGE_FOR_RESULTS), text);
                startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
            }
        });
    }
}