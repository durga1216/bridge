package com.bridge;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
/**
 * Servlet implementation class Title
 */
@MultipartConfig(maxFileSize = 16177215)
public class Title extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Title() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String logo=request.getParameter("appid");
		Connection con=null;
		BufferedImage buffimg = null;PrintWriter out=response.getWriter();
			// response.setHeader("Content-Type","text/html;charset=UTF-8");
			  String imgLen="";
		out.println("<div>");
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
			PreparedStatement ps=con.prepareStatement("select * from title where appid=?");
		ps.setString(1, logo);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			  imgLen = rs.getString("img").toString();
			int len = imgLen.length();
			  byte [] rb = new byte[len];
			  InputStream readImg = rs.getBinaryStream("img");
			  int index=readImg.read(rb, 0, len);  
			  response.reset();
			  response.setContentType("image/jpg");
			  response.getOutputStream().write(rb,0,len);
			  response.getOutputStream().flush(); 
		}
		}
		catch(Exception e)
		{ e.printStackTrace();
			}

    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;  
	   	 response.setHeader("Content-Type","text/html;charset=UTF-8");
   PrintWriter out=response.getWriter();
   System.out.println("test");
   String appname=request.getParameter("app1"); 
   String descr=request.getParameter("descr");  
   String mode=request.getParameter("mode");
   System.out.println("test"+appname+"--"+descr);
   InputStream inputStream=null;
   //Part filePart = request.getPart("logo"); 
   String filePart =request.getParameter("logo");
		if (filePart != null) {  
  // inputStream = filePart.getInputStream();
			}
	 HttpSession session=request.getSession();
   try{
  	 Class.forName("com.mysql.jdbc.Driver").newInstance();
  	 con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
  	 PreparedStatement st=con.prepareStatement("insert into title (tit,des,img,mode) values ('"+appname+"','"+descr+"',?,'"+mode+"')");
  	 if (filePart != null) {  
  	 st.setBlob(1, inputStream);}
  	 st.executeUpdate();
  	 PreparedStatement st1=con.prepareStatement("select * from title ORDER BY appid DESC LIMIT 1");
  	 ResultSet rs=st1.executeQuery();
  	 while(rs.next()){
  		 String apid=rs.getString("appid");
  		 session.setAttribute("apid", apid);
  	 }
  	 out.println("<html style='background-color:#ff9900;'><h2><center><font color='#000000;'>Processing...</font></center></h3><br><br><br><br>"
        		+ "<br><br><br><br><center><img style='height:100px;width:100px;' src='images/load.gif'></center><html>");
		     response.setHeader("Refresh", "1; URL=auth.jsp");
   
   }
   catch(Exception e){
  	 out.println(e);
   }
	}

}
