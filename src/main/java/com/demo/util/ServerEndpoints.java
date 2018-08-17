package com.demo.util;

import org.springframework.stereotype.Service;

@Service
public class ServerEndpoints {
    private static final String SERVER_AUTH_ENDPOINT = "/auth";
    private static final String SERVER_TDATA_ENDPOINT = "/tdata";

    private static final String DEFAULT_SERVER_URL = "http://172.30.29.65:8080";

    public ServerEndpoints(){

    }

    public static String getServerAuthEndpoint() {
        return DEFAULT_SERVER_URL + SERVER_AUTH_ENDPOINT;
    }

    public static String getServerTDataEndpoint() {
        return DEFAULT_SERVER_URL + SERVER_TDATA_ENDPOINT;
    }

}
