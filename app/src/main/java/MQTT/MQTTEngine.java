package MQTT;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

import javax.net.ssl.SSLSocketFactory;

public class MQTTEngine {

    private final String TAG = "MQTTEngine";

    private MqttClient mqttClient = null;
    private MqttConnectOptions mqttOptions = null;

    public MQTTEngine(MQTTEntity mqttEntity) {
        try {
        initMqttOptions(mqttEntity.getUser(), mqttEntity.getPassword());
        initMqttClient(mqttEntity.getBroker(), mqttEntity.getPort());
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void connectToMqttBroker () {
        try {
            mqttClient.connect(mqttOptions);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnectFromMqttBroker () {
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribeToTopic(String topic, int qos) {
        try {
            mqttClient.subscribe(topic, qos);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMessageReceivedCallback(MqttCallback mqttCallback) {
        mqttClient.setCallback(mqttCallback);
    }

    public void publish(String topic, int qos, String dataToBePublished) {
        try {
            mqttClient.publish(topic, dataToBePublished.getBytes(StandardCharsets.UTF_8), qos, false);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }




    private void initMqttOptions(String user, String password) {
        mqttOptions = new MqttConnectOptions();
        mqttOptions.setUserName(user);
        mqttOptions.setPassword(password.toCharArray());
        // mqttOptions.setSocketFactory(SSLSocketFactory.getDefault());
    }

    private void initMqttClient(String broker, int port) throws MqttException {
        mqttClient = new MqttClient(
                "tcp://" + broker + ":" + port,  // Broker URI, protocol://broker:port
                MqttClient.generateClientId(),
                new MemoryPersistence()
        );
    }

}
