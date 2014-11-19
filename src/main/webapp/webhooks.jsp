<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="js/jquery-latest.js"></script>
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
</head>
<style type="text/css">
body{
background-color:#FF9900;
}
#head{
font-family:verdana;
font-weight:bold;
font-size:25px;
color:#FFFFFF;
}
#txar{
font-family:verdana;
font-size:20px;
color:#FF9900;
width:500px;
height:200px;
}
input[type="text"]{
width:200px;
height:25px;
font-family:verdana; 
font-size:19px;
color:#FF9900;
border-radius:5px;
}
#lft1{
float:left;
margin-left:100px;
width:40%;
}
#res{
margin-left:100px;
width:40%;
float:left;
}
select{
width:200px;
height:30px;
background-color:#fff;
font-family:verdana; 
font-size:19px;
color:#FF9900;
border-radius:5px;
}
#msg{
font-family:verdana;
  font-size:18px;
  color:#FFFFFF;  
  margin-left:150px;
}
#pa{
  font-family:verdana;
  font-size:18px;
  color:#FFFFFF;  
}
h3{
font-family:verdana;
font-weight:bold;
color:#fff;
}
#full{
width:100%;
height:300px;
}
input[type="button"],input[type="submit"]{
padding:7px;
border:solid 1px #fff;
border-radius:5px;
background-color:#fff;
font-family:verdana;
font-size:17px;
color:#ff9900;
font-weight:bold;
}
</style>
<body>
<center><div id=head>Mind-Connectors</div><br><br><hr><br></center>
<form action="finish.jsp">
<div id=msg>
**Alert:
<ul><li>Did you enter the MindPulpy Webhook Url into Your account..??</li>
<li>Did you make your Webhook Response as Xml or Json..??</li>
<li>Did you do the specified action in you account..??</li></ul>
Then view and choose your required fields.. Or Do the above steps in your account and refresh this page.
</div><br>
<div id=full>
<div id=lft1>
<h3>Webhook response:</h3>
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<%
    String respo="";char chfirst='a';
	try{
		PreparedStatement ps=conn.prepareStatement("select * from hook order by count desc limit 1");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			respo=rs.getString("str");
			chfirst=respo.charAt(0);
			%>
			<textarea id=txar><%=respo%></textarea>
			<%
		}
	}
	catch(Exception e){
		
	}
%>
</div>
<br>
<div id=res>
	<h3>Parsing:</h3>
	<input type="text" style="display:none;" id="ptag" name="ptag" placeholder=" parant Tag"><br>
	<a id='pa' href="javascript:addParam()">Add_Tag</a>&nbsp;
    <a id='pa' href="javascript:removeParam()">Remove_Tags</a><br>
	<div id="content"></div>
</div></div>
<br><br><br><br>
<center><input type="submit" value=Continue></center>
</form>
</body>
<script type="text/javascript" src="js/keys.js"></script>
<script type="text/javascript">
var num=0;
var respo2='<%=respo%>';
var chfst='<%=chfirst%>';
</script>
</html>