package com.test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class Hpost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			JSONObject obj=new JSONObject();
			obj.put("name", "susee");
			obj.put("address", "");
			obj.put("area", "");
			obj.put("tin_no", "");
			obj.put("latitude", "");
			obj.put("longitude", "");
			obj.put("status", "");
			obj.put("order_quantity", "");
			obj.put("level", "");
			obj.put("influencerType", "");
			obj.put("feedback", "");
			obj.put("steelUsed", "");
			obj.put("mobile", 9566);
			System.out.println(obj);
			HttpClient client=new DefaultHttpClient();
			String str=" ";String line="";
			HttpPost post=new HttpPost("https://api.parse.com/1/classes/DealerFirstVisit");
			post.addHeader("X-Parse-Application-Id","QRgHJmTvRLIr4FrWuUH83cZ2RyRelm4z9OFvuePR");
			post.addHeader("X-Parse-REST-API-Key","vqm5GfSHBJbxLGHLXW50gG40LWO8HILgtPxYg1pP");
			StringEntity str1=new StringEntity(obj.toString());
			post.setEntity(str1);
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
