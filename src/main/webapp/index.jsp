<!doctype html>
<html lang="en">
<head>
<meta name="viewport" content="initial-scale=1.0, width=device-width" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Mind Connectors</title>
	<%
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Cache-Control", "no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
    %>
<link rel="shortcut icon" href="favicon.ico" />
<style>
body{
background-color:#FF9900;
overflow: hidden;
}
#head{
font-family:verdana;
font-weight:bold;
font-size:25px;
color:#FFFFFF;
}
.dropdown-menu{
width:320px;
height:250px;
overflow:auto;
background-color:#ff9900;
}
.container{
float:left;
margin-left:240px;
}
#tst{
width:320px;
font-family:verdana;
font-size:15px;
color:#FF9900;
font-weight:bold;
background-color:#fff;
}
img{
width:80px;
height:80px;
border-radius:8px;
box-shadow:1px 1px 2px 1px #000;
}
#tit{
font-family:verdana;
font-size:20px;
font-weight:bold;
margin-left:70px;
color:#FFFFFF;
font-weight:bold;
}
input[type="button"],input[type="submit"]{
padding:7px;
border:solid 1px #ffffff;
border-radius:5px;
background-color:#ffffff;
font-family:verdana;
font-size:17px;
color:#ff9900;
font-weight:bold;
margin-left:500px;
}
#ifm{
background-color:#ff9900;
}
select{
font-family:verdana;
font-size:17px;
color:#FF9900;
font-weight:bold;
width:350px;
height:70px;
background-color:#fff;
border-radius:5px;
}
#actmeth option{
     font-family:verdana;
font-size:18px;
color:#FF9900;
font-weight:bold;
   }
hr{
color:#fff;
background-color:#fff;
height:1px;
}
#ins{
font-family:verdana;
font-size:15px;
color:#FFFFFF;
margin-left:250px;
}


</style>

<script type="text/javascript">
var app;
$(document).ready(function(){

	
	$('#select1').change(function(){
	    
	});
	
	$('#tgmeth').change(function(){
		$('#select2').attr('disabled',false);
		/*$('#con_trig').show();
		$('#con_trig').attr('disabled',false);
		/*$('#con_act').show();
		$('#con_act').attr('disabled',true);
		$('#select2').attr('disabled',true);	
		$('#actmeth').attr('disabled',true);	*/

	});
	$('#select2').change(function(){
		 app=$("#select1 option:selected").val();
		 var app1=$("#select2 option:selected").val();
		 
		 window.location.replace("index.jsp?app="+app+"&app1="+app1);

		
	});	
	
	$('#tgmeth').change(function(){
		$('#actmeth').attr('disabled',false);

		
	});

	$('#actmeth').change(function(){
		$('#con_trig').show();
		$('#con_trig').attr('disabled',false);
		$('#con_act').show();


	});
	
	$('#con_trig').click(function(){
	});
});
function load(){
	window.open("https://docs.google.com/forms/d/1mULCyQRpHS3IBRaoKOuKg8cGrR6o8i0QyfcW4bXfeO0/viewform?embedded=true",'_blank');
}
function load1(){
	window.location="final.jsp";
}
</script>
</head>
<body>
<%
	String u = (String) request.getSession().getAttribute("id");
    if (u != null ) {
    }else{
    	response.sendRedirect("logout.jsp");
    }
