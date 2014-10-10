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
.dropdown-menu{
width:300px;
background-color:#ff9900;
}
#tst{
width:300px;
color:#ff9900;
background-color:#fff;
}
img{
width:80px;
height:80px;
border-radius:20px;
}
</style>
</head>
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<body>
<br><br><br><br>
<div class="container">
	<div class="btn-group">
	<button type="button" id="tst" class="btn btn-danger">Choose any item from the list</button>
  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu">
  	<%
	  	try{
	  		int i=1;int k=0;
	  	    PreparedStatement ps = conn.prepareStatement("select * from title");
	  	    ResultSet rs =ps.executeQuery() ;
	  	    PreparedStatement ps1 = conn.prepareStatement("select * from title");
	  	    ResultSet rs1 =ps1.executeQuery() ;
		  	while(rs.next()){
		  		i++;	  
		    }out.println(i);
		  	String [] data=new String[i];
	  	    while(rs1.next()){
	  	    	String appid=rs1.getString("appid");
	  	    	data[k]=appid;
	  	    	k++;
	  	    }
  	    	for(int a=0,b=1,c=2;c>i;a=a+3,b=b+3,c=c+3){
  	    		%>
	  	    	<li><div class="col-md-4"><a href="#"><img src="Title?appid=<%=data[a]%>"></a><a href="#"><img src="Title?appid=<%=data[b]%>"></a><a href="#"><img src='Title?appid=<%=data[c]%>'></a></div></li>
	  	    	<%
  	    	} 
	  	}catch(Exception e){
	  		out.println(e);
	  	}	 
  	%>
    <!-- 
    <li><div class="col-md-4"><a href="#"><img src="images/p.jpg"></a><a href="#"><img src="images/p.jpg"></a><a href="#"><img src='images/p.jpg'></a></div></li>
    <li><div class="col-md-4"><a href="#"><img src="images/p.jpg" ></a><a href="#"><img src="images/p.jpg"></a><a href="#"><img src="images/p.jpg" ></a></div></li>
    <li><div class="col-md-3"><a href="#"><img src="images/p.jpg" ></a><a href="#"><img src="images/p.jpg" ></a><a href="#"><img src="images/p.jpg"></a></div></li>
	-->  
  </ul>
</div>
</div>
</body>
</html>