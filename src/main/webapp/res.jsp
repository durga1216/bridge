<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script src="js/jquery-latest.js"></script>
	<style>
body{
background-color:#FF9900;}
.head{
font-family:verdana;
font-size:28px;
font-weight:bold;
color:#FFFFFF;
margin-left:600px;
}
#ful{
width:100%;
height:100%;
border:solid 1px #fff;
color:#fff;
font-family:verdana; 
font-weight:bold;
font-size:16px;
}
input[type="text"]{
width:250px;
height:20px;
font-family:verdana; 
font-weight:bold;
font-size:15px;
color:#FF9900;
border-radius:5px;
}
#para{
width:45%;
height:100%;
border:solid 1px #fff;
}
#txt1{
font-family:verdana;
font-size:15px;
margin-left:100px;
color:#FF9900;
font-weight:bold;
width:400px;
height:150px;
}
#res{
width:45%;
height:100%;
border:solid 1px #fff;
}
</style>
</head>
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<%
		ResultSet r=null;ResultSet rs =null; 
		String actit="";String tgtit="";String tid="";String aid="";String tempid="";
		String rformat="";String[] tp=new String[5]; 
%>
<%
	try{
	     	 PreparedStatement st1=conn.prepareStatement("select * from home order by tempid desc limit 1");
	     	 r=st1.executeQuery();
	    	 while(r.next()){
	    			tempid=r.getString("tempid");
	 	   			tid=r.getString("tid");
	 	   			aid=r.getString("aid");
	 	   			tgtit=r.getString("tgtit");
	 	   			actit=r.getString("actit");
	 	   	 }
		  PreparedStatement ps = conn.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
	      ps.setString(1,tid);
	      rs=ps.executeQuery();
	      while(rs.next()){
	    			rformat=rs.getString("rformat");
	    			tp[1]=rs.getString("p1");tp[2]=rs.getString("p2");tp[3]=rs.getString("p3");tp[4]=rs.getString("p4");
	      }
%>
<body>
<br><br><div class="head">Mind Bridge</div><br><br>
<div id=ful>
	<center>
		<h3>Action Parameter:</h3>
<%
	if(rformat.equals("rest")){
		%><input type="text" palceholder="parant Tag"><%
		for(int i=1;i<5;i++){
				if(!tp[i].equals("null")){
						out.println("<br>*"+tp[i]+":&nbsp;&nbsp;&nbsp;&gt;----Map With Trigger node----&lt;&nbsp;&nbsp;&nbsp;<input style='width:100px;border-radius:5px;'name='pv"+i+"' type='text'><br>");
				}
		}
	}else{
		%><textarea name="descr" id="txt1"  placeholder="Give Sample xml or json structure"></textarea>&nbsp;&nbsp;&nbsp;&gt;----Map With Trigger node----&lt;&nbsp;&nbsp;&nbsp;Parse any thing<% 	
	}
%>
</center>
</div>
<%		}
		catch(Exception e){
			out.println(e);
		}
%>
</body>
</html>