package com.bridge;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
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
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Path("/webhooks")
public class Resthook {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response addPlainText(@PathParam("id") String id,@QueryParam("data") String data,@Context HttpServletRequest request,@Context HttpServletResponse response) {
    	String result11="test---"+id+"---"+data;
    	String eurl="";String str="";
    	String resformat="json";
    	if(data.equals(null)){
    		try{
	    		StringBuffer jb = new StringBuffer();
				String line = null;
				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null){
					jb.append(line);
				}
		    	str=jb.toString();
    		}
	    	catch(Exception e){
    		}
    	}else{
    		str=data;
    	}
    	HttpSession session=request.getSession(true);
    	session.setAttribute("xml1", str);
    	try{
    		//PrintWriter out=response.getWriter();
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
		    final Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
		    PreparedStatement ps11=con.prepareStatement("insert into hook (str) values ('"+str+"')");
	    	ps11.executeUpdate();
    		String x1="";String x2="";String x3="";String x4="";String x5="";
			//TODO For Checking xx value purpose I take null
			String xx1="null";String xx2="null";String xx3="null";String xx4="null";String xx5="null";
			String[] xx=new String[10];String check="null";String resf="";
			String ptag="";String exres="";String shname="";
			PreparedStatement st2=con.prepareStatement("select * from parse where tempid=?");
			st2.setString(1, id);
			ResultSet rs1=st2.executeQuery();
			while(rs1.next()){
				x1=rs1.getString("x1");x2=rs1.getString("x2");
				x3=rs1.getString("x3");x4=rs1.getString("x4");
				x5=rs1.getString("x5");ptag=rs1.getString("ptag");
				exres=rs1.getString("exres");shname=rs1.getString("shname");
				resf=rs1.getString("resf");
			} 
			//Json parsing for trigger response
			if(resformat.equals("json")){
				try{
					ScriptEngineManager manager = new ScriptEngineManager();
				    ScriptEngine engine = manager.getEngineByName("javascript");
				    engine.eval("var x = "+str+";");
					if(!x1.equals("null")){
						xx1=(String)engine.eval("x."+x1+";");}
					if(!x2.equals("null")){
						xx2=(String)engine.eval("x."+x2+";");}
					if(!x3.equals("null")){
						xx3=(String)engine.eval("x."+x3+";");}
					if(!x4.equals("null")){
						xx4=(String)engine.eval("x."+x4+";");}
					if(!x5.equals("null")){
						xx5=(String)engine.eval("x."+x5+";");}
				}catch(Exception e){
					check=e.toString();
				}
			}
			//XMl parsing for trigger response
			else if(resformat.equals("xml")){
				try{
		       	 	DocumentBuilderFactory domFactory=DocumentBuilderFactory.newInstance();
		       	 	DocumentBuilder builder=domFactory.newDocumentBuilder();
					Document doc=builder.parse(new InputSource(new ByteArrayInputStream(str.getBytes("UTF-8"))));
					XPath xPath = XPathFactory.newInstance().newXPath();
					if(!x1.equals("null")){
						xx1=xPath.compile(ptag+"/"+x1).evaluate(doc);}
					if(!x2.equals("null")){
						xx2=xPath.compile(ptag+"/"+x2).evaluate(doc);}
					if(!x3.equals("null")){
						xx3=xPath.compile(ptag+"/"+x3).evaluate(doc);}
					if(!x4.equals("null")){
						xx4=xPath.compile(ptag+"/"+x4).evaluate(doc);}
					if(!x5.equals("null")){
						xx5=xPath.compile(ptag+"/"+x5).evaluate(doc);}
				}catch(Exception e){
					check=e.toString();
				}
			}
			String orurl="";
			//Construct the Json Or Xml String by removing the @@
			xx[1]=xx1;xx[2]=xx2;xx[3]=xx3;xx[4]=xx4;xx[5]=xx5;
			String[] slt=exres.split("@@");
			int nn=slt.length;
			if(!(nn==0)){
				for(int i=1,j=1;i<nn;i=i+2,j++){
					slt[i]=xx[j];
				}
				for(int k=0;k<nn;k++){
					orurl=orurl+slt[k];
				}
			}
			else{
				orurl=exres;
			}
	/**Action part for the webhook configuration**/
			
    		PreparedStatement st3=con.prepareStatement("select * from act_all where tempid=?");
			st3.setString(1, id);
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
					//input.setContentType("application/json");
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
		   			String oauth_signature_method=rs2.getString("osmeth");
		   			String oauth_consumer_key=rs2.getString("ockey"); String secret=rs2.getString("oskey");
		   			String oauth_token="";
		   			String access_secret1="";
		   			PreparedStatement st4=con.prepareStatement("select * from token where tempid=? && tid=?");
		   			st4.setString(1, id);
		   			st4.setString(2, aid);
		   			ResultSet rs4=st4.executeQuery();
		   			while(rs4.next()){
		   				oauth_token=rs4.getString("oauthtoken");
		   				access_secret1=rs4.getString("secret");
		   			}
		   			rs4.close();
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
		   				String signature_base_string = "POST&"+enurl+"&" + URLEncoder.encode(tst4, "UTF-8");
		   				System.out.println("signature_base_string=" + signature_base_string);
		   				String oauth_signature = "";String oauth_signature1 = "";
		   				try {
		   					oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
		   					oauth_signature1 = URLEncoder.encode(oauth_signature, "UTF-8");
		   				}
		   				catch (GeneralSecurityException e) {
		   					// TODO Auto-generated catch block
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
		   				session.setAttribute("samp", oauth_consumer_key+"\n"+secret+oauth_token+"\n"+access_secret1+"\n"
		   						+ endurl1+"\n"+authorization_header_string+"\n"+parameter_string+"\n"+exres);
		   				HttpPost post = new HttpPost(endurl1);
		   				post.setHeader("Authorization", authorization_header_string);
		   				StringEntity input = new StringEntity(exres);
		   				input.setContentType("application/xml");
		   				post.setEntity(input);
		   				response1 = httpclient.execute(post);
		   				BufferedReader rd = new BufferedReader(
		   						new InputStreamReader(response1.getEntity().getContent()));
		   				StringBuffer result = new StringBuffer();
		   				String line = "";
		   				while ((line = rd.readLine()) != null) {
		   					result.append(line);
		   				}
		   				str1=result.toString();
		   				//session.setAttribute("samp", str1);	  
		   			} 
		   		}
		   		else if(authen.equals("Oauth2")){
		   			String refresh=null;
		   			PreparedStatement st4=con.prepareStatement("select * from token where tempid=? && tid=?");
		   			st4.setString(1, id);
		   			st4.setString(2, aid);
		   			ResultSet rs4=st4.executeQuery();String access_token="";
		   			while(rs4.next()){
		   				access_token=rs4.getString("oauthtoken");
		   				refresh=rs4.getString("secret");
		   			}
		   			HttpClient client = new DefaultHttpClient();
		   			String line="";
		   			// Just filter the google Oauth api's
		   			if(rmethod.equals("DELETE")){
		   				//we will get from database also
		   				String CLIENT_ID = "758153664645-n04dc4ki6pr383jdnrq6hmgjsvbsibls";
		   				String CLIENT_SECRET = "YsLu7TgD4q_NmheHjx4W2Okf";
		   				HttpTransport transport = new NetHttpTransport();
		   				JacksonFactory jsonFactory = new JacksonFactory();
		   				Credential credencial = new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
											.setJsonFactory(jsonFactory).setTransport(transport).build()
   										.setAccessToken(access_token).setRefreshToken(refresh);
		   				SpreadsheetService service = new SpreadsheetService("Aplication-name");
		   				service.setOAuth2Credentials(credencial);
		   				URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
		   				SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,SpreadsheetFeed.class);
		   				List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets = feed.getEntries();
		   				if (spreadsheets.isEmpty()) {
		   					// 	TODO: There were no spreadsheets, act accordingly.
		   				}
		   				//Choose the spreadsheet
		   				int k=0;
		   			    for(int i=0;i<spreadsheets.size();i++){
		   				     String name=(String)spreadsheets.get(i).getTitle().getPlainText();
		   				     if(shname.equals(name)){
		   				    	 k=i;
		   				     }
		   			    }
		   				com.google.gdata.data.spreadsheet.SpreadsheetEntry spreadsheet = spreadsheets.get(k);
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
		   				//Choose the columns from parsing
		   				ListEntry row = new ListEntry();
		   				if(!xx1.equals("null")){
		   					row.getCustomElements().setValueLocal(ar.get(0), xx1);}
		   				if(!xx2.equals("null")){
		   					row.getCustomElements().setValueLocal(ar.get(1), xx2);}
		   				if(!xx3.equals("null")){
		   					row.getCustomElements().setValueLocal(ar.get(2), xx3);}
		   				if(!xx4.equals("null")){
		   					row.getCustomElements().setValueLocal(ar.get(3), xx4);}
		   				if(!xx5.equals("null")){
		   					row.getCustomElements().setValueLocal(ar.get(4), xx5);}
		   				// 	Sending the new row for insertion into worksheet.
		   				row = service.insert(listFeedUrl, row);
		   			}else
		   				if(rmethod.equals("Get")){ 
		   					if("Authorization:Bearer".equals(treplace)){
		   						HttpGet get=new HttpGet(endurl1);
		   						get.addHeader("Authorization", "Bearer "+access_token);
		   						HttpResponse response1 = client.execute(get);
		   						BufferedReader rd = new BufferedReader(
		   								new InputStreamReader(response1.getEntity().getContent()));
		   						while ((line = rd.readLine()) != null) {
		   							str1+=line;		     			
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
		   							str1+=line;
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
		   							str1+=line;		     			
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
		   							str1+=line;	        
	   							}
		   					}			 
		   				}
		   			}
		   			PreparedStatement ps=con.prepareStatement("insert into testing (data) values('"+str1+"')");
		   			ps.executeUpdate();
		   			result11=str;
				}//while
			}//try
			catch(Exception e){
				result11=e.getMessage();
    		}
    	return Response.ok(result11)
    			.header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
    			.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
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

