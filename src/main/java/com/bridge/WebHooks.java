package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WebHooks
 */
@WebServlet("/WebHooks")
public class WebHooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebHooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("<h1>Documentation:</h1><br>");
		out.println("");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try{
			StringBuffer jb = new StringBuffer();
			String line = null;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null){
				jb.append(line);
			}
	    	String res=jb.toString();
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn=DriverManager.getConnection(Util.url,Util.user,Util.pass);
	    	PreparedStatement ps=conn.prepareStatement("insert into hook (str) values ('"+res+"')");
	    	ps.executeUpdate();
		}
		catch(Exception e){
			
		}
	}

}
