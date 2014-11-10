package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.scribe.model.OAuthConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class OauthCall
 */
@WebServlet("/OauthCall")
public class OauthCall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OauthCall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		HttpSession session=request.getSession(true);
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
			String tokenurl=(String) session.getAttribute("tokenurl");
			String apikey=(String) session.getAttribute("ckey");
			String apisecvalue=(String) session.getAttribute("cseckey");
			String rm1=(String) session.getAttribute("rm1");
			String tempid=(String) session.getAttribute("tempid");
			String tid=(String) session.getAttribute("tid");
			String but=(String) session.getAttribute("but");
			String code = request.getParameter(OAuthConstants.CODE);
			String res=null;
			String responseMsg=null;
			String access_token=null;
			StringBuilder responseBody=new StringBuilder();
	        String line = "";
			HttpClient client=new DefaultHttpClient();
	        pw.println("<body style='background-color:#ff9900;'>");
			if(rm1.equals("POST")){
				HttpPost post = new HttpPost(tokenurl);
				try{
					List <NameValuePair> cod = new ArrayList <NameValuePair>(5);
					cod.add(new BasicNameValuePair("code", code)); 
					cod.add(new BasicNameValuePair("grant_type", "authorization_code")); 
					cod.add(new BasicNameValuePair("client_id",apikey));
					cod.add(new BasicNameValuePair("client_secret",apisecvalue)); 
					cod.add(new BasicNameValuePair("redirect_uri","https://bridge-minddotss.rhcloud.com/OauthCall")); 
					post.setEntity(new UrlEncodedFormEntity(cod));
					HttpResponse response1 = client.execute(post);
					BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
					while ((responseMsg = rd.readLine()) != null) {
						responseBody.append(responseMsg);	        
					}
						res=responseBody.toString();
					//session.setAttribute("xml1", responseBody);
				}
				catch(Exception e){
					pw.println(e);
				}
			}
			else if(rm1.equals("GET")){
				pw.println("inside get");
				HttpGet get=new HttpGet(tokenurl+"?code="+code+"&grant_type=authorization_code&client_id="+apikey+"&client_secret="+apisecvalue+"&redirect_uri=https://mindapp-pulpy.rhcloud.com/OauthCallBackServlet");
				List <NameValuePair> cod = new ArrayList <NameValuePair>();
				cod.add(new BasicNameValuePair("code",code));
				cod.add(new BasicNameValuePair("grant_type","authorization_code"));
				cod.add(new BasicNameValuePair("client_id",apikey));
				cod.add(new BasicNameValuePair("client_secret",apisecvalue)); 
				cod.add(new BasicNameValuePair("redirect_uri","https://bridge-minddotss.rhcloud.com/OauthCall")); 
				HttpResponse response1 = client.execute(get);
				BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));   		    
				while ((responseMsg = rd.readLine()) != null) {
					responseBody.append(responseMsg);	        
				}
					res=responseBody.toString();
				//session.setAttribute("xml1", responseBody);   
		 	}
			pw.println("zcvdsfgsergsedt");
			pw.println(res);
			BufferedReader br=new BufferedReader(new StringReader(res));
			while ((line = br.readLine()) != null) {
				if(line.startsWith("{") || line.startsWith("[{")){
					JSONObject json = null;
					//PrintWriter out=response.getWriter();
					pw.println(responseBody);
					json = new JSONObject(responseBody);
					access_token = json.getString("access_token"); 
				}
				else if(line.startsWith("<?") || line.endsWith("?>")){
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(res)));  
					NodeList nl = document.getElementsByTagName("access_token"); 
					Node n = nl.item(0);
					access_token = n.getFirstChild().getNodeValue();
				}
				else{
					String[] pairs = line.split("&");
					for (String pair : pairs) {
						String[] kv = pair.split("=");
						if (kv.length != 2) {
							throw new RuntimeException("Unexpected auth response");
						} 
						else {
							if (kv[0].equals("access_token")) {
								access_token = kv[1];
							}
						}         
	                 } 
				}
            }
			PreparedStatement st3=con.prepareStatement("select * from triger t1 join auth t2 on t1.appid=t2.appid join title t3 on t1.appid=t3.appid where t1.appid ='"+tid+"'");
			ResultSet rs=st3.executeQuery();
			while(rs.next()){
				String str="";
				String mode=rs.getString("mode");String rmethod=rs.getString("rmethod");
				String treplace=rs.getString("treplace");String endurl1=rs.getString("t1");
				String tlabel=rs.getString("tlabel");
				if(mode.equals("Trigger")){
					if(rmethod.equals("Get")){ 
	   					if("Authorization:Bearer".equals(treplace)){
	   						HttpGet get=new HttpGet(endurl1);
	   						get.addHeader("Authorization", "Bearer "+access_token);
	   						HttpResponse response1 = client.execute(get);
	   						BufferedReader rd = new BufferedReader(
	   								new InputStreamReader(response1.getEntity().getContent()));
	   						while ((line = rd.readLine()) != null) {
	   							str+=line;		     			
	   						}
	   						session.setAttribute("xml1", str);
	   					}
	   					else if("QueryString".equals(treplace)){
	   						//if parameter are ther put it here
	   						String param=tlabel+"="+access_token;	
	   						String pointurl=endurl1+"?"+param;
	   						HttpGet get=new HttpGet(pointurl);
	   						HttpResponse response1=client.execute(get);
	   						BufferedReader rd = new BufferedReader
	   								(new InputStreamReader(response1.getEntity().getContent())); 
	   						while ((line = rd.readLine()) != null) {
	   							str+=line;
	   						}		
	   						session.setAttribute("xml1", str);
	   					} // query string
	   				}//get
	   				else if(rmethod.equals("Post")){
	   					HttpPost post=new HttpPost(endurl1);
	   					if("Authorization:Bearer".equals(treplace)){					     			
	   						post.addHeader("Authorization", "Bearer "+access_token);
	   						HttpResponse response1=client.execute(post);
	   						BufferedReader rd = new BufferedReader(
	   								new InputStreamReader(response1.getEntity().getContent()));
	   						while ((line = rd.readLine()) != null) {
	   							str+=line;		     			
   							}
	   						session.setAttribute("xml1", str);
	   					}
	   					else if("QueryString".equals(treplace)){
	   						List <NameValuePair> cod = new ArrayList <NameValuePair>();
	   						cod.add(new BasicNameValuePair(tlabel,access_token));
	   						post.setEntity(new UrlEncodedFormEntity(cod));
	   						HttpResponse response1 = client.execute(post);
	   						BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	   						while ((line = rd.readLine()) != null) {
	   							str+=line;	        
   							}
	   						session.setAttribute("xml1", str);
	   					}	
	   				}
				}
			}
			session.setAttribute("access_token", access_token);						
			PreparedStatement st2=con.prepareStatement("insert into token (tempid,tid,oauthtoken) values ('"+tempid+"','"+tid+"','"+access_token+"')");
			st2.executeUpdate();
			st2.close();
			//PreparedStatement ps=con.prepareStatement("insert into testing (data) values('"+access_token+"')");
			//ps.executeUpdate();
			if(access_token.equals("")){
				pw.println("<br><br><center><b><h2><font color='white'>Authentication Error</font></center></h2></b>");
				if(but.equals("trig")){
					request.setAttribute("code", 400);
					request.setAttribute("code1", 0);
					request.getRequestDispatcher("check.jsp").forward(request, response);
				}else if(but.equals("act")){
					request.setAttribute("code", 200);
					request.setAttribute("code1", 400);
					request.getRequestDispatcher("check.jsp").forward(request, response);
				}
			}
			else{
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				pw.println("<br><br><center><b><h2><font color='#ffffff;'>Sucessfully Authenticated</font></center></h2></b>");
				if(but.equals("trig")){
					request.setAttribute("code", 200);
					request.setAttribute("code1", 0);
					request.getRequestDispatcher("check.jsp").forward(request, response);
            	}else if(but.equals("act")){
            		request.setAttribute("code", 200);
            		request.setAttribute("code1", 200);
            		request.getRequestDispatcher("check.jsp").forward(request, response);
            	}
			}
		}
		catch(Exception e){
			pw.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
