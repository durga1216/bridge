package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		 Document doc;
			try {
				//String str="<name><fname><niname>sfs</niname></fname><lname>sdfas</lname></name>";
				HttpClient cli = new DefaultHttpClient();
				  HttpGet get = new HttpGet("https://www.eventbrite.com/xml/event_search?app_key=3NC7TK5MON5ABMCYD4&city=chennai");
				  HttpResponse res=cli.execute(get);
				  BufferedReader in = new BufferedReader(
		                  new InputStreamReader(res.getEntity().getContent()));
					String line="";String str="";
		                  while((line=in.readLine())!=null){
		                  	str+=line;
		                  }
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			     InputSource is = new InputSource();
			     is.setCharacterStream(new StringReader(str));
				doc = db.parse(is);
				NodeList nn=doc.getElementsByTagName("event");
				Element element = (Element) nn.item(0);
				NodeList nod=element.getChildNodes();
				//NodeList nod = doc.getDocumentElement().getChildNodes();
				out.println("<style>body{background-color:#ff9900;}input[type='text'],select,option{width:250px;height:40px;padding:5px;font-size:17px;color:#ff9900;margin-left:100px;border-radius:7px;background-color:#fff;}h2{color:#fff;margin-left:100px;}th{color:#fff;}</style>");
				out.println("<html><body><br><br><h2>3,Pick the mapping feilds from response:</h2>");
				out.println("<br><br><table style='border:none;padding:5px;margin-left:100px;'><tr><th>*Pick a feild for Mapping</th><th>If other</th><th>Pick Action Parameter for Mapping</th></tr><tr><td><select>");
			     for (int i = 0; i < nod.getLength(); i++) {
			       Node node = nod.item(i);
			       if (node instanceof Element) {
			         Element childElement = (Element) node;
			         out.println("<option value="+childElement.getTagName()+">"+childElement.getTagName()+"</option>");
			         System.out.println("child name: " + childElement.getTagName());
			       }
			     }
			     out.println("</select></td>"
			     		+ "<td><input type='text' name='other'></td>"
			     		+ "<td><select><option>Choose Action Parameter</option></select></td></tr></table>"
			     		+ "<br><br><br><br><center><input type='button' value='continue' style='padding:7px;color:#ff9900;font-family:verdana;font-size:18px;background-color:#fff;border-radius:5px;border:1px solid #fff;'></center></body></html>");
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
