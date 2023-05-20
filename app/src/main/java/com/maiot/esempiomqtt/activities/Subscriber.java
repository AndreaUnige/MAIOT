package com.maiot.esempiomqtt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.maiot.esempiomqtt.Constants;
import com.maiot.esempiomqtt.R;

import java.util.ArrayList;

import MQTT.MQTTEntity;
import MQTT.MQTTSensorSubscriber;
import interfaces.IMqttMessages;

public class Subscriber extends AppCompatActivity implements IMqttMessages {
    private final String TAG = "Subscriber";

    private TextView tvTopic = null;
    private TextView tvX = null, tvY = null, tvZ = null;
    private TextView tvStatus = null;

    private Button bttSubscriber = null;

    private MQTTSensorSubscriber mqttSubscriber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscriber);

        initViews();

        MQTTEntity mqttEntity = new MQTTEntity(Constants.BROKER, Constants.PORT, Constants.USER, Constants.PASSWORD);
        mqttSubscriber = new MQTTSensorSubscriber(mqttEntity, this);

        bttSubscriber.setOnClickListener( (v) -> {
            mqttSubscriber.connectAndSubscribe();
            setSubscribingStatus(true);
        });
    }

    @Override
    public void onMessagePublished(String topic, String message) { }

    @Override
    public void onMessageReceived(String topic, String message) {
        ArrayList<Float[]> accelerometricValues = parseData(message);
        Float[] means_x_y_z = computeMean(accelerometricValues);
        runOnUiThread(() -> {updateTextViews(means_x_y_z);} );
    }


    private ArrayList<Float[]> parseData (String message) {
        ArrayList<Float[]> parsedData = new ArrayList<>();

        String[] accelerometricTriplets = message.split(";");
        for (String singleAccelerometricTriple : accelerometricTriplets) {
            String[] x_y_z = singleAccelerometricTriple.split(",");

            parsedData.add(
                new Float [] {
                    Float.parseFloat(x_y_z[0]),
                    Float.parseFloat(x_y_z[1]),
                    Float.parseFloat(x_y_z[2])
            });
        }
        return parsedData;
    }

    private Float[] computeMean(ArrayList<Float[]> accelerometricValues) {
        float mean_x = 0f, mean_y = 0f, mean_z = 0f;

        for (Float[] singleTriple : accelerometricValues) {
            mean_x += singleTriple[0];
            mean_y += singleTriple[1];
            mean_z += singleTriple[2];
        }
        return new Float[] {
                mean_x / accelerometricValues.size(),
                mean_y / accelerometricValues.size(),
                mean_z / accelerometricValues.size(),
        };
    }

    private void updateTextViews(Float[] means_x_y_z) {
        tvX.setText("X: " + means_x_y_z[0]);
        tvY.setText("X: " + means_x_y_z[1]);
        tvZ.setText("X: " + means_x_y_z[2]);
    }

    private void initViews() {
        tvTopic = findViewById(R.id.tvTopic);
        tvTopic.setText("Topic: " + Constants.TOPIC);

        tvX = findViewById(R.id.tvX);
        tvY = findViewById(R.id.tvY);
        tvZ = findViewById(R.id.tvZ);

        tvStatus = findViewById(R.id.tvStatus);

        bttSubscriber = findViewById(R.id.bttGoSubscribe);
        setSubscribingStatus(false);
    }

    private void setSubscribingStatus(boolean isSubscribed) {
        String subscribed =    "<b>Status: </b> <font color='green'> Subscribed </font>";
        String notSubscribed = "<b>Status: </b> <font color='red'> Not subscribed </font>";

        if (isSubscribed)
            tvStatus.setText(Html.fromHtml(subscribed));
        else
            tvStatus.setText(Html.fromHtml(notSubscribed));
    }
}