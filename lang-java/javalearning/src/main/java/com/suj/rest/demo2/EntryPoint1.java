package com.suj.rest.demo2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// http://localhost:8080/api/pt1/test1
@Path("/")
public class EntryPoint1 {

    @GET
    @Path("test1")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test Entry Pt 1";
    }
}