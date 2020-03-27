package com.suj.rest.demo2;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// http://localhost:8080/api/pt2/test2
@Path("/")
public class EntryPoint2 {

    @GET
    @Path("test2")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test Entry Pt 2";
    }

    @POST
    @Path("/sendemail")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendEmail(@FormParam("email") String email) {
        System.out.println(email);
        return Response.ok("email=" + email).build();
    }
}