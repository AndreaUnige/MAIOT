package com.maiot.broadcastreceiverexample.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.maiot.broadcastreceiverexample.R;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private final String TAG = "MyBroadcastReceiver";
    private Context context;

    public MyBroadcastReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");

        String intentAction = intent.getAction();

        if (intentAction.equals(context.getString(R.string.MY_INTENT_ACTION)))
            processCustomIntent(intent);

        if (intentAction.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED))
            processAirplaneModeIntent(intent);
    }

    private void processCustomIntent(Intent intent) {
        String text = intent.getStringExtra(context.getString(R.string.MY_INTENT_LABEL));
        Log.i(TAG, "Messaggio ricevuto: " + text);

        Toast.makeText(context, "Messaggio ricevuto: " + text, Toast.LENGTH_LONG).show();
    }

    private void processAirplaneModeIntent(Intent intent) {
        boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
        Toast.makeText(context, "Airplane mode: " + isAirplaneModeOn, Toast.LENGTH_LONG).show();
    }


}
