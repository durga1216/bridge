package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;

/**
 * Servlet implementation class Final
 */
@WebServlet("/Final")
public class Final extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Final() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final PrintWriter out=response.getWriter();
		final HttpSession session=request.getSession(true);
		ArrayList<String> ar=new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    final Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);	
		    //Get all the valid Trigger data
		    PreparedStatement st=con.prepareStatement("select tempid from trig_all");
		    ResultSet rs1=st.executeQuery();
		    while(rs1.next()){
		    	ar.add(rs1.getString("tempid"));
		    }
		    int ttl=ar.size();
		    //Create Thread for Every Trigger
		    Thread[] th =new Thread[ttl];
			for(int i=0;i<ttl;i++){
		    final String da=ar.get(i);
			th[i]=new Thread(){
			public void run(){
				try{
					String str="";String eurl="";String resformat="";
					PreparedStatement st1=con.prepareStatement("select * from trig_all where tempid=?");
					st1.setString(1, da);
					ResultSet rs=st1.executeQuery();
					while(rs.next()){
				   		String authen=rs.getString("authen");String apkey=rs.getString("apkey");
				   		String ak1=rs.getString("aplabel");
				   		String tempid=rs.getString("tempid");String tid=rs.getString("tid");
				   		String rmethod=rs.getString("rmethod");String rformat=rs.getString("rformat");
				   		resformat=rs.getString("resformat");String endurl1=rs.getString("emethod");
				   		String dn=rs.getString("dn");String dn1=rs.getString("dn1");
				   		String p1=rs.getString("p1");String p2=rs.getString("p2");
				   		String p3=rs.getString("p3");String p4=rs.getString("p4");
				   		String p5=rs.getString("p5");String p6=rs.getString("p6");
				   		String p7=rs.getString("p7");
				   		String b2=rs.getString("b2");String b4=rs.getString("b4");
				   		String tlabel=rs.getString("tlabel");String treplace=rs.getString("treplace");
				   		String pv1=rs.getString("pv1");String pv2=rs.getString("pv2");
				   		String pv3=rs.getString("pv3");String pv4=rs.getString("pv4");
				   		String pv5=rs.getString("pv5");String pv6=rs.getString("pv6");
				   		String pv7=rs.getString("p7");
				   		String h1=rs.getString("h1"); String hv1=rs.getString("hv1");
				   		String h2=rs.getString("h2"); String hv2=rs.getString("hv2");
				   		String h3=rs.getString("h3"); String hv3=rs.getString("hv3");
				   		String h4=rs.getString("h4"); String hv4=rs.getString("hv4");
				   		String h5=rs.getString("h5"); String hv5=rs.getString("hv5");
				   		if(authen.equals("No Auth")){
			            	HttpClient cli=new DefaultHttpClient();
			            	HttpGet get=new HttpGet(endurl1);
			            	HttpResponse res=cli.execute(get);
			            	BufferedReader bf=new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
			            	String line="";
			            	while((line=bf.readLine())!=null){
			            		str+=line;
			            	}
				   		}else if(authen.equals("API keys")){
				   			if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
				   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
		        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
				   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
		        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
				   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
		        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2)){
				   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2;}
		        		 
				   			else if(!"null".equals(p1)){
				   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1;}
				   			
				   			else if("null".equals(p1))
				   				eurl=endurl1+"?"+ak1+"="+apkey;
		        		 
				   			else if("null".equals(ak1) && "null".equals(apkey))
				   				eurl=endurl1;	        		
		        		
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
			   			}
				   		else if(authen.equals("Basic Auth")){
				   			URL url1=new URL(endurl1);
				   			HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
				   			connection.setDoOutput(true);
				   			connection.setDoInput(true);
				   			connection.setRequestMethod("GET");
				   			String encoding=null;
				   			if(!b2.equals("") && !b2.equals("null")){
				   				encoding = new String(org.apache.commons.codec.binary.Base64.encodeBase64   
		                        		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+b4)));
				   				connection.setRequestProperty  ("Authorization", "Basic " + encoding);
				   			}
				   			if(!"".equals(h1) && !"".equals(h2) && !"".equals(h3)){
				            	connection.setRequestProperty(h1, hv1);connection.setRequestProperty(h2, hv2); connection.setRequestProperty(h3, hv3);  
				            }
				   			else if(!"".equals(h1) && !"".equals(h2)){
				            	connection.setRequestProperty(h1, hv1);connection.setRequestProperty(h2, hv2);  
				            }
				   			else if(!"".equals(h1)){
				            	connection.setRequestProperty(h1, hv1);  
				            }
				   				connection.setRequestProperty("Content-Type", "application/json");
				   				InputStream stream = (InputStream)connection.getInputStream();
				   				BufferedReader bf=new BufferedReader(new InputStreamReader(stream));
				   				String lin="";
		    				while((lin=bf.readLine())!=null){
		    					str+=lin;
		    				}
			   			}
				   		else if(authen.equals("Oauth1")){
				   			String res="";
				   			//out.println("in Oauth");
				   			String oauth_signature_method=rs.getString("osmeth");
				   			String oauth_consumer_key=rs.getString("ockey"); String secret=rs.getString("oskey");
				   			String oauth_token="";
				   			String access_secret1="";
				   			PreparedStatement st4=con.prepareStatement("select * from token where tempid=? && tid=?");
				   			st4.setString(1, da);
				   			st4.setString(2, tid);
				   			ResultSet rs4=st4.executeQuery();
				   			while(rs4.next()){
				   				oauth_token=rs4.getString("oauthtoken");
				   				access_secret1=rs4.getString("secret");
				   			}
				   			String[] tok11=oauth_token.split("=");
				   			String oauthtk=tok11[1];
				   			String[] tok1=access_secret1.split("=");
				   			String sec1=tok1[1];	 
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

				   			if(rmethod.equals ("Get")){
		   					//========initial=========
			   					String uuid_string = UUID.randomUUID().toString();
			   					uuid_string = uuid_string.replaceAll("-", "");
			   					String oauth_nonce = uuid_string; 
			   					String enurl = URLEncoder.encode(endurl1, "UTF-8");
			   					String oauth_timestamp = (new Long(System.currentTimeMillis()/1000)).toString();
			   					String parameter_string ="";
			   					if(eurl.equals("null")){
			   						parameter_string ="oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
			   					}
			   					else{
			   						parameter_string = eurl+"&oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
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
			                    	oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
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
				   			}
				   			else if(rmethod.equals ("Post")){
				   				out.println("in post");
				   				String exhead="";
				   				if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6) && !"null".equals(p7)){
				   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\","+p4+"=\""+pv4+"\","+p5+"=\""+pv5+"\","+p6+"=\""+pv6+"\","+p7+"=\""+pv7+"\"";}
			        		 
				   				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
				   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\","+p4+"=\""+pv4+"\","+p5+"=\""+pv5+"\","+p6+"=\""+pv6+"\"";}
			        		 
				   				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
				   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\","+p4+"=\""+pv4+"\","+p5+"=\""+pv5+"\"";}
			        		 
				   				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
				   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\","+p4+"=\""+pv4+"\"";}
			        		 
				   				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
				   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\"";}
			        		 
				   				else if(!"null".equals(p1) && !"null".equals(p2)){
				   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\"";}
			        		 
				   				else if(!"null".equals(p1)){
				   					exhead=p1+"=\""+pv1+"\"";}
				   				
				   				else if("null".equals(p1))
				   					exhead="null";
			        		 
				   				out.println("inside"+exhead);
				   				String uuid_string = UUID.randomUUID().toString();
				   				uuid_string = uuid_string.replaceAll("-", "");
				   				String oauth_nonce = uuid_string; 
				   				String enurl = URLEncoder.encode(endurl1, "UTF-8");
				   				String oauth_timestamp = (new Long(System.currentTimeMillis()/1000)).toString();
				   				String parameter_string ="";
				   				if(eurl.equals("null")){
				   					parameter_string ="oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
				   				}
				   				else{
				   					parameter_string = eurl+"&oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
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
			                    	oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
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
				   			} 
				   			session.setAttribute("samp", str);	  
				   		}
				   		else if(authen.equals("Oauth2")){
				   			
				   			if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
				   				eurl=endurl1+"?"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
		        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
				   				eurl=endurl1+"?"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
		        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
				   				eurl=endurl1+"?"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
		        		 
				   			else if(!"null".equals(p1) && !"null".equals(p2)){
				   				eurl=endurl1+"?"+p1+"="+pv1+"&"+p2+"="+pv2;}
		        		 
				   			else if(!"null".equals(p1)){
				   				eurl=endurl1+"?"+p1+"="+pv1;}
				   			
				   			else if("null".equals(p1))
				   				eurl=endurl1;      
				   			
				   			PreparedStatement st=con.prepareStatement("select * from token where tempid=? && tid=?");
				   			st.setString(1, da);
				   			st.setString(2, tid);
				   			ResultSet rs1=st.executeQuery();String access_token="";
				   			while(rs1.next()){
				   				access_token=rs1.getString("oauthtoken");
				   			}
				   			HttpClient client = new DefaultHttpClient();
				   			String line="";
				   			if(rmethod.equals("DELETE")){
				   				//String url="https://www.googleapis.com/analytics/v3/data/ga?ids=ga%3A85990559&start-date=2014-01-01&end-date=today&metrics=ga%3Apageviews";
								HttpClient cli=new DefaultHttpClient();
								HttpGet get=new HttpGet(eurl);
								get.addHeader("Authorization","Bearer "+access_token);
								get.addHeader("X-JavaScript-User-Agent","Google APIs Explorer");
								HttpResponse response=cli.execute(get);
								BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
								while((line=bf.readLine())!=null){
										str=str+line;
								}
								//TODO For getting spread sheet elements
//				   				String CLIENT_ID = "758153664645-n04dc4ki6pr383jdnrq6hmgjsvbsibls";
//				   				String CLIENT_SECRET = "YsLu7TgD4q_NmheHjx4W2Okf";
//				   				HttpTransport transport = new NetHttpTransport();
//				   			    JacksonFactory jsonFactory = new JacksonFactory();
//				   				Credential credencial = new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
//				   						.setJsonFactory(jsonFactory).setTransport(transport).build()
//				   						.setAccessToken(access_token).setRefreshToken(null);
//				   				SpreadsheetService service =new SpreadsheetService("Aplication-name");
//				   				service.setOAuth2Credentials(credencial);
//				   				URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
//				   				SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,SpreadsheetFeed.class);
//				   				List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets = feed.getEntries();
//				   				if (spreadsheets.isEmpty()) {
//				   					// TODO: There were no spreadsheets, act accordingly.
//				   				}
//				   				com.google.gdata.data.spreadsheet.SpreadsheetEntry spreadsheet = spreadsheets.get(0);
//				   				WorksheetFeed worksheetFeed = service.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
//				   				List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
//				   				WorksheetEntry worksheet = worksheets.get(0);
//				   				URL cellFeedUrl = worksheet.getCellFeedUrl();
//				   				CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
//				   				JSONObject obj = new JSONObject();
//				   				JSONObject obj1 = new JSONObject();
//				   				String title=spreadsheet.getTitle().getPlainText();
//				   				for (CellEntry cell : cellFeed.getEntries()) {
//				   					String name=cell.getTitle().getPlainText();
//				   					String value=cell.getCell().getValue();
//				   					obj.put(name, value);
//				   				}
//				   				obj1.put(title, obj);
//				   				String cng=obj1.toString();
//				   				cng=cng.replaceAll(" ", "_");
//				   				str=cng;
				   			}
				   			else if(rmethod.equals("GET")){ 
				   				if("Authorization:Bearer".equals(treplace)){
				   					HttpGet get=new HttpGet(endurl1);
				   					get.addHeader("Authorization", "Bearer "+access_token);
				   					HttpResponse response1 = client.execute(get);
				   					BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
					     			while ((line = rd.readLine()) != null) {
					     				str=line;		     			
				     				}
				   				}
				   				else if("QueryString".equals(treplace)){
				   					String param = null;
				   					// List<NameValuePair> params = new LinkedList<NameValuePair>();

				   					if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6))
				   						param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5+"&"+p6+"="+pv6;
						        		 
				   					else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5))
				   						param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;
						     	
				   					else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4))
				   						param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;

				   					else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3))
				   						param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;

				   					else if(!"null".equals(p1) && !"null".equals(p2))
				   						param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2;

				   					else if(!"null".equals(p1))
				   						param=tlabel+"="+access_token+"&"+p1+"="+pv1;

				   					else if("null".equals(p1))
				   						param=tlabel+"="+access_token;
				   					
				   					String pointurl=endurl1+"?"+param;
				   					//String paramString = URLEncodedUtils.format(param, "utf-8");
				   					HttpGet get=new HttpGet(pointurl);
				   					HttpResponse response1=client.execute(get);
				   					BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));   
						    		while ((line = rd.readLine()) != null) {
						    			str=line;
						    		}    			
				   				} // query string
				   			}//get
					    	else if(rmethod.equals("POST")){
					     		HttpPost post=new HttpPost(endurl1);
					     		if("Authorization:Bearer".equals(treplace)){
					     			post.addHeader("Authorization", "Bearer "+access_token);
									HttpResponse response1=client.execute(post);
									BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
				     				while ((line = rd.readLine()) != null) {
				     					str=line;		     			}
									}
				     			else if("QueryString".equals(treplace)){	
				     				List <NameValuePair> cod = new ArrayList <NameValuePair>();
				     				if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
								    	cod.add(new BasicNameValuePair(tlabel,access_token));
							    	    cod.add(new BasicNameValuePair(p1,p1));
							    	    cod.add(new BasicNameValuePair(p2,p2));
							    	    cod.add(new BasicNameValuePair(p3,p3));
							    	    cod.add(new BasicNameValuePair(p4,p4));
							    	    cod.add(new BasicNameValuePair(p5,p5));
							    	    cod.add(new BasicNameValuePair(p6,p6));
						    	    }
				     				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
				     					cod.add(new BasicNameValuePair(tlabel,access_token));
				     					cod.add(new BasicNameValuePair(p1,p1));
				     					cod.add(new BasicNameValuePair(p2,p2));
				     					cod.add(new BasicNameValuePair(p3,p3));
				     					cod.add(new BasicNameValuePair(p4,p4));
				     					cod.add(new BasicNameValuePair(p5,p5));	
			     					}    	 
				     				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){cod.add(new BasicNameValuePair(tlabel,access_token));
				     					cod.add(new BasicNameValuePair(p1,p1));
				     					cod.add(new BasicNameValuePair(p2,p2));
				     					cod.add(new BasicNameValuePair(p3,p3));
				     					cod.add(new BasicNameValuePair(p4,p4));
				     				} 
					     		 	else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){cod.add(new BasicNameValuePair(tlabel,access_token));
					     		 		cod.add(new BasicNameValuePair(p1,p1));
					     		 		cod.add(new BasicNameValuePair(p2,p2));
					     		 		cod.add(new BasicNameValuePair(p3,p3));
					     		 	} 
					     		 	else if(!"null".equals(p1) && !"null".equals(p2)){cod.add(new BasicNameValuePair(tlabel,access_token));
					     		 		cod.add(new BasicNameValuePair(p1,p1));
					     		 		cod.add(new BasicNameValuePair(p2,p2));
					     		 	}   		 
					     		 	else if(!"null".equals(p1)){
					     		 		cod.add(new BasicNameValuePair(tlabel,access_token));
					     		 		cod.add(new BasicNameValuePair(p1,p1));     
				     		 		}
					     		 	else if("null".equals(p1)){
					     		 		cod.add(new BasicNameValuePair(tlabel,access_token));	
					     		 	}
								        post.setEntity(new UrlEncodedFormEntity(cod));
								        HttpResponse response1 = client.execute(post);
								        BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
								        while ((line = rd.readLine()) != null) {
								        	str=line;	        
							        	}
					     			}			 
					     		}
				   			}//oauth2
			   			}//while
						session.setAttribute("samp", str);	 
						String x1="";String x2="";String x3="";String x4="";String x5="";
						String xx1="mind";String xx2="mind";String xx3="mind";String xx4="mind";String xx5="mind";
						String[] xx=new String[10];
						String ptag="";String exres="";
						PreparedStatement st2=con.prepareStatement("select * from parse where tempid=?");
						st2.setString(1, da);
						ResultSet rs1=st2.executeQuery();
						while(rs1.next()){
							x1=rs1.getString("x1");x2=rs1.getString("x2");
							x3=rs1.getString("x3");x4=rs1.getString("x4");
							x5=rs1.getString("x5");ptag=rs1.getString("ptag");
							exres=rs1.getString("exres");
						} 
						if(resformat.equals("json")){
							try{
								JSONObject js=new JSONObject(str);
								if(!x1.equals("null")){
									xx1=(String)js.getString(x1);}
								if(!x2.equals("null")){
									xx2=(String)js.getString(x2);}
								if(!x3.equals("null")){
									xx3=(String)js.getString(x3);}
								if(!x4.equals("null")){
									xx4=(String)js.getString(x4);}
								if(!x5.equals("null")){
									xx5=(String)js.getString(x5);}
							}
							catch(Exception e){
								// out.println(e);
							}
						}
						else if(resformat.equals("xml")){
							DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
							InputSource is = new InputSource();
							is.setCharacterStream(new StringReader(str));
							Document doc = db.parse(is);
							NodeList nodes = doc.getElementsByTagName(ptag);
							int tot=nodes.getLength();String res="";
							String[] data=new String[tot];
							for (int i = 0,j=1; i < nodes.getLength(); i++,j++) {
								Element element = (Element) nodes.item(i);
								if(!x1.equals("null")){
									String[] temp=x1.split("@");
			      	    	   	   	if(temp.length==1){
			      	    	   	   		out.println("in if");
			      	    	   	   		NodeList name = element.getElementsByTagName(x1);
			      	    	   	   		Element line = (Element) name.item(0);
			      	    	   	   		xx1=getCharacterDataFromElement(line);}
			      	    	   	   	else{
			      	    	   	   		String tmp1=temp[0];
			      	    	   	   		String tmp2=temp[1];
			      	    	   	   		NodeList name = element.getElementsByTagName(tmp1);
			      	    	   	   		Element line = (Element) name.item(0);
			      	    	   	   		xx1=line.getAttribute(tmp2);
			      	    	   	   		out.println(tmp1+tmp2+xx1);
			      	    	   	   	}
								}
								if(!x2.equals("null")){
									String[] temp=x2.split("@");
		      	    	   	   		if(temp.length==1){
		      	    	   	   			out.println("in if");
		      	    	   	   			NodeList name = element.getElementsByTagName(x2);
		      	    	   	   			Element line = (Element) name.item(0);
		      	    	   	   			xx2=getCharacterDataFromElement(line);}
		      	    	   	   		else{
		      	    	   	   			String tmp1=temp[0];
		      	    	   	   			String tmp2=temp[1];
		      	    	   	   			NodeList name = element.getElementsByTagName(tmp1);
		      	    	   	   			Element line = (Element) name.item(0);
		      	    	   	   			xx2=line.getAttribute(tmp2);
		      	    	   	   			out.println(tmp1+tmp2+xx2);
		      	    	   	   		}
								}
								if(!x3.equals("null")){
									NodeList name2 = element.getElementsByTagName(x3);
					      	       	Element line2 = (Element) name2.item(0);
					      	       	xx3=getCharacterDataFromElement(line2);
								}if(!x4.equals("null")){
									NodeList name3 = element.getElementsByTagName(x4);
					      	       	Element line3 = (Element) name3.item(0);
					      	       	xx4=getCharacterDataFromElement(line3);
								}if(!x5.equals("null")){
									NodeList name4 = element.getElementsByTagName(x5);
					      	       	Element line4 = (Element) name4.item(0);
					      	       	xx5=getCharacterDataFromElement(line4);
								}
							}
						}
						xx[1]=xx1;xx[2]=xx2;xx[3]=xx3;xx[4]=xx4;xx[5]=xx5;
						String[] slt=exres.split("@@");
						int nn=slt.length;String orurl="";
						if(!(nn==0)){
							for(int i=1,j=1;i<nn;i=i+2,j++){
								slt[i]=xx[j];
							}
							for(int k=0;k<nn;k++){
								orurl=orurl+slt[k];
							}
						}
						PreparedStatement st3=con.prepareStatement("select * from act_all where tempid=?");
						st3.setString(1, da);
						ResultSet rs2=st3.executeQuery();
						while(rs2.next()){
							String authen=rs2.getString("authen");String apkey=rs2.getString("apkey");
					   		String ak1=rs2.getString("aplabel");
					   		String tempid=rs2.getString("tempid");String aid=rs2.getString("aid");
					   		String rmethod=rs2.getString("rmethod");String rformat=rs2.getString("rformat");
					   		String resformat1=rs2.getString("resformat");String endurl1=rs2.getString("emethod");
					   		String dn=rs2.getString("dn");String dn1=rs2.getString("dn1");
					   		String p1=rs2.getString("p1");String p2=rs2.getString("p2");
					   		String p3=rs2.getString("p3");String p4=rs2.getString("p4");
					   		String p5=rs2.getString("p5");String p6=rs2.getString("p6");
					   		String p7=rs2.getString("p7");
					   		String tlabel=rs2.getString("tlabel");String treplace=rs2.getString("treplace");
					   		String pv1=rs2.getString("pv1");String pv2=rs2.getString("pv2");
					   		String pv3=rs2.getString("pv3");String pv4=rs2.getString("pv4");
					   		String pv5=rs2.getString("pv5");String pv6=rs2.getString("pv6");
					   		String pv7=rs2.getString("p7");
					   		String h1=rs2.getString("h1"); String hv1=rs2.getString("hv1");
					   		String h2=rs2.getString("h2"); String hv2=rs2.getString("hv2");
					   		String h3=rs2.getString("h3"); String hv3=rs2.getString("hv3");
					   		String h4=rs2.getString("h4"); String hv4=rs2.getString("hv4");
					   		String h5=rs2.getString("h5"); String hv5=rs2.getString("hv5");
					   		String b2=rs2.getString("b2"); String b4=rs2.getString("b4");
					   		String str1="";
					   		if(authen.equals("API keys")){
					   			if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
					   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3+"&"+p4+"="+xx4+"&"+p5+"="+xx5;}
				        		 
					   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
					   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3+"&"+p4+"="+xx4;}
				        		 
					   			else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
				        			eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3;}
				        		 
					   			else if(!"null".equals(p1) && !"null".equals(p2)){
					   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1+"&"+p2+"="+xx2;}
				        		 
					   			else if(!"null".equals(p1)){
					   				eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1;}
					   			
					   			else if("null".equals(p1))
					   				eurl=endurl1+"?"+ak1+"="+apkey;
				        		 
					   			else if("null".equals(ak1) && "null".equals(apkey))
					   				eurl=endurl1;	        		
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
					   				str1+=line;
					   			}
					   		}
					   		else if (authen.equals("Basic Auth")){
								HttpClient httpClient = new DefaultHttpClient();
								HttpPost postRequest = new HttpPost(endurl1);
								StringEntity input = new StringEntity(orurl);
								input.setContentType("application/json");
								postRequest.setEntity(input);
								if(!b2.equals("") && !b2.equals("null")){
									String encoding = new String(
											org.apache.commons.codec.binary.Base64.encodeBase64   
											(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+b4)));
									postRequest.setHeader("Authorization","Basic " + encoding);
								}
								if(!h1.equals("null") && !h2.equals("null") && !h3.equals("null")){
									postRequest.setHeader(h1, hv1);postRequest.setHeader(h2, hv2);postRequest.setHeader(h3,hv3);
								}else if(!h1.equals("null") && !h2.equals("null")){
									postRequest.setHeader(h1, hv1);postRequest.setHeader(h2, hv2);
								}else if(!h1.equals("null")){
									postRequest.setHeader(h1, hv1);
								}
								HttpResponse response1 = httpClient.execute(postRequest);
								BufferedReader in1 = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
								String line1="";
							    while((line1=in1.readLine())!=null){
							    	str1+=line1;
							    }
					   		}
					   		else if(authen.equals("Oauth1")){
					   			String res="";
					   			out.println("in Oauth");
					   			String oauth_signature_method=rs.getString("osmeth");String url1=rs.getString("ourl1");
					   			String ourl21=rs.getString("ourl2");String ourl31=rs.getString("ourl3");
					   			String oauth_consumer_key=rs.getString("ockey"); String secret=rs.getString("oskey");
					   			String oreq1=rs.getString("oreq");
					   			String oauth_token="";
					   			String access_secret1="";
					   			PreparedStatement st4=con.prepareStatement("select * from token where tempid=? && tid=?");
					   			st4.setString(1, da);
					   			st4.setString(2, aid);
					   			ResultSet rs4=st4.executeQuery();
					   			while(rs4.next()){
					   				oauth_token=rs4.getString("oauthtoken");
					   				access_secret1=rs4.getString("secret");
					   			}
					   			String[] tok11=oauth_token.split("=");
					   			String oauthtk=tok11[1];
					   			String[] tok1=access_secret1.split("=");
					   			String sec1=tok1[1];
			        	 
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
				   				// out.println(eurl);
				   				//=========================
					   			if(rmethod.equals ("Get")){
					   				//========initial=========
					   				String uuid_string = UUID.randomUUID().toString();
					   				uuid_string = uuid_string.replaceAll("-", "");
					   				String oauth_nonce = uuid_string; 
					   				String enurl = URLEncoder.encode(endurl1, "UTF-8");
					   				String oauth_timestamp = (new Long(System.currentTimeMillis()/1000)).toString();
					   				String parameter_string ="";
					   				if(eurl.equals("null")){
					   					parameter_string ="oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
					   				}
					   				else{
					   					parameter_string = eurl+"&oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
					   				}
					   				String[] tst1=parameter_string.split("&");Arrays.sort(tst1);
					   				int no=tst1.length;String tst3="";
					   				for(int i=1;i<no;i++){
					   					tst3=tst3+"&"+tst1[i];
					   				}
					   				String tst4=tst1[0]+tst3;
					   				String signature_base_string = rmethod+"&"+enurl+"&" + URLEncoder.encode(tst4, "UTF-8");
					   				//  System.out.println("signature_base_string=" + signature_base_string);
					   				String oauth_signature = "";String oauth_signature1 = "";
					   				try {
					   					oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
					   					oauth_signature1 = URLEncoder.encode(oauth_signature, "UTF-8");
					   				} catch (GeneralSecurityException e) {
					   					// TODO Auto-generated catch block
					   					out.println(e);
					   				}
					   				String actok=endurl1+"?"+tst4+"&oauth_signature="+oauth_signature1;
					   				//out.println(actok);
					   				HttpClient httpclient = new DefaultHttpClient();
					   				HttpGet get1=new HttpGet(actok);
					   				HttpResponse response1=httpclient.execute(get1);
					   				BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
					   				StringBuffer result = new StringBuffer();
					   				String line = "";
					   				while ((line = rd.readLine()) != null) {
					   					result.append(line);
					   				}
					   				str1=result.toString();
					   			}
					   			else if(rmethod.equals ("Post")){
					   				//out.println("in post");
					   				String exhead="";
					   				if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6) && !"null".equals(p7)){
					   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\","+p4+"=\""+pv4+"\","+p5+"=\""+pv5+"\","+p6+"=\""+pv6+"\","+p7+"=\""+pv7+"\"";}
			        		 
					   				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
					   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\","+p4+"=\""+pv4+"\","+p5+"=\""+pv5+"\","+p6+"=\""+pv6+"\"";}
			        		 
					   				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
					   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\","+p4+"=\""+pv4+"\","+p5+"=\""+pv5+"\"";}
			        		 
					   				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
					   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\","+p4+"=\""+pv4+"\"";}
			        		 
					   				else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
					   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\","+p3+"=\""+pv3+"\"";}
			        		 
					   				else if(!"null".equals(p1) && !"null".equals(p2)){
					   					exhead=p1+"=\""+pv1+"\","+p2+"=\""+pv2+"\"";}
			        		 
					   				else if(!"null".equals(p1)){
					   					exhead=p1+"=\""+pv1+"\"";}
					   				
					   				else if("null".equals(p1))
					   					exhead="null";
			        		 
					   				//out.println("inside"+exhead);
					   				String uuid_string = UUID.randomUUID().toString();
					   				uuid_string = uuid_string.replaceAll("-", "");
					   				String oauth_nonce = uuid_string; 
					   				String enurl = URLEncoder.encode(endurl1, "UTF-8");
					   				String oauth_timestamp = (new Long(System.currentTimeMillis()/1000)).toString();
					   				String parameter_string ="";
					   				if(eurl.equals("null")){
					   					parameter_string ="oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
					   				}
					   				else{
					   					parameter_string = eurl+"&oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
					   				}
					   				String[] tst1=parameter_string.split("&");Arrays.sort(tst1);
					   				int no=tst1.length;String tst3="";
					   				for(int i=1;i<no;i++){
					   					tst3=tst3+"&"+tst1[i];
					   				}
					   				String tst4=tst1[0]+tst3;
					   				String signature_base_string = rmethod+"&"+enurl+"&" + URLEncoder.encode(tst4, "UTF-8");
					   				//  System.out.println("signature_base_string=" + signature_base_string);
					   				String oauth_signature = "";String oauth_signature1 = "";
					   				try {
					   					oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
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
					   				//out.println(authorization_header_string);
					   				HttpClient httpclient = new DefaultHttpClient();
					   				HttpResponse response1=null;
					   				HttpPost post = new HttpPost(endurl1);
					   				post.setHeader("Authorization", authorization_header_string);
					   				response1 = httpclient.execute(post);
					   				BufferedReader rd = new BufferedReader(
					   						new InputStreamReader(response1.getEntity().getContent()));
					   				StringBuffer result = new StringBuffer();
					   				String line = "";
					   				while ((line = rd.readLine()) != null) {
					   					result.append(line);
					   				}
					   				str1=result.toString();
					   			} 
					   		}
					   		else if(authen.equals("Oauth2")){
					   			PreparedStatement st4=con.prepareStatement("select * from token where tempid=? && tid=?");
					   			st4.setString(1, da);
					   			st4.setString(2, aid);
					   			ResultSet rs4=st4.executeQuery();String access_token="";
					   			while(rs4.next()){
					   				access_token=rs4.getString("oauthtoken");
					   			}
					   			HttpClient client = new DefaultHttpClient();
					   			String line="";
					   			if(rmethod.equals("DELETE")){
					   				String CLIENT_ID = "758153664645-n04dc4ki6pr383jdnrq6hmgjsvbsibls";
					   				String CLIENT_SECRET = "YsLu7TgD4q_NmheHjx4W2Okf";
					   				HttpTransport transport = new NetHttpTransport();
					   				JacksonFactory jsonFactory = new JacksonFactory();
					   				Credential credencial = new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
	   												.setJsonFactory(jsonFactory).setTransport(transport).build()
			   										.setAccessToken(access_token).setRefreshToken(null);
					   				SpreadsheetService service = new SpreadsheetService("Aplication-name");
					   				service.setOAuth2Credentials(credencial);
					   				URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
					   				SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,SpreadsheetFeed.class);
					   				List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets = feed.getEntries();
					   				if (spreadsheets.isEmpty()) {
					   					// 	TODO: There were no spreadsheets, act accordingly.
					   				}
					   				com.google.gdata.data.spreadsheet.SpreadsheetEntry spreadsheet = spreadsheets.get(0);
					   				URL listFeedUrl = ((WorksheetEntry) spreadsheet.getWorksheets().get(0)).getListFeedUrl();
					   				ListFeed feed1 = (ListFeed) service.getFeed(listFeedUrl, ListFeed.class);
					   				ArrayList<String > ar=new ArrayList<String>();
					   				for(ListEntry entry : feed1.getEntries())
					   				{
					   					for(String tag : entry.getCustomElements().getTags())
					   					{
					   						//System.out.println("     "+tag + ": " + entry.getCustomElements().getValue(tag));
					   						ar.add(tag);
					   					}
					   					break;
					   				}
					   				ListEntry row = new ListEntry();
					   				if(!xx1.equals("mind")){
					   					row.getCustomElements().setValueLocal(ar.get(0), xx1);}
					   				if(!xx2.equals("mind")){
					   					row.getCustomElements().setValueLocal(ar.get(1), xx2);}
					   				if(!xx3.equals("mind")){
					   					row.getCustomElements().setValueLocal(ar.get(2), xx3);}
					   				if(!xx4.equals("mind")){
					   					row.getCustomElements().setValueLocal(ar.get(3), xx4);}
					   				if(!xx5.equals("mind")){
					   					row.getCustomElements().setValueLocal(ar.get(4), xx5);}
					   				// 	Sending the new row for insertion into worksheet.
					   				row = service.insert(listFeedUrl, row);
					   			}else
					   				if(rmethod.equals("GET")){ 
					   					if("Authorization:Bearer".equals(treplace)){
					   						HttpGet get=new HttpGet(endurl1);
					   						get.addHeader("Authorization", "Bearer "+access_token);
					   						HttpResponse response1 = client.execute(get);
					   						BufferedReader rd = new BufferedReader(
					   								new InputStreamReader(response1.getEntity().getContent()));
					   						while ((line = rd.readLine()) != null) {
					   							str=line;		     			
					   						}
					   					}
					   					else if("QueryString".equals(treplace)){
					   						String param = null;
					   						// List<NameValuePair> params = new LinkedList<NameValuePair>();

					   						if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5))
					   							param=tlabel+"="+access_token+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3+"&"+p4+"="+xx4+"&"+p5+"="+xx5;

					   						else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4))
					   							param=tlabel+"="+access_token+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3+"&"+p4+"="+xx4;

					   						else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3))
					   							param=tlabel+"="+access_token+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3;

					   						else if(!"null".equals(p1) && !"null".equals(p2))
					   							param=tlabel+"="+access_token+"&"+p1+"="+xx1+"&"+p2+"="+xx2;

					   						else if(!"null".equals(p1))
					   							param=tlabel+"="+access_token+"&"+p1+"="+xx1;

					   						else if("null".equals(p1))
					   							param=tlabel+"="+access_token;
					   						
					   						String pointurl=endurl1+"?"+param;
					   						//String paramString = URLEncodedUtils.format(param, "utf-8");
					   						HttpGet get=new HttpGet(pointurl);
					   						HttpResponse response1=client.execute(get);
					   						BufferedReader rd = new BufferedReader
					   								(new InputStreamReader(response1.getEntity().getContent())); 
					   						while ((line = rd.readLine()) != null) {
					   							str=line;
					   						}		
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
					   							str=line;		     			
				   							}
					   					}
					   					else if("QueryString".equals(treplace)){
					   						List <NameValuePair> cod = new ArrayList <NameValuePair>();
					   						if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
					   							cod.add(new BasicNameValuePair(tlabel,access_token));
					   							cod.add(new BasicNameValuePair(p1,p1));
					   							cod.add(new BasicNameValuePair(p2,p2));
					   							cod.add(new BasicNameValuePair(p3,p3));
					   							cod.add(new BasicNameValuePair(p4,p4));
					   							cod.add(new BasicNameValuePair(p5,p5));
					   							cod.add(new BasicNameValuePair(p6,p6));
				   							}
					   						else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
					   							cod.add(new BasicNameValuePair(tlabel,access_token));
					   							cod.add(new BasicNameValuePair(p1,xx1));
					   							cod.add(new BasicNameValuePair(p2,xx2));
					   							cod.add(new BasicNameValuePair(p3,xx3));
					   							cod.add(new BasicNameValuePair(p4,xx4));
					   							cod.add(new BasicNameValuePair(p5,xx5));	
				   							}    
					   						else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){cod.add(new BasicNameValuePair(tlabel,access_token));
					   							cod.add(new BasicNameValuePair(p1,xx1));
					   							cod.add(new BasicNameValuePair(p2,xx2));
					   							cod.add(new BasicNameValuePair(p3,xx3));
					   							cod.add(new BasicNameValuePair(p4,xx4));
					   						}
					   						else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){cod.add(new BasicNameValuePair(tlabel,access_token));
					   							cod.add(new BasicNameValuePair(p1,xx1));
					   							cod.add(new BasicNameValuePair(p2,xx2));
					   							cod.add(new BasicNameValuePair(p3,xx3));
					   						} 
					   						else if(!"null".equals(p1) && !"null".equals(p2)){cod.add(new BasicNameValuePair(tlabel,access_token));
					   							cod.add(new BasicNameValuePair(p1,xx1));
					   							cod.add(new BasicNameValuePair(p2,xx2));
					   						}	 
					   						else if(!"null".equals(p1)){
					   							cod.add(new BasicNameValuePair(tlabel,access_token));
					   							cod.add(new BasicNameValuePair(p1,xx1));	     
					   						}
					   						else if("null".equals(p1)){
					   							cod.add(new BasicNameValuePair(tlabel,access_token));
					   								
							     		 	}	
					   						post.setEntity(new UrlEncodedFormEntity(cod));
					   						HttpResponse response1 = client.execute(post);
					   						BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
					   						while ((line = rd.readLine()) != null) {
					   							str1=line;	        
				   							}
					   					}			 
					   				}
					   			}
					   			PreparedStatement ps=con.prepareStatement("insert into testing (data) values('"+str1+"')");
					   			ps.executeUpdate();
					   			//out.println(str);
							}//while
						}//try
						catch(Exception e)
						{
							try {
								PreparedStatement ps1=con.prepareStatement("insert into testing (data) values('"+e+"')");
								ps1.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}//run
				};th[i].start();
			}//for
		} catch (Exception e1) {
			out.println(e1);
		}
		response.sendRedirect("final.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
