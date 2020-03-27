package com.suj.jetty.async2;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AsyncServlet extends HttpServlet
{
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