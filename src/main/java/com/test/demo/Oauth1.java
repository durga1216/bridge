package com.test.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Oauth1 {
		public static void main(String args[]){
			try{
				String url1="https://api.twitter.com/oauth/request_token";
				String endurl1="https://api.twitter.com/1.1/statuses/update.json";
				String oauth_consumer_key="Hv3OScz3A90OTg2TI56wmfl9j";
				String secret="NwxL4KegyzbRlGSvkhzdKx5VqDl8HWThb7NlJBJfzviuCXZ4Z6";
				String ourl2="https://api.twitter.com/oauth/authorize";
				String callback="";
				String oauth_signature_method="HMAC-SHA1";
				String oreq1="POST";
				String status="auto Testt hjhhjsssreply4";
				String stat=status.replaceAll(" ", "%20");
				String eurl="status="+stat+"&in_reply_to_user_id=268370644&in_reply_to_status_id=521932749725122561&in_reply_to_screen_name=minddotss";
				String oauth_token="oauth_token=497155949-yO17YQwC0FyOpDjniU6aSJ2NAOHWq710j8BzNrWS";
				String access_secret1="oauth_token_secret=bNrlZtJmkLC8i3qxHKN9zXpx4QiUMJ5etoGYdZfzZw7UK";
				String[] tok11=oauth_token.split("=");
           	    String oauthtk=tok11[1];
        	    String[] tok1=access_secret1.split("=");
        	    String sec1=tok1[1];
				String uuid_string = UUID.randomUUID().toString();
	            uuid_string = uuid_string.replaceAll("-", "");
	            String oauth_nonce = uuid_string; 
	            String eurl1 = URLEncoder.encode(endurl1, "UTF-8");
	            String call = URLEncoder.encode(callback, "UTF-8");
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
                String signature_base_string = oreq1+"&"+eurl1+"&" + URLEncoder.encode(tst4, "UTF-8");
	       		System.out.println(signature_base_string);
                String oauth_signature = "";String oauth_signature1 = "";
                 try {
	                      oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
	                       oauth_signature1 = URLEncoder.encode(oauth_signature, "UTF-8");
	                  } catch (GeneralSecurityException e) {
	                     // TODO Auto-generated catch block
	                   }
	                  String actok=endurl1+"?"+tst4+"&oauth_signature="+oauth_signature1;
	                  String authorization_header_string = "OAuth oauth_consumer_key=\"" + oauth_consumer_key + "\","
	                     		+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_token=\""+oauthtk+"\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
	                            oauth_timestamp + "\",oauth_version=\"1.0\"";
	                  System.out.println(authorization_header_string);
			     		 HttpClient httpclient = new DefaultHttpClient();
			     		 HttpPost post = new HttpPost(endurl1);
			     		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        				urlParameters.add(new BasicNameValuePair("in_reply_to_user_id","268370644"));
        				urlParameters.add(new BasicNameValuePair("in_reply_to_status_id","521932749725122561"));
        				urlParameters.add(new BasicNameValuePair("in_reply_to_screen_name","minddotss"));
        				urlParameters.add(new BasicNameValuePair("status", status));
        				post.setEntity(new UrlEncodedFormEntity(urlParameters));
        				post.setHeader("Authorization", authorization_header_string);
	     				HttpResponse response1 = httpclient.execute(post);
			           BufferedReader rd = new BufferedReader(
			                       new InputStreamReader(response1.getEntity().getContent()));
				 
						StringBuffer result = new StringBuffer();
						String line = "";
						while ((line = rd.readLine()) != null) {
							result.append(line);
						}
						String str1=result.toString();
						System.out.println(str1);
			}catch(Exception e){
				System.out.println(e);
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
