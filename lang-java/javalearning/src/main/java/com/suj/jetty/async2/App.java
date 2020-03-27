package com.suj.jetty.async2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

// This class shows how to create a servlet with one standard (async) servlet then two jersey classes as well
public class App
{
    public static void main( String[] args ) throws Exception
    {
        // Create a basic com.suj.jetty server object that will listen on port 8080.
        // Note that if you set this to port 0 then a randomly available port
        // will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(8080);

        // Jersey ***************************
        ServletContextHandler jerseyServletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        {
            ServletHolder jerseyServletHolder = jerseyServletHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/pt1/*");
            jerseyServletHolder.setInitOrder(0);
            jerseyServletHolder.setInitParameter("jersey.config.server.provider.classnames", EntryPoint1.class.getCanonicalName()); // localhost:8080/api/pt1/zzz/test1
        }
        {
            ServletHolder jerseyServletHolder2 = jerseyServletHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/pt2/*");
            jerseyServletHolder2.setInitOrder(0);
            jerseyServletHolder2.setInitParameter("jersey.config.server.provider.classnames", EntryPoint2.class.getCanonicalName()); // localhost:8080/api/pt2/zzz/test2
        }
        // Normal com.suj.jetty servlet **************
        jerseyServletHandler.addServlet(AsyncServlet.class, "/async/*"); // localhost:8080/async


        // Start things up!
        server.setHandler(jerseyServletHandler);
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See
        // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();
    }
}