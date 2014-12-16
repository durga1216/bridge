package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.PostalAddress;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

/**
 * Servlet implementation class GauthCall
 */
@WebServlet("/GauthCall")
public class GauthCall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GauthCall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try{
			String CLIENT_ID = "758153664645-n04dc4ki6pr383jdnrq6hmgjsvbsibls";
			String CLIENT_SECRET = "YsLu7TgD4q_NmheHjx4W2Okf";
			String REDIRECT_URI = "https://bridge-minddotss.rhcloud.com/GauthCall";
			HttpSession session=request.getSession(true);
			String tempid=(String) session.getAttribute("tempid");
			String tid=(String) session.getAttribute("tid");
			String type=(String) session.getAttribute("rtype");
			String Gurl=(String) session.getAttribute("Gurl");
			HttpTransport transport = new NetHttpTransport();
		    JacksonFactory jsonFactory = new JacksonFactory();
			String code=request.getParameter("code");
			GoogleTokenResponse response1 = new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory,
					CLIENT_ID, CLIENT_SECRET,code, REDIRECT_URI).execute();
			//TODO Store the access_token in database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
			PreparedStatement st2=con.prepareStatement("insert into token (tempid,tid,oauthtoken,secret) values ('"+tempid+"','"+tid+"','"+response1.getAccessToken()+"','"+response1.getRefreshToken()+"')");
		   	st2.executeUpdate();
		   	st2.close();
		   	if(type.equals("trigger")){
		   		if(Gurl.equals("spreadsheet_data")){
		   			//TODO continue with spreadsheet services
					Credential credential =  new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
							.setJsonFactory(jsonFactory).setTransport(transport).build()
					    	.setAccessToken(response1.getAccessToken()).setRefreshToken(response1.getRefreshToken());
					SpreadsheetService service = new SpreadsheetService("Aplication-name");
				    service.setOAuth2Credentials(credential);
				    URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
				    SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,SpreadsheetFeed.class);
				    List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets = feed.getEntries();
				    if (spreadsheets.isEmpty()) {
				    	//TODO no spreadsheeet
				    }
				    com.google.gdata.data.spreadsheet.SpreadsheetEntry spreadsheet = spreadsheets.get(0);
				    //System.out.println(spreadsheet.getTitle().getPlainText());
				    WorksheetFeed worksheetFeed = service.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
				    List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
				    WorksheetEntry worksheet = worksheets.get(0);
				    //TODO Fetch the cell feed of the worksheet.
				    URL cellFeedUrl = worksheet.getCellFeedUrl();
				    CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
				    JSONObject obj = new JSONObject();
				    JSONObject obj1 = new JSONObject();
				    String title=spreadsheet.getTitle().getPlainText();
				    for (CellEntry cell : cellFeed.getEntries()) {
				    	String name=cell.getTitle().getPlainText();
				    	String value=cell.getCell().getValue();
				    	obj.put(name, value);
				    }
				    obj1.put(title, obj); 
				    String sdata=obj1.toString();
				    sdata=sdata.replaceAll(" ", "_");
				    session.setAttribute("xml1", sdata);
		   		}
		   		if(Gurl.equals("Google_contacts")){
		   			Credential credential =  new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
							.setJsonFactory(jsonFactory).setTransport(transport).build()
					    	.setAccessToken(response1.getAccessToken()).setRefreshToken(response1.getRefreshToken());
		   			  ContactsService myService = new ContactsService("Mind Pulpy");
			  		  myService.setOAuth2Credentials(credential);
			  		  URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			  		  Query myQuery = new Query(feedUrl);
			  		  myQuery.setMaxResults(500);
			  		  myQuery.setStringCustomParameter("sortorder", "ascending");
			  		  ContactFeed resultFeed = myService.query(myQuery, ContactFeed.class);
			  		  // Print the results
			  		  String title=resultFeed.getTitle().getPlainText();
			  		title=title.replaceAll(" ", "_");
			  		  System.out.println(title);
			  		  System.out.println(resultFeed.getEntries().size());
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
			  		  session.setAttribute("xml1", obj1.toString());
		   		}
		   		else{
				   	//TODO For google analytics
				   	//String url="https://www.googleapis.com/analytics/v3/data/ga?ids=ga%3A85990559&start-date=2014-01-01&end-date=today&metrics=ga%3Apageviews";
					HttpClient cli=new DefaultHttpClient();
					HttpGet get=new HttpGet(Gurl);
					get.addHeader("Authorization","Bearer "+response1.getAccessToken());
					get.addHeader("X-JavaScript-User-Agent","Google APIs Explorer");
					HttpResponse response2=cli.execute(get);
					BufferedReader bf=new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
					String line="";String str="";
					while((line=bf.readLine())!=null){
							str=str+line;
					}
					session.setAttribute("xml1", str);
		   		}
		   	}
		   	else{
		   		String sheetname="";
				Credential credential =  new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
						.setJsonFactory(jsonFactory).setTransport(transport).build()
				    	.setAccessToken(response1.getAccessToken()).setRefreshToken(response1.getRefreshToken());
				SpreadsheetService service = new SpreadsheetService("Aplication-name");
			    service.setOAuth2Credentials(credential);
			    URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
			    SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,SpreadsheetFeed.class);
			    List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets = feed.getEntries();
			    for(int i=0;i<spreadsheets.size();i++){
				    sheetname+=spreadsheets.get(i).getTitle().getPlainText()+"@@";
			    }
			    session.setAttribute("sheetname", sheetname);
		   	}
			
		}catch(Exception e){
			  out.println(e);
		}
		request.setAttribute("code", 200);
        request.setAttribute("code1", 200);
        request.getRequestDispatcher("check.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
