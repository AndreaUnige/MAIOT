package com.maiot.passaggiotraactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private EditText etMessaggio = null;
    private Button bttStartActivity = null, bttStartActivity4Results = null;

    private ActivityResultLauncher<Intent> activityResultLauncher = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessaggio = findViewById(R.id.etMessaggio);

        buttonStartActivity();
        buttonStartActivity4Result();
    }

    private void buttonStartActivity() {
        bttStartActivity = findViewById(R.id.bttStartActivity);
        bttStartActivity.setOnClickListener( v -> {

            String text = etMessaggio.getText().toString();

            Intent intent = new Intent(getString(R.string.ACTION_LAUNCH_ACTIVITY));
            intent.putExtra(getString(R.string.LABEL_FOR_ACTIVITY), text);

            startActivity(intent);
        });
    }

    private void buttonStartActivity4Result() {


        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Log.i(TAG, "risultato ricevuto");

            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();
                if (intent != null) {
                    String backText = intent.getStringExtra( getString(R.string.LABEL_FOR_BACK_PROPAGATION) );
                    Log.i(TAG,"Back text: " + backText);
                    etMessaggio.setText(backText);
                }

            }else {
                Log.i(TAG, "Error!");
            }
        });

        bttStartActivity4Results = findViewById(R.id.bttStartActivity4Result);
        bttStartActivity4Results.setOnClickListener( v -> {
            String text = etMessaggio.getText().toString();

            Intent intent = new Intent(this, AnotherActivityForResults.class);
            intent.putExtra(getString(R.string.LABEL_FOR_ACTIVITY_4_RESULT), text);

            activityResultLauncher.launch(intent);
        });
    }
}