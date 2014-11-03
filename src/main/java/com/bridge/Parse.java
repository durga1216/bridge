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

/**
 * Servlet implementation class Parse
 */
@WebServlet("/Parse")
public class Parse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parse() {
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
//		PrintWriter out=response.getWriter();
//		String ptag=request.getParameter("ptag");
//		String exres=request.getParameter("exres");
//		String[] x=new String[11];
//		String tempid="";String tid="";String aid="";String tgtit="";String actit="";
//		for(int i=1;i<11;i++){
//			x[i]=request.getParameter("x"+i);
//		}
//		try{
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
//			PreparedStatement ps=con.prepareStatement("select * from home order by tempid desc limit 1");
//			ResultSet rs=ps.executeQuery();
//			while(rs.next()){
//				tempid=rs.getString("tempid");
// 	   			tid=rs.getString("tid");
// 	   			aid=rs.getString("aid");
// 	   			tgtit=rs.getString("tgtit");
// 	   			actit=rs.getString("actit");
//			}
//			PreparedStatement ps1=con.prepareStatement("insert into parse (tempid,tid,aid,ptag,exres,x1,x2,x3,x4,x5,x6,x7,x8,x9,x10) values('"+tempid+"','"+tid+"','"+aid+"','"+ptag+"','"+exres+"','"+x[1]+"','"+x[2]+"','"+x[3]+"','"+x[4]+"','"+x[5]+"','"+x[6]+"','"+x[7]+"','"+x[8]+"','"+x[9]+"','"+x[10]+"')");
//			ps1.executeUpdate();
//			response.sendRedirect(request.getContextPath()+"/Final");
//		}catch(Exception e){
//			out.println(e);
//		}
		Connection con=null;  
	   	response.setHeader("Content-Type","text/html;charset=UTF-8");
	   	PrintWriter out=response.getWriter();
	   	System.out.println("test");
	   	String appname="";
	   	String descr=""; 
	   	String mode="";
		appname=request.getParameter("app1");
		descr=request.getParameter("descr");
		mode=request.getParameter("mode");
		String selectFile=request.getParameter("selectFile");
		out.println(appname+"---"+descr+"----"+mode+"-----"+selectFile);
	}

}
