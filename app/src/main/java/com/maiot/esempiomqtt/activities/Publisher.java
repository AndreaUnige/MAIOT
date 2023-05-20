package com.maiot.esempiomqtt.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.maiot.esempiomqtt.Constants;
import com.maiot.esempiomqtt.MyAccelometer;
import com.maiot.esempiomqtt.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletionStage;

import MQTT.MQTTEntity;
import MQTT.MQTTSensorPublisher;
import interfaces.IMqttMessages;
import interfaces.IMySensor;

public class Publisher extends AppCompatActivity implements IMqttMessages {

    private final String TAG = "Publisher";

    private MQTTSensorPublisher mqttSensorPublisher = null;
    private IMySensor myAccelerometer = null;

    private Button bttGo = null, bttStop = null, bttClearMessages = null;
    private TextView tvBroker = null, tvPort = null, tvStatus = null, tvMessages = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publisher);

        initViews();
        myAccelerometer = new MyAccelometer(this);
        MQTTEntity mqttEntity = new MQTTEntity(Constants.BROKER, Constants.PORT, Constants.USER, Constants.PASSWORD);
        mqttSensorPublisher = new MQTTSensorPublisher(mqttEntity, this, myAccelerometer);

        bttGo.setOnClickListener((v) -> {
            bttGo.setEnabled(false);
            bttStop.setEnabled(true);

            mqttSensorPublisher.startPublishing();
            setPublishingStatus(true);
        });

        bttStop.setOnClickListener((v) -> {
            bttGo.setEnabled(true);
            bttStop.setEnabled(false);

            mqttSensorPublisher.stopPublishing();
            setPublishingStatus(false);
        });
    }

    @Override
    public void onMessagePublished(String topic, String message) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        final String text = tvMessages.getText() +
                timestamp + " - Published message on topic: " + topic + "\n";

        runOnUiThread( () -> {
            tvMessages.setText(text);
        });
    }

    @Override
    public void onMessageReceived(String topic, String message) { }

    private void initViews() {
        bttGo = findViewById(R.id.bttGo);
        bttStop = findViewById(R.id.bttStop);

        bttClearMessages = findViewById(R.id.bttClearMessages);
        bttClearMessages.setOnClickListener( (v) -> {
            tvMessages.setText("");
        });

        tvBroker = findViewById(R.id.tvBroker);
        tvBroker.setText("Broker: " + Constants.BROKER);

        tvPort = findViewById(R.id.tvPort);
        tvPort.setText("Port: " + Constants.PORT);

        tvStatus = findViewById(R.id.tvPublishingStatus);
        tvMessages = findViewById(R.id.tvMessages);

        setPublishingStatus(false);
    }

    private void setPublishingStatus(boolean isPublishing) {
        String publishing =    "<b>Status: </b> <font color='green'> Publishing </font>";
        String notPublishing = "<b>Status: </b> <font color='red'> Not publishing </font>";

        if (isPublishing)
            tvStatus.setText(Html.fromHtml(publishing));
        else
            tvStatus.setText(Html.fromHtml(notPublishing));
    }
}
