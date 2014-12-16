package com.test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bridge.ActionClass;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.PostalAddress;
import com.jayway.jsonpath.JsonPath;

public class Hget {

	public static void main(String[] args) {
		try{
			String str="";
			String CLIENT_ID = "758153664645-n04dc4ki6pr383jdnrq6hmgjsvbsibls";
				String CLIENT_SECRET = "YsLu7TgD4q_NmheHjx4W2Okf";
				HttpTransport transport = new NetHttpTransport();
			    JacksonFactory jsonFactory = new JacksonFactory();
			    System.out.println("test1");
			Credential credential =  new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
					.setJsonFactory(jsonFactory).setTransport(transport).build()
			    	.setAccessToken("ya29.3gCaCS6HjfjnqQM9eKHRJ-5V-CeUnqJS9MZmlCNfNvH-I6xy_NYFhqUHHzk_uNyebx6lECbg6QyX6A").setRefreshToken("1/SIChJG3OURk0x3tNuVybME-ajzBNXpJEF-PjKQ6e2kg");
			System.out.println("test1");  
			ContactsService myService = new ContactsService("Mind-Pulpy");
	  		  myService.setOAuth2Credentials(credential);
	  		System.out.println("test1");
	  		  URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
	  		  Query myQuery = new Query(feedUrl);
	  		  myQuery.setMaxResults(500);
	  		System.out.println("test1");
	  		  ContactFeed resultFeed = myService.query(myQuery, ContactFeed.class);
	  		System.out.println("test1");
	  		  JSONArray arr=new JSONArray();
	  		  int i=0;
	  		  for (ContactEntry entry : resultFeed.getEntries()) {
	  			  JSONObject obj = new JSONObject();
	  			  if (entry.hasName()) {
		  		      Name name = entry.getName();
		  		      String fullname = name.getFullName().getValue();
		  		      obj.put("Name", fullname);}	
	  		      else
		  		    	obj.put("Name", "");
		  		      List<PhoneNumber> number=entry.getPhoneNumbers();
	  		      if(number.size()>0){
		  		      String num=number.get(0).getPhoneNumber().toString();
		  		      obj.put("Phone", num);}
	  		      else
		  		    	obj.put("Phone", "");
		  		      List<PostalAddress> country=entry.getPostalAddresses();
	  		      if(country.size()>0){
		  		      String cty=country.get(0).getLabel();
		  		      obj.put("Address", cty);}
	  		      else
		  		    	obj.put("Address", "");
		  		      List<Email> email=entry.getEmailAddresses();
	  		      if(email.size()>0){
		  		      String em=email.get(0).getAddress();
		  		      obj.put("Email", em);}
	  		      else
		  		      obj.put("Email", "");
		  		      List<GroupMembershipInfo> group=entry.getGroupMembershipInfos();
	  		      if(group.size()>0){
		  		      String groupHref = group.get(0).getHref();
		  		      obj.put("Group_id", groupHref);}
	  		      else
	  		    	  obj.put("Group_id", "");
		  		      System.out.println(obj.toString());
		  		      arr.put(i, obj);
		  		      i++;
	  		  }
	  		  JSONObject obj1 = new JSONObject();
	  		  obj1.put("contacts", arr);
	  		  str=obj1.toString();

	  		  System.out.println(str);
			}catch(Exception e){
				System.out.println(e);
			}
	}

}
