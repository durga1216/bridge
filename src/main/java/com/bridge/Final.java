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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
			   		String b2=rs.getString("b2");String b4=rs.getString("b4");
			   		String tlabel=rs.getString("tlabel");String treplace=rs.getString("treplace");
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
					   			HttpClient httpClient = new DefaultHttpClient();
								HttpGet postRequest = new HttpGet(endurl1);
								String encoding = new String(
								  		 org.apache.commons.codec.binary.Base64.encodeBase64   
								  		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+b4))
								  		  );
								postRequest.setHeader("Authorization","Basic " + encoding);
								HttpResponse response1 = httpClient.execute(postRequest);
								BufferedReader in1 = new BufferedReader(
								     new InputStreamReader(response1.getEntity().getContent()));
								String line1="";
								     while((line1=in1.readLine())!=null){
								     	 	str+=line1;
								     }
			   			}
			   		else if(authen.equals("Oauth2")){
			   			PreparedStatement st=con.prepareStatement("select * from token where tempid=?");
			   			st.setString(1, da);
					    ResultSet rs1=st.executeQuery();String access_token="";
					    while(rs1.next()){
					    	access_token=rs1.getString("oauthtoken");
					    }
			   			HttpClient client = new DefaultHttpClient();
			   			String line="";
			   			if(rmethod.equals("GET")){ 
				     		if("Authorization:Bearer".equals(treplace)){

			     			HttpGet get=new HttpGet(endurl1);
						       get.addHeader("Authorization", "Bearer "+access_token);
					     		HttpResponse response1 = client.execute(get);
					     		BufferedReader rd = new BufferedReader(
					     				new InputStreamReader(response1.getEntity().getContent()));
					     			while ((line = rd.readLine()) != null) {
			                         str=line;		     			}
					     	}
					     	else if("QueryString".equals(treplace)){
					     		
					     		String param = null;
						     	   // List<NameValuePair> params = new LinkedList<NameValuePair>();

						     		 if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6))
						     			 param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5+"&"+p6+"="+pv6;
						        		 
						             else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5))
				                         param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4+"&"+p5+"="+pv5;

						     	
						              else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4))
					                         param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3+"&"+p4+"="+pv4;

						              else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3))
					                         param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2+"&"+p3+"="+pv3;

						        	  else if(!"null".equals(p1) && !"null".equals(p2))
					                         param=tlabel+"="+access_token+"&"+p1+"="+pv1+"&"+p2+"="+pv2;

						              else if(!"null".equals(p1))
					                         param=tlabel+"="+access_token+"&"+p1+"="+pv1;

						        	  else if("null".equals(p1))
					                         param=tlabel+"="+access_token;
						     		 String pointurl=endurl1+"?"+param;
						     	    //String paramString = URLEncodedUtils.format(param, "utf-8");
								     	HttpGet get=new HttpGet(pointurl);
							            HttpResponse response1=client.execute(get);
							            BufferedReader rd = new BufferedReader
									    		  (new InputStreamReader(response1.getEntity().getContent()));
									    		    
									    		while ((line = rd.readLine()) != null) {
									    			str=line;
									    		}
									    			
							} // query string
			     		}//get

					     	

					    	else if(rmethod.equals("POST")){
					     		HttpPost post=new HttpPost(endurl1);

					     		
					     		if("Authorization:Bearer".equals(treplace)){
					     			
					     			post.addHeader("Authorization", "Bearer "+access_token);
									HttpResponse response1=client.execute(post);
									BufferedReader rd = new BufferedReader(
						     				new InputStreamReader(response1.getEntity().getContent()));
						     			while ((line = rd.readLine()) != null) {
				                         str=line;		     			}
									}
					     		
					     		else if("QueryString".equals(treplace)){
						     		
					     			 List <NameValuePair> cod = new ArrayList <NameValuePair>();
						     		 if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
								    	 cod.add(new BasicNameValuePair(tlabel,access_token));
							    	     cod.add(new BasicNameValuePair(p1,p1));
							    	     cod.add(new BasicNameValuePair(p2,p2));
							    	     cod.add(new BasicNameValuePair(p3,p3));
							    	     cod.add(new BasicNameValuePair(p4,p4));
							    	     cod.add(new BasicNameValuePair(p5,p5));
							    	     cod.add(new BasicNameValuePair(p6,p6));}


						     			 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
						     				  cod.add(new BasicNameValuePair(tlabel,access_token));
								    	     cod.add(new BasicNameValuePair(p1,p1));
								    	     cod.add(new BasicNameValuePair(p2,p2));
								    	     cod.add(new BasicNameValuePair(p3,p3));
								    	     cod.add(new BasicNameValuePair(p4,p4));
								    	     cod.add(new BasicNameValuePair(p5,p5));	}    
							     		
						     			 
						     			 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){cod.add(new BasicNameValuePair(tlabel,access_token));
							    	     cod.add(new BasicNameValuePair(p1,p1));
							    	     cod.add(new BasicNameValuePair(p2,p2));
							    	     cod.add(new BasicNameValuePair(p3,p3));
							    	     cod.add(new BasicNameValuePair(p4,p4));
							    	     }
								     		
							     		 
							     		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){cod.add(new BasicNameValuePair(tlabel,access_token));
								    	     cod.add(new BasicNameValuePair(p1,p1));
								    	     cod.add(new BasicNameValuePair(p2,p2));
								    	     cod.add(new BasicNameValuePair(p3,p3));
								    	     }
									     		
								     		 
								     		 else if(!"null".equals(p1) && !"null".equals(p2)){cod.add(new BasicNameValuePair(tlabel,access_token));
									    	     cod.add(new BasicNameValuePair(p1,p1));
									    	     cod.add(new BasicNameValuePair(p2,p2));
									    	     }
										     		
									     		 
									     		 else if(!"null".equals(p1)){
										     			cod.add(new BasicNameValuePair(tlabel,access_token));
											    	    cod.add(new BasicNameValuePair(p1,p1));
											    	     
										     		 }
									     		 else if("null".equals(p1)){
										     			cod.add(new BasicNameValuePair(tlabel,access_token));

									     		 }
								        post.setEntity(new UrlEncodedFormEntity(cod));
								        HttpResponse response1 = client.execute(post);
								        BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
								        while ((line = rd.readLine()) != null) {
						                 str=line;	        }


					     		}			 
					     	}
			   		}
			   		}//while
			   String x1="";String x2="";String x3="";String x4="";String x5="";
			   String xx1="";String xx2="";String xx3="";String xx4="";String xx5="";
			   String[] xx=new String[6];
			   String ptag="";String exres="";
			   PreparedStatement st2=con.prepareStatement("select * from parse where tempid=?");
			   st2.setString(1, da);
			   ResultSet rs1=st2.executeQuery();
			   while(rs1.next()){
				   x1=rs1.getString("x1");x2=rs1.getString("x2");
				   x3=rs1.getString("x3");x4=rs1.getString("x4");
				   x5=rs1.getString("x5");ptag=rs1.getString("ptag");
				   exres=rs1.getString("exres");
				   
			   } 
			 DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      	     InputSource is = new InputSource();
      	     is.setCharacterStream(new StringReader(str));
      	     Document doc = db.parse(is);
      	     NodeList nodes = doc.getElementsByTagName(ptag);
      	     int tot=nodes.getLength();String res="";
      	     String[] data=new String[tot];
      	     for (int i = 0,j=1; i < nodes.getLength(); i++,j++) {
	      	       Element element = (Element) nodes.item(i);
	      	       NodeList name = element.getElementsByTagName(x1);
	      	       Element line = (Element) name.item(0);
	      	       xx1=getCharacterDataFromElement(line);
      	     }
      	    xx[1]=xx1;xx[2]=xx2;xx[3]=xx3;
      	    String[] slt=exres.split("@@");
      		int nn=slt.length;String orurl="";
      		if(!(nn==0)){
	      		for(int i=1,j=1;i<nn;i=i+2,j++){
	      			slt[i]=xx[j];
	      		}
	      		for(int k=0;k<nn;k++){
	      			orurl=orurl+slt[k];
	      		}
      		}
      			PreparedStatement st3=con.prepareStatement("select * from act_all where tempid=?");
			    st3.setString(1, da);
			    ResultSet rs2=st3.executeQuery();
			   while(rs2.next()){
				   String authen=rs2.getString("authen");String apkey=rs2.getString("apkey");
			   		String ak1=rs2.getString("aplabel");
			   		String tempid=rs2.getString("tempid");String aid=rs2.getString("aid");
			   		String rmethod=rs2.getString("rmethod");String rformat=rs2.getString("rformat");
			   		String resformat=rs2.getString("resformat");String endurl1=rs2.getString("emethod");
			   		String dn=rs2.getString("dn");String dn1=rs2.getString("dn1");
			   		String p1=rs2.getString("p1");String p2=rs2.getString("p2");
			   		String p3=rs2.getString("p3");String p4=rs2.getString("p4");
			   		String p5=rs2.getString("p5");String p6=rs2.getString("p6");
			   		String p7=rs2.getString("p7");
			   		String tlabel=rs2.getString("tlabel");String treplace=rs2.getString("treplace");
			   		String pv1=rs2.getString("pv1");String pv2=rs2.getString("pv2");
			   		String pv3=rs2.getString("pv3");String pv4=rs2.getString("pv4");
			   		String pv5=rs2.getString("pv5");String pv6=rs2.getString("pv6");
			   		String pv7=rs2.getString("p7");
			   		String h1=rs2.getString("h1"); String hv1=rs2.getString("hv1");
			   		String h2=rs2.getString("h2"); String hv2=rs2.getString("hv2");
			   		String h3=rs2.getString("h3"); String hv3=rs2.getString("hv3");
			   		String h4=rs2.getString("h4"); String hv4=rs2.getString("hv4");
			   		String h5=rs2.getString("h5"); String hv5=rs2.getString("hv5");
			   		String b2=rs2.getString("b2"); String b4=rs2.getString("b4");
			   		String str1="";
			   		if(authen.equals("API keys")){
			   			if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3+"&"+p4+"="+xx4+"&"+p5+"="+xx5;}
		        		 
		        		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3+"&"+p4+"="+xx4;}
		        		 
		        		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3;}
		        		 
		        		 else if(!"null".equals(p1) && !"null".equals(p2)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1+"&"+p2+"="+xx2;}
		        		 
		        		 else if(!"null".equals(p1)){
			        		 eurl=endurl1+"?"+ak1+"="+apkey+"&"+p1+"="+xx1;}
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
	    	  	       	 		str1+=line;
	    	  	       	 }
		   			}
			   		else if (authen.equals("Basic Auth")){
			   			
							HttpClient httpClient = new DefaultHttpClient();
							HttpPost postRequest = new HttpPost(endurl1);
							StringEntity input = new StringEntity(orurl);
							input.setContentType("application/json");
							postRequest.setEntity(input);
							String encoding = new String(
							  		 org.apache.commons.codec.binary.Base64.encodeBase64   
							  		    (org.apache.commons.codec.binary.StringUtils.getBytesUtf8(b2+":"+b4))
							  		  );
							postRequest.setHeader("Authorization","Basic " + encoding);
							HttpResponse response1 = httpClient.execute(postRequest);
							BufferedReader in1 = new BufferedReader(
							     new InputStreamReader(response1.getEntity().getContent()));
							String line1="";
							     while((line1=in1.readLine())!=null){
							     	 	str1+=line1;
							     }
			   		}
			   		else if(authen.equals("Oauth2")){
			   			PreparedStatement st4=con.prepareStatement("select * from token where tempid=?");
			   			st4.setString(1, da);
					    ResultSet rs4=st4.executeQuery();String access_token="";
					    while(rs4.next()){
					    	access_token=rs4.getString("oauthtoken");
					    }
			   			HttpClient client = new DefaultHttpClient();
			   			String line="";
			   			if(rmethod.equals("GET")){ 
				     		if("Authorization:Bearer".equals(treplace)){

			     			HttpGet get=new HttpGet(endurl1);
						       get.addHeader("Authorization", "Bearer "+access_token);
					     		HttpResponse response1 = client.execute(get);
					     		BufferedReader rd = new BufferedReader(
					     				new InputStreamReader(response1.getEntity().getContent()));
					     			while ((line = rd.readLine()) != null) {
			                         str=line;		     			}
					     	}
					     	else if("QueryString".equals(treplace)){
					     		
					     		String param = null;
						     	   // List<NameValuePair> params = new LinkedList<NameValuePair>();

					     			  if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5))
				                         param=tlabel+"="+access_token+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3+"&"+p4+"="+xx4+"&"+p5+"="+xx5;

						     	
						              else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4))
					                         param=tlabel+"="+access_token+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3+"&"+p4+"="+xx4;

						              else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3))
					                         param=tlabel+"="+access_token+"&"+p1+"="+xx1+"&"+p2+"="+xx2+"&"+p3+"="+xx3;

						        	  else if(!"null".equals(p1) && !"null".equals(p2))
					                         param=tlabel+"="+access_token+"&"+p1+"="+xx1+"&"+p2+"="+xx2;

						              else if(!"null".equals(p1))
					                         param=tlabel+"="+access_token+"&"+p1+"="+xx1;

						        	  else if("null".equals(p1))
					                         param=tlabel+"="+access_token;
						     		 String pointurl=endurl1+"?"+param;
						     	    //String paramString = URLEncodedUtils.format(param, "utf-8");
								     	HttpGet get=new HttpGet(pointurl);
							            HttpResponse response1=client.execute(get);
							            BufferedReader rd = new BufferedReader
									    		  (new InputStreamReader(response1.getEntity().getContent()));
									    		    
									    		while ((line = rd.readLine()) != null) {
									    			str=line;
									    		}
									    			
							} // query string
			     		}//get

					     	

					    	else if(rmethod.equals("Post")){
					     		HttpPost post=new HttpPost(endurl1);

					     		
					     		if("Authorization:Bearer".equals(treplace)){
					     			
					     			post.addHeader("Authorization", "Bearer "+access_token);
									HttpResponse response1=client.execute(post);
									BufferedReader rd = new BufferedReader(
						     				new InputStreamReader(response1.getEntity().getContent()));
						     			while ((line = rd.readLine()) != null) {
				                         str=line;		     			}
									}
					     		
					     		else if("QueryString".equals(treplace)){
						     		
					     			 List <NameValuePair> cod = new ArrayList <NameValuePair>();
						     		 if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5) && !"null".equals(p6)){
								    	 cod.add(new BasicNameValuePair(tlabel,access_token));
							    	     cod.add(new BasicNameValuePair(p1,p1));
							    	     cod.add(new BasicNameValuePair(p2,p2));
							    	     cod.add(new BasicNameValuePair(p3,p3));
							    	     cod.add(new BasicNameValuePair(p4,p4));
							    	     cod.add(new BasicNameValuePair(p5,p5));
							    	     cod.add(new BasicNameValuePair(p6,p6));}


						     			 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4) && !"null".equals(p5)){
						     				  cod.add(new BasicNameValuePair(tlabel,access_token));
								    	     cod.add(new BasicNameValuePair(p1,xx1));
								    	     cod.add(new BasicNameValuePair(p2,xx2));
								    	     cod.add(new BasicNameValuePair(p3,xx3));
								    	     cod.add(new BasicNameValuePair(p4,xx4));
								    	     cod.add(new BasicNameValuePair(p5,xx5));	}    
							     		
						     			 
						     			 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3) && !"null".equals(p4)){cod.add(new BasicNameValuePair(tlabel,access_token));
							    	     cod.add(new BasicNameValuePair(p1,xx1));
							    	     cod.add(new BasicNameValuePair(p2,xx2));
							    	     cod.add(new BasicNameValuePair(p3,xx3));
							    	     cod.add(new BasicNameValuePair(p4,xx4));
							    	     }
								     		
							     		 
							     		 else if(!"null".equals(p1) && !"null".equals(p2) && !"null".equals(p3)){cod.add(new BasicNameValuePair(tlabel,access_token));
								    	     cod.add(new BasicNameValuePair(p1,xx1));
								    	     cod.add(new BasicNameValuePair(p2,xx2));
								    	     cod.add(new BasicNameValuePair(p3,xx3));
								    	     }
									     		
								     		 
								     		 else if(!"null".equals(p1) && !"null".equals(p2)){cod.add(new BasicNameValuePair(tlabel,access_token));
									    	     cod.add(new BasicNameValuePair(p1,xx1));
									    	     cod.add(new BasicNameValuePair(p2,xx2));
									    	     }
										     		
									     		 
									     		 else if(!"null".equals(p1)){
										     			cod.add(new BasicNameValuePair(tlabel,access_token));
											    	    cod.add(new BasicNameValuePair(p1,xx1));
											    	     
										     		 }
									     		 else if("null".equals(p1)){
										     			cod.add(new BasicNameValuePair(tlabel,access_token));

									     		 }
								        post.setEntity(new UrlEncodedFormEntity(cod));
								        HttpResponse response1 = client.execute(post);
								        BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
								        while ((line = rd.readLine()) != null) {
						                 str1=line;	        }


					     		}			 
					     	}
			   		}
					    PreparedStatement ps=con.prepareStatement("insert into testing (data) values('"+str1+"')");
						ps.executeUpdate();
				   		//out.println(str);
			   		}//while
				}//try
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
