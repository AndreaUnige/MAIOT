package MQTT;

import com.maiot.esempiomqtt.Constants;
import java.util.Timer;
import java.util.TimerTask;

import interfaces.IMqttMessages;
import interfaces.IMySensor;

public class MQTTSensorPublisher {
    private final String TAG = "MQTTSensorPublisher";

    private IMySensor sensor = null;
    private MQTTEngine mqttEngine = null;

    private IMqttMessages mqttMessagesInterface = null;
    private Timer timer = null;

    public MQTTSensorPublisher(MQTTEntity mqttEntity, IMqttMessages mqttMessagesInterface, IMySensor sensor) {
        this.sensor = sensor;
        this.mqttMessagesInterface = mqttMessagesInterface;
        this.mqttEngine = new MQTTEngine(mqttEntity);
    }

    public void startPublishing() {
        connectToMqttBroker();
        startSensorAcquisition();
        startSendingDataLoop();
    }

    public void stopPublishing () {
        stopSendingDataLoop();
        stopSensorAcquisition();
        disconnectFromMqttBroker();
    }


    private void connectToMqttBroker() {
        mqttEngine.connectToMqttBroker();
    }
    private void disconnectFromMqttBroker() {
        mqttEngine.disconnectFromMqttBroker();
    }

    private void startSensorAcquisition() {
        sensor.start();
    }

    private void stopSensorAcquisition() {
        sensor.stop();
    }

    private void startSendingDataLoop() {
        timer = null;
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String sensorData = sensor.toMyString();
                if (sensorData.length() > 0) {
                    mqttEngine.publish(Constants.TOPIC, Constants.QOS, sensorData);
                    mqttMessagesInterface.onMessagePublished(Constants.TOPIC, sensorData);
                    sensor.clearData();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, Constants.TIMER_DELAY_IN_MILLIS, Constants.TIMER_PERIOD_IN_MILLIS);
    }

    private void stopSendingDataLoop() {
        timer.cancel();
    }

}
