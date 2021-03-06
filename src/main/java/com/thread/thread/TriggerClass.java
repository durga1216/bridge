package com.thread.thread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import javax.servlet.http.HttpSession;

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
import org.json.JSONArray;
import org.json.JSONObject;

import com.bridge.Util;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.PostalAddress;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;

public class TriggerClass {
	String da;
	String str="";
	public TriggerClass(String tempid){
		da=tempid;
	}
	public String start(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    final Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);	
			String eurl="";String resformat="";
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
		   		String pv7=rs.getString("p7");String exreq=rs.getString("exreq");
		   		String h1=rs.getString("h1"); String hv1=rs.getString("hv1");
		   		String h2=rs.getString("h2"); String hv2=rs.getString("hv2");
		   		String h3=rs.getString("h3"); String hv3=rs.getString("hv3");
		   		String h4=rs.getString("h4"); String hv4=rs.getString("hv4");
		   		String h5=rs.getString("h5"); String hv5=rs.getString("hv5");
		   		String sigmsg=rs.getString("smessage");String sigskey=rs.getString("sigskey");
		   		String sigckey=rs.getString("sigckey");String header=rs.getString("signature");
		   		if(authen.equals("No Auth")){
		   			HttpClient cli=new DefaultHttpClient();
		   			if(rmethod.equals("Get")){
		   				HttpGet get=new HttpGet(endurl1);
		            	HttpResponse res=cli.execute(get);
		            	BufferedReader bf=new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		            	String line="";
		            	while((line=bf.readLine())!=null){
		            		str+=line;
		            	}
		   			}
		   			else if(rmethod.equals("Post")){
		   				HttpPost post=new HttpPost(endurl1);
   	 					StringEntity stt=new StringEntity(exreq);
   	 					post.setEntity(stt);
   	 					HttpResponse res=cli.execute(post);
	   	 				BufferedReader bf=new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
	   	 				String line="";
	   	 				while((line=bf.readLine())!=null){
	   	 					str+=line;
	   	 				}
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
		   		else if(authen.equals("Signed Auth")){
   	 			/*	String uuid_string = UUID.randomUUID().toString();
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
 					//creating signature
 					SecretKeySpec signingKey = new SecretKeySpec(sigskey.getBytes(), "HMACSHA1");
 			        Mac mac = Mac.getInstance("HMACSHA1");
 			        mac.init(signingKey);
 			       String orig = new String(Hex.encodeHex(mac.doFinal(msg.getBytes())));
			           byte[] encoded = Base64.encodeBase64(orig.getBytes());  
 			        //merge all the params
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
 					//construct the url
 				/*	String[] slt1=eurl.split("@@");
 					int nn1=slt1.length;String orurl1="";
 					if(!(nn1==0)){
 						for(int i=0;i<nn1;i++){
 							if(slt1[i].equals("timestamp")){
 								slt1[i]=timestamp;
 							}else if(slt1[i].equals("nonce")){
 								slt1[i]=nonce;
 							}else if(slt1[i].equals("signature")){
 								slt1[i]=signature1;
 							}
 						}
 						for(int k=0;k<nn1;k++){
 							orurl1=orurl1+slt1[k];
 						}
 						eurl=orurl1;
 					}
 					String callurl=endurl1+"?"+eurl;*/
 					String callurl=endurl1;
 					//Request to client
 					HttpClient cli=new DefaultHttpClient();
   	 				HttpGet get=new HttpGet(callurl);
   	 					get.addHeader(header,"");
   	 				
   	 				HttpResponse res=cli.execute(get);
   	 				BufferedReader bf=new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
   	 				String line="";
   	 				while((line=bf.readLine())!=null){
   	 					str+=line;
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
		   			String refresh=null;
		   			PreparedStatement st=con.prepareStatement("select * from token where tempid=? && tid=?");
		   			st.setString(1, da);
		   			st.setString(2, tid);
		   			ResultSet rs1=st.executeQuery();String access_token="";
		   			while(rs1.next()){
		   				refresh=rs1.getString("secret");
		   				access_token=rs1.getString("oauthtoken");
		   			}
		   			HttpClient client = new DefaultHttpClient();
		   			String line="";
		   			if(rmethod.equals("DELETE")){
						//TODO For getting spread sheet elements
		   				String CLIENT_ID = "758153664645-n04dc4ki6pr383jdnrq6hmgjsvbsibls";
		   				String CLIENT_SECRET = "YsLu7TgD4q_NmheHjx4W2Okf";
		   				HttpTransport transport = new NetHttpTransport();
		   			    JacksonFactory jsonFactory = new JacksonFactory();
						if(eurl.equals("spreadsheet_data")){
			   				Credential credencial = new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
			   						.setJsonFactory(jsonFactory).setTransport(transport).build()
			   						.setAccessToken(access_token).setRefreshToken(refresh);
			   				SpreadsheetService service =new SpreadsheetService("Aplication-name");
			   				service.setOAuth2Credentials(credencial);
			   				URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
			   				SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,SpreadsheetFeed.class);
			   				List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets = feed.getEntries();
			   				if (spreadsheets.isEmpty()) {
			   					// TODO: There were no spreadsheets, act accordingly.
			   				}
			   				com.google.gdata.data.spreadsheet.SpreadsheetEntry spreadsheet = spreadsheets.get(0);
			   				WorksheetFeed worksheetFeed = service.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
			   				List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
			   				WorksheetEntry worksheet = worksheets.get(0);
			   				URL cellFeedUrl = worksheet.getCellFeedUrl();
			   				CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
			   				JSONObject obj = new JSONObject();
			   				JSONObject obj1 = new JSONObject();
			   				String title=spreadsheet.getTitle().getPlainText();
			   				for (CellEntry cell : cellFeed.getEntries()) {
			   					String name=cell.getTitle().getPlainText();
			   					String value=cell.getCell().getValue();
			   					obj.put(name, value);
			   				}
			   				obj1.put(title, obj);
			   				String cng=obj1.toString();
			   				cng=cng.replaceAll(" ", "_");
			   				str=cng;
						}
						if(eurl.equals("Google_contacts")){
				   			Credential credential =  new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
									.setJsonFactory(jsonFactory).setTransport(transport).build()
							    	.setAccessToken(access_token).setRefreshToken(refresh);
				   			  ContactsService myService = new ContactsService("Mind Pulpy");
					  		  myService.setOAuth2Credentials(credential);
					  		  URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
					  		  Query myQuery = new Query(feedUrl);
					  		  myQuery.setMaxResults(500);
					  		  myQuery.setStringCustomParameter("sortorder", "ascending");
					  		  ContactFeed resultFeed = myService.query(myQuery, ContactFeed.class);
					  		  // Print the results
					  		  String title=resultFeed.getTitle().getPlainText();
					  		title=title.replaceAll(" ", "_");
					  		  System.out.println(title);
					  		  System.out.println(resultFeed.getEntries().size());
					  		  JSONArray arr=new JSONArray();
					  		  int i=0;
					  		  for (ContactEntry entry : resultFeed.getEntries()) {
					  			  JSONObject obj = new JSONObject();
					  			  if (entry.hasName()) {
						  		      Name name = entry.getName();
						  		      String fullname = name.getFullName().getValue();
						  		      obj.put("Name", fullname);}	
					  		      else
						  		    	obj.put("Name", "");
						  		      List<PhoneNumber> number=entry.getPhoneNumbers();
					  		      if(number.size()>0){
						  		      String num=number.get(0).getPhoneNumber().toString();
						  		      obj.put("Phone", num);}
					  		      else
						  		    	obj.put("Phone", "");
						  		      List<PostalAddress> country=entry.getPostalAddresses();
					  		      if(country.size()>0){
						  		      String cty=country.get(0).getLabel();
						  		      obj.put("Address", cty);}
					  		      else
						  		    	obj.put("Address", "");
						  		      List<Email> email=entry.getEmailAddresses();
					  		      if(email.size()>0){
						  		      String em=email.get(0).getAddress();
						  		      obj.put("Email", em);}
					  		      else
						  		      obj.put("Email", "");
						  		      List<GroupMembershipInfo> group=entry.getGroupMembershipInfos();
					  		      if(group.size()>0){
						  		      String groupHref = group.get(0).getHref();
						  		      obj.put("Group_id", groupHref);}
					  		      else
					  		    	  obj.put("Group_id", "");
						  		      System.out.println(obj.toString());
						  		      arr.put(i, obj);
						  		      i++;
					  		  }
					  		  JSONObject obj1 = new JSONObject();
					  		  obj1.put("contacts", arr);
					  		  str=obj1.toString();
				   		}
						else{
							//TODO for getting analytics data
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
						}
		   			}
		   			else if(rmethod.equals("Get")){ 
		   				if("Authorization:Bearer".equals(treplace)){
		   					HttpGet get=new HttpGet(endurl1);
		   					get.addHeader("Authorization", "Bearer "+access_token);
		   					HttpResponse response1 = client.execute(get);
		   					BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
			     			while ((line = rd.readLine()) != null) {
			     				str+=line;		     			
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
				    			str+=line;
				    		}    			
		   				} // query string
		   			}//get
			    	else if(rmethod.equals("Post")){
			     		HttpPost post=new HttpPost(endurl1);
			     		if("Authorization:Bearer".equals(treplace)){
			     			post.addHeader("Authorization", "Bearer "+access_token);
							HttpResponse response1=client.execute(post);
							BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
		     				while ((line = rd.readLine()) != null) {
		     					str+=line;		     			}
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
						        	str+=line;	        
					        	}
			     			}			 
			     		}
		   			}//oauth2
	   			}//while
			return str;
		}
		catch(Exception e){
			return e.toString();
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
