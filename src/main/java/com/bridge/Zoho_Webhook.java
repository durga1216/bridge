package com.bridge;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/Zoho_Webhook")
public class Zoho_Webhook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Zoho_Webhook() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Connection conn=null;
		//String Tid="TMP_004";
		String zoho=request.getParameter("zoho_webhook");
		out.println(zoho);
	
	}

}
