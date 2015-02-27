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
import org.json.JSONException;
import org.json.JSONObject;

public class Parse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			HttpClient client=new DefaultHttpClient();
			String str=" ";String line="";
			HttpGet post=new HttpGet("http://www.pointhacks.com.au/questions/feed/");
//			post.addHeader("X-Parse-Application-Id","6B71a7vWFvdhsCBSdT9aPCJLt6bw5t7CpcV1i2r5");
//			post.addHeader("X-Parse-REST-API-Key","Ypljyme6tsZgwwPyScpsMXYl0JwSYQbBu1KNt6I1");
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
