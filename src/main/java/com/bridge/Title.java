package com.bridge;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class Title
 */
public class Title extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String folderLocation=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override  
	 public void init() throws ServletException {  
	  super.init();  
	  this.folderLocation = getServletContext().getInitParameter("FILE");  
	 } 
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
		catch(Exception e){
			e.printStackTrace();
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
	   	String appname="";
	   	String descr=""; 
	   	String mode="";
	   	System.out.println("teggst"+appname+"--"+descr);
	   	InputStream is = null;
	   	HashMap<String, String> formParams = new HashMap<String, String>();  
 		boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
 		System.out.println("valkkue"+isMultipart);
 		try{  
 			if (isMultipart){  
 				System.out.println("inside part");
 				FileItemFactory factory = new DiskFileItemFactory();  
 				ServletFileUpload upload = new ServletFileUpload(factory);    
 				List<FileItem> items = upload.parseRequest(request);  
 				Iterator<FileItem> iter = items.iterator();  
 				int k=0;
 				while (iter.hasNext()) {  
 					k++;
 					out.println("inside");
 					appname=iter.next().getName();
 					descr=iter.next().getName();
 					FileItem item = (FileItem) iter.next();  
 					is=item.getInputStream();
 					mode=iter.next().getName();
 					out.println("calue---"+k+appname+descr+mode);
 				}
 			}
		}catch(FileUploadException fue){  
			System.out.println(fue);  
			throw new ServletException(fue.getMessage());
		}
 		try{
 			appname=request.getParameter("app1");
 			descr=request.getParameter("descr");
 			mode=request.getParameter("mode");
 			out.println(appname+"---"+descr+"----"+mode);
 			HttpSession session=request.getSession();
 			Class.forName("com.mysql.jdbc.Driver").newInstance();
 			con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
 			PreparedStatement st=con.prepareStatement("insert into title (tit,des,img,mode) values ('"+appname+"','"+descr+"',?,'"+mode+"')");
 			st.setBlob(1, is);
 			st.executeUpdate();
 			PreparedStatement st1=con.prepareStatement("select * from title ORDER BY appid DESC LIMIT 1");
 			ResultSet rs=st1.executeQuery();
 			while(rs.next()){
 				String apid=rs.getString("appid");
 				session.setAttribute("apid", apid);
 			}
 			//out.println("<html style='background-color:#ff9900;'><h2><center><font color='#000000;'>Processing...</font></center></h3><br><br><br><br>"
 				//	+ "<br><br><br><br><center><img style='height:100px;width:100px;' src='images/load.gif'></center><html>");
	     	//response.setHeader("Refresh", "1; URL=auth.jsp");
		}catch(Exception e){
 			out.println(e);
 		}
	}

}
