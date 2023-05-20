package MQTT;

import android.util.Log;

import com.maiot.esempiomqtt.Constants;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import interfaces.IMqttMessages;

public class MQTTSensorSubscriber {

    private final String TAG = "MQTTSensorSubscriber";

    private MQTTEngine mqttEngine = null;
    private IMqttMessages mqttMessagesInterface = null;

    public MQTTSensorSubscriber (MQTTEntity mqttEntity, IMqttMessages mqttMessagesInterface) {
        this.mqttMessagesInterface = mqttMessagesInterface;
        this.mqttEngine = new MQTTEngine(mqttEntity);
    }

    public void connectAndSubscribe() {
        connectToMqttBroker();
        subscribeToMqttTopic();
        setMessageCallback();
    }

    private void connectToMqttBroker() {
        mqttEngine.connectToMqttBroker();
    }

    private void subscribeToMqttTopic() {
        mqttEngine.subscribeToTopic(Constants.TOPIC, Constants.QOS);
    }

    private void setMessageCallback() {
        MqttCallback mqttCallback = new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.i(TAG, "Connection to broker " + Constants.BROKER + " lost. " + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.i(TAG, "Message received: " +
                        "\n\t - Topic: " + topic +
                        "\n\t - Message: " + new String(message.getPayload()) +
                        "\n\t - QoS: " + message.getQos());
                mqttMessagesInterface.onMessageReceived(topic, new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) { }
        };
        mqttEngine.setMessageReceivedCallback(mqttCallback);
    }
}
