package com.suj.jetty.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sujayjayaram on 13/02/2016.
 */
final class MyStandardDataStream implements WriteListener
{
    private final InputStream content;
    private final AsyncContext async;
    private final ServletOutputStream out;

    public MyStandardDataStream(InputStream content, AsyncContext async, ServletOutputStream out)
    {
        this.content = content;
        this.async = async;
        this.out = out;
    }

    public void onWritePossible() throws IOException
    {
        byte[] buffer = new byte[4096];

        // while we are able to write without blocking
        // Instead of the input stream here, we could have stored a Future representing a piece of work
        // which we would check to see if its ready on it's side too.
        while(out.isReady())
        {
            // read some content into the copy buffer
            int len=content.read(buffer);

            // If we are at EOF then complete</span>
            if (len < 0)
            {
                async.complete();
                return;
            }

            // write out the copy buffer.
            out.write(buffer,0,len);
        }
    }

    public void onError(Throwable t)
    {
        // getServletContext().log("Async Error",t);
        async.complete();
    }
}
