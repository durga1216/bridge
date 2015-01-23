package com.thread.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Qbtest {

	public static void main(String[] args) {
		try{
			String oauth_consumer_key="qyprdX6G2AFPGqXc0KZ3eeq3gxNBfY";
			String secret="F99WcjcynTlh13CtdQJy9xU5mLg31DETm7EQNgZ6";
			String oauth_signature_method="HMAC-SHA1";
			String rmethod="GET";
			String oauth_token="oauth_token=qyprdi8J3Pir2jpjNTQlSRtEmXC0dKxs9QC0Omt5bYWQLLRm";
			String access_secret1="oauth_token_secret=hA2DWSvMQqLE2wZr7njl2IF4czNjBO0RQ8j4xeFF";
			String endurl1="https://qb.sbfinance.intuit.com/v3/company/1326601655/query?query=select%20%2A%20from%20invoice";
   			String[] tok11=oauth_token.split("=");
   			String oauthtk=tok11[1];
   			String[] tok1=access_secret1.split("=");
   			String sec1=tok1[1];
			//inputs
   			String uuid_string = UUID.randomUUID().toString();
			uuid_string = uuid_string.replaceAll("-", "");
			String oauth_nonce = uuid_string; 
			String enurl = URLEncoder.encode(endurl1, "UTF-8");
			String oauth_timestamp = (new Long(System.currentTimeMillis()/1000)).toString();
			String parameter_string ="oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce=" + oauth_nonce + "&oauth_signature_method=" + oauth_signature_method + "&oauth_timestamp=" + oauth_timestamp +"&"+oauth_token+"&oauth_version=1.0";        
			String[] tst1=parameter_string.split("&");Arrays.sort(tst1);
			int no=tst1.length;String tst3="";
			for(int i=1;i<no;i++){
				tst3=tst3+"&"+tst1[i];
			}
			String tst4=tst1[0]+tst3;
			String signature_base_string = rmethod+"&"+enurl+"&" + URLEncoder.encode(tst4, "UTF-8");
			String oauth_signature = "";String oauth_signature1 = "";
			try {
				oauth_signature = computeSignature(signature_base_string, secret+"&"+sec1);  // note the & at the end. Normally the user access_token would go here, but we don't know it yet for request_token
				oauth_signature1 = URLEncoder.encode(oauth_signature, "UTF-8");
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
			}
			String authorization_header_string = "OAuth oauth_consumer_key=\"" + oauth_consumer_key + "\","
							+ "oauth_nonce=\"" + oauth_nonce + "\",oauth_signature_method=\"HMAC-SHA1\",oauth_token=\""+oauthtk+"\",oauth_signature=\"" + URLEncoder.encode(oauth_signature, "UTF-8") + "\",oauth_timestamp=\"" + 
							oauth_timestamp + "\",oauth_version=\"1.0\"";
			String actok="https://qb.sbfinance.intuit.com/v3/company/1326601655/query?query=select%20%2A%20from%20invoice";
			System.out.println(authorization_header_string);
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet get1=new HttpGet(actok);
			get1.setHeader("Authorization", authorization_header_string);
			get1.setHeader("Accept", "application/json");
			get1.setHeader("content-type","text/plain");
			HttpResponse response1=httpclient.execute(get1);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";String str1="";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			str1=result.toString();
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
