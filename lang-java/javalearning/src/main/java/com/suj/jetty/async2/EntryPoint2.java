package com.suj.jetty.async2;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// http://http://localhost:8080/api/pt2/zzz/test2
@Path("/zzz")
public class EntryPoint2 {

    @GET
    @Path("test2")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test Entry Pt 2";
    }
}
