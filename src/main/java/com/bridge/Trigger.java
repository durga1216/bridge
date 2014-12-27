package com.bridge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
@WebServlet("/Trigger")
public class Trigger extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Trigger() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
	Connection con=null;  
  	response.setHeader("Content-Type","text/html;charset=UTF-8");
    PrintWriter out=response.getWriter();
    String appid=(String)session.getAttribute("apid");String js=request.getParameter("js");
    String f1=request.getParameter("f1");String f2=request.getParameter("f2");
    String f3=request.getParameter("f3");String f4=request.getParameter("f4");
    String f5=request.getParameter("f5");String f6=request.getParameter("f6");
    String f7=request.getParameter("f7");String f8=request.getParameter("f8");
    String f9=request.getParameter("f9");String f10=request.getParameter("f10");

    String name=request.getParameter("name");String t1=request.getParameter("t1");
    String p1=request.getParameter("p1");String pv1=request.getParameter("pv1");
    String p2=request.getParameter("p2");String pv2=request.getParameter("pv2");
    String p3=request.getParameter("p3");String pv3=request.getParameter("pv3");
    String p4=request.getParameter("p4");String pv4=request.getParameter("pv4");
    String p5=request.getParameter("p5");String pv5=request.getParameter("pv5");
    String p6=request.getParameter("p6");String pv6=request.getParameter("pv6");
    String p7=request.getParameter("p7");String pv7=request.getParameter("pv7");
    String p8=request.getParameter("p8");String pv8=request.getParameter("pv8");
    String p9=request.getParameter("p9");String pv9=request.getParameter("pv9");
    String p10=request.getParameter("p10");String pv10=request.getParameter("pv10");
    String p11=request.getParameter("p11");String pv11=request.getParameter("pv11");
    String p12=request.getParameter("p12");String pv12=request.getParameter("pv12");
    String p13=request.getParameter("p13");String pv13=request.getParameter("pv13");
    String p14=request.getParameter("p14");String pv14=request.getParameter("pv14");
    String p15=request.getParameter("p15");String pv15=request.getParameter("pv15");
    String p16=request.getParameter("p16");String pv16=request.getParameter("pv16");
    String p17=request.getParameter("p17");String pv17=request.getParameter("pv17");
    String p18=request.getParameter("p18");String pv18=request.getParameter("pv18");
    String p19=request.getParameter("p19");String pv19=request.getParameter("pv19");
    String p20=request.getParameter("p20");String pv20=request.getParameter("pv20");
    String rmethod=request.getParameter("rmethod");String rformat=request.getParameter("rformat");
    String resformat=request.getParameter("resformat");
    String note=request.getParameter("note");
    

    try{
      	 Class.forName("com.mysql.jdbc.Driver").newInstance();
      	 con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
         PreparedStatement st=con.prepareStatement("insert into triger(appid,name,t1,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,p8,pv8,p9,pv9,p10,pv10,p11,pv11,p12,pv12,p13,pv13,p14,pv14,p15,pv15,p16,pv16,p17,pv17,p18,pv18,p19,pv19,p20,pv20,rmethod,rformat,resformat,note,js,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10) values ('"+appid+"','"+name+"','"+t1+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+p8+"','"+pv8+"','"+p9+"','"+pv9+"','"+p10+"','"+pv10+"','"+p11+"','"+pv11+"','"+p12+"','"+pv12+"','"+p13+"','"+pv13+"','"+p14+"','"+pv14+"','"+p15+"','"+pv15+"','"+p16+"','"+pv16+"','"+p17+"','"+pv17+"','"+p18+"','"+pv18+"','"+p19+"','"+pv19+"','"+p20+"','"+pv20+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+note+"','"+js+"','"+f1+"','"+f2+"','"+f3+"','"+f4+"','"+f5+"','"+f6+"','"+f7+"','"+f8+"','"+f9+"','"+f10+"')");				 
         st.executeUpdate();
      	 st.close();
      	 out.println("<html style='background-color:#ff9900;'><h2><center><font color='#000000;'>Processing...</font></center></h3><br><br><br><br>"
            		+ "<br><br><br><br><center><img style='height:100px;width:100px;' src='images/load.gif'></center><html>");
         if (request.getParameter("new") != null) {

   		     response.setHeader("Refresh", "1; URL=trigger.jsp");
         }
         else if(request.getParameter("finish")!=null){
         	response.sendRedirect("finish.jsp");
         }
       }
       catch(Exception e){
      	 out.println(e);
       }
    
	}

}
