package com.maiot.audiorecorder.recorder;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.maiot.audiorecorder.interfaces.IRecording;
import com.maiot.audiorecorder.wave.WavIO;

import java.util.ArrayList;

public class Recorder {
    private final String TAG = "Recorder";

    private int fsInHz;
    private int nSamples;
    private int recordingLengthInSec;

    private short[] audioData;

    private AudioRecord audioRecord = null;

    private Context context;
    private IRecording iRecording;


    public Recorder(Context context, int fsInHz, int recordingLengthInSec) {
        Log.i(TAG, "Recorder()");

        this.fsInHz = fsInHz;
        this.recordingLengthInSec = recordingLengthInSec;

        this.nSamples = fsInHz * this.recordingLengthInSec;

        this.context = context;
        iRecording = (IRecording) context;
    }

    public void start() {
        new Thread( () -> {

            initRecorder();
            doRecording(false);
            releaseRecorder();

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> iRecording.onRecordingDone(audioData));

        }).start();
    }

    public void stop() {
        audioRecord.stop();
        releaseRecorder();
    }

    private void initRecorder() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, fsInHz, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, 2 * nSamples);
        audioData = new short[nSamples];
    }

    private void releaseRecorder() {
        audioRecord.release();
        audioRecord = null;
    }


    
    private void doRecording(boolean writeWav) {
    	Log.i(TAG, "Start recording...");
    	
    	audioRecord.startRecording();
    	audioRecord.read(audioData, 0, nSamples);

        if (writeWav)
    		writeWavFile();

        Log.i(TAG, "Recording done!");
    }
    
    private byte[] fromShortArray2ByteArray(short[] data) {
    	byte[] dataByte = new byte[2 * data.length];

    	for (int i=0; i<data.length; i++) {
    		dataByte[2*i] = (byte)(data[i] & 0x00ff);
    		dataByte[2*i+1] = (byte)(data[i]>>8 & 0x00ff);
    	}
    	return dataByte;
    }

    private void writeWavFile() {
        String storeDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        String fullPath = storeDir + "/MyRecording.wav";

        byte[] audioDataInByte = fromShortArray2ByteArray(audioData);

        WavIO wavIO = new WavIO(fullPath, 16, 1, 1, fsInHz, 2, 16, audioDataInByte);
        wavIO.save();
    }


}
