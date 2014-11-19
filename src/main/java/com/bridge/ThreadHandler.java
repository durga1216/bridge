package com.bridge;

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
 * Servlet implementation class ThreadHandler
 */
@WebServlet("/ThreadHandler")
public class ThreadHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String tempid=request.getParameter("tempid");
		String time=request.getParameter("time");
		String state=request.getParameter("state");
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
			if(state.equals("dummy") && !time.equals("dummy")){
				PreparedStatement ps=con.prepareStatement("update home set time='"+time+"' where  tempid='"+tempid+"'");
				ps.executeUpdate();
			}else if(!state.equals("dummy") && time.equals("dummy")){
				PreparedStatement ps=con.prepareStatement("update home set state='"+state+"' where  tempid='"+tempid+"'");
				ps.executeUpdate();
			}else if(!state.equals("dummy") && !time.equals("dummy")){
				PreparedStatement ps=con.prepareStatement("update home set state='"+state+"',time='"+time+"' where  tempid='"+tempid+"'");
				ps.executeUpdate();
			}
			response.sendRedirect("final.jsp");
		}catch(Exception e){
			out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
