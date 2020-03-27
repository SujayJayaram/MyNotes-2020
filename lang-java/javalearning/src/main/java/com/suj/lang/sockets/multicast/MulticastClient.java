package com.suj.lang.sockets.multicast;

import java.io.*;
import java.net.*;

public class MulticastClient {

    public static void main(String[] args) throws IOException {

        // Client does not need to know the IP Address of the server -
        // it just needs to know the Mulicast Group AND THE PORT NUMBER
        // NOTE this port number is NOT the port number which the server binds
        // to when created (4445) but rather the port number that the datagram
        // packet is sent out with. This from the MulticastSocket class javadoc
        /*
        * A multicast group is specified by a class D IP address
        * and by a standard UDP port number. Class D IP addresses
        * are in the range <CODE>224.0.0.0</CODE> to <CODE>239.255.255.255</CODE>,
        * inclusive. The address 224.0.0.0 is reserved and should not be used.
        * */
        MulticastSocket socket = new MulticastSocket(4446);
        InetAddress address = InetAddress.getByName("230.0.0.1");
        socket.joinGroup(address);

        DatagramPacket packet;

        // get a few quotes
        for (int i = 0; i < 5; i++) {

            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quote of the Moment: " + received);
        }

        socket.leaveGroup(address);
        socket.close();
    }

}