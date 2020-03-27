package com.suj.lang.nio.GOOD.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

// see - http://www.java2s.com/Tutorials/Java/Java_Network/0080__Java_Network_Asynchronous_Socket_Channels.htm
public class Main {
    public static void main(String[] args) throws Exception {
        // Bind to our port/host
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        String host = "localhost";
        int port = 8989;
        InetSocketAddress sAddr = new InetSocketAddress(host, port);
        server.bind(sAddr);
        System.out.format("Server is listening at %s%n", sAddr);

        // Just fill in the server attribute for now - we will fill in other details
        // in the connection handler.
        Attachment attach = new Attachment();
        attach.server = server;
        server.accept(attach, new ConnectionHandler()); // accept connections
        System.out.println("Thread at main() is called: " + Thread.currentThread().getName());


        while(true) {
            //System.out.println("ping");
            Thread.currentThread().sleep(5000);
        }
    }
}

// This is userdata that is passed back to us by the framework
// and allows us to keep track of which client and buffer we are working
// with.
class Attachment {
    AsynchronousServerSocketChannel server;
    AsynchronousSocketChannel client;
    ByteBuffer buffer;
    SocketAddress clientAddr;
    boolean isRead;
}

class ConnectionHandler implements
        CompletionHandler<AsynchronousSocketChannel, Attachment> {
    @Override
    public void completed(AsynchronousSocketChannel client, Attachment attach) {
        System.out.println("Thread at completed() is called: " + Thread.currentThread().getName());

        try {
            // We have received a connection from a new client
            // Prepare for the client after this one by calling accept again.
            SocketAddress clientAddr = client.getRemoteAddress();
            System.out.format("Accepted a  connection from  %s%n", client.getRemoteAddress());
            attach.server.accept(attach, this);

            // Properly fill in the attachment and READ
            ReadWriteHandler rwHandler = new ReadWriteHandler();
            Attachment newAttach = new Attachment();
            newAttach.server = attach.server; // Always the same original AsynchronousServerSocketChannel object as created above.
            newAttach.client = client; // The new client
            newAttach.buffer = ByteBuffer.allocate(2048); // fresh buffer
            newAttach.isRead = true; // we read their message to start off with
            newAttach.clientAddr = clientAddr;
            client.read(newAttach.buffer, newAttach, rwHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable e, Attachment attach) {
        System.out.println("Failed to accept a  connection.");
        e.printStackTrace();
    }
}

class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attach) {
        if (result == -1) {
            try {
                attach.client.close();
                System.out.format("Stopped   listening to the   client %s%n",
                        attach.clientAddr);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        if (attach.isRead) {
            attach.buffer.flip();
            int limits = attach.buffer.limit();
            byte bytes[] = new byte[limits];
            attach.buffer.get(bytes, 0, limits);
            Charset cs = Charset.forName("UTF-8");
            String msg = new String(bytes, cs);
            System.out.format("Client at  %s  says: %s%n \r\n", attach.clientAddr, msg);
            attach.isRead = false; // Write same thing back
            attach.buffer.rewind();
            attach.client.write(attach.buffer, attach, this);

        } else {
            System.out.println("Write finished");

            // Write will have finished so wait for the next response
            attach.isRead = true;
            attach.buffer.clear();
            attach.client.read(attach.buffer, attach, this);
        }
    }

    @Override
    public void failed(Throwable e, Attachment attach) {
        e.printStackTrace();
    }
}