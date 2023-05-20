package MQTT;

public class MQTTEntity {

    private String broker = "";
    private int port;
    private String user = "";
    private String password = "";

    public MQTTEntity(String broker, int port, String user, String password) {
        this.broker = broker;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
