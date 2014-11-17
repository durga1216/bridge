package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import sun.misc.BASE64Encoder;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;


@WebServlet("/TriggerAuth")
public class TriggerAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
   
    public TriggerAuth() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		Connection con=null;  
	   	 response.setHeader("Content-Type","text/html;charset=UTF-8");
        HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
        String tempid=(String)session.getAttribute("tempid");
        String tid=(String)session.getAttribute("tid");  
        String aid=(String)session.getAttribute("aid");
        String action=request.getParameter("submit");
        String dn=request.getParameter("dn");String apkey=request.getParameter("apkey");String b2=request.getParameter("uname");String b4=request.getParameter("pwd");
        String dn1=request.getParameter("dn1");
        String hd1=request.getParameter("hd1");String hd2=request.getParameter("hd2");String hd3=request.getParameter("hd3");
        String hd4=request.getParameter("hd4");String hd5=request.getParameter("hd5");
        String pv1=request.getParameter("pv1");String pv2=request.getParameter("pv2");String pv3=request.getParameter("pv3");
        String pv4=request.getParameter("pv4");String pv5=request.getParameter("pv5");String pv6=request.getParameter("pv6");
        String pv7=request.getParameter("pv7");
        String[] tdm={"Test",request.getParameter("tdm1"),request.getParameter("tdm2"),request.getParameter("tdm3"),request.getParameter("tdm4"),request.getParameter("tdm5")};
        String[] ndm={"Test",request.getParameter("ndm1"),request.getParameter("ndm2"),request.getParameter("ndm3"),request.getParameter("ndm4"),request.getParameter("ndm5")};
        String[] adm={"Test",request.getParameter("adm1"),request.getParameter("adm2"),request.getParameter("adm3"),request.getParameter("adm4"),request.getParameter("adm5")};
        String sigckey=request.getParameter("sigckey");String sigskey=request.getParameter("sigskey");
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
	   	 			String pav1=rs.getString("pv1");String pav2=rs.getString("pv2");String pav3=rs.getString("pv3");
	   	 			String pav4=rs.getString("pv4");String pav5=rs.getString("pv5");String pav6=rs.getString("pv6");String pav7=rs.getString("p7");
	   	 			String b1=rs.getString("b1");
	   	 			String b3=rs.getString("b3");
	   	 			String treplace=rs.getString("treplace");String tlabel=rs.getString("tlabel");
	   	 			String h1=rs.getString("h1"); String hv1=rs.getString("hv1");
	   	 			String h2=rs.getString("h2"); String hv2=rs.getString("hv2");
	   	 			String h3=rs.getString("h3"); String hv3=rs.getString("hv3");
	   	 			String h4=rs.getString("h4"); String hv4=rs.getString("hv4");
	   	 			String h5=rs.getString("h5"); String hv5=rs.getString("hv5");
	   	 			String h6=rs.getString("h6"); String hv6=rs.getString("hv6");
	   	 			String h7=rs.getString("h7"); String hv7=rs.getString("hv7");
	   	 			String ockey=rs.getString("ockey");String oskey=rs.getString("oskey");
	   	 			String ourl1=rs.getString("ourl1");String ourl2=rs.getString("ourl2");
	   	 			String ourl3=rs.getString("ourl3");String osmeth=rs.getString("osmeth");
	   	 			String oreq=rs.getString("oreq");
	   	 			String ckey1=rs.getString("ckey");String cseckey1=rs.getString("cseckey");
	   	 			String sname1=rs.getString("sname");String svalue1=rs.getString("svalue");
	   	 			String aurl1=rs.getString("aurl");String tokenurl1=rs.getString("tokenurl");
	   	 			String tlabel1=rs.getString("tlabel");String treplace1=rs.getString("treplace");
	   	 			String el1=rs.getString("el");String ev1=rs.getString("ev");String rmethod1=rs.getString("select2");
	   	 			//For signed auth
	   	 			//String sigckey=rs.getString("sigckey");String sigskey=rs.getString("sigskey");
	   	 			String sigmsg=rs.getString("message");
	   	 			
	   	 			out.println(authen);
	   	 			String str="";
	   	 			String eurl="null";
	   	 			if(authen.equals("No Auth")){
	   	 				String[] slt=t1.split("@@");
	   	 				int nn=slt.length;String orurl="";
	   	 				if(!(nn==0)){
	   	 					for(int i=1,j=1;i<nn;i=i+2,j++){
	   	 						slt[i]=ndm[j];
	   	 					}
	   	 					for(int k=0;k<nn;k++){
	   	 						orurl=orurl+slt[k];
	   	 					}
	   	 					t1=orurl;
	   	 				}
	   	 				HttpClient cli=new DefaultHttpClient();
	   	 				HttpGet get=new HttpGet(t1);
	   	 				HttpResponse res=cli.execute(get);
	   	 				BufferedReader bf=new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
	   	 				String line="";
	   	 				while((line=bf.readLine())!=null){
	   	 					str+=line;
	   	 				}
	   	 				code=200;
	   	 				session.setAttribute("xml1", str);
	   	 				PreparedStatement st2=con.prepareStatement("insert into trig_all (userid,tempid,tid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+id+"','"+tempid+"','"+tid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
	   	 				st2.executeUpdate();
	   	 				st2.close();
	   	 			}else if(authen.equals("API keys")){  
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
	   	 				code=200;
	   	 				session.setAttribute("xml1", str);
	   	 				PreparedStatement st2=con.prepareStatement("insert into trig_all (userid,tempid,tid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+id+"','"+tempid+"','"+tid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
	   	 				st2.executeUpdate();
	   	 				st2.close();
	   	 				
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
	   	 					encoding = new String(org.apache.commons.codec.binary.Base64.encodeBase64   
	                    		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+"")));
	   	 					connection.setRequestProperty  ("Authorization", "Basic " + encoding);

	   	 				}
	   	 				else if(!"null".equals(b4) && "null".equals(b2)){encoding = new String(
	   	 						org.apache.commons.codec.binary.Base64.encodeBase64   
	   	 						(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(""+":"+b4)));	              
	   	 				connection.setRequestProperty  ("Authorization", "Basic " + encoding);
	   	 				}
	   	 				else if(!"null".equals(b2) && !"null".equals(b4)){
	   	 					encoding = new String(
	   	 						org.apache.commons.codec.binary.Base64.encodeBase64   
	                    	    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+b4)));
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
	 	                PreparedStatement st2=con.prepareStatement("insert into trig_all (userid,tempid,tid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+id+"','"+tempid+"','"+tid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
	 	 			   	st2.executeUpdate();
	 	 			   	st2.close();
	   	 			}//basic auth
	   	 			else if(authen.equals("Signed Auth")){
	   	 				String uuid_string = UUID.randomUUID().toString();
	 					uuid_string = uuid_string.replaceAll("-", "");
	 					String nonce = uuid_string; 
	 					String timestamp = String.valueOf((System.currentTimeMillis()/1000) +3600);
	 					String smessage=sigmsg;
	 					//construct the message
	 					String[] slt=smessage.split("@@");
	 					int nn=slt.length;String orurl="";
	 					if(!(nn==0)){
	 						for(int i=0;i<nn;i++){
	 							if(slt[i].equals("timestamp")){
	 								slt[i]=timestamp;
	 							}else if(slt[i].equals("nonce")){
	 								slt[i]=nonce;
	 							}
	 						}
	 						for(int k=0;k<nn;k++){
	 							orurl=orurl+slt[k];
	 						}
	 						smessage=orurl;
	 					}
	 					out.println("smessage");
	 					//creating signature
	 					SecretKeySpec signingKey = new SecretKeySpec(sigskey.getBytes(), "HMACSHA1");
	 			        Mac mac = Mac.getInstance("HMACSHA1");
	 			        mac.init(signingKey);
	 			        byte[] rawHmac = mac.doFinal(smessage.getBytes());
	 			        String result = new BASE64Encoder().encode(rawHmac);
	 			        String signature1 = URLEncoder.encode(result, "UTF-8") ;
	 			        //merge all the params
	 					if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
			   				eurl=p1+"="+pav1+"&"+p2+"="+pav2+"&"+p3+"="+pav3+"&"+p4+"="+pav4+"&"+p5+"="+pav5+"&"+p6+"="+pav6;}
			   			
			   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
			   				eurl=p1+"="+pav1+"&"+p2+"="+pav2+"&"+p3+"="+pav3+"&"+p4+"="+pav4+"&"+p5+"="+pav5;}
		        		 
			   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
			   				eurl=p1+"="+pav1+"&"+p2+"="+pav2+"&"+p3+"="+pav3+"&"+p4+"="+pav4;}
		        		 
			   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
			   				eurl=p1+"="+pav1+"&"+p2+"="+pav2+"&"+p3+"="+pav3;}
		        		 
			   			else if(!"null".equals(p1) && !"null".equals(p2)){
			   				eurl=p1+"="+pav1+"&"+p2+"="+pav2;}
		        		 
			   			else if(!"null".equals(p1)){
			   				eurl=p1+"="+pav1;}
	 					//construct the url
	 					String[] slt1=eurl.split("@@");
	 					int nn1=slt1.length;String orurl1="";
	 					if(!(nn1==0)){
	 						for(int i=0;i<nn1;i++){
	 							if(slt1[i].equals("timestamp")){
	 								slt1[i]=timestamp;
	 							}else if(slt1[i].equals("nonce")){
	 								slt1[i]=nonce;
	 							}else if(slt1[i].equals("signature")){
	 								slt1[i]=signature1;
	 							}else if(slt1[i].equals("apikey")){
	 								slt1[i]=sigckey;
	 							}
	 						}
	 						for(int k=0;k<nn1;k++){
	 							orurl1=orurl1+slt1[k];
	 						}
	 						eurl=orurl1;
	 					}
	 					String callurl=t1+"?"+eurl;
	 					//Request to client
	   	 				HttpClient cli=new DefaultHttpClient();
	   	 				HttpGet get=new HttpGet(callurl);
	   	 				HttpResponse res=cli.execute(get);
	   	 				BufferedReader bf=new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
	   	 				String line="";
	   	 				while((line=bf.readLine())!=null){
	   	 					str+=line;
	   	 				}
	   	 				code=200;
	   	 				session.setAttribute("xml1", str);
	   	 				PreparedStatement st2=con.prepareStatement("insert into trig_all (userid,tempid,tid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace,smessage,sigskey) values ('"+id+"','"+tempid+"','"+tid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pav1+"','"+p2+"','"+pav2+"','"+p3+"','"+pav3+"','"+p4+"','"+pav4+"','"+p5+"','"+pav5+"','"+p6+"','"+pav6+"','"+p7+"','"+pav7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"','"+sigmsg+"','"+sigskey+"')");
	   	 				st2.executeUpdate();
	   	 				st2.close();
	   	 			}
	   	 			else if(authen.equals("Oauth1")){
		   	 			if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
			   				eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5+"&"+p6+"="+pv6;}
			   			
			   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
			   				eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
		        		 
			   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
			   				eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
		        		 
			   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
			   				eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
		        		 
			   			else if(!"null".equals(p1) && !"null".equals(p2)){
			   				eurl=p1+"="+pv1+"&"+p2+"="+pv2;}
		        		 
			   			else if(!"null".equals(p1)){
			   				eurl=p1+"="+pv1;}
			   			
			   			else if("null".equals(p1))
			   				eurl="null";
	   	 				if(oreq.equals("GET")){
	   	 					String uuid_string = UUID.randomUUID().toString();
	   	 					uuid_string = uuid_string.replaceAll("-", "");
	   	 					String oauth_nonce = uuid_string; 
	   	 					String eurl1 = URLEncoder.encode(ourl1, "UTF-8");
	   	 					long oauth_timestamp = System.currentTimeMillis()/1000;
	   	 					String parameter_string="";
	   	 					String call="https://bridge-minddotss.rhcloud.com/Oauth1call";
	   	 					if(rmethod1.equals("DELETE")){// get current time in milliseconds, then divide by 1000 to get seconds
	   	 						parameter_string = "oauth_callback="+URLEncoder.encode(call, "UTF-8")+"&oauth_consumer_key=" + ockey + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + osmeth + "&oauth_timestamp=" + oauth_timestamp + "&oauth_version=1.0";        
	   	 					}else{
	   	 						parameter_string = "oauth_consumer_key=" + ockey + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + osmeth + "&oauth_timestamp=" + oauth_timestamp + "&oauth_version=1.0";
	   	 					}
	   	 					String signature_base_string = oreq+"&"+eurl1+"&" + URLEncoder.encode(parameter_string, "UTF-8");
	   	 					String oauth_signature = "";String oauth_signature1 = "";
	   	 					try {
	   	 						oauth_signature = computeSignature(signature_base_string, oskey+"&");  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
	   	 						oauth_signature1 =URLEncoder.encode(oauth_signature, "UTF-8");
	   	 					}catch (GeneralSecurityException e) {
	   	 						// TODO Auto-generated catch block
	   	 						e.printStackTrace();
	   	 					}
	   	 					session.setAttribute("oauth_signature1", oauth_signature1);
	   	 					session.setAttribute("parameter_string", parameter_string);
	   	 					String authorization_header_string = "OAuth oauth_consumer_key=\"" + ockey + "\","
	   	 							+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
		                            oauth_timestamp + "\",oauth_version=\"1.0\"";
	   	 					String uurl=ourl1+"?"+parameter_string+"&oauth_signature="+URLEncoder.encode(oauth_signature, "UTF-8");
	   	 					System.out.println(uurl);
	   	 					String oauth_token = "";
	   	 					HttpClient httpclient = new DefaultHttpClient();
	   	 					HttpResponse response1=null;
	   	 					try {
	   	 						HttpGet get1=new HttpGet(uurl);
	   	 						response1=httpclient.execute(get1);
	   	 						BufferedReader rd = new BufferedReader(
				                                new InputStreamReader(response1.getEntity().getContent()));
				         		StringBuffer result = new StringBuffer();
				         		String line = "";
				         		while ((line = rd.readLine()) != null) {
				         			result.append(line);
				         		}
				         		String tok=result.toString();
				         		String sec1="";
				         		String[] chk1=tok.split("&");
				        		for(int i=0;i<chk1.length;i++){
				        			String[] stest=chk1[i].split("=");
				        			if(stest[0].equals("oauth_token")){
				        				oauth_token=chk1[i];
				        			}else if(stest[0].equals("oauth_token_secret")){
				        				sec1=chk1[i];
				        			}
				        		}
				         		session.setAttribute("samp", tok+"\n"+oauth_token+"\n"+sec1+"\n"+signature_base_string+"\n"+uurl);
				         		session.setAttribute("secret1", sec1);  
				         		session.setAttribute("tempid", tempid);
				        	    session.setAttribute("tid", tid);
				        	    session.setAttribute("ourl", eurl);
	   	 					}
	   	 					catch(ClientProtocolException cpe)  {  
	   	 						System.out.println(cpe.getMessage());  
   	 						}
	   	 					catch(IOException ioe) {   
	   	 						System.out.println(ioe.getMessage());  
   	 						}
	   	 					
	   	 					String author=ourl2+"?"+oauth_token+"&perms=write";
	   	 					response.sendRedirect(author);
	   	 				}
	   	 				else {
	   	 					String uuid_string = UUID.randomUUID().toString();
	   	 					uuid_string = uuid_string.replaceAll("-", "");
	   	 					String oauth_nonce = uuid_string; 
	   	 					String eurl1 = URLEncoder.encode(ourl1, "UTF-8");
	   	 					long oauth_timestamp = System.currentTimeMillis()/1000;
	   	 					String parameter_string = "oauth_consumer_key=" + ockey + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + osmeth + "&oauth_timestamp=" + oauth_timestamp + "&oauth_version=1.0";        
	   	 					String signature_base_string = oreq+"&"+eurl1+"&" + URLEncoder.encode(parameter_string, "UTF-8");out.println("signature_base_string=" + signature_base_string);
	   	 					out.println(signature_base_string);
	   	 					String oauth_signature = "";String oauth_signature1 = "";
	   	 					try {
	   	 						oauth_signature = computeSignature(signature_base_string, oskey+"&");  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
	   	 						oauth_signature1 =URLEncoder.encode(oauth_signature, "UTF-8");
	   	 					} catch (GeneralSecurityException e) {
	   	 						// TODO Auto-generated catch block
	   	 						e.printStackTrace();
	   	 					}
	   	 					String authorization_header_string = "OAuth oauth_consumer_key=\"" + ockey + "\","
	   	 							+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
	   	 							oauth_timestamp + "\",oauth_version=\"1.0\"";
	   	 					out.println(authorization_header_string);
	   	 					String oauth_token = "";
	   	 					HttpClient httpclient = new DefaultHttpClient();
	   	 					HttpResponse response1=null;
	   	 					try {
	   	 						HttpPost post = new HttpPost(ourl1);
	   	 						post.setHeader("Authorization", authorization_header_string);
	   	 						response1 = httpclient.execute(post);       	
  				 			 	BufferedReader rd = new BufferedReader(
  				 			 			new InputStreamReader(response1.getEntity().getContent()));
  				 			 	StringBuffer result = new StringBuffer();
					       		String line = "";
					       		while ((line = rd.readLine()) != null) {
					       			 	result.append(line);
					       		}
					       		String tok=result.toString();
					       		String sec1="";
				         		String[] chk1=tok.split("&");
				        		for(int i=0;i<chk1.length;i++){
				        			String[] stest=chk1[i].split("=");
				        			if(stest[0].equals("oauth_token")){
				        				oauth_token=chk1[i];
				        			}else if(stest[0].equals("oauth_token_secret")){
				        				sec1=chk1[i];
				        			}
				        		}
					       		session.setAttribute("secret1", sec1);
					       		session.setAttribute("tempid", tempid);
				        	    session.setAttribute("tid", tid);
				        	    session.setAttribute("ourl", eurl);
	   	 					} 
	   	 					catch(ClientProtocolException cpe)  {   
	   	 						System.out.println(cpe.getMessage());  
   	 						}
	   	 					catch(IOException ioe) {   
	   	 						System.out.println(ioe.getMessage());  
   	 						}
	                  
	   	 					String author=ourl2+"?"+oauth_token+"&perms=write";
	   	 					response.sendRedirect(author);
	   	 				}
	   	 				PreparedStatement st2=con.prepareStatement("insert into trig_all (userid,tempid,tid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace,ockey,oskey,osmeth) values ('"+id+"','"+tempid+"','"+tid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pav1+"','"+p2+"','"+pav2+"','"+p3+"','"+pav3+"','"+p4+"','"+pav4+"','"+p5+"','"+pav5+"','"+p6+"','"+pav6+"','"+p7+"','"+pav7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"','"+ockey+"','"+oskey+"','"+osmeth+"')");
	   	 				st2.executeUpdate();
	   	 				st2.close();
	   	 			}
	   	 			else if(authen.equals("Oauth2")){
	   	 				String eeurl="";
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
		   	 			if(eurl.equals("null")){
		   	 				eeurl=t1;
		   	 			}else{
		   	 				eeurl=t1+"?"+eurl;
		   	 			}
	   	 				if(rmethod1.equals("DELETE")){
	   	 					session.setAttribute("tempid", tempid);
	   	 					session.setAttribute("tid", tid);
	   	 					session.setAttribute("rtype", "trigger");
	   	 					session.setAttribute("Gurl", eeurl);
	   	 					String CLIENT_ID = "758153664645-n04dc4ki6pr383jdnrq6hmgjsvbsibls";
	   	 					String CLIENT_SECRET = "YsLu7TgD4q_NmheHjx4W2Okf";
	   	 					String REDIRECT_URI = "https://bridge-minddotss.rhcloud.com/GauthCall";
	   	 					List<String> SCOPES = Arrays.asList(svalue1);
	   	 					String authorizationUrl =
	   	 							new GoogleAuthorizationCodeRequestUrl(CLIENT_ID, REDIRECT_URI, SCOPES).setApprovalPrompt("force").setAccessType("offline").build();
	   	 					response.sendRedirect(authorizationUrl);
	   	 					PreparedStatement st2=con.prepareStatement("insert into trig_all (userid,tempid,tid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+id+"','"+tempid+"','"+tid+"','"+authen+"','DELETE','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pav6+"','"+p7+"','"+pav7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
	   	 					st2.executeUpdate();
	   	 					st2.close();
	   	 				}else{
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
	   	 					PreparedStatement st2=con.prepareStatement("insert into trig_all (userid,tempid,tid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+id+"','"+tempid+"','"+tid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pav1+"','"+p2+"','"+pav2+"','"+p3+"','"+pav3+"','"+p4+"','"+pav4+"','"+p5+"','"+pav5+"','"+p6+"','"+pav6+"','"+p7+"','"+pav7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
	   	 					st2.executeUpdate();
	   	 					st2.close();
	   	 				}
	   	 			}
	   	 			request.setAttribute("code", code);
	   	 			request.setAttribute("code1", code1);
	   	 			request.getRequestDispatcher("check.jsp").forward(request, response);	
	   	 		} //while
	   	 	} //auth trigger 		  	   	 
	   	 	else if("Authenticate Action".equals(action)){
	   	 		out.println("Authenticate Action inside");
	   	 		PreparedStatement st1=con.prepareStatement("select * from triger t1 JOIN auth t2 on t1.appid=t2.appid where t1.appid=?");
	   	 		st1.setString(1, aid);
	   	 		code=200;
	   	 		ResultSet rs=st1.executeQuery();
	   	 		while(rs.next()){
	   	 			String authen=rs.getString("authen");String a1=rs.getString("a1");
	   	 			String t1=rs.getString("t1");String rmethod=rs.getString("rmethod");String rformat=rs.getString("rformat");
	   	 			String resformat=rs.getString("resformat");String p1=rs.getString("p1");String p2=rs.getString("p2");String p3=rs.getString("p3");
	   	 			String p4=rs.getString("p4");String p5=rs.getString("p5");String p6=rs.getString("p6");String p7=rs.getString("p7");
	   	 			String p8=rs.getString("p8");String p9=rs.getString("p9");String p10=rs.getString("p10");
	   	 			String pav1=rs.getString("pv1");String pav2=rs.getString("pv2");String pav3=rs.getString("pv3");
	   	 			String pav4=rs.getString("pv4");String pav5=rs.getString("pv5");String pav6=rs.getString("pv6");String pav7=rs.getString("p7");
	   	 			String b1=rs.getString("b1");
	   	 			String b3=rs.getString("b3");
	   	 			String treplace=rs.getString("treplace");String tlabel=rs.getString("tlabel");
	   	 			String h1=rs.getString("h1"); String hv1=rs.getString("hv1");
	   	 			String h2=rs.getString("h2"); String hv2=rs.getString("hv2");
	   	 			String h3=rs.getString("h3"); String hv3=rs.getString("hv3");
	   	 			String h4=rs.getString("h4"); String hv4=rs.getString("hv4");
	   	 			String h5=rs.getString("h5"); String hv5=rs.getString("hv5");
	   	 			String h6=rs.getString("h6"); String hv6=rs.getString("hv6");
	   	 			String h7=rs.getString("h7"); String hv7=rs.getString("hv7");
	   	 			String ockey=rs.getString("ockey");String oskey=rs.getString("oskey");
	   	 			String ourl1=rs.getString("ourl1");String ourl2=rs.getString("ourl2");
	   	 			String ourl3=rs.getString("ourl3");String osmeth=rs.getString("osmeth");
	   	 			String oreq=rs.getString("oreq");
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
	   	 				PreparedStatement st2=con.prepareStatement("insert into act_all (tempid,aid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+tempid+"','"+aid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
	   	 				st2.executeUpdate();
	   	 				st2.close();
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
				  			url1 = new URL (t1+"?"+eurl);
			  			}
				  		else
				  			url1 =new URL(t1);
				  		
				  			HttpClient httpClient = new DefaultHttpClient();
				  			HttpPost postRequest = new HttpPost(t1);
				  			if(!b2.equals("") && !b2.equals("null")){
				  				String encoding = new String(
				  						org.apache.commons.codec.binary.Base64.encodeBase64   
				  						(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+b4)));
				  				postRequest.setHeader("Authorization","Basic " + encoding);
				  			}
				  			if(!h1.equals("null") && !h2.equals("null") && !h3.equals("null")){
				  				postRequest.setHeader(h1, hd1);postRequest.setHeader(h2, hd2);postRequest.setHeader(h3,hd3);
				  			}
				  			else if(!h1.equals("null") && !h2.equals("null")){
				  				postRequest.setHeader(h1, hd1);postRequest.setHeader(h2, hd2);
				  			}
				  			else if(!h1.equals("null")){
				  				postRequest.setHeader(h1, hd1);
				  			}
				  			HttpResponse response1 = httpClient.execute(postRequest);
				  			code1=response1.getStatusLine().getStatusCode();
				  			code1=200;
				  			out.println(code1);
				  			PreparedStatement st2=con.prepareStatement("insert into act_all (tempid,aid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+tempid+"','"+aid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
				  			st2.executeUpdate();
				  			st2.close();
	   	 				} //Basic
	   	 				else if(authen.equals("Oauth1")){
		   	 				if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
				   				eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5+"&"+p6+"="+pv6;}
				   			
				   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
				   				eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
			        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
				   				eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
			        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
				   				eurl=p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
			        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2)){
				   				eurl=p1+"="+pv1+"&"+p2+"="+pv2;}
			        		 
				   			else if(!"null".equals(p1)){
				   				eurl=p1+"="+pv1;}
				   			
				   			else if("null".equals(p1))
				   				eurl="null";
		   	 				if(oreq.equals("GET")){
		   	 					String uuid_string = UUID.randomUUID().toString();
		   	 					uuid_string = uuid_string.replaceAll("-", "");
		   	 					String oauth_nonce = uuid_string; 
		   	 					String eurl1 = URLEncoder.encode(ourl1, "UTF-8");
		   	 					long oauth_timestamp = System.currentTimeMillis()/1000;
		   	 					String parameter_string="";
		   	 					String call="https://bridge-minddotss.rhcloud.com/Oauth1call";
		   	 					if(rmethod1.equals("DELETE")){// get current time in milliseconds, then divide by 1000 to get seconds
		   	 						parameter_string = "oauth_callback="+URLEncoder.encode(call, "UTF-8")+"&oauth_consumer_key=" + ockey + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + osmeth + "&oauth_timestamp=" + oauth_timestamp + "&oauth_version=1.0";        
		   	 					}else{
		   	 						parameter_string = "oauth_consumer_key=" + ockey + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + osmeth + "&oauth_timestamp=" + oauth_timestamp + "&oauth_version=1.0";
		   	 					}
		   	 					String signature_base_string = oreq+"&"+eurl1+"&" + URLEncoder.encode(parameter_string, "UTF-8");
		   	 					String oauth_signature = "";String oauth_signature1 = "";
		   	 					try {
		   	 						oauth_signature = computeSignature(signature_base_string, oskey+"&");  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
		   	 						oauth_signature1 =URLEncoder.encode(oauth_signature, "UTF-8");
		   	 					}catch (GeneralSecurityException e) {
		   	 						// TODO Auto-generated catch block
		   	 						e.printStackTrace();
		   	 					}
		   	 					session.setAttribute("oauth_signature1", oauth_signature1);
		   	 					session.setAttribute("parameter_string", parameter_string);
		   	 					String authorization_header_string = "OAuth oauth_consumer_key=\"" + ockey + "\","
		   	 							+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
			                            oauth_timestamp + "\",oauth_version=\"1.0\"";
		   	 					String uurl=ourl1+"?"+parameter_string+"&oauth_signature="+URLEncoder.encode(oauth_signature, "UTF-8");
		   	 					System.out.println(uurl);
		   	 					String oauth_token = "";
		   	 					HttpClient httpclient = new DefaultHttpClient();
		   	 					HttpResponse response1=null;
		   	 					try {
		   	 						HttpGet get1=new HttpGet(uurl);
		   	 						response1=httpclient.execute(get1);
		   	 						BufferedReader rd = new BufferedReader(
					                                new InputStreamReader(response1.getEntity().getContent()));
					         		StringBuffer result = new StringBuffer();
					         		String line = "";
					         		while ((line = rd.readLine()) != null) {
					         			result.append(line);
					         		}
					         		String tok=result.toString();
					         		String sec1="";
					         		String[] chk1=tok.split("&");
					        		for(int i=0;i<chk1.length;i++){
					        			String[] stest=chk1[i].split("=");
					        			if(stest[0].equals("oauth_token")){
					        				oauth_token=chk1[i];
					        			}else if(stest[0].equals("oauth_token_secret")){
					        				sec1=chk1[i];
					        			}
					        		}
					         		session.setAttribute("samp", tok+"\n"+oauth_token+"\n"+sec1+"\n"+signature_base_string+"\n"+uurl);
					         		session.setAttribute("secret1", sec1);  
					         		session.setAttribute("tempid", tempid);
					        	    session.setAttribute("tid", aid);
					        	    session.setAttribute("ourl", eurl);
		   	 					}
		   	 					catch(ClientProtocolException cpe)  {  
		   	 						System.out.println(cpe.getMessage());  
		 						}
		   	 					catch(IOException ioe) {   
		   	 						System.out.println(ioe.getMessage());  
		 						}
		   	 					
		   	 					String author=ourl2+"?"+oauth_token+"&perms=write";
		   	 					response.sendRedirect(author);
		   	 				}
		   	 				else {
		   	 					String uuid_string = UUID.randomUUID().toString();
		   	 					uuid_string = uuid_string.replaceAll("-", "");
		   	 					String oauth_nonce = uuid_string; 
		   	 					String eurl1 = URLEncoder.encode(ourl1, "UTF-8");
		   	 					long oauth_timestamp = System.currentTimeMillis()/1000;
		   	 					String parameter_string = "oauth_consumer_key=" + ockey + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + osmeth + "&oauth_timestamp=" + oauth_timestamp + "&oauth_version=1.0";        
		   	 					String signature_base_string = oreq+"&"+eurl1+"&" + URLEncoder.encode(parameter_string, "UTF-8");out.println("signature_base_string=" + signature_base_string);
		   	 					out.println(signature_base_string);
		   	 					String oauth_signature = "";String oauth_signature1 = "";
		   	 					try {
		   	 						oauth_signature = computeSignature(signature_base_string, oskey+"&");  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
		   	 						oauth_signature1 =URLEncoder.encode(oauth_signature, "UTF-8");
		   	 					} catch (GeneralSecurityException e) {
		   	 						// TODO Auto-generated catch block
		   	 						e.printStackTrace();
		   	 					}
		   	 					String authorization_header_string = "OAuth oauth_consumer_key=\"" + ockey + "\","
		   	 							+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
		   	 							oauth_timestamp + "\",oauth_version=\"1.0\"";
		   	 					out.println(authorization_header_string);
		   	 					String oauth_token = "";
		   	 					HttpClient httpclient = new DefaultHttpClient();
		   	 					HttpResponse response1=null;
		   	 					try {
		   	 						HttpPost post = new HttpPost(ourl1);
		   	 						post.setHeader("Authorization", authorization_header_string);
		   	 						response1 = httpclient.execute(post);       	
					 			 	BufferedReader rd = new BufferedReader(
					 			 			new InputStreamReader(response1.getEntity().getContent()));
					 			 	StringBuffer result = new StringBuffer();
						       		String line = "";
						       		while ((line = rd.readLine()) != null) {
						       			 	result.append(line);
						       		}
						       		String tok=result.toString();
						       		String sec1="";
					         		String[] chk1=tok.split("&");
					        		for(int i=0;i<chk1.length;i++){
					        			String[] stest=chk1[i].split("=");
					        			if(stest[0].equals("oauth_token")){
					        				oauth_token=chk1[i];
					        			}else if(stest[0].equals("oauth_token_secret")){
					        				sec1=chk1[i];
					        			}
					        		}
						       		session.setAttribute("secret1", sec1);
						       		session.setAttribute("tempid", tempid);
					        	    session.setAttribute("tid", aid);
					        	    session.setAttribute("ourl", eurl);
		   	 					} 
		   	 					catch(ClientProtocolException cpe)  {   
		   	 						System.out.println(cpe.getMessage());  
		 						}
		   	 					catch(IOException ioe) {   
		   	 						System.out.println(ioe.getMessage());  
		 						}
		                  
		   	 					String author=ourl2+"?"+oauth_token+"&perms=write";
		   	 					response.sendRedirect(author);
		   	 				}
	   	 					PreparedStatement st2=con.prepareStatement("insert into act_all (tempid,aid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace,ockey,oskey,osmeth) values ('"+tempid+"','"+aid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"','"+ockey+"','"+oskey+"','"+osmeth+"')");
	   	 					st2.executeUpdate();
	   	 					st2.close();
	   	 				}
	   	 				else if(authen.equals("Oauth2")){
	   	 					if(rmethod1.equals("DELETE")){
	   	 						session.setAttribute("tempid", tempid);
	   	 						session.setAttribute("tid", aid);
	   	 						session.setAttribute("rtype", "action");
	   	 						session.setAttribute("Gurl", t1);
	   	 						String CLIENT_ID = "758153664645-n04dc4ki6pr383jdnrq6hmgjsvbsibls";
	   	 						String CLIENT_SECRET = "YsLu7TgD4q_NmheHjx4W2Okf";
	   	 						String REDIRECT_URI = "https://bridge-minddotss.rhcloud.com/GauthCall";
	   	 						List<String> SCOPES = Arrays.asList("https://spreadsheets.google.com/feeds");
	   	 						String authorizationUrl =
	   	 								new GoogleAuthorizationCodeRequestUrl(CLIENT_ID, REDIRECT_URI, SCOPES).setApprovalPrompt("force").setAccessType("offline").build();
	   	 						response.sendRedirect(authorizationUrl);
	   	 						PreparedStatement st2=con.prepareStatement("insert into act_all(tempid,aid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+tempid+"','"+aid+"','"+authen+"','DELETE','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pav1+"','"+p2+"','"+pav2+"','"+p3+"','"+pav3+"','"+p4+"','"+pav4+"','"+p5+"','"+pav5+"','"+p6+"','"+pav6+"','"+p7+"','"+pav7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
	   	 						st2.executeUpdate();
	   	 						st2.close();
	   	 					}
	   	 					else{
	   	 						session.setAttribute("ckey", ckey1);
	   	 						session.setAttribute("cseckey", cseckey1);
	   	 						session.setAttribute("tokenurl", tokenurl1);
	   	 						session.setAttribute("tempid", tempid);
	   	 						session.setAttribute("tid", aid);
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
	   	 						PreparedStatement st2=con.prepareStatement("insert into act_all (tempid,aid,authen,rmethod,rformat,resformat,emethod,dn,aplabel,apkey,dn1,b2,b4,p1,pv1,p2,pv2,p3,pv3,p4,pv4,p5,pv5,p6,pv6,p7,pv7,h1,hv1,h2,hv2,h3,hv3,h4,hv4,h5,hv5,tlabel,treplace) values ('"+tempid+"','"+aid+"','"+authen+"','"+rmethod+"','"+rformat+"','"+resformat+"','"+t1+"','"+dn+"','"+a1+"','"+apkey+"','"+dn1+"','"+b2+"','"+b4+"','"+p1+"','"+pv1+"','"+p2+"','"+pv2+"','"+p3+"','"+pv3+"','"+p4+"','"+pv4+"','"+p5+"','"+pv5+"','"+p6+"','"+pv6+"','"+p7+"','"+pv7+"','"+h1+"','"+hd1+"','"+h2+"','"+hd2+"','"+h3+"','"+hd3+"','"+h4+"','"+hd4+"','"+h5+"','"+hd5+"','"+tlabel+"','"+treplace+"')");
	   	 						st2.executeUpdate();
	   	 						st2.close();
	   	 					}
	   	 				}
	   	 				request.setAttribute("code", code);
	   	 				request.setAttribute("code1", code1);
	   	 				request.getRequestDispatcher("check.jsp").forward(request, response);
	   	 			} //While
	   	 		}	  //action
        	}		//try 
        	catch(Exception e){
        		out.println(e);
    		}
	}
	private static String computeSignature(String baseString, String keyString) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKey secretKey = null;
		byte[] keyBytes = keyString.getBytes();
        secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        byte[] text = baseString.getBytes();
        return new String(Base64.encodeBase64(mac.doFinal(text))).trim();
	}
}