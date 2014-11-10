package com.test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Hget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			HttpClient client=new DefaultHttpClient();
			String str=" ";String line="";
			HttpGet get=new HttpGet("https://api.linkedin.com/v1/people/~");
			get.addHeader("Authorization", "Bearer "+"ya29.ugCYLoqEnOpEPfSj98ETsxvRjwVos12bU9PK0zGJxfagDX7Do2WIx4Yc");
			HttpResponse response1 = client.execute(get);
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
