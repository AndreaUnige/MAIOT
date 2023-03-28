package com.maiot.audiorecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.maiot.audiorecorder.interfacce.IRecording;
import com.maiot.audiorecorder.recorder.Recorder;

public class MainActivity extends AppCompatActivity implements IRecording {

    private final String TAG = "MainActivity";

    private Button bttStart = null;
    private TextView tvStatus = null;

    private Recorder recorder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        bttStart = findViewById(R.id.bttStartRec);

        bttStart.setOnClickListener( (v) -> {
            tvStatus.setText("Stato: Recording...");
            recorder = new Recorder(this, 5, 8000);
            recorder.start();
        });
    }

    @Override
    public void onRecordingDone(String fullPath) {
        tvStatus.setText("Stato: Done!");
    }
}