package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

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
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 * Servlet implementation class Oauth1call
 */
@WebServlet("/Oauth1call")
public class Oauth1call extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Oauth1call() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   	response.setHeader("Content-Type","text/html;charset=UTF-8");
	   	InputStream inputStream=null;
	   	PrintWriter out=response.getWriter();
		String oauth_verifier=request.getParameter("oauth_verifier");
		String oauth_token=request.getParameter("oauth_token");
		HttpSession session=request.getSession(true);
	    String appid=(String) session.getAttribute("tid");
	    String tempid=(String) session.getAttribute("tempid");
	    String eurl1=(String) session.getAttribute("ourl");
	    String secret11=(String ) session.getAttribute("secret1");
	    String otyp=(String ) session.getAttribute("otyp");
	    String str="";String oauthtk1="";String sectk1="";
	    //out.println(secret11);
	    String[] tok1=secret11.split("=");
	    String sec1=tok1[1];
	    //System.out.println(sec1);
	    //String oauth_signature1=(String) session.getAttribute("oauth_signature1");
	    // String parameter_string=(String) session.getAttribute("parameter_string");
	    try{
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
		    final Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
             PreparedStatement st=null;
             st=con.prepareStatement("SELECT * From auth t1 JOIN triger t2 ON t1.appid=t2.appid where t1.appid=?");
             st.setString(1, appid);
	         ResultSet rs = st.executeQuery();
	         while(rs.next()){
	        	 String oauth_signature_method=rs.getString("osmeth");String url1=rs.getString("ourl1");
            	 String ourl21=rs.getString("ourl2");String ourl31=rs.getString("ourl3");
            	 String oauth_consumer_key=rs.getString("ockey"); String secret=rs.getString("oskey");
            	 String oreq1=rs.getString("oreq");String rmethod1=rs.getString("select2");
            	 String rmethod=rs.getString("rmethod");String endurl1=rs.getString("t1");
            	 //========initial=========
            	 if(oreq1.equals("GET")){
            		 String uuid_string = UUID.randomUUID().toString();
	                 uuid_string = uuid_string.replaceAll("-", "");
	                 String oauth_nonce = uuid_string; 
	                 String eurl = URLEncoder.encode(ourl31, "UTF-8");
	                 long oauth_timestamp = System.currentTimeMillis()/1000;
	                 String parameter_string="";
	 				 String call="https://bridge-minddotss.rhcloud.com/Oauth1call";
		   	 			//For checking the callback is required or not
	                 if(rmethod1.equals("DELETE")){
	                	 parameter_string = "oauth_callback="+URLEncoder.encode(call, "UTF-8")+"&oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp + "&oauth_token="+oauth_token+"&oauth_verifier="+oauth_verifier+"&oauth_version=1.0";        
	                 }
	                 else{
	                	 parameter_string = "oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp + "&oauth_token="+oauth_token+"&oauth_verifier="+oauth_verifier+"&oauth_version=1.0";        
	                 }
	                 String signature_base_string = oreq1+"&"+eurl+"&" + URLEncoder.encode(parameter_string, "UTF-8");
	                 System.out.println("signature_base_string=" + signature_base_string);
	                 String oauth_signature = "";String oauth_signature1 = "";
	                    //=========signature===========
	                 try {
		                     oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
		                     oauth_signature1 = URLEncoder.encode(oauth_signature, "UTF-8");
	                 } catch (GeneralSecurityException e) {
	                     // TODO Auto-generated catch block
	                     	 e.printStackTrace();
	                 }
	                 String authorization_header_string = "OAuth oauth_consumer_key=\"" + oauth_consumer_key + "\","
	                     		+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
	                            oauth_timestamp + "\",oauth_version=\"1.0\"";
	                 String actok=ourl31+"?"+parameter_string+"&oauth_signature="+oauth_signature1;
	            	 out.println(actok);
	            	 HttpClient httpclient1 = new DefaultHttpClient();
	                 HttpResponse response1=null;
	                 HttpGet get1=new HttpGet(actok);
	                 response1=httpclient1.execute(get1);
	                 BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent())); 
	         		 StringBuffer result = new StringBuffer();
	         		 String line = "";
	         		 while ((line = rd.readLine()) != null) {
	         			 	result.append(line);
	         		 }
	         		 String tok=result.toString();
	         		 out.println(tok);
	         		 String secrt="";String tokn="";
	         		 String[] chk1=tok.split("&");
	         		 for(int i=0;i<chk1.length;i++){
	         			 String[] stest=chk1[i].split("=");
	         			 if(stest[0].equals("oauth_token")){
	         				 tokn=chk1[i];
	         			 }else if(stest[0].equals("oauth_token_secret")){
	         				 secrt=chk1[i];
	         			 }
	         		 }
		         	 session.setAttribute("samp", tok+"\n"+oauth_token+"\n"+sec1+"\n"+signature_base_string+"\n"+actok);
	         		 session.setAttribute("access_token1", tokn);
	         		 session.setAttribute("access_secret1", secrt);
	         		 PreparedStatement st2=con.prepareStatement("insert into token (tempid,tid,oauthtoken,secret) values ('"+tempid+"','"+appid+"','"+tokn+"','"+secrt+"')");
				   	 st2.executeUpdate();
				   	 st2.close();
				   	 oauthtk1=tokn;sectk1=secrt;
	         		 request.setAttribute("code", 200);
		             request.setAttribute("code1", 200);
			         request.getRequestDispatcher("check.jsp").forward(request, response);
            	 }         
		         else{
		        	 String uuid_string = UUID.randomUUID().toString();
	                 uuid_string = uuid_string.replaceAll("-", "");
	                 String oauth_nonce = uuid_string; 
	                 String eurl = URLEncoder.encode(ourl31, "UTF-8");
	                 long oauth_timestamp = System.currentTimeMillis()/1000;
	                 String parameter_string = "oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp + "&oauth_token="+oauth_token+"&oauth_verifier="+oauth_verifier+"&oauth_version=1.0";        
	                 String signature_base_string = oreq1+"&"+eurl+"&" + URLEncoder.encode(parameter_string, "UTF-8");
	                 System.out.println("signature_base_string=" + signature_base_string);
	                 String oauth_signature = "";String oauth_signature1 = "";
	                    //=========signature===========
	                 try {
	                        oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
	                        oauth_signature1 = URLEncoder.encode(oauth_signature, "UTF-8");
	                 } catch (GeneralSecurityException e) {
	                     // TODO Auto-generated catch block
	                     	e.printStackTrace();
	                 }
	                 String authorization_header_string = "OAuth oauth_consumer_key=\"" + oauth_consumer_key + "\","
	                     		+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_token=\""+oauth_token+"\",oauth_verifier=\""+oauth_verifier+"\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
	                            oauth_timestamp + "\",oauth_version=\"1.0\"";
	                 String actok=ourl31+"?"+parameter_string+"&oauth_signature="+oauth_signature1;
	            	// out.println(actok);
	            	 HttpClient httpclient = new DefaultHttpClient();
	                 HttpResponse response1=null;
	                 HttpPost post = new HttpPost(ourl31);
                     post.setHeader("Authorization", authorization_header_string);
    				 response1 = httpclient.execute(post);
                     BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
		       		 StringBuffer result = new StringBuffer();
		       		 String line = "";
		       		 while ((line = rd.readLine()) != null) {
		       			 	result.append(line);
		       		 }
		       		 String tok=result.toString();
		       		 //out.println(tok);
	         		 String secrt="";String tokn="";
	         		 String[] chk1=tok.split("&");
	         		 for(int i=0;i<chk1.length;i++){
	         			 String[] stest=chk1[i].split("=");
	         			 if(stest[0].equals("oauth_token")){
	         				 tokn=chk1[i];
	         			 }else if(stest[0].equals("oauth_token_secret")){
	         				 secrt=chk1[i];
	         			 }
	         		 }
		   		   	 oauthtk1=tokn;sectk1=secrt;
		       		 session.setAttribute("access_token1", tokn);
		       		 session.setAttribute("access_secret1", secrt);
		       		 PreparedStatement st2=con.prepareStatement("insert into token (tempid,tid,oauthtoken,secret) values ('"+tempid+"','"+appid+"','"+tokn+"','"+secrt+"')");
				   	 st2.executeUpdate();
				   	 st2.close();
		       		 request.setAttribute("code", 200);
		             request.setAttribute("code1", 200);
			         request.getRequestDispatcher("check.jsp").forward(request, response);
	         	 }
            	 if(otyp.equals("trigger")){
	            	 if(rmethod.equals ("Get")){
						//========initial=========
	   					String uuid_string = UUID.randomUUID().toString();
	   					oauth_token=oauthtk1;
	   					String[] tok11=oauth_token.split("=");
			   			String oauthtk=tok11[1];
			   			String[] tok2=sectk1.split("=");
			   			String sectk=tok2[1];
			   			
	   					uuid_string = uuid_string.replaceAll("-", "");
	   					String oauth_nonce = uuid_string; 
	   					String enurl = URLEncoder.encode(endurl1, "UTF-8");
	   					long oauth_timestamp = System.currentTimeMillis()/1000;
	   					String parameter_string ="";
	   					if(eurl1.equals("null")){
	   						parameter_string ="oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
	   					}
	   					else{
	   						parameter_string = eurl1+"&oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
	   					}
	   					String[] tst1=parameter_string.split("&");Arrays.sort(tst1);
	   					int no=tst1.length;String tst3="";
	   					for(int i=1;i<no;i++){
	   						tst3=tst3+"&"+tst1[i];
	   					}
	   					String tst4=tst1[0]+tst3;
	   					String signature_base_string = "GET"+"&"+enurl+"&" + URLEncoder.encode(tst4, "UTF-8");
	   					//  out.println("signature_base_string=" + signature_base_string);
	                    String oauth_signature = "";String oauth_signature1 = "";
	                    try {
	                    	oauth_signature = computeSignature(signature_base_string, secret+"&"+sectk);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
	                    	oauth_signature1 = URLEncoder.encode(oauth_signature, "UTF-8");
	              		} 
	                    catch (GeneralSecurityException e) {
	                    	// TODO Auto-generated catch block
	                    	out.println(e);
	                    }
	                    String actok=endurl1+"?"+tst4+"&oauth_signature="+oauth_signature1;
	                    // out.println(actok+"---"+secret+"---"+sec1);
	                    HttpClient httpclient = new DefaultHttpClient();
	                    HttpGet get1=new HttpGet(actok);
	                    HttpResponse response1=httpclient.execute(get1);
	                    BufferedReader rd = new BufferedReader( new InputStreamReader(response1.getEntity().getContent()));
	                    StringBuffer result = new StringBuffer();
	                    String line = "";
	                    while ((line = rd.readLine()) != null) {
	                    	result.append(line);
	                    }
	                    str=result.toString();
	                    session.setAttribute("xml1", str);
	                    session.setAttribute("samp", "tok---"+oauth_token+"--sec---"+sectk1+"---eurl--"+eurl1+"---res---"+str+"----act--"+actok);
		   			}
		   			else if(rmethod.equals ("Post")){
		   				out.println("in post");
		   				String exhead="";
		   				oauth_token=oauthtk1;
	   					String[] tok11=oauth_token.split("=");
			   			String oauthtk=tok11[1];
			   			String[] tok2=sectk1.split("=");
			   			String sectk=tok2[1];
		   				out.println("inside"+exhead);
		   				String uuid_string = UUID.randomUUID().toString();
		   				uuid_string = uuid_string.replaceAll("-", "");
		   				String oauth_nonce = uuid_string; 
		   				String enurl = URLEncoder.encode(endurl1, "UTF-8");
		   				long oauth_timestamp = System.currentTimeMillis()/1000;
		   				String parameter_string ="";
		   				if(eurl1.equals("null")){
		   					parameter_string ="oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
		   				}
		   				else{
		   					parameter_string = eurl1+"&oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
		   				}
		   				String[] tst1=parameter_string.split("&");Arrays.sort(tst1);
		   				int no=tst1.length;String tst3="";
		   				for(int i=1;i<no;i++){
		   					tst3=tst3+"&"+tst1[i];
		   				}
		   				String tst4=tst1[0]+tst3;
		   				String signature_base_string = "POST"+"&"+enurl+"&" + URLEncoder.encode(tst4, "UTF-8");
		   				//  System.out.println("signature_base_string=" + signature_base_string);
	                    String oauth_signature = "";String oauth_signature1 = "";
	                    try {
	                    	oauth_signature = computeSignature(signature_base_string, secret+"&"+sectk);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
	                    	oauth_signature1 = URLEncoder.encode(oauth_signature, "UTF-8");
		                }	
	                    catch (GeneralSecurityException e) {
	                    	// TODO Auto-generated catch block
	                    	out.println(e);
	                	}
	                    String authorization_header_string="";
	                    if(exhead.equals("null")){
	                    	authorization_header_string = "OAuth oauth_consumer_key=\"" + oauth_consumer_key + "\","
	                    			+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_token=\""+oauthtk+"\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
	                    			oauth_timestamp + "\",oauth_version=\"1.0\"";}
	                    else{
	                    	authorization_header_string = "OAuth "+exhead+",oauth_consumer_key=\"" + oauth_consumer_key + "\","
		                     		+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_access_token=\""+oauthtk+"\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
		                            oauth_timestamp + "\",oauth_version=\"1.0\"";
	                    }
	                    out.println(authorization_header_string);
	                    HttpClient httpclient = new DefaultHttpClient();
	                    HttpResponse response1=null;
	                    HttpPost post = new HttpPost(endurl1);
	                    post.setHeader("Authorization", authorization_header_string);
	                    response1 = httpclient.execute(post);
	                    
	                    BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	                    StringBuffer result = new StringBuffer();
	                    String line = "";
	                    while ((line = rd.readLine()) != null) {
	                    	result.append(line);
	                    }
	                    str=result.toString();
	                    session.setAttribute("xml1", str);
		   			}
            	 }
	         }
	         con.close();
	    }
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
