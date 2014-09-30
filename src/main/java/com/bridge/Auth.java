package com.bridge;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
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
		// TODO Auto-generated method stub
		Connection con=null;  
	   	 response.setHeader("Content-Type","text/html;charset=UTF-8");
    PrintWriter out=response.getWriter();
    String authen=request.getParameter("authen");
    String txt1=request.getParameter("txt1");String txt2=request.getParameter("txt2");
    String txt3=request.getParameter("txt3");String txt4=request.getParameter("txt4");
    String b1=request.getParameter("b1");String b2=request.getParameter("b2");
    String b3=request.getParameter("b3");String b4=request.getParameter("b4");
    String h1=request.getParameter("h1"); String hv1=request.getParameter("hv1");
    String h2=request.getParameter("h2"); String hv2=request.getParameter("hv2");
    String h3=request.getParameter("h3"); String hv3=request.getParameter("hv3");
    String h4=request.getParameter("h4"); String hv4=request.getParameter("hv4");
    String h5=request.getParameter("h5"); String hv5=request.getParameter("hv5");
    String h6=request.getParameter("h6"); String hv6=request.getParameter("hv6");
    String h7=request.getParameter("h7"); String hv7=request.getParameter("hv7");
	  String a1=request.getParameter("a1");String a2=request.getParameter("a2");
	  String select2=request.getParameter("select2"); String ckey=request.getParameter("ckey");
		 HttpSession session=request.getSession();
		 String id=(String) session.getAttribute("apid");
	  String cseckey=request.getParameter("cseckey");String sname=request.getParameter("sname"); String svalue=request.getParameter("svalue");
	  String aurl=request.getParameter("aurl"); String tokenurl=request.getParameter("tokenurl"); String tlabel=request.getParameter("tlabel");
	  String treplace=request.getParameter("treplace"); String el=request.getParameter("el"); String ev=request.getParameter("ev");
    try{
   	 Class.forName("com.mysql.jdbc.Driver").newInstance();
   	 con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
    PreparedStatement st=con.prepareStatement("insert into auth(appid,authen,txt1,txt2,b1,b2,b3,b4,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,h6,hv6,h7,hv7,txt3,txt4,a1,a2,ckey,cseckey,sname,svalue,aurl,tokenurl,tlabel,treplace,el,ev,select2) values ('"+id+"','"+authen+"','"+txt1+"','"+txt2+"','"+b1+"','"+b2+"','"+b3+"','"+b4+"','"+h1+"','"+hv1+"','"+h2+"','"+hv2+"','"+h3+"','"+hv3+"','"+h4+"','"+hv4+"','"+h5+"','"+hv5+"','"+h6+"','"+hv6+"','"+h7+"','"+hv7+"','"+txt3+"','"+txt4+"','"+a1+"','"+a2+"','"+ckey+"','"+cseckey+"','"+sname+"','"+svalue+"','"+aurl+"','"+tokenurl+"','"+tlabel+"','"+treplace+"','"+el+"','"+ev+"','"+select2+"')");				 
   	 st.executeUpdate();
   	 out.println("<html style='background-color:#ff9900;'><h2><center><font color='#000000;'>Processing...</font></center></h3><br><br><br><br>"
         		+ "<br><br><br><br><center><img style='height:100px;width:100px;' src='images/load.gif'></center><html>");
		     response.setHeader("Refresh", "1; URL=trigger.jsp");
    
    }
    catch(Exception e){
   	 out.println(e);
    }
	}

}
