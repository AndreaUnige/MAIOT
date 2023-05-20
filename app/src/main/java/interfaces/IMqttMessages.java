package interfaces;

public interface IMqttMessages {
    void onMessagePublished(String topic, String message);
    void onMessageReceived(String topic, String message);
}