%>
<form action="MethodStorage" method="get">
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<%
ResultSet rs2=null; ResultSet rs3=null;%>
<%
    try{
       String appid="";String title="";String mode="";String title1="";String appid1="";String mode1="";String app="";String app1="";
String id="";String id1="";
%>
<br>
<a href="http://minddotss.com"><img style="margin-left:100px;box-shadow:0px 0px 0px 0px;width:150px;height:70px;" src="images/log.png"></a>
<input type="button" value="Your Account" Onclick="javascript:load1()" style="margin-right:100px;float:right;"><div id="head"><center>Mind-Pulpy Cloud Connectors</center></div><br><hr>
<br><div id='tit'>1.Choose trigger and action &nbsp;&nbsp;&nbsp; (OR) &nbsp;&nbsp;&nbsp; <a style="color:#fff;" href="javascript:load()">Click here to Submit New Cloud Connect Request</a></div>
<br><br>
<div id="ins">a.Choose a trigger -> b. Choose an action -> c.Choose Trigger Method -> d.Choose Action Method</div><br>
<div class="container">
	<br><div class="btn-group">
	<button type="button" id="tst" class="btn btn-danger">Choose any Trigger from the list</button>
  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu"><br>
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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="btn-group">
	<button type="button" id="tst" class="btn btn-danger">Choose any Action from the list</button>
  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu"><br>
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
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp?app1=<%=data[a]%>&app=<%=chk%>"><img name=nn  src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<a href="index.jsp?app1=<%=data[b]%>&app=<%=chk%>"><img  src="Title?appid=<%=data[b]%>"></a>&nbsp;&nbsp;&nbsp;<a href="index.jsp?app1=<%=data[c]%>&app=<%=chk%>"><img  src='Title?appid=<%=data[c]%>'></a>&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}
  	    	if((b+2)==i){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp?app1=<%=data[a]%>&app=<%=chk%>"><img name=nn  src="Title?appid=<%=data[a]%>"></a>&nbsp;&nbsp;&nbsp;<a href="index.jsp?app1=<%=data[b]%>&app=<%=chk%>"><img  src="Title?appid=<%=data[b]%>"></a>&nbsp;&nbsp;&nbsp;</div><br></li>
	  	    	<%
  	    	}else if((a+2)==i){
  	    		%>
	  	    	<li><div class="row-md-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp?app1=<%=data[a]%>&app=<%=chk%>"><img name=nn  src="Title?appid=<%=data[a]%>"></a></div><br></li>
	  	    	<%
  	    	}
	  	}catch(Exception e){
	  		out.println(e);
	  	}	 
  	%>
  </ul>
</div>
</div>
<br>
<br><br>     

  <% 
       app=request.getParameter("app");
       PreparedStatement st=conn.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
       st.setString(1,app);
       rs2=st.executeQuery();
       app1=request.getParameter("app1");
       PreparedStatement st1=conn.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
       st1.setString(1,app1);
       rs3=st1.executeQuery();
       String t1="";String t2="";String t3="";String t4="";String t5="";String mode2="";String act_t1="";String mode3="";String accname="";String dis="";String dis1="";
 String act_t2="";String act_t3="";String act_t4="";String act_t5=""; %>    
<br><select name="tgmeth" id="tgmeth" onchange="change()" style="margin-left:250px;height:40px;" >
    <option>&nbsp;&nbsp;&nbsp;&nbsp;Choose Any Trigger Method from the list</option>
    <option disabled="disabled" ></option>
    <% while(rs2.next()){
        id=rs2.getString("appid");
        dis=rs2.getString("tit");
    	t1=rs2.getString("name");
    	t2=rs2.getString("name2");
    	t3=rs2.getString("name3");
    	t4=rs2.getString("name4");
    	t5=rs2.getString("name5");
        mode2=rs2.getString("mode");
        if("Trigger".equals(mode2)){ %>
      <option value="<%=t1 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=t1%></option>
      <option value="<%=t2 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=t2%></option>
      <option value="<%=t3 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=t3%></option>
      <option value="<%=t4 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=t4%></option>
      <option value="<%=t5 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=t5%></option>
   
   <option disabled="disabled" ></option>
    	        <%}} %></select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <select name="actmeth" id="actmeth" onchange="change()" style="height:40px;" >
    <option>&nbsp;&nbsp;&nbsp;&nbsp;Choose any Action Method from the list</option>
    <option disabled="disabled" ></option>
    <% while(rs3.next()){
    	id1=rs3.getString("appid");
    	dis1=rs3.getString("tit");
    	act_t1=rs3.getString("name");
    	act_t2=rs3.getString("name2");
    	act_t3=rs3.getString("name3");
    	act_t4=rs3.getString("name4");
    	act_t5=rs3.getString("name5");

    	mode3=rs3.getString("mode");
    	 if("Action".equals(mode3)){%>
   <option value="<%=act_t1 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=act_t1%></option>
      <option value="<%=act_t2 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=act_t2%></option>
      <option value="<%=act_t3 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=act_t3%></option>
      <option value="<%=act_t4 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=act_t4%></option>
      <option value="<%=act_t5 %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=act_t5%></option>
   
   <option disabled="disabled" ></option>
    	        <%}}%></select><input type="text" name="app1" value="<%=app1%>" style="display:none"><input type="text" name="app" value="<%=app %>" style="display:none">      <br><br>  <br><br>
    	  
    	        
  <br><br><input type="submit" name="con_trig" id="con_trig" value="Continue" style="display:none"><br><br>	
  
   <% 
       
    }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
   %>

</body>
</html>