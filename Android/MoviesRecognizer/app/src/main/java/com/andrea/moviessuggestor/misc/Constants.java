package com.andrea.moviessuggestor.misc;

public class Constants {

    private static final String HTTP = "http://";
    private static final int PORT = 5000;
    private static final String SERVER_IP_ANDREA = "192.168.1.249";
    private static final String ENDPOINT_RECOGNIZE = "/recognize";

    public static String getServerFullUrl() { return HTTP + SERVER_IP_ANDREA + ":" + PORT + ENDPOINT_RECOGNIZE; }

}
