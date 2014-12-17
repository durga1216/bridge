package com.bridge;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.thread.thread.TriggerClass;

public class MainThreadClass implements Runnable{
	public Thread t;
	   private String Tempid;
	   boolean suspended = false;
	   public MainThreadClass( String name){
		   Tempid = name;
	   }
	   public void run() {
	      try {
	    	  //trigger Part started
	    	  TriggerClass trg=new TriggerClass(Tempid);
	    	  String str=trg.start();
	    	  //Parsing part
	    	  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  final Connection con=DriverManager.getConnection(Util.url,Util.user,Util.pass);	
	    	  String x1="";String x2="";String x3="";String x4="";String x5="";String resf="";
	    	  //TODO For Checking xx value purpose I take null
	    	  String xx1="null";String xx2="null";String xx3="null";String xx4="null";String xx5="null";
	    	  String xx6="null";String xx7="null";String xx8="null";String xx9="null";String xx10="null";
	    	  String xx11="null";String xx12="null";String xx13="null";String xx14="null";String xx15="null";
	    	  String xx16="null";String xx17="null";String xx18="null";String xx19="null";String xx20="null";

	    	  
	    	  String[] xx=new String[10];String check="null";
	    	  String ptag="";String exres="";String shname="";
	    	  PreparedStatement st2=con.prepareStatement("select * from parse where tempid=?");
	    	  st2.setString(1, Tempid);
	    	  ResultSet rs1=st2.executeQuery();
	    	  while(rs1.next()){
	    		  x1=rs1.getString("x1");x2=rs1.getString("x2");
	    		  x3=rs1.getString("x3");x4=rs1.getString("x4");
	    		  x5=rs1.getString("x5");ptag=rs1.getString("ptag");
	    		  exres=rs1.getString("exres");shname=rs1.getString("shname");
	    		  resf=rs1.getString("resf");
	    	  } 
	    	  if(resf.equals("json")){
					try{
						ScriptEngineManager manager = new ScriptEngineManager();
					    ScriptEngine engine = manager.getEngineByName("javascript");
					    engine.eval("var x = "+str+";");
						if(!x1.equals("null")){
							xx1=String.valueOf(engine.eval("x."+x1+";"));}
						if(!x2.equals("null")){
							xx2=String.valueOf(engine.eval("x."+x2+";"));}
						if(!x3.equals("null")){
							xx3=String.valueOf(engine.eval("x."+x3+";"));}
						if(!x4.equals("null")){
							xx4=String.valueOf(engine.eval("x."+x4+";"));}
						if(!x5.equals("null")){
							xx5=String.valueOf(engine.eval("x."+x5+";"));}
					}catch(Exception e){
						check=e.toString();
					}
				}
				else if(resf.equals("xml")){
					try{
			       	 	DocumentBuilderFactory domFactory=DocumentBuilderFactory.newInstance();
			       	 	DocumentBuilder builder=domFactory.newDocumentBuilder();
						Document doc=builder.parse(new InputSource(new ByteArrayInputStream(str.getBytes("UTF-8"))));
						XPath xPath = XPathFactory.newInstance().newXPath();
						if(!x1.equals("null")){
							xx1=xPath.compile(ptag+"/"+x1).evaluate(doc);}
						if(!x2.equals("null")){
							xx2=xPath.compile(ptag+"/"+x2).evaluate(doc);}
						if(!x3.equals("null")){
							xx3=xPath.compile(ptag+"/"+x3).evaluate(doc);}
						if(!x4.equals("null")){
							xx4=xPath.compile(ptag+"/"+x4).evaluate(doc);}
						if(!x5.equals("null")){
							xx5=xPath.compile(ptag+"/"+x5).evaluate(doc);}
					}catch(Exception e){
						check=e.toString();
					}
				}
				PreparedStatement st31=con.prepareStatement("insert into test (te,temp) values ('"+str+"\n"+xx1+"\n"+xx2+"\n"+x1+"\n"+x2+"\n"+check+"\n"+resf+"','"+Tempid+"')");
			   	st31.executeUpdate();
			   	st31.close();
			   	String orurl="";
			   	try{
					xx[1]=xx1;xx[2]=xx2;xx[3]=xx3;xx[4]=xx4;xx[5]=xx5;
					String[] slt=exres.split("@@");
					int nn=slt.length;
					if(!(nn==0)){
						for(int i=1,j=1;i<nn;i=i+2,j++){
							slt[i]=xx[j];
						}
						for(int k=0;k<nn;k++){
							orurl=orurl+slt[k];
						}
					}else{
						orurl=exres;
					}
			   	}catch(Exception e){
			   		check=e.getMessage();
			   	}
	 			  ActionClass act=new ActionClass(Tempid,xx1,xx2,xx3,xx4,xx5,xx6,xx7,xx8,xx9,xx10,xx11,xx12,xx13,xx14,xx15,xx16,xx17,xx18,xx19,xx20,orurl,shname);
			  String str1=act.start();
	    	  synchronized(this) {
	    		  while(suspended) {
	    			  wait();
	    		  }
	    	  }
	      }catch (InterruptedException e) {
	    	  System.out.println("Thread " +  Tempid + " interrupted.");
	      }
	      catch(Exception e){
	    	  
	      }
	   }
	   public void start () {
	      System.out.println("Starting " +  Tempid );
	      if (t == null){
	         t = new Thread (this, Tempid);
	         t.start ();
	      }
	   }
	   void suspend() {
	      suspended = true;
	   }
	   synchronized void resume() {
	      suspended = false;
	       notify();
	   }
}
