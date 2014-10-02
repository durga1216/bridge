<%@ page language="java"  charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
	<script src="js/jquery-latest.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
	  $('#b1').click(function(){
		  location.href="https://mind-4.myshopify.com/admin/oauth/authorize?redirect_uri=https://mind-pulpy.rhcloud.com/CallBack&response_type=code&client_id=67fa2a7f4d0c6cf62a3bafd4da145981&scope=write_products,write_orders";
	  });
	  
	  $('#b2').click(function(){
		  location.href="https://graph.facebook.com/oauth/authorize?client_id=1444773389075351&display=page&redirect_uri=http://mind-pulpy.rhcloud.com/FaceCallBack&response_type=code&scope=publish_actions,manage_pages,status_update";
	  });
	  
  });
 </script>
 
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
#trig{
margin-left:350px;
font-family:verdana;
font-size:20px;
color:#FFFFFF;
}

#act{
margin-left:1000px;
font-family:verdana;
font-size:20px;
color:#FFFFFF;
}

select{
font-family:verdana;
font-size: 18px;
font-weight:bold;
color:#FF9900;
width:25%;
height:15%;
background-color:#FFFFFF;
padding:15px;
}

#check{
font-family:verdana;
font-size:25px;
color:#FFFFFF;
margin-left:450px;
}

#check1{font-family:verdana;
font-size:25px;
color:#FFFFFF;
margin-left:450px;}

input[type="button"]{
color:#FFFFFF;
font-size:20px;
background-color:#FF9900;
margin-left:470px;
font-family:verdana;
width:320px;
height:50px;
border:solid 2px;
border-color:#FFFFFF;
border-radius:50px;
padding:10px;
}

#submit{
color:#FFFFFF;
font-size:20px;
background-color:#FF9900;
font-family:verdana;
margin-left:440px;
width:420px;
height:50px;
border:solid 2px;
border-color:#FFFFFF;
border-radius:50px;
padding:10px;
}


#work{
margin-left:800px;
font-size:15px;
font-family:verdana;
color:#FFFFFF;
}

</style>
</head>
<body>
<form action="Test" method="post">
<br><br><div class="head">Mind-Bridge</div>
<br><br><br><div id="trig">Trigger App &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Action App</div>

<br><br><select name="select1" id="select1" onchange="change()" style="margin-left:250px;" >
   <option value="Shopify">Shopify</option></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<select name="select2" id="select2" onchange="change()" >
   <option value="Facebook">Facebook</option>
   </select><br><br>
<select name="select3" id="select3" value="Select an Action" onchange="change3()" style="margin-left:250px;">
   <option value="Create a new product">Create a new product</option>
</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<select name="select4" id="select4" onchange="change4()">
   <option value="Write Post on Facebook">Write post on facebook</option>
</select>
<br><br><br><br><br><div id="check"> Select a &nbsp;&nbsp;<img src="shopify.jpg" width="50" height="50">&nbsp;&nbsp; Shopify Account</div><br><br>
<input type="button" name="b1" id="b1" value="Connect to Shopify Account">
<div id="work">
<%String p1=request.getParameter("p1");
if(p1.equals("true")){
	out.println("<img src='tick.jpg' height='20' width='20'>&nbsp;&nbsp;Account is Working");
}
%>
</div>
<br><br><br><br><div id="check1"> Select a &nbsp;&nbsp;<img src="face.jpg" width="50" height="50">&nbsp;&nbsp; Facebook Account</div><br><br>
<input type="button" name="b2" id="b2" value="Connect to Facebook Account">
<div id="work">
<%String p2=request.getParameter("p2");
if(p2.equals("true")){
	out.println("<img src='tick.jpg' height='20' width='20'>&nbsp;&nbsp;Account is Working");
}
%></div>

<br><br><br><br><input type="submit" value="Test Trigger and Action App here" id="submit"></form>
</body>
</html>