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
width:320px;
height:300px;
overflow:auto;
background-color:#ff9900;
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
<script>

  $(function() {
    $('[data-toggle="tooltip"]').tooltip();
    });

        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments);},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-49334062-1', 'humanapi.co');
  ga('send', 'pageview');

</script>
</head>
<body>
<form action="fff" method="get">
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<br><br><br><br>
<div class="container">
	<div class="btn-group">
	<button type="button" id="tst" class="btn btn-danger">Choose any Trigger from the list</button>
  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu">
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
	  	    	String appid=rs1.getString("appid");
	  	    	String name=rs1.getString("tit");
	  	    	data[k]=appid;
	  	    	nam[k]=name;
	  	    	k++;
	  	    }
  	    	for(a=0,b=1,c=2;c<i;a=a+3,b=b+3,c=c+3){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img name=nn data-original-title="<%=nam[a] %>" src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<a href="#"><img  src="Title?appid=<%=data[b]%>"></a>&nbsp;&nbsp;&nbsp;<a href="#"><img  src='Title?appid=<%=data[c]%>'></a>&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	} 
  	    	if((b+1)==i){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img name=nn data-original-title="<%=nam[a] %>" src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<a href="#"><img  src="Title?appid=<%=data[b]%>"></a>&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}else if((a+1)==i){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img name=nn data-original-title="<%=nam[a] %>" src="Title?appid=<%=data[a]%>"></div><br></li>
	  	    	<%
  	    	}
	  	}catch(Exception e){
	  		out.println(e);
	  	}	 
  	%>
  </ul>
</div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="btn-group">
	<button type="button" id="tst" class="btn btn-danger">Choose any Action from the list</button>
  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu">
  	<%
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
	  	    	String appid=rs1.getString("appid");
	  	    	String name=rs1.getString("tit");
	  	    	data[k]=appid;
	  	    	nam[k]=name;
	  	    	k++;
	  	    }
  	    	for(a=0,b=1,c=2;c<i;a=a+3,b=b+3,c=c+3){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img name=nn data-original-title="<%=nam[a] %>" src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<a href="#"><img  src="Title?appid=<%=data[b]%>"></a>&nbsp;&nbsp;&nbsp;<a href="#"><img  src='Title?appid=<%=data[c]%>'></a>&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}
  	    	if((b+1)==i){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img name=nn data-original-title="<%=nam[a] %>" src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<a href="#"><img  src="Title?appid=<%=data[b]%>"></a>&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}else if((a+1)==i){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img name=nn data-original-title="<%=nam[a] %>" src="Title?appid=<%=data[a]%>"></div><br></li>
	  	    	<%
  	    	}
	  	}catch(Exception e){
	  		out.println(e);
	  	}	 
  	%>
  </ul>
</div>
</div>
<br><br>
</form>
</body>
</html>