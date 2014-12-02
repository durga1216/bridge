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
import javax.servlet.http.HttpSession;

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
		String XmlString=request.getParameter("data");
		try{
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	Connection conn=DriverManager.getConnection(Util.url,Util.user,Util.pass);
	    	PreparedStatement ps=conn.prepareStatement("insert into hook (str) values ('"+XmlString+"')");
	    	ps.executeUpdate();
		}
		catch(Exception e){
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String XmlString=request.getParameter("data");
		try{
			StringBuffer jb = new StringBuffer();
			String line = null;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null){
				jb.append(line);
			}
	    	String res=jb.toString();
	    	HttpSession session=request.getSession(true);
	    	session.setAttribute("xml1", res+"\n"+XmlString);
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	Connection conn=DriverManager.getConnection(Util.url,Util.user,Util.pass);
	    	PreparedStatement ps=conn.prepareStatement("insert into hook (str) values ('"+res+"')");
	    	ps.executeUpdate();
		}
		catch(Exception e){
			
		}
	}
}
