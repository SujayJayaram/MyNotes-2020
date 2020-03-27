package com.suj.jetty.basic;

import org.eclipse.jetty.server.Server;

/**
 * Created by sujayjayaram on 13/02/2016.
 */
public class SimplestServer
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new HelloHandler());

        server.start();
        server.join();
    }
}
