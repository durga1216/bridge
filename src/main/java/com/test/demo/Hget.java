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
			HttpGet get=new HttpGet("https://mail.google.com/mail/feed/atom");
			get.addHeader("Authorization", "Bearer "+"ya29.ugBuJPDD-_FiYVns-Cjwer9X0LyF9k9Ovb6aFWpJnC0NPAfv-Av9JoMNcXDglHsEH3j8hezJQ_nMmg");
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
