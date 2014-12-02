package com.bridge;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/get")
public class Restimg {
    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("/appid")
    public Response addPlainText(@PathParam("id") String id) {
    	String result11="<?xml version='1.0'?><id>";
    	try{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
		    final Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
		    PreparedStatement ps=con.prepareStatement("select * from title");
	    	ResultSet rs=ps.executeQuery();
	    	while(rs.next()){
	    		String appid=rs.getString("appid");
	    		String mode=rs.getString("mode");
	    		result11+="<root><appid>"+appid+"</appid><mode>"+mode+"</mode></root>";
	    	}
	    	result11+="</id>";
    	}catch(Exception e){
    		
    	}
    	return Response.ok(result11)
    			.header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
    			.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
}

