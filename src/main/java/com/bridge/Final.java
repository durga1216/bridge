package com.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class Final
 */
@WebServlet("/Final")
public class Final extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Final() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final PrintWriter out=response.getWriter();
		ArrayList<String> ar=new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    final Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);	 
		    PreparedStatement st=con.prepareStatement("select tempid from trig_all");
		    ResultSet rs1=st.executeQuery();
		    while(rs1.next()){
		    	ar.add(rs1.getString("tempid"));
		    }
		    int ttl=ar.size();
		    Thread[] th =new Thread[ttl];
			for(int i=0;i<ttl;i++){
		    final String da=ar.get(i);
			th[i]=new Thread(){
				public void run(){
				try{
			   String str="";String eurl="";
			   PreparedStatement st1=con.prepareStatement("select * from trig_all where tempid=?");
			   st1.setString(1, da);
			   ResultSet rs=st1.executeQuery();
			   while(rs.next()){
			   		String authen=rs.getString("authen");String apkey=rs.getString("apkey");
			   		String ak1=rs.getString("aplabel");
			   		String tempid=rs.getString("tempid");String tid=rs.getString("tid");
			   		String rmethod=rs.getString("rmethod");String rformat=rs.getString("rformat");
			   		String resformat=rs.getString("resformat");String endurl1=rs.getString("emethod");
			   		String dn=rs.getString("dn");String dn1=rs.getString("dn1");
			   		String p1=rs.getString("p1");String p2=rs.getString("p2");
			   		String p3=rs.getString("p3");String p4=rs.getString("p4");
			   		String p5=rs.getString("p5");String p6=rs.getString("p6");
			   		String p7=rs.getString("p7");
			   		String pv1=rs.getString("pv1");String pv2=rs.getString("pv2");
			   		String pv3=rs.getString("pv3");String pv4=rs.getString("pv4");
			   		String pv5=rs.getString("pv5");String pv6=rs.getString("pv6");
			   		String pv7=rs.getString("p7");
			   		String h1=rs.getString("h1"); String hv1=rs.getString("hv1");
			   		String h2=rs.getString("h2"); String hv2=rs.getString("hv2");
			   		String h3=rs.getString("h3"); String hv3=rs.getString("hv3");
			   		String h4=rs.getString("h4"); String hv4=rs.getString("hv4");
			   		String h5=rs.getString("h5"); String hv5=rs.getString("hv5");
			   		
			   		if(authen.equals("API keys")){
			   			if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;}
		        		 
		        		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;}
		        		 
		        		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;}
		        		 
		        		 else if(!"null".equals(p1) && !"null".equals(p2)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1+"&"+p2+"="+pv2;}
		        		 
		        		 else if(!"null".equals(p1)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+pv1;}
		        		 else if("null".equals(p1))
		        			eurl=endurl1+"?"+ak1+"="+apkey;
		        		 
		        		 else if("null".equals(ak1) && "null".equals(apkey))
		        			 eurl=endurl1;	        		
		        		
		        			eurl=eurl.replaceAll(" ", "%20"); 
		        		 URL eurl1=new URL(eurl);
		        		 URLConnection uconn = eurl1.openConnection();
		        	     HttpURLConnection conn = (HttpURLConnection) uconn;
		        	     conn.connect();
		        	     Object content = conn.getContent();
		        	     InputStream stream = (InputStream) content;
		        	     String line=null;
		        	     BufferedReader br=new BufferedReader(new InputStreamReader(stream));
		        	     while((line=br.readLine())!=null){
	    	  	       	 str+=line;
	    	  	       	 }
			   			}
			   		else if(authen.equals("Basic Auth")){
			   			
			   			}
			   		}//while
			   DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      	     InputSource is = new InputSource();
      	     is.setCharacterStream(new StringReader(str));
      	     Document doc = db.parse(is);
      	     NodeList nodes = doc.getElementsByTagName("event");
      	     int tot=nodes.getLength();String res="";
      	     String[] data=new String[tot];
      	     for (int i = 0; i < nodes.getLength(); i++) {
      	       Element element = (Element) nodes.item(i);

      	       NodeList name = element.getElementsByTagName("title");
      	       Element line = (Element) name.item(0);
      	       data[i]=getCharacterDataFromElement(line);
      	       //res=res+"\t\t\tName: " + getCharacterDataFromElement(line);
      	     }
      	   String blt="{";String name="Event-Title";
	        //out.println(data[1]+"---"+data[2]+"---"+data[3]);
   	     int nn=data.length;
	        for(int i=0;i<nn;i++){
  			  blt=blt+"\""+name+"\":\""+data[i]+"\",";
  		  }
  		  blt=method(blt);
  		  blt=blt+"}";
  		  //System.out.println(blt);
 HttpClient httpClient = new DefaultHttpClient();
HttpPost postRequest = new HttpPost("https://minddots.cloudant.com/crud");
StringEntity input = new StringEntity(blt);
input.setContentType("application/json");
postRequest.setEntity(input);
String encoding = new String(
  		 org.apache.commons.codec.binary.Base64.encodeBase64   
  		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8("minddots:MindTest12"))
  		  );
postRequest.setHeader("Authorization","Basic " + encoding);
HttpResponse response1 = httpClient.execute(postRequest);
BufferedReader in1 = new BufferedReader(
     new InputStreamReader(response1.getEntity().getContent()));
String line1="";String str1="";
     while((line1=in1.readLine())!=null){
     	str1+=line1;
     }
			   PreparedStatement ps=con.prepareStatement("insert into testing (data) values('"+str1+"')");
				ps.executeUpdate();
			   		out.println(str);
				}
				catch(Exception e)
				{
					try {
						PreparedStatement ps1=con.prepareStatement("insert into testing (data) values('"+e+"')");
						ps1.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				}//run
			};th[i].start();
		  }//for
		} catch (Exception e1) {
			out.println(e1);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }
	 public static String method(String str) {
		    if (str.length() > 0 && str.charAt(str.length()-1)==',') {
		      str = str.substring(0, str.length()-1);
		    }
		    return str;
		}
}
