<!doctype html>
<html lang="en">
<head>
<title>try</title>
<meta name="viewport" content="initial-scale=1.0, width=device-width" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<style>
body{
background-color:#ff9900;
}
input[type="text"]{
width:100%;}
li {list-style-type: none;}
.dropdown-menu{
width:320px;
height:300px;
overflow:auto;
background-color:#ff9900;
}
#tri{
width:350px;
height:320px;
overflow-y:auto;
}
input[type='text']{
width:100%;
}
.container{
float:left;
margin-left:200px;
}
#tst{
width:300px;
color:#ff9900;
background-color:#fff;
}
img{
width:80px;
height:80px;
border-radius:8px;
box-shadow:1px 1px 2px 1px #000;
}
</style>
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
<div id=tri>
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
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp?app=<%=data[a] %>"><img name=nn  src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<a href="index.jsp?app=<%=data[b] %>"><img  src="Title?appid=<%=data[b]%>"></a>&nbsp;&nbsp;&nbsp;<a href="index.jsp?app=<%=data[c] %>"><img  src='Title?appid=<%=data[c]%>'></a>&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	} 
  	    	if((b+2)==i){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp?app=<%=data[a] %>"><img name=nn  src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<a href="index.jsp?app=<%=data[b] %>"><img  src="Title?appid=<%=data[b]%>"></a>&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}else if((a+2)==i){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp?app=<%=data[a] %>"><img name=nn  src="Title?appid=<%=data[a]%>"></a></div><br></li>
	  	    	<%
  	    	}
	  	}catch(Exception e){
	  		out.println(e);
	  	}	 
  	%>
  </ul>
  </div>
</body>
</html>