package com.suj.lang.sockets;

/**
 * Created by sujayjayaram on 03/02/2016.
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        System.out.println("Binding to localhost : " + portNumber);
        List<Thread> threadList = new ArrayList<>();
        try (
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));

        ) {
            while(true){
                try
                {
                    // This is handed off to another thread so it's cleanup (e.g. call to close())
                    // MUST NOT be done here.
                    Socket clientSocket = serverSocket.accept();

                    // Each new connection needs to be started in a new thread otherwise
                    // it will only service ONE connection (the first connection)
                    Thread t = (new Thread(() -> processNewConnection(clientSocket)));
                    threadList.add(t);
                    t.start();
                }
                catch(Exception ex1){
                    System.out.println(ex1.getMessage());
                    ex1.printStackTrace();
                }

            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    private static void processNewConnection(Socket clientSocket)  {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                out.println(inputLine);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {clientSocket.close();} catch(Exception eIgnore){}

        }
    }
}