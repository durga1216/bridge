package com.bridge;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/webhook")
public class Resthook {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response addPlainText(@PathParam("id") String id,@QueryParam("result") String result) {
    	String result11="test---"+id+"---"+result;
    	return Response.ok(result11)
    			.header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
    			.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
}

