package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		HttpSession session=request.getSession(true);
		String xml=(String)session.getAttribute("xml1");
		PrintWriter out=response.getWriter();
 		out.println(xml);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		try{
			String str="";
			HttpClient cli=new DefaultHttpClient();
			HttpGet get=new HttpGet("https://mindapp-pulpy.rhcloud.com/rest/log/check/"+user+"/"+pass);
			HttpResponse res=cli.execute(get);
			BufferedReader bf=new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        	String line="";
        	while((line=bf.readLine())!=null){
        		str+=line;
        	}
        	if(str.charAt(0)=='M' && str.charAt(1)=='P'){
        		HttpSession session=request.getSession();
        		session.setAttribute("id", str);
        		response.sendRedirect("index.jsp");
        	}else if(str.equals("Invalid")){
        		String alert="Invalid Credentials.! Get SignUp with MindPulpy.!";
       	     	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                request.setAttribute("alert", alert); // set your String value in the attribute
                dispatcher.forward( request, response ); 
        	}else{
        		String alert="Sorry for Service Problem.! Login after few minutes.!";
       	     	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                request.setAttribute("alert", alert); // set your String value in the attribute
                dispatcher.forward( request, response ); 
        	}
		}
		catch(Exception e){
			
		}
	}

}
