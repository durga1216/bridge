package com.test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class Hget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			HttpClient client=new DefaultHttpClient();
			String str=" ";String line="";
			HttpGet get=new HttpGet("https://www.googleapis.com/books/v1/volumes?key=AIzaSyDhka1g6k9JVmWn7N5sgis4_oG9nZhwgoc&q=9780605039070");
			HttpResponse response1 = client.execute(get);
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response1.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {
				str+=line;		     			
			}
			System.out.println(str);
			JSONObject obj=new JSONObject(str);
			String getkind=obj.getString("kind");
			String totalitems=obj.getString("totalItems");
			System.out.println(getkind+"\n"+totalitems);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
