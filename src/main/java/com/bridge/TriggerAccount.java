package com.bridge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

@WebServlet("/TriggerAccount")
public class TriggerAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TriggerAccount() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
	  //  String appid=request.getParameter("select1");
		
		String appid="MB018";
	    Connection con;
		   try{
			   Class.forName("com.mysql.jdbc.Driver").newInstance();
			   con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
			   PreparedStatement st=con.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 on t1.appid=t3.appid where t1.appid=?");
			   st.setString(1, appid);
			   ResultSet rs=st.executeQuery();
			   while(rs.next()){ 
				   String tit=rs.getString("tit");
				   
				    //auth
				   String authen=rs.getString("authen");String b1=rs.getString("b1");String b2=rs.getString("b2");
					String b3=rs.getString("b3");String b4=rs.getString("b4");String h1=rs.getString("h1");String hv1=rs.getString("hv1");
					String h2=rs.getString("h2");String hv2=rs.getString("hv2");String h3=rs.getString("h3");String hv3=rs.getString("hv3");
					String h4=rs.getString("h4");String hv4=rs.getString("hv4");String h5=rs.getString("h5");String hv5=rs.getString("hv5");
					String h6=rs.getString("h6");String hv6=rs.getString("hv6");String h7=rs.getString("h7");String hv7=rs.getString("hv7");
					String a1=rs.getString("a1");String a2=rs.getString("a2");String ckey=rs.getString("ckey");String cseckey=rs.getString("cseckey");
					String sname=rs.getString("sname");String svalue=rs.getString("svalue");String aurl=rs.getString("aurl");String tokenurl=rs.getString("tokenurl");
					String tlabel=rs.getString("tlabel");String treplace=rs.getString("treplace");String el=rs.getString("el");String ev=rs.getString("ev");
					String select2=rs.getString("select2");
					
					// triger
					
					String name=rs.getString("name");String t1=rs.getString("t1");
					String p1=rs.getString("p1");String pv1=rs.getString("pv1");String p2=rs.getString("p2");String pv2=rs.getString("pv2");
					String p3=rs.getString("p3");String pv3=rs.getString("pv3");	String p4=rs.getString("p4");String pv4=rs.getString("pv4");
					String p5=rs.getString("p5");String pv5=rs.getString("pv5");	String p6=rs.getString("p6");String pv6=rs.getString("pv6");
					String p7=rs.getString("p7");String pv7=rs.getString("pv7"); 	String p8=rs.getString("p8");String pv8=rs.getString("pv8");
					String p9=rs.getString("p9");String pv9=rs.getString("pv9");	String p10=rs.getString("p10");String pv10=rs.getString("pv10");
					String p11=rs.getString("p11");String pv11=rs.getString("pv11");	String p12=rs.getString("p12");String pv12=rs.getString("pv12");
					String p13=rs.getString("p13");String pv13=rs.getString("pv13");	String p14=rs.getString("p14");String pv14=rs.getString("pv14");
					String p15=rs.getString("p15");String pv15=rs.getString("pv15");	String p16=rs.getString("p16");String pv16=rs.getString("pv16");
					String p17=rs.getString("p17");String pv17=rs.getString("pv17");	String p18=rs.getString("p18");String pv18=rs.getString("pv18");
					String p19=rs.getString("p19");String pv19=rs.getString("pv19");	String p20=rs.getString("p20");String pv20=rs.getString("pv20");

				   out.println(authen);
					
					
			   }
		   }
		   catch(Exception e){
			   out.println(e);
		   }
	}

}
