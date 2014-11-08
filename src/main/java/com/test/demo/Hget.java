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
			get.addHeader("Authorization", "Bearer "+"AQX-GRrRJvhlarxgg-Gb-w2yuSp9MX0pcl_l1WQHbw9l5ZqQLtCZIDX9niIpxQSb0Xn5Tx_vJXIau3pU5ZZnwyefqGkYs0AFQ1pbPQAV_Tow_0IWRbsmAqI09ZQYkWs5G3mYHu1D25q-vtps5zmXtFO3OtMsvIsK-edaIAGJPg2OWc_9JeA");
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
