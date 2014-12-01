package com.bridge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MethodStorage
 */
@WebServlet("/MethodStorage")
public class MethodStorage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MethodStorage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
	    String app=request.getParameter("app");
	    String app1=request.getParameter("app1");
	    String tgmeth="";String actmeth="";
	    String aid="";String tid="";String tgtit="";String actit="";int code=0;int code1=0;
	    Connection con;
	    try{
			   Class.forName("com.mysql.jdbc.Driver").newInstance();
			   con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
			 /*  PreparedStatement st=con.prepareStatement("insert into home(tid,aid,tgmeth,actmeth) values ('"+app+"','"+app1+"','"+tgmeth+"','"+actmeth+"')");
	           st.executeUpdate();
	           st.close();*/
	           
	           PreparedStatement st2=con.prepareStatement("select * from title t1 JOIN triger t2 ON t1.appid=t2.appid where t1.appid=?");
	           st2.setString(1, app);
	           ResultSet rs2=st2.executeQuery();
	           while(rs2.next()){
	        	   tid=rs2.getString("appid");
	        	   tgtit=rs2.getString("tit");
	        	   tgmeth=rs2.getString("t1");
	           }
	           st2.close();
	           PreparedStatement st3=con.prepareStatement("select * from title t1 JOIN triger t2 ON t1.appid=t2.appid where t1.appid=?");
	           st3.setString(1, app1);
	           ResultSet rs3=st3.executeQuery();
	           while(rs3.next()){
	        	   aid=rs3.getString("appid");
	        	   actit=rs3.getString("tit");
	        	   actmeth=rs3.getString("t1");
	           }
	           st3.close();
	           
	           PreparedStatement st=con.prepareStatement("insert into home(userid,tid,aid,tgtit,actit,tgmeth,actmeth,state,time) values ('"+id+"','"+tid+"','"+aid+"','"+tgtit+"','"+actit+"','"+tgmeth+"','"+actmeth+"','Active','15')");
	           st.executeUpdate();
	           st.close();
	           request.setAttribute("code1", code1);
	           request.setAttribute("code", code);
	               request.getRequestDispatcher("check.jsp").forward(request, response);
	          
	          
	           
	    }
	    catch(Exception e){out.println(e);}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
