package com.suj.rest.asyncbad;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 * Created by sujayjayaram on 13/02/2016.
 * **** WON'T WORK AS WE NEED A SERVLET 3.0 CONTAINER
 */
// http://localhost:8080/entry-point/test
@Path("/entry-point")
public class AsyncResource {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public void asyncGet(@Suspended final AsyncResponse asyncResponse) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = veryExpensiveOperation();
                asyncResponse.resume(result);
            }

            private String veryExpensiveOperation() {
                // ... very expensive operation

                try {
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Hello";
            }
        }).start();
    }
}