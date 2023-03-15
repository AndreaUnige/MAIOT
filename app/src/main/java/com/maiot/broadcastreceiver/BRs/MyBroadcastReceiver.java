package com.maiot.broadcastreceiver.BRs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.maiot.broadcastreceiver.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = "MyBroadcastReceiver";

    public MyBroadcastReceiver() {
        Log.i(TAG, "MyBroadcastReceiver()");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");

        String text = intent.getStringExtra(context.getString(R.string.LABEL_MESSAGGIO));
        Log.i(TAG, "Messaggio ricevuto:" + text);

        Toast.makeText(context, "Messaggio ricevuto: " + text, Toast.LENGTH_LONG).show();
    }

}
