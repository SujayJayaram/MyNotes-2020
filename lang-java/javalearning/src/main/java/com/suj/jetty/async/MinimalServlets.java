package com.suj.jetty.async;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class MinimalServlets
{
    public static void main( String[] args ) throws Exception
    {
        // Create a basic com.suj.jetty server object that will listen on port 8080.
        // Note that if you set this to port 0 then a randomly available port
        // will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(8080);

        // The ServletHandler is a dead simple way to create a context handler
        // that is backed by an instance of a Servlet.
        // This handler then needs to be registered with the Server object.
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        // Passing in the class for the Servlet allows com.suj.jetty to instantiate an
        // instance of that Servlet and mount it on a given context path.

        // IMPORTANT:
        // This is a raw Servlet, not a Servlet that has been configured
        // through a web.xml @WebServlet annotation, or anything similar.
        handler.addServletWithMapping(HelloServlet.class, "/*");

        // Start things up!
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See
        // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();
    }

    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet
    {
        //@Override
        /*
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
        */

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException
        {

            String info=request.getPathInfo();
            response.setContentType(getServletContext().getMimeType(info));

            //InputStream content = getServletContext().getResourceAsStream(info);
            //if (content==null)
            //{
            //    response.sendError(404);
            //   return;
            //}

            // Even though this is written here, the whole page is diosplayed in one go after the async part.
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");

            AsyncContext async = request.startAsync();
            Runnable r = () -> {
                try {
                    Thread.currentThread().sleep(5000);
                    response.setContentType("text/html");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println("<h1>Bye bye from HelloServlet</h1>");
                    async.complete();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            };

            (new Thread(r)).start();

            // We have allowed the processing to be async, now if we want to allow the I/O to be async then implement the below....
            //ServletOutputStream out = response.getOutputStream();
            //out.setWriteListener(new MyStandardDataStream(content,async,out));
        }
    }
}