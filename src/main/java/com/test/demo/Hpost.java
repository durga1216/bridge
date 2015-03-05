package com.test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;

public class Hpost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			JSONObject obj=new JSONObject();
			obj.put("userId", "cf28f4fd-096e-4862-af6b-0fd3e76cb845");
			obj.put("isPartnerLead", "2");
			obj.put("lastLeadId", "82071");
//			obj.put("name", "susee");
//			obj.put("address", "");
//			obj.put("area", "");
//			obj.put("tin_no", "");
//			obj.put("latitude", "");
//			obj.put("longitude", "");
//			obj.put("status", "");
//			obj.put("order_quantity", "");
//			obj.put("level", "");
//			obj.put("influencerType", "");
//			obj.put("feedback", "");
//			obj.put("steelUsed", "");
//			obj.put("mobile", 9566);
			System.out.println(obj);
			HttpClient client=new DefaultHttpClient();
			String str=" ";String line="";
			//HttpPost post=new HttpPost("https://www.mssmbdealhub.com/Services/Service1.svc/GetMobileDashboardP?deviceId=0384741FD8A9C7E851EFA9B218E3F907A8936091");
			HttpPost post=new HttpPost("https://www.mssmbdealhub.com/Services/Service1.svc/GetNextSetLeadSummariesP?deviceId=0384741FD8A9C7E851EFA9B218E3F907A8936091");
//			post.addHeader("X-Parse-Application-Id","QRgHJmTvRLIr4FrWuUH83cZ2RyRelm4z9OFvuePR");
//			post.addHeader("X-Parse-REST-API-Key","vqm5GfSHBJbxLGHLXW50gG40LWO8HILgtPxYg1pP");
			StringEntity str1=new StringEntity(obj.toString());
			str1.setContentType("application/json");
			post.setEntity(str1);
			HttpResponse response1 = client.execute(post);
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response1.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {
				str+=line;		     			
			}
			System.out.println(str);
			//System.out.println(arx1.get(0));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
