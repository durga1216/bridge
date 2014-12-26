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
		PrintWriter out=response.getWriter();
		String ptag=request.getParameter("ptag");
		String exres=request.getParameter("exres");
		String sheet=request.getParameter("sheet");
		String parpol=request.getParameter("parpol");
		String unipol=request.getParameter("unipol");
		String resf=request.getParameter("resf");
		String c1=request.getParameter("c1");String cv1=request.getParameter("cv1");
		String c2=request.getParameter("c2");String cv2=request.getParameter("cv2");
		String c3=request.getParameter("c3");String cv3=request.getParameter("cv3");
		String c4=request.getParameter("c4");String cv4=request.getParameter("cv4");
		String c5=request.getParameter("c5");String cv5=request.getParameter("cv5");
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
        String tempid=(String)session.getAttribute("tempid");
		String[] x=new String[21];
		String[] xx=new String[21];
		String tid="";String aid="";String tgtit="";String actit="";String type="";
		for(int i=1;i<21;i++){
			x[i]=request.getParameter("x"+i);
		}
		for(int i=1;i<21;i++){
			xx[i]=request.getParameter("xx"+i);
		}
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
			PreparedStatement ps=con.prepareStatement("select * from home where tempid='"+tempid+"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				tempid=rs.getString("tempid");
 	   			tid=rs.getString("tid");
 	   			aid=rs.getString("aid");
 	   			tgtit=rs.getString("tgtit");
 	   			actit=rs.getString("actit");
 	   			type=rs.getString("type");
			}
			PreparedStatement ps1=con.prepareStatement("insert into parse (tempid,tid,aid,ptag,exres,resf,x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,x14,x15,x16,x17,x18,x19,x20,shname,xx1,xx2,xx3,xx4,xx5,xx6,xx7,xx8,xx9,xx10,xx11,xx12,xx13,xx14,xx15,xx16,xx17,xx18,xx19,xx20,parpol,unipol,c1,cv1,c2,cv2,c3,cv3,c4,cv4,c5,cv5) values('"+tempid+"','"+tid+"','"+aid+"','"+ptag+"','"+exres+"','"+resf+"','"+x[1]+"','"+x[2]+"','"+x[3]+"','"+x[4]+"','"+x[5]+"','"+x[6]+"','"+x[7]+"','"+x[8]+"','"+x[9]+"','"+x[10]+"','"+x[11]+"','"+x[12]+"','"+x[13]+"','"+x[14]+"','"+x[15]+"','"+x[16]+"','"+x[17]+"','"+x[18]+"','"+x[19]+"','"+x[20]+"','"+sheet+"','"+xx[1]+"','"+xx[2]+"','"+xx[3]+"','"+xx[4]+"','"+xx[5]+"','"+xx[6]+"','"+xx[7]+"','"+xx[8]+"','"+xx[9]+"','"+xx[10]+"','"+xx[11]+"','"+xx[12]+"','"+xx[13]+"','"+xx[14]+"','"+xx[15]+"','"+xx[16]+"','"+xx[17]+"','"+xx[18]+"','"+xx[19]+"','"+xx[20]+"','"+parpol+"','"+unipol+"','"+c1+"','"+cv1+"','"+c2+"','"+cv2+"','"+c3+"','"+cv3+"','"+c4+"','"+cv4+"','"+c5+"','"+cv5+"')");
			ps1.executeUpdate();
			
			if(type.equals("polling"))
				response.sendRedirect(request.getContextPath()+"/Polling?temp="+tempid);
			else
				response.sendRedirect(request.getContextPath()+"/Final?temp="+tempid);
			
		}catch(Exception e){
			out.println(e);
		}
		
	}

}
