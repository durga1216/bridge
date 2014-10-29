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
	    String secret11=(String ) session.getAttribute("secret1");
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
	             st=con.prepareStatement("SELECT * From auth where appid=?");
	             st.setString(1, appid);
		         ResultSet rs = st.executeQuery();
		         while(rs.next()){
		        	 String oauth_signature_method=rs.getString("osmeth");String url1=rs.getString("ourl1");
	            	 String ourl21=rs.getString("ourl2");String ourl31=rs.getString("ourl3");
	            	 String oauth_consumer_key=rs.getString("ockey"); String secret=rs.getString("oskey");
	            	 String oreq1=rs.getString("oreq");
	            	 //========initial=========
	            	 if(oreq1.equals("GET")){
	            	 String uuid_string = UUID.randomUUID().toString();
	                 uuid_string = uuid_string.replaceAll("-", "");
	                 String oauth_nonce = uuid_string; 
	                 String eurl = URLEncoder.encode(ourl31, "UTF-8");
	                 int millis = (int) System.currentTimeMillis() * -1;// any relatively random alphanumeric string will work here. I used UUID minus "-" signs
	                 String oauth_timestamp = (new Long(System.currentTimeMillis()/1000)).toString();
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
	         		 String[] acctok=tok.split("&");
	         		 session.setAttribute("access_token1", acctok[1]);
	         		 session.setAttribute("access_secret1", acctok[2]);
	         		 PreparedStatement st2=con.prepareStatement("insert into token (tempid,tid,oauthtoken,secret) values ('"+tempid+"','"+appid+"','"+acctok[1]+"','"+acctok[2]+"')");
				   	 st2.executeUpdate();
				   	 st2.close();
	         		 request.setAttribute("code", 200);
		             request.setAttribute("code1", 200);
			         request.getRequestDispatcher("check.jsp").forward(request, response);
            	 }         
		         else{
			        	 String uuid_string = UUID.randomUUID().toString();
		                 uuid_string = uuid_string.replaceAll("-", "");
		                 String oauth_nonce = uuid_string; 
		                 String eurl = URLEncoder.encode(ourl31, "UTF-8");
		                 int millis = (int) System.currentTimeMillis() * -1;// any relatively random alphanumeric string will work here. I used UUID minus "-" signs
		                 String oauth_timestamp = (new Long(System.currentTimeMillis()/1000)).toString();
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
			       		 String[] acctok=tok.split("&");
			       		 session.setAttribute("xml1", tok);
			       		 session.setAttribute("access_token1", acctok[0]);
			       		 session.setAttribute("access_secret1", acctok[1]);
			       		 PreparedStatement st2=con.prepareStatement("insert into token (tempid,tid,oauthtoken,secret) values ('"+tempid+"','"+appid+"','"+acctok[0]+"','"+acctok[1]+"')");
					   	 st2.executeUpdate();
					   	 st2.close();
			       		 request.setAttribute("code", 200);
			             request.setAttribute("code1", 200);
				         request.getRequestDispatcher("check.jsp").forward(request, response);
	         	 }
	         }
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
