package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


@WebServlet("/TriggerAuth")
public class TriggerAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TriggerAuth() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*	PrintWriter out=response.getWriter();
		try{
		URL url = new URL ("https://minddotss.freshservice.com/helpdesk/tickets.json");
	
    String encoding = new String(
   		 org.apache.commons.codec.binary.Base64.encodeBase64   
   		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8("QquZIyp23YptsoWpBO7P:X"))
   		  );
   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  HttpRequest httprequest;
   
   connection.setRequestMethod("GET");
   connection.setDoOutput(true);
   connection.setRequestProperty  ("Authorization", "Basic " + encoding);
   InputStream content = (InputStream)connection.getInputStream();
   BufferedReader in   = 
       new BufferedReader (new InputStreamReader (content));
  
   String line=null;String res="";
   while((line=in.readLine())!=null){
	   res+=line;
   }
   String id="";
   JSONArray array=new JSONArray(res);
   for(int i=0;i<array.length();i++){
	   JSONObject obj=array.getJSONObject(i);
	    id=obj.getString("id");

   }
   out.println(res);

		}
	catch(Exception e){}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Connection con=null;  
	   	 response.setHeader("Content-Type","text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String tempid=(String)session.getAttribute("tempid");
        String tid=(String)session.getAttribute("tid");  
        String aid=(String)session.getAttribute("aid");
        String action=request.getParameter("submit");
        String dn=request.getParameter("dn");String apkey=request.getParameter("apkey");String b2=request.getParameter("uname");String b4=request.getParameter("pwd");
        String dn1=request.getParameter("dn1");String pa1=request.getParameter("p1");String pa2=request.getParameter("pa2");String pa3=request.getParameter("p3");
      /*  String p1=request.getParameter("p1");String p2=request.getParameter("p2");String p3=request.getParameter("p3");
        String p4=request.getParameter("p4");String p5=request.getParameter("p5");String p6=request.getParameter("p6");
        String pv1=request.getParameter("pv1");String pv2=request.getParameter("pv2");String pv3=request.getParameter("pv3");
        String pv4=request.getParameter("pv4");String pv5=request.getParameter("pv5");String pv6=request.getParameter("pv6");
        String p7=request.getParameter("p7");String pv7=request.getParameter("pv7");*/
        String[] tdm={"Test",request.getParameter("tdm1"),request.getParameter("tdm2"),request.getParameter("tdm3"),request.getParameter("tdm4"),request.getParameter("tdm5")};
        String[] adm={"Test",request.getParameter("adm1"),request.getParameter("adm2"),request.getParameter("adm3"),request.getParameter("adm4"),request.getParameter("adm5")};
        out.println(action);
        int code=0;int code1=0;
        try{
	   
	   Class.forName("com.mysql.jdbc.Driver").newInstance();
	   con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
	  	
	   	 
	   	 if(action.equals("Authenticate Trigger")){
	 	
	   		 
	   	 PreparedStatement st1=con.prepareStatement("select * from triger t1 JOIN auth t2 on t1.appid=t2.appid where t1.appid=?");
	   	 st1.setString(1, tid);
	   	 ResultSet rs=st1.executeQuery();
	   	  while(rs.next()){
	   		String authen=rs.getString("authen");String a1=rs.getString("a1");
	   		String t1=rs.getString("t1");String rmethod=rs.getString("rmethod");String rformat=rs.getString("rformat");
	   		String resformat=rs.getString("resformat");String p1=rs.getString("p1");String p2=rs.getString("p2");String p3=rs.getString("p3");
	   		String p4=rs.getString("p4");String p5=rs.getString("p5");String p6=rs.getString("p6");String p7=rs.getString("p7");
	   		String p8=rs.getString("p8");String p9=rs.getString("p9");String p10=rs.getString("p10");
	   		String pv1=rs.getString("pv1");String pv2=rs.getString("pv2");String pv3=rs.getString("pv3");
	   		String pv4=rs.getString("pv4");String pv5=rs.getString("pv5");String pv6=rs.getString("pv6");String pv7=rs.getString("p7");
	   		String pv8=rs.getString("pv8");String pv9=rs.getString("pv9");String pv10=rs.getString("pv10");String b1=rs.getString("b1");
	   		String b3=rs.getString("b3");
	   		String treplace=rs.getString("treplace");String tlabel=rs.getString("tlabel");
	   		String h1=rs.getString("h1"); String hv1=rs.getString("hv1");
	   		String h2=rs.getString("h2"); String hv2=rs.getString("hv2");
	   		String h3=rs.getString("h3"); String hv3=rs.getString("hv3");
	   		String h4=rs.getString("h4"); String hv4=rs.getString("hv4");
	   		String h5=rs.getString("h5"); String hv5=rs.getString("hv5");
	   		String h6=rs.getString("h6"); String hv6=rs.getString("hv6");
	   		String h7=rs.getString("h7"); String hv7=rs.getString("hv7");
	   	 String ckey1=rs.getString("ckey");String cseckey1=rs.getString("cseckey");
    	 String sname1=rs.getString("sname");String svalue1=rs.getString("svalue");
    	 String aurl1=rs.getString("aurl");String tokenurl1=rs.getString("tokenurl");
    	 String tlabel1=rs.getString("tlabel");String treplace1=rs.getString("treplace");
    	 String el1=rs.getString("el");String ev1=rs.getString("ev");String rmethod1=rs.getString("select2");
    
	   		out.println(authen);
           String str="";
           String eurl="null";
	   		if(authen.equals("API keys")){  
	   
	   			 if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
	        		 eurl=t1+"?"+a1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
      		 
      		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
	        		 eurl=t1+"?"+a1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
      		 
      		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
	        		 eurl=t1+"?"+a1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
      		 
      		 else if(!"null".equals(p1) && !"null".equals(p2)){
	        		 eurl=t1+"?"+a1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2;}
      		 
      		 else if(!"null".equals(p1)){
	        		 eurl=t1+"?"+a1+"="+apkey+"&"+p1+"="+pv1;}
	   	   
      		 else if("null".equals(p1))
      			     eurl=t1+"?"+a1+"="+apkey+"&";
      		 
	   			eurl=eurl.replaceAll(" ", "%20"); 
       		 URL eurl1=new URL(eurl);
       		 URLConnection uconn = eurl1.openConnection();
       	     HttpURLConnection conn = (HttpURLConnection) uconn;
       	     conn.connect();
       	     Object content = conn.getContent();
       	     InputStream stream = (InputStream) content;
       	     String line=null;
       	     BufferedReader br=new BufferedReader(new InputStreamReader(stream));
       	     while((line=br.readLine())!=null){
	  	       	 str+=line;
       	     }
       	     out.println(200);
       	     session.setAttribute("xml1", str);
	   			
	   			
	   		}	//api keys
	   			
	   		
	   		else if(authen.equals("Basic Auth")){

	   	    if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
	        		 eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
      		 
      		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
	        		 eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
      		 
      		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
	        		 eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
      		 
      		 else if(!"null".equals(p1) && !"null".equals(p2)){
	        		 eurl=p1+"="+pv1+"&"+p2+"="+pv2;}
      		 
      		 else if(!"null".equals(p1)){
	        		 eurl=p1+"="+pv1;}
	   	   
      		 else if("null".equals(p1)){
      			     eurl="null";
      		 }
			   	String[] slt=t1.split("@@");
		  		int nn=slt.length;String orurl="";
		  		if(!(nn==0)){
		      		for(int i=1,j=1;i<nn;i=i+2,j++){
		      			slt[i]=tdm[j];
		      		}
		      		for(int k=0;k<nn;k++){
		      			orurl=orurl+slt[k];
		      		}
		      		t1=orurl;
		  		}
	   	    	 URL url1;
	              if(!"null".equals(eurl)){

  	               url1 = new URL (t1+"?"+eurl);}
	              else
	                   url1 =new URL(t1);
	              HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                 connection.setDoOutput(true);
                 connection.setDoInput(true);
    	   	     if(rmethod.equals("Get")){
	              connection.setRequestMethod("GET");}
    	   	     else if(rmethod.equals("Post")){		             
    	   	  connection.setRequestMethod("POST");
           	  connection.connect();  
              DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
              wr.writeBytes(eurl);
              wr.flush();
              wr.close();
}
	              String encoding=null;
	            	 if(!"null".equals(b2)&& "null".equals(b4)){
	            		 encoding = new String(
	                    		 org.apache.commons.codec.binary.Base64.encodeBase64   
	                    		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+""))
	                    		  );
	   	              connection.setRequestProperty  ("Authorization", "Basic " + encoding);

	            	 }
	            	 else if(!"null".equals(b4) && "null".equals(b2)){encoding = new String(
       		 org.apache.commons.codec.binary.Base64.encodeBase64   
    		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(""+":"+b4))
    		  );	              
	            	 connection.setRequestProperty  ("Authorization", "Basic " + encoding);
}
	            	 else if(!"null".equals(b2) && !"null".equals(b4)){
	            		 encoding = new String(
	                    		 org.apache.commons.codec.binary.Base64.encodeBase64   
	                    		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+b4))
	                    		  );
	   	              connection.setRequestProperty  ("Authorization", "Basic " + encoding);

	   	            	 } // else if encoding
	            	 else if("null".equals(b2) && "null".equals(b4)){
	            		 encoding=null;
	            	 }

	            	 if(!"".equals(h1) && !"".equals(h2) && !"".equals(h3) && !"".equals(h4) && !"".equals(h5)){
	 	            	connection.setRequestProperty(h1, hv1);connection.setRequestProperty(h2, hv2); connection.setRequestProperty(h3, hv3);connection.setRequestProperty(h4, hv4);connection.setRequestProperty(h5, hv5);  
	 	              }
	 	              else if(!"".equals(h1) && !"".equals(h2) && !"".equals(h3) && !"".equals(h4)){
	 	            	connection.setRequestProperty(h1, hv1);connection.setRequestProperty(h2, hv2); connection.setRequestProperty(h3, hv3);connection.setRequestProperty(h4, hv4);  
	 	              }
	 	              else if(!"".equals(h1) && !"".equals(h2) && !"".equals(h3)){
	 		            	connection.setRequestProperty(h1, hv1);connection.setRequestProperty(h2, hv2); connection.setRequestProperty(h3, hv3);  
	 		              }
	 	              else if(!"".equals(h1) && !"".equals(h2)){
	 		            	connection.setRequestProperty(h1, hv1);connection.setRequestProperty(h2, hv2);  
	 		              }
	 	              else if(!"".equals(h1)){
	 		            	connection.setRequestProperty(h1, hv1);  
	 		              }
	            	  code = connection.getResponseCode();
	            	 out.println(code);
	 	              String line=null;
	 	              InputStream content = (InputStream)connection.getInputStream();
	 	                 BufferedReader in   = new BufferedReader (new InputStreamReader (content));
	 	                    while((line=in.readLine())!=null){
	 	                    	str+=line;
	 	                    }//while
	   	     
	 	                   session.setAttribute("xml1", str);

	   		}//basic auth
	   		
	   		
	            
	        else if(authen.equals("Oauth2")){
        	    session.setAttribute("ckey", ckey1);
        	    session.setAttribute("cseckey", cseckey1);
        	    session.setAttribute("tokenurl", tokenurl1);
        	    session.setAttribute("tempid", tempid);
        	    session.setAttribute("tid", tid);
        	    session.setAttribute("rm1", rmethod1);
        	    session.setAttribute("but", "trig");
	        	if(sname1.equals("") && el1.equals(""))
          		  response.sendRedirect(aurl1+"?redirect_uri=https://bridge-minddotss.rhcloud.com/OauthCall&response_type=code&client_id="+ckey1);
               else if(!sname1.equals("")&& el1.equals(""))
          		  response.sendRedirect(aurl1+"?redirect_uri=https://bridge-minddotss.rhcloud.com/OauthCall&response_type=code&client_id="+ckey1+"&"+sname1+"="+svalue1);
               else if(!sname1.equals("")&& !el1.equals(""))
          		  response.sendRedirect(aurl1+"?redirect_uri=https://bridge-minddotss.rhcloud.com/OauthCall&response_type=code&client_id="+ckey1+"&"+sname1+"="+svalue1+"&"+el1+"="+ev1);
               else if(sname1.equals("")&& !el1.equals(""))
          		  response.sendRedirect(aurl1+"?redirect_uri=https://bridge-minddotss.rhcloud.com/OauthCall&response_type=code&client_id="+ckey1+"&"+el1+"="+ev1);
          	 

	   		}
	   	   
			   	PreparedStatement st2=con.prepareStatement("insert into trig_all (tempid,tid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+tempid+"','"+tid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hv1+"','"+h2+"','"+hv2+"','"+h3+"','"+hv3+"','"+h4+"','"+hv4+"','"+h5+"','"+hv5+"','"+tlabel+"','"+treplace+"')");
			   	st2.executeUpdate();
			   	st2.close();
			   	
			   	request.setAttribute("code", code);
	            request.setAttribute("code1", code1);
		       request.getRequestDispatcher("check.jsp").forward(request, response);	
	   } //while
 } //auth trigger 		  
 
	   	 
	   	 else if("Authenticate Action".equals(action)){
	  		 out.println("Authenticate Action inside");
	   		PreparedStatement st1=con.prepareStatement("select * from triger t1 JOIN auth t2 on t1.appid=t2.appid where t1.appid=?");
		   	 st1.setString(1, aid);
		   	 ResultSet rs=st1.executeQuery();
		   	  while(rs.next()){
		   		String authen=rs.getString("authen");String a1=rs.getString("a1");
		   		String t1=rs.getString("t1");String rmethod=rs.getString("rmethod");String rformat=rs.getString("rformat");
		   		String resformat=rs.getString("resformat");String p1=rs.getString("p1");String p2=rs.getString("p2");String p3=rs.getString("p3");
		   		String p4=rs.getString("p4");String p5=rs.getString("p5");String p6=rs.getString("p6");String p7=rs.getString("p7");
		   		String p8=rs.getString("p8");String p9=rs.getString("p9");String p10=rs.getString("p10");
		   		String pv1=rs.getString("pv1");String pv2=rs.getString("pv2");String pv3=rs.getString("pv3");
		   		String pv4=rs.getString("pv4");String pv5=rs.getString("pv5");String pv6=rs.getString("pv6");String pv7=rs.getString("p7");
		   		String pv8=rs.getString("pv8");String pv9=rs.getString("pv9");String pv10=rs.getString("pv10");String b1=rs.getString("b1");
		   		String b3=rs.getString("b3");
		   		String treplace=rs.getString("treplace");String tlabel=rs.getString("tlabel");
		   		String h1=rs.getString("h1"); String hv1=rs.getString("hv1");
		   		String h2=rs.getString("h2"); String hv2=rs.getString("hv2");
		   		String h3=rs.getString("h3"); String hv3=rs.getString("hv3");
		   		String h4=rs.getString("h4"); String hv4=rs.getString("hv4");
		   		String h5=rs.getString("h5"); String hv5=rs.getString("hv5");
		   		String h6=rs.getString("h6"); String hv6=rs.getString("hv6");
		   		String h7=rs.getString("h7"); String hv7=rs.getString("hv7");
		   		String ckey1=rs.getString("ckey");String cseckey1=rs.getString("cseckey");
		       	 String sname1=rs.getString("sname");String svalue1=rs.getString("svalue");
		       	 String aurl1=rs.getString("aurl");String tokenurl1=rs.getString("tokenurl");
		       	 String tlabel1=rs.getString("tlabel");String treplace1=rs.getString("treplace");
		       	 String el1=rs.getString("el");String ev1=rs.getString("ev");String rmethod1=rs.getString("select2");
	           String str="";
	String eurl="null";
		   		if(authen.equals("API keys")){  
			  		 out.println("api inside");

		   			if(rmethod.equals("Get")){
		   	  		 out.println("get inside");

		   			 if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
		        		 eurl=t1+"?"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
	      		 
	      		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
		        		 eurl=t1+"?"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
	      		 
	      		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
		        		 eurl=t1+"?"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
	      		 
	      		 else if(!"null".equals(p1) && !"null".equals(p2)){
		        		 eurl=t1+"?"+p1+"="+pv1+"&"+p2+"="+pv2;}
	      		 
	      		 else if(!"null".equals(p1)){
		        		 eurl=t1+"?"+p1+"="+pv1;}
		   	   
	      		 else if("null".equals(p1))
	      			     eurl=t1;
	      		 
		   		 eurl=eurl.replaceAll(" ", "%20"); 
	       		 URL eurl1=new URL(eurl);
	       		 URLConnection uconn = eurl1.openConnection();
	       	     HttpURLConnection conn = (HttpURLConnection) uconn;
	       	     conn.connect();
	       	     Object content = conn.getContent();
	       	     InputStream stream = (InputStream) content;
	       	     String line=null; String strcon=null;
	       	     BufferedReader br=new BufferedReader(new InputStreamReader(stream));
	       	     StringBuilder strb=new StringBuilder();
	       	     code1=conn.getResponseCode();
	       	     while((line=br.readLine())!= null){
	       	    	 str+=line;
	       	     }
	       	     out.println(code1);
		   	     conn.disconnect();
		   			}
		   			//post
		   		}	
		   			
		   		
		   		else if(authen.equals("Basic Auth")){

		   	    if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
		        		 eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
	      		 
	      		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
		        		 eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
	      		 
	      		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
		        		 eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
	      		 
	      		 else if(!"null".equals(p1) && !"null".equals(p2)){
		        		 eurl=p1+"="+pv1+"&"+p2+"="+pv2;}
	      		 
	      		 else if(!"null".equals(p1)){
		        		 eurl=p1+"="+pv1;}
		   	   
	      		 else if("null".equals(p1)){
	      			     eurl="null";
	      		 }

				   	 String[] slt=t1.split("@@");
				  		int nn=slt.length;String orurl="";
				  		if(!(nn==0)){
				      		for(int i=1,j=1;i<nn;i=i+2,j++){
				      			slt[i]=adm[j];
				      		}
				      		for(int k=0;k<nn;k++){
				      			orurl=orurl+slt[k];
				      		}
				      		t1=orurl;
				  		}
		   	    	 URL url1;
		              if(!"null".equals(eurl)){
		            
	  	               url1 = new URL (t1+"?"+eurl);}
		              else
		                   url1 =new URL(t1);
		              HttpClient httpClient = new DefaultHttpClient();
		        	  HttpPost postRequest = new HttpPost(t1);
		        	  String encoding = new String(
		        		   		 org.apache.commons.codec.binary.Base64.encodeBase64   
		        		   		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+b4))
		        		   		  );
		        	  postRequest.setHeader("Authorization","Basic " + encoding);
		        	  HttpResponse response1 = httpClient.execute(postRequest);
		        	  code1=response1.getStatusLine().getStatusCode();
		        	  out.println(code1);
			   	 } //Basic
		   		else if(authen.equals("Oauth2")){
	        	    session.setAttribute("ckey", ckey1);
	        	    session.setAttribute("cseckey", cseckey1);
	        	    session.setAttribute("tokenurl", tokenurl1);
	        	    session.setAttribute("tempid", tempid);
	        	    session.setAttribute("tid", tid);
	        	    session.setAttribute("rm1", rmethod1);
	                session.setAttribute("but", "act");
		        	if(sname1.equals("") && el1.equals(""))
	          		  response.sendRedirect(aurl1+"?redirect_uri=https://bridge-minddotss.rhcloud.com/OauthCall&response_type=code&client_id="+ckey1);
	               else if(!sname1.equals("")&& el1.equals(""))
	          		  response.sendRedirect(aurl1+"?redirect_uri=https://bridge-minddotss.rhcloud.com/OauthCall&response_type=code&client_id="+ckey1+"&"+sname1+"="+svalue1);
	               else if(!sname1.equals("")&& !el1.equals(""))
	          		  response.sendRedirect(aurl1+"?redirect_uri=https://bridge-minddotss.rhcloud.com/OauthCall&response_type=code&client_id="+ckey1+"&"+sname1+"="+svalue1+"&"+el1+"="+ev1);
	               else if(sname1.equals("")&& !el1.equals(""))
	          		  response.sendRedirect(aurl1+"?redirect_uri=https://bridge-minddotss.rhcloud.com/OauthCall&response_type=code&client_id="+ckey1+"&"+el1+"="+ev1);
	          	 

		   		}
		   		PreparedStatement st2=con.prepareStatement("insert into act_all (tempid,aid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+tempid+"','"+tid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hv1+"','"+h2+"','"+hv2+"','"+h3+"','"+hv3+"','"+h4+"','"+hv4+"','"+h5+"','"+hv5+"','"+tlabel+"','"+treplace+"')");
	   			   	st2.executeUpdate();
	   			   	st2.close();
		   		request.setAttribute("code", code);
	            request.setAttribute("code1", code1);
		       request.getRequestDispatcher("check.jsp").forward(request, response);
	   	 } //While
		   	  
		   	  
	   	 }	  //action
        
        
        }		//try 
	   	 
	
  catch(Exception e){
	  out.println(e);}
}
}