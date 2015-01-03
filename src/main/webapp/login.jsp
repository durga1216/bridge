<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
<style type="text/css">
body{
background-color:#ff9900;
}
h1{
color:#fff;
}
input[type="text"]{
color:#FF9900;
font-size:18px;
background-color:#FFFFFF;
font-family:verdana;
width:250px;
height:22px;
padding:5px;
border-radius:5px;
}
input[type="button"],input[type="submit"]{
color:#FFF;
font-size:20px;
background-color:#00f;
font-family:verdana;
width:260px;
height:40px;
border:solid 2px;
border-color:#00f;
border-radius:3px;
padding:5px;
}
input[type="password"]{
color:#FF9900;
font-size:20px;
background-color:#FFFFFF;
font-family:verdana;
width:250px;
height:22px;
padding:5px;
border-radius:5px;
}  
.result{
color:#FFF;
font-size:18px;
font-family:verdana;
}
#tri{
width:30%;
float:left;
margin-left:50px;
height:460px;
overflow-y:auto;
}
#act{
width:30%;
float:left;
margin-left:30px;
height:460px;
overflow-y:auto;
}
#log{
float:left;
width:25%;
margin-left:30px;
}
#tit{
font-family:verdana;
font-size:18px;
font-weight:bold;
color:#FFFFFF;
font-weight:bold;
}
hr{
color:#fff;
background-color:#fff;
height:1px;
}
#head{
font-family:verdana;
font-weight:bold;
font-size:25px;
color:#FFFFFF;
}
li {list-style-type: none;}
img{
width:80px;
height:80px;
border-radius:8px;
box-shadow:1px 1px 2px 1px #000;
}
#lnk{
font-family:verdana;
font-size:16px;
color:#FFFFFF;
}
#naam{
font-family:verdana;
font-size:14px;
color:#FFFFFF;
width:90px;
word-wrap:break-word;
float:left;
margin-left:7px;
}
#naam1{
width:300px;
height:35px;
overflow:hidden;
}
</style>
<script type="text/javascript">
function load1(){
	window.open("https://mindapp-pulpy.rhcloud.com/index.jsp", '_blank');
}
function load2(){
	window.open("https://mindapp-pulpy.rhcloud.com/fgtpass.jsp", '_blank');
}
</script>
</head>
<body>
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<%
ResultSet rs2=null; ResultSet rs3=null;%>
<%
String appid="";String title="";String mode="";String title1="";String appid1="";String mode1="";String app="";String app1="";
String id="";String id1="";
%>
<a href="http://minddotss.com"><img style="margin-left:100px;box-shadow:0px 0px 0px 0px;width:150px;height:70px;" alt="MindDots" src="images/log.png"></a><center><div id="head">Mind-Pulpy Cloud Connectors</div></center><br><hr>
<br>
<form action="Login" method="post">
<div id=tri>
<center><div id=tit>Trigger Pulpy</div></center>
<ul><br>
  	<%
	  	try{
	  		int i=1;int k=0;int a,b,c;
	  	    PreparedStatement ps = conn.prepareStatement("select * from title where mode='Trigger'");
	  	    ResultSet rs =ps.executeQuery() ;
	  	    PreparedStatement ps1 = conn.prepareStatement("select * from title where mode='Trigger'");
	  	    ResultSet rs1 =ps1.executeQuery() ;
		  	while(rs.next()){
		  		i++;	  
		    }
		  	String [] data=new String[i];
		  	String [] nam=new String[i];
	  	    while(rs1.next()){
	  	        appid=rs1.getString("appid");
	  	    	String name=rs1.getString("tit");
	  	    	data[k]=appid;
	  	    	nam[k]=name;
	  	    	k++;
	  	    }
  	    	for(a=0,b=1,c=2;c<k;a=a+3,b=b+3,c=c+3){
  	    		%>
  	    		<li><div id="naam1"><div id="naam"><%=nam[a] %></div><div id="naam"><%=nam[b] %></div><div id="naam"><%=nam[c] %></div></div></li>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;<img name=nn  src="Title?appid=<%=data[a]%>">&nbsp;&nbsp;&nbsp;<img  src="Title?appid=<%=data[b]%>">&nbsp;&nbsp;&nbsp;<img  src='Title?appid=<%=data[c]%>'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	} 
  	    	if((b+2)==i){
  	    		%>
  	    		<li><div id="naam1"><div id="naam"><%=nam[a] %></div><div id="naam"><%=nam[b] %></div></div></li>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;<img name=nn  src="Title?appid=<%=data[a]%>">&nbsp;&nbsp;&nbsp;<img  src="Title?appid=<%=data[b]%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}else if((a+2)==i){
  	    		%>
  	    		<li><div id="naam1"><div id="naam"><%=nam[a] %></div></div></li>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;<img name=nn  src="Title?appid=<%=data[a]%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}
	  	}catch(Exception e){
	  		out.println(e);
	  	}	 
  	%>
  </ul>
</div><div id=act>
<center><div id=tit>Action Pulpy</div></center>
<ul><br>
  	<%  String chk=request.getParameter("app");
	  	try{
	  		int i=1;int k=0;int a,b,c;
	  	    PreparedStatement ps = conn.prepareStatement("select * from title where mode='Action'");
	  	    ResultSet rs =ps.executeQuery() ;
	  	    PreparedStatement ps1 = conn.prepareStatement("select * from title where mode='Action'");
	  	    ResultSet rs1 =ps1.executeQuery() ;
		  	while(rs.next()){
		  		i++;	  
		    }
		  	String [] data=new String[i];
		  	String [] nam=new String[i];
	  	    while(rs1.next()){
	  	        appid=rs1.getString("appid");
	  	    	String name=rs1.getString("tit");
	  	    	data[k]=appid;
	  	    	nam[k]=name;
	  	    	k++;
	  	    }
  	    	for(a=0,b=1,c=2;c<k;a=a+3,b=b+3,c=c+3){
  	    		%>
  	    		<li><div id="naam1"><div id="naam"><%=nam[a] %></div><div id="naam"><%=nam[b] %></div><div id="naam"><%=nam[c] %></div></div></li>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;<img name=nn  src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<img  src="Title?appid=<%=data[b]%>">&nbsp;&nbsp;&nbsp;<img  src='Title?appid=<%=data[c]%>'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}
  	    	if((b+2)==i){
  	    		%>
  	    		<li><div id="naam1"><div id="naam"><%=nam[a] %></div><div id="naam"><%=nam[b] %></div></div></li>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;<img name=nn  src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<img  src="Title?appid=<%=data[b]%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}else if((a+2)==i){
  	    		%>
  	    		<li><div id="naam1"><div id="naam"><%=nam[a] %></div><div id="naam"><%=nam[b] %></div></div></li>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;<img name=nn  src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}
	  	}catch(Exception e){
	  		out.println(e);
	  	}	 
  	%>
  </ul>
</div><div id=log><center>
<div id=tit>Login Here</div><br>
<div class="result">${alert}</div><br>
<input type="text" placeholder="UserName" name="user" required><br><br>
<input type="password" placeholder="Password" name="pass" required><br><br>
<input type=submit value="login"><br><br>
<a id=lnk href="javascript:load1()">Mindpulpy Signup</a><br><br><a id=lnk href="javascript:load2()">Forgot Password?</a>
</center>
</div>
</form>
</body>
</html>