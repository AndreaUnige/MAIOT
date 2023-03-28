package com.maiot.audiorecorder.recorder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.maiot.audiorecorder.interfacce.IRecording;
import com.maiot.audiorecorder.wave.WavIO;

public class Recorder {
    private final String TAG = "Recorder";

    private int recordingLengthInSec;
    private int fs; // in Hz
    private int nSamples;
    private short[] audioData = null;

    private AudioRecord audioRecord = null;
    private IRecording iRecording = null;

    private Activity a = null;

    @SuppressLint("MissingPermission")
    public Recorder(Activity a, IRecording iRecording, int recordingLengthInSec, int fs) {
        this.recordingLengthInSec = recordingLengthInSec;
        this.fs = fs;

        nSamples = recordingLengthInSec * fs;
        audioData = new short[nSamples];

        this.iRecording = iRecording;

        this.a = a;
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                fs, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, nSamples * 2);
    }

    public void start() {
        new Thread( () -> {
            // Gira in background
            final String fullPath = doRecording();
            destroy();

            a.runOnUiThread(() -> {

            });

//            new Handler(Looper.getMainLooper()).post( () -> {
//                iRecording.onRecordingDone(fullPath);
//            });
        }).start();
    }

    private String doRecording() {
        Log.i(TAG, "Start recording...");

        audioRecord.startRecording();
        audioRecord.read(audioData, 0, nSamples);

        String storeDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .getAbsolutePath();
        String fullPath = storeDir + "/Recording.wav";

        byte[] dataByte = fromShortToByte(audioData);
        WavIO wavIO = new WavIO(fullPath, 16, 1, 1, fs, 2, 16, dataByte);
        wavIO.save();

        return fullPath;
    }

    private void destroy() {
        audioRecord.release();
        audioRecord = null;
    }

    private byte[] fromShortToByte(short[] data) {
        byte[] dataByte = new byte[2 * data.length];

        for (int i= 0; i<data.length; i++) {
            dataByte[2*i] = (byte) (data[i] & 0x00ff);
            dataByte[2*i +1 ] = (byte) (data[i] >> 8 & 0x00ff);
        }
        return dataByte;
    }
}
