package com.suj.lang.sockets.multicast;

import com.suj.lang.sockets.datagram.QuoteServerThread;

import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastServerThread extends QuoteServerThread {

    private long FIVE_SECONDS = 5000;

    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }

    public void run() {
        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];

                // construct quote
                String dString = null;
                if (in == null)
                    dString = new Date().toString();
                else
                    dString = getNextQuote();
                buf = dString.getBytes();

                // send it
                /*
                The hard-coded InetAddress of the DatagramPacket is "203.0.113.0" and is a group identifier
                (rather than the Internet address of the machine on which a single client is running).
                This particular address was arbitrarily chosen from the reserved for this purpose.
                Created in this way, the DatagramPacket is destined for all clients listening to port
                number 4446 who are member of the "203.0.113.0" group.
                 */
                InetAddress group = InetAddress.getByName("230.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);

                // sleep for a while
                try {
                    sleep((long)(Math.random() * FIVE_SECONDS));
                } catch (InterruptedException e) { }
            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }
}