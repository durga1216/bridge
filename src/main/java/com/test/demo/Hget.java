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
			String str="";
			HttpClient cli=new DefaultHttpClient();
			HttpGet get=new HttpGet("https://mindapp-pulpy.rhcloud.com/rest/log/check/susee@minddotss.com/12456");
			HttpResponse res=cli.execute(get);
			BufferedReader bf=new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        	String line="";
        	while((line=bf.readLine())!=null){
        		str+=line;
        	}
        	if(str.charAt(0)=='M' && str.charAt(1)=='P'){
        		System.out.println(str);
        	}else if(str.equals("Invalid")){
        		System.out.println(str);
        	}else{
        		
        	}
		}
		catch(Exception e){
			
		}
	}

}
