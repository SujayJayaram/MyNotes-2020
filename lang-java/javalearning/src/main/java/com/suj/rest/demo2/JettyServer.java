package com.suj.rest.demo2;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by sujayjayaram on 13/02/2016.
 */
public class JettyServer {
    public static void main(String[] args) throws Exception {
        // Create a basic Jetty server object that will listen on port 8080.  Note that if you set this to port 0
        // then a randomly available port will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(8080);

        // Create the ResourceHandler. It is the object that will actually handle the request for a given file. It is
        // a Jetty Handler object so it is suitable for chaining with other handlers as you will see in other examples.
        ResourceHandler resource_handler = new ResourceHandler();

        // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
        // In this example it is the current directory but it can be configured to anything that the jvm has access to.
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{"./html/index.html"});
        resource_handler.setResourceBase(".");

        //Jersey ServletContextHandler1
        ServletContextHandler servletContextHandler1 = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletHolder jerseyServlet1 = servletContextHandler1.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/pt1/*");
        jerseyServlet1.setInitOrder(0);
        jerseyServlet1.setInitParameter("jersey.config.server.provider.classnames", EntryPoint1.class.getCanonicalName());

        // **** Use the same servletContextHandler1 class!!!!
        // Each servlet must map to  different url though - e.g. api2
        ServletHolder jerseyServlet2 = servletContextHandler1.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/pt2/*");
        jerseyServlet2.setInitOrder(0);
        jerseyServlet2.setInitParameter("jersey.config.server.provider.classnames", EntryPoint2.class.getCanonicalName());

        // Add the ResourceHandler to the server.
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, servletContextHandler1, new DefaultHandler()});
        server.setHandler(handlers);

        // Start things up! By using the server.join() the server thread will join with the current thread.
        // See "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()" for more details.
        server.start();
        server.join();
    }
}