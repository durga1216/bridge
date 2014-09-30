package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
@WebServlet("/TriggerAction")
public class TriggerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public TriggerAction() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out=response.getWriter();
		   //String appid=request.getParameter("tgmeth");
		    //String appid1=request.getParameter("actmeth");
		    
		   // out.println(appid + "<br>" + appid1);
		    String app=request.getParameter("app");
		    String app1=request.getParameter("app1");
		    String tgmeth=request.getParameter("tgmeth");
		    String actmeth=request.getParameter("actmeth");
		    String apikey=request.getParameter("apit1");
		    String user=request.getParameter("apit2");
		    String pass=request.getParameter("apit3");
		    String ak1="";String ak2="";String eurl="";String auth="";String b2="";String b4="";
		    out.println(tgmeth+"<br>"+actmeth+"<br>"+app+"<br>"+app1);
		    String[] data = null;
		    try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
				PreparedStatement st1=con.prepareStatement("select * from auth where appid='"+app+"'" );
		        ResultSet rs1=st1.executeQuery();
		        while(rs1.next()){
		        	auth=rs1.getString("authen");
		        	ak1=rs1.getString("a1");
		        	ak2=rs1.getString("a2");
		        	b2=rs1.getString("b2");
		        	b4=rs1.getString("b4");
		        }
				PreparedStatement st2=con.prepareStatement("select * from triger where appid='"+app+"' && name='"+tgmeth+"'" );
		        ResultSet rs2=st2.executeQuery();
		        while(rs2.next()){
		        	String endurl1=rs2.getString("t1");
		        	String pa1=rs2.getString("p1");String pva1=rs2.getString("pv1");
		        	String pa2=rs2.getString("p2");String pva2=rs2.getString("pv2");
		        	String pa3=rs2.getString("p3");String pva3=rs2.getString("pv3");
		        	
		        	if(!"null".equals(pa1) && !"null".equals(pa2) && !"null".equals(pa3)){
		        		 eurl=endurl1+"?"+ak1+"="+apikey+"&"+pa1+"="+pva1+"&"+pa2+"="+pva2+"&"+pa3+"="+pva3;}
	        		 
	        		 else if(!"null".equals(pa1) && !"null".equals(pa2)){
		        		 eurl=endurl1+"?"+ak1+"="+apikey+"&"+pa1+"="+pva1+"&"+pa2+"="+pva2;}
	        		 
	        		 else if(!"null".equals(pa1)){
		        		 eurl=endurl1+"?"+ak1+"="+apikey+"&"+pa1+"="+pva1;}
	        		 else if("null".equals(pa1))
	        			eurl=endurl1+"?"+ak1+"="+apikey;
	        		 
	        		 else if("null".equals(ak1) && "null".equals(apikey))
	        			 eurl=endurl1;
		        	 eurl=eurl.replaceAll(" ", "%20"); 
		        	 out.println(eurl);
	        		 URL eurl1=new URL(eurl);
	        		 URLConnection uconn = eurl1.openConnection();
	        	     HttpURLConnection conn = (HttpURLConnection) uconn;
	        	     conn.connect();
	        	     Object content = conn.getContent();
	        	     InputStream stream = (InputStream) content;
	        	     String line1=""; String str="";
	        	     BufferedReader br=new BufferedReader(new InputStreamReader(stream));
	        	     while((line1=br.readLine())!=null){
   	  	       	 	 str+=line1;
	        	     }
	        	     conn.disconnect();
	        	     DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        	     InputSource is = new InputSource();
	        	     is.setCharacterStream(new StringReader(str));

	        	     Document doc = db.parse(is);
	        	     NodeList nodes = doc.getElementsByTagName("event");
	        	     int tot=nodes.getLength();
	        	     data=new String[tot];
	        	     for (int i = 0; i < nodes.getLength(); i++) {
	        	       Element element = (Element) nodes.item(i);

	        	       NodeList name = element.getElementsByTagName("title");
	        	       Element line = (Element) name.item(0);
	        	       data[i]=getCharacterDataFromElement(line);
	        	       out.println("Name: " + getCharacterDataFromElement(line));
	        	     }
	        	     
	        	    // out.println(str);
	        	     
   	                    
		        }
		        PreparedStatement st3=con.prepareStatement("select * from triger where appid='"+app1+"' && name='"+actmeth+"'" );
		        ResultSet rs3=st3.executeQuery();
		        while(rs3.next()){
		        	out.println("test test");
		        	String endurl1=rs3.getString("t1");
		        	String pa1=rs3.getString("p1");String pva1=rs3.getString("pv1");
		        	String pa2=rs3.getString("p2");String pva2=rs3.getString("pv2");
		        	String pa3=rs3.getString("p3");String pva3=rs3.getString("pv3");
		        	String[] slt=endurl1.split("@@");
		        	int nn=slt.length;String orurl="";
		        	if(!(nn==0)){
		        	String n[]={user,"crud"};
		        	for(int i=1,j=0;i<nn;i=i+2,j++){
		        		slt[i]=n[j];
		        	}
		        	for(int k=0;k<nn;k++){
		        		orurl=orurl+slt[k];
		        	}
		        	}else{
		        		orurl=endurl1;
		        	}
		        String blt="{";String name="name";
		        //out.println(data[1]+"---"+data[2]+"---"+data[3]);
		        for(int i=0;i<3;i++){
      			  blt=blt+"\"\"+"+name+"+\"\":\"\"+data["+i+"]+\"\",";
      		  }
      		  blt=method(blt);
      		  blt=blt+"}";
      		  out.println(blt);
      		  String j1="fasfasdfasdfasd";String jv1="fasdfasdfsadf";
      		  String ttst="{\""+j1+"\":\""+jv1+"\"}";
			HttpClient httpClient = new DefaultHttpClient();
      	     HttpPost postRequest = new HttpPost(endurl1);
//      		URL url1 =new URL(endurl1);
//            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setUseCaches (false);
//            connection.setRequestMethod("POST");
     	     StringEntity input = new StringEntity("{\""+j1+"\":\""+jv1+"\"}");
   			 input.setContentType("application/json");
   			 postRequest.setEntity(input);
//   			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
//   		    wr.write(ttst);
//   		    wr.flush();
		    	 
		    	String encoding = new String(
        		   		 org.apache.commons.codec.binary.Base64.encodeBase64   
        		   		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(user+":"+pass))
        		   		  );
        		postRequest.setHeader("Authorization","Basic " + encoding);
		    	//connection.setRequestProperty  ("Authorization", "Basic " + encoding);
        		HttpResponse response1 = httpClient.execute(postRequest);
		    	//BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    	BufferedReader in = new BufferedReader(
                        new InputStreamReader(response1.getEntity().getContent()));
     			String line="";String str="";
	                    while((line=in.readLine())!=null){
	                    	str+=line;
	                    }
	                    out.println("sdfkks"+str);
		        } 
		    }
		    catch(Exception e){
		    	out.println(e);
		    }
		    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	  /* Connection con;
	   try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
		   PreparedStatement st=con.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 on t1.appid=t3.appid where t1.appid=?");
		   st.setString(1, appid);
		   ResultSet rs=st.executeQuery();
		   while(rs.next()){ 
			   
		  
			
	String tit=rs.getString("tit");String authen=rs.getString("authen");String b1=rs.getString("b1");String b2=rs.getString("b2");
	String b3=rs.getString("b3");String b4=rs.getString("b4");String h1=rs.getString("h1");String hv1=rs.getString("hv1");
	String h2=rs.getString("h2");String hv2=rs.getString("hv2");String h3=rs.getString("h3");String hv3=rs.getString("hv3");
	String h4=rs.getString("h4");String hv4=rs.getString("hv4");String h5=rs.getString("h5");String hv5=rs.getString("hv5");
	String h6=rs.getString("h6");String hv6=rs.getString("hv6");String h7=rs.getString("h7");String hv7=rs.getString("hv7");
	String a1=rs.getString("a1");String a2=rs.getString("a2");String ckey=rs.getString("ckey");String cseckey=rs.getString("cseckey");
	String sname=rs.getString("sname");String svalue=rs.getString("svalue");String aurl=rs.getString("aurl");String tokenurl=rs.getString("tokenurl");
	String tlabel=rs.getString("tlabel");String treplace=rs.getString("treplace");String el=rs.getString("el");String ev=rs.getString("ev");
	String rm=rs.getString("select2");
	
	// triger
	
	String name=rs.getString("name");String t1=rs.getString("t1");
	String p1=rs.getString("p1");String pv1=rs.getString("pv1");String p2=rs.getString("p2");String pv2=rs.getString("pv2");
	String p3=rs.getString("p3");String pv3=rs.getString("pv3");	String p4=rs.getString("p4");String pv4=rs.getString("pv4");
	String p5=rs.getString("p5");String pv5=rs.getString("pv5");	String p6=rs.getString("p6");String pv6=rs.getString("pv6");
	String p7=rs.getString("p7");String pv7=rs.getString("pv7"); 	String p8=rs.getString("p8");String pv8=rs.getString("pv8");
	String p9=rs.getString("p9");String pv9=rs.getString("pv9");	String p10=rs.getString("p10");String pv10=rs.getString("pv10");
	String p11=rs.getString("p11");String pv11=rs.getString("pv11");	String p12=rs.getString("p12");String pv12=rs.getString("pv12");
	String p13=rs.getString("p13");String pv13=rs.getString("pv13");	String p14=rs.getString("p14");String pv14=rs.getString("pv14");
	String p15=rs.getString("p15");String pv15=rs.getString("pv15");	String p16=rs.getString("p16");String pv16=rs.getString("pv16");
	String p17=rs.getString("p17");String pv17=rs.getString("pv17");	String p18=rs.getString("p18");String pv18=rs.getString("pv18");
	String p19=rs.getString("p19");String pv19=rs.getString("pv19");	String p20=rs.getString("p20");String pv20=rs.getString("pv20");

	  HttpSession session=request.getSession();
	    
	    
      if(authen.equals("Oauth2")){
    
    	session.setAttribute("ckey", ckey);
  	    session.setAttribute("cseckey", cseckey);
  	    session.setAttribute("sname", sname);
  	    session.setAttribute("svalue", svalue);
  	    session.setAttribute("aurl", aurl);
  	    session.setAttribute("tokenurl", tokenurl);
  	    session.setAttribute("tlabel", tlabel);
  	    session.setAttribute("treplace", treplace);
  	    session.setAttribute("el", el);
  	    session.setAttribute("ev", ev);
  	    session.setAttribute("rm", rm);
  	    
  	  if(sname.equals("") && el.equals(""))
		  response.sendRedirect(aurl+"?redirect_uri=https://localhost:8080/OauthCallBack&response_type=code&client_id="+ckey);
     else if(!sname.equals("")&& el.equals(""))
		  response.sendRedirect(aurl+"?redirect_uri=https://localhost:8080/OauthCallBack&response_type=code&client_id="+ckey+"&"+sname+"="+svalue);
     else if(!sname.equals("")&& !el.equals(""))
		  response.sendRedirect(aurl+"?redirect_uri=https://localhost:8080/OauthCallBack&response_type=code&client_id="+ckey+"&"+sname+"="+svalue+"&"+el+"="+ev);
     else if(sname.equals("")&& !el.equals(""))
		  response.sendRedirect(aurl+"?redirect_uri=https://localhost:8080/OauthCallBack&response_type=code&client_id="+ckey+"&"+el+"="+ev);
    	  
      }
		   }
 
	   }
	   catch(Exception e){
		   out.println(e);
	   }*/
	    
		
	}
	public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }
	 public static String method(String str) {
		    if (str.length() > 0 && str.charAt(str.length()-1)==',') {
		      str = str.substring(0, str.length()-1);
		    }
		    return str;
		}
}
