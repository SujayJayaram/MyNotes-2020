package com.suj.lang.sockets.datagram;

/**
 * Created by sujayjayaram on 05/02/2016.
 */
import java.io.*;
import java.net.*;

public class QuoteClient {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            //System.out.println("Usage: java QuoteClient <hostname>");
            //return;
            args = new String[1];
            args[0] = "localhost"; // CAN RUN BOTH ON SAME SERVER
        }

        // get a datagram socket
        DatagramSocket socket = new DatagramSocket(); // BINDS TO A TRANSIENT PORT - the datagram is created with the required well-known 4445 port number.

        // send request
        // We are sending to the same ip address and port that the server is listening on.
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName(args[0]);
        // MUST be the same port as the server is listening on (i.e. that it initially binds to)
        // which is the difference between this and Multicast.
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);

        // get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Quote of the Moment: " + received);

        socket.close();
    }
}