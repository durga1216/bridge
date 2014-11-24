package com.test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class Hget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			HttpClient client=new DefaultHttpClient();
			String str=" ";String line="";
			HttpPost post=new HttpPost("https://us3.api.mailchimp.com/2.0/reports/bounce-messages.json");
			StringEntity str1=new StringEntity("{\"apikey\": \"a1840fe8506a8257062ad3cb44f0ab01-us3\",\"cid\":\"614a0b5a90\"}");//\"id\":\"619e890eb0\"   ,\"cid\":\"614a0b5a90\",\"struct\":{\"data\":\"message\"}
			post.setEntity(str1);
			//HttpGet post=new HttpGet("https://ap1.salesforce.com/services/data/v28.0/searchlayout?oauth_token=00D900000010GvM!AQ8AQJzntgY4kdBf75XV7Shfuc7SdRD2zsyHzGS40plIeMApT3QWv7s5TpvO2w0MliL65_uhzOKnhLyb5jwMB0KgXZcGn89i&q=Accout,Name,Email,Contact");
			HttpResponse response1 = client.execute(post);
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response1.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {
				str+=line;		     			
			}
			System.out.println(str);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
