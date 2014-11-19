package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Zoho
 */
@WebServlet("/Zoho")
public class Zoho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Zoho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
	    	String line=null;String res=null;
			StringBuffer str=new StringBuffer();
		            BufferedReader reader = request.getReader();
		            while((line = reader.readLine()) != null){
                          str.append(line);
                          }
		           res=str.toString();
		           String code="";
		          // get code
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		//Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/mpulpy","root","root");
		Connection conn=DriverManager.getConnection("jdbc:mysql://127.12.212.2:33306/bridge", "admin7R9w6e8", "5n4gq2Pz4q_b");
		PreparedStatement pt=conn.prepareStatement("insert into hook(str,count) values('"+res+"','null')");
		pt.execute();
		pt.close();
	    }
	    catch(Exception e){}
	    }	

}
