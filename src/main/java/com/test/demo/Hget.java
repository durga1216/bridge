package com.test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class Hget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			HttpClient client=new DefaultHttpClient();
			String str=" ";String line="";
			String para="where={\"name\":\"hhf\"}";
			para=URLEncoder.encode(para);
			HttpGet post=new HttpGet("https://api.parse.com/1/classes/Inflencer?"+para);
			post.addHeader("X-Parse-Application-Id","QRgHJmTvRLIr4FrWuUH83cZ2RyRelm4z9OFvuePR");
			post.addHeader("X-Parse-REST-API-Key","vqm5GfSHBJbxLGHLXW50gG40LWO8HILgtPxYg1pP");
			//post.addHeader("Content-Ty","");
//			HttpPost post=new HttpPost("https://us3.api.mailchimp.com/2.0/lists/list.json");
//			StringEntity str1=new StringEntity("{\"apikey\": \"3aa658f00ad38e7754bbc30c00cf75e7-us3\"}");//\"id\":\"619e890eb0\"   ,\"cid\":\"614a0b5a90\",\"struct\":{\"data\":\"message\"}
//			post.setEntity(str1);
			HttpResponse response1 = client.execute(post);
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response1.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {
				str+=line;		     			
			}
			System.out.println(str);
			JSONObject obj=new JSONObject(str);
			JSONArray arr=(JSONArray)obj.getJSONArray("results");
			System.out.println(arr.length());
			for(int i=0;i<arr.length();i++){
				JSONObject obj2=new JSONObject(arr.get(i).toString());
				String id1=obj2.getString("name");
				System.out.println("\n"+id1);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
