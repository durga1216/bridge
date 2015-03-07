package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;

/**
 * Servlet implementation class WebHooks
 */
@WebServlet("/WebHooks")
public class WebHooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebHooks() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
//		String order = request.getParameter("order");
//		String subject = request.getParameter("subject");
//		String XmlString = request.getParameter("XmlString");
		try {
			StringBuffer jb = new StringBuffer();
			String line = null;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
			String res = jb.toString();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(Util.url, Util.user, Util.pass);
			PreparedStatement ps = conn
					.prepareStatement("insert into hook (str) values ('"
							+ res + "')");
			ps.executeUpdate();
		} catch (Exception e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String Tid = "TMP_004";
		String zoho = request.getParameter("zoho_webhook");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(Util.url, Util.user, Util.pass);
			PreparedStatement ps = conn
					.prepareStatement("insert into hook (str) values ('" + zoho
							+ "')");
			ps.executeUpdate();
			PreparedStatement st3 = conn
					.prepareStatement("select * from act_all where tempid=?");
			st3.setString(1, Tid);
			ResultSet rs2 = st3.executeQuery();
			while (rs2.next()) {
				String authen = rs2.getString("authen");
				String apkey = rs2.getString("apkey");
				String ak1 = rs2.getString("aplabel");
				String tempid = rs2.getString("tempid");
				String aid = rs2.getString("aid");
				String rmethod = rs2.getString("rmethod");
				String rformat = rs2.getString("rformat");
				String resformat1 = rs2.getString("resformat");
				String endurl1 = rs2.getString("emethod");
				String dn = rs2.getString("dn");
				String dn1 = rs2.getString("dn1");
				String p1 = rs2.getString("p1");
				String p2 = rs2.getString("p2");
				String p3 = rs2.getString("p3");
				String p4 = rs2.getString("p4");
				String p5 = rs2.getString("p5");
				String p6 = rs2.getString("p6");
				String p7 = rs2.getString("p7");
				String tlabel = rs2.getString("tlabel");
				String treplace = rs2.getString("treplace");
				String pv1 = rs2.getString("pv1");
				String pv2 = rs2.getString("pv2");
				String pv3 = rs2.getString("pv3");
				String pv4 = rs2.getString("pv4");
				String pv5 = rs2.getString("pv5");
				String pv6 = rs2.getString("pv6");
				String pv7 = rs2.getString("p7");
				String h1 = rs2.getString("h1");
				String hv1 = rs2.getString("hv1");
				String h2 = rs2.getString("h2");
				String hv2 = rs2.getString("hv2");
				String h3 = rs2.getString("h3");
				String hv3 = rs2.getString("hv3");
				String h4 = rs2.getString("h4");
				String hv4 = rs2.getString("hv4");
				String h5 = rs2.getString("h5");
				String hv5 = rs2.getString("hv5");
				String b2 = rs2.getString("b2");
				String b4 = rs2.getString("b4");

				String str1 = "";
				if (authen.equals("Oauth2")) {
					String refresh = null;
					PreparedStatement st4 = conn
							.prepareStatement("select * from token where tempid=? && tid=?");
					st4.setString(1, Tid);
					st4.setString(2, aid);
					ResultSet rs4 = st4.executeQuery();
					String access_token = "";
					while (rs4.next()) {
						access_token = rs4.getString("oauthtoken");
						refresh = rs4.getString("secret");
					}
					HttpClient client = new DefaultHttpClient();
					String line = "";
					if (rmethod.equals("Post")) {
						HttpPost post = new HttpPost(endurl1);
						if ("Authorization:Bearer".equals(treplace)) {
							StringEntity input = new StringEntity(zoho);
							input.setContentType("application/json;charset=UTF-8");
							post.setHeader("X-Shopify-Access-Token",
									"28eff0aa6917bee2403c1595efea1699");
							post.setHeader("Accept", "application/json");
							post.setEntity(input);
							HttpResponse response1 = client.execute(post);
							BufferedReader rd = new BufferedReader(
									new InputStreamReader(response1.getEntity()
											.getContent()));
							while ((line = rd.readLine()) != null) {
								str1 += line;
							}
						}
					}
				}
				PreparedStatement ps1 = conn
						.prepareStatement("insert into testing (data) values('"
								+ str1 + "')");
				ps1.executeUpdate();
			}
		} catch (Exception e) {

		}
	}

}
