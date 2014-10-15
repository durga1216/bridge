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

import org.json.JSONObject;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
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
			HttpTransport transport = new NetHttpTransport();
		    JacksonFactory jsonFactory = new JacksonFactory();
			String code=request.getParameter("code");
			GoogleTokenResponse response1 =
				 new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory, CLIENT_ID, CLIENT_SECRET,
			    		code, REDIRECT_URI).execute();
			Credential credential =  new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
			    .setJsonFactory(jsonFactory).setTransport(transport).build()
			    		.setAccessToken(response1.getAccessToken()).setRefreshToken(response1.getRefreshToken());
			//===========================
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);
			PreparedStatement st2=con.prepareStatement("insert into token (tempid,tid,oauthtoken) values ('"+tempid+"','"+tid+"','"+response1.getAccessToken()+"')");
		   	st2.executeUpdate();
		   	st2.close();
			//===========================
			SpreadsheetService service =
		            new SpreadsheetService("Aplication-name");
		     service.setOAuth2Credentials(credential);
		    URL SPREADSHEET_FEED_URL = new URL(
		        "https://spreadsheets.google.com/feeds/spreadsheets/private/full");
		    SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
		        SpreadsheetFeed.class);
		    List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets = feed.getEntries();
		     if (spreadsheets.isEmpty()) {
		    }
		    com.google.gdata.data.spreadsheet.SpreadsheetEntry spreadsheet = spreadsheets.get(0);
		    System.out.println(spreadsheet.getTitle().getPlainText());
		    WorksheetFeed worksheetFeed = service.getFeed(
		        spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
		    List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
		    WorksheetEntry worksheet = worksheets.get(0);

		    // Fetch the cell feed of the worksheet.
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
		    session.setAttribute("xml1", obj1.toString());
		  }catch(Exception e){
			  out.println(e);
		  }
		request.setAttribute("code", 200);
        request.setAttribute("code1", 0);
        request.getRequestDispatcher("check.jsp").forward(request, response);
	  }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
