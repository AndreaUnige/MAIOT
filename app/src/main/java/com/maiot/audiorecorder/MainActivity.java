package com.maiot.audiorecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.maiot.audiorecorder.interfaces.IRecording;
import com.maiot.audiorecorder.plots.Plotter;
import com.maiot.audiorecorder.process.Processor;
import com.maiot.audiorecorder.recorder.Recorder;

public class MainActivity extends AppCompatActivity implements IRecording {

	private final String TAG = "MainActivity";

	private Button bttStart, bttStop;
    private boolean shouldRecordingKeepGoing = false;

    private GraphView graphViewTime, graphViewFreq;
	private Recorder recorder = null;
	
	private final int FS = 8000;
	private final int RECORDING_LENGTH_IN_SEC = 1;

    private Plotter timePlotter, frequencyPlotter;
    Processor processor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bttStart = findViewById(R.id.bttStartRec);
        bttStop = findViewById(R.id.bttStopRec);

        graphViewTime = findViewById(R.id.gvTime);
        graphViewFreq = findViewById(R.id.gvFrequency);
        
        recorder = new Recorder(this, FS, RECORDING_LENGTH_IN_SEC);

        timePlotter = new Plotter(graphViewTime, "Time");
        frequencyPlotter = new Plotter(graphViewFreq, "Frequency");
        processor = new Processor(FS);
        
        bttStart.setOnClickListener( (v) -> {
            shouldRecordingKeepGoing = true;
            recorder.start();
            bttStart.setEnabled(false);
            bttStop.setEnabled(true);

        } );
        bttStop.setOnClickListener( (v) -> {
            shouldRecordingKeepGoing = false;
            recorder.stop();
            bttStart.setEnabled(true);
            bttStop.setEnabled(false);
        } );
    }


    @Override
    public void onRecordingDone(short[] audioData) {

        timePlotter.plot(audioData);

        processor.run(audioData);

        double[] frequencies = processor.getFrequencies();
        double[] spectrum = processor.getSpectrum();
        frequencyPlotter.plot(frequencies, spectrum);

        if (shouldRecordingKeepGoing)
            recorder.start();
    }

}
