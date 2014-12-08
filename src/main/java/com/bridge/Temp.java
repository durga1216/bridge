package com.bridge;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Temp
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/Temp")
public class Temp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Temp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		String sample=(String)session.getAttribute("str");
		String msg=(String)session.getAttribute("msg");
		String secret=(String)session.getAttribute("sigskey");

		PrintWriter out=response.getWriter();
		out.println(sample);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setHeader("Content-Type","text/html;charset=UTF-8");
//		String no=request.getParameter("no");
//		InputStream inputStream=null;
//   Part filePart = request.getPart("logo"); 
//	if (filePart != null) {  
//   inputStream = filePart.getInputStream();}
//	Connection con=null;
//	PrintWriter out=response.getWriter();
//	out.println("came");
//	try
//	{
//		Class.forName("com.mysql.jdbc.Driver").newInstance();
//       con = (Connection) DriverManager.getConnection(Util.url,Util.user,Util.pass);
//        PreparedStatement st=null;
//		 st=con.prepareStatement("update title set img=? where appid=?");
//		 if (inputStream != null) {  
//		        st.setBlob(1, inputStream);  
//		}
//		 st.setString(2, no);
//		 st.executeUpdate();
//		 st.close();
//	     out.println("<br><br><br><br><html><body bgcolor='#FF9900'><center><h2 style='color:#ffffff'>UPDATE SUCCESSFULLY</h2></center></body></html>");
//		 response.setHeader("Refresh", "1; URL=parse.jsp");
//		
//	}
//	catch(Exception e)
//	{
//		out.println(e);
//	}
//	
	}

}
