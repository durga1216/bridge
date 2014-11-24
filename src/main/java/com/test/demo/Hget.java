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
			HttpPost post=new HttpPost("https://mandrillapp.com/api/1.0/users/info.json");
			StringEntity str1=new StringEntity("{\"key\": \"bfOjWW_1dPe6Y3a9odyWsQ\"}");
			post.setEntity(str1);
			//HttpGet get=new HttpGet("https://www.googleapis.com/books/v1/volumes?key=AIzaSyDhka1g6k9JVmWn7N5sgis4_oG9nZhwgoc&q=9780605039070");
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
