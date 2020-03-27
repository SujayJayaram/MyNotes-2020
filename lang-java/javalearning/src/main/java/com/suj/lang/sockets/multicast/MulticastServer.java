package com.suj.lang.sockets.multicast;

/**
 * Created by sujayjayaram on 05/02/2016.
 */
public class MulticastServer {
    public static void main(String[] args) throws java.io.IOException {
        new MulticastServerThread().start();
    }
}
