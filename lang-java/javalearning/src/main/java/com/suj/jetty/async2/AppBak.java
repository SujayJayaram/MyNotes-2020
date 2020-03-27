package com.suj.jetty.async2;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class AppBak
{
    public static void main( String[] args ) throws Exception
    {
        // Create a basic com.suj.jetty server object that will listen on port 8080.
        // Note that if you set this to port 0 then a randomly available port
        // will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(8080);

        // ASYNC SERVLET ******************
        // The ServletHandler is a dead simple way to create a context handler
        // that is backed by an instance of a Servlet.
        // This handler then needs to be registered with the Server object.
        ServletHandler standardServletHandler = new ServletHandler();

        // Passing in the class for the Servlet allows com.suj.jetty to instantiate an
        // instance of that Servlet and mount it on a given context path.
        // IMPORTANT:
        // This is a raw Servlet, not a Servlet that has been configured
        // through a web.xml @WebServlet annotation, or anything similar
        // so we need to do this.
        standardServletHandler.addServletWithMapping(AsyncServlet.class, "/async/*"); // http://localhost:8080/async

        // Jersey ***************************
        ServletContextHandler jerseyServletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletHolder jerseyServletHolder = jerseyServletHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/*");
        jerseyServletHolder.setInitOrder(0);
        jerseyServletHolder.setInitParameter("jersey.config.server.provider.classnames", EntryPoint1.class.getCanonicalName());

        // Start things up!
        HandlerList handlers = new HandlerList();
        // handlers.setHandlers(new Handler[]{standardServletHandler, jerseyServletHandler});
        handlers.setHandlers(new Handler[]{jerseyServletHandler, standardServletHandler});
        server.setHandler(handlers);
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See
        // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();
    }
}