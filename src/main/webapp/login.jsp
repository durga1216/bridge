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
font-size:20px;
background-color:#FFFFFF;
font-family:verdana;
width:300px;
height:25px;
padding:10px;
border-radius:5px;
}
input[type="button"],input[type="submit"]{
color:#FFF;
font-size:20px;
background-color:#00f;
font-family:verdana;
width:320px;
height:50px;
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
width:300px;
height:25px;
padding:10px;
border-radius:5px;
}  
.result{
color:#FFF;
font-size:18px;
font-family:verdana;
}
#tit{
font-family:verdana;
font-size:20px;
font-weight:bold;
margin-left:70px;
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
#lnk{
font-family:verdana;
font-size:18px;
color:#FFFFFF;
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
<body><br>
<a href="http://minddotss.com"><img style="margin-left:100px;box-shadow:0px 0px 0px 0px;width:150px;height:70px;" alt="MindDots" src="images/log.png"></a><center><div id="head">Mind-Pulpy Cloud Connectors</div></center><br><hr>
<br><br><div id=tit>Login with MindPulpy Credentials:</div><br><br>
<br>
<form action="Login" method="post">
<center>
<div class="result">${alert}</div><br>
<input type="text" placeholder="UserName" name="user" required><br><br>
<input type="password" placeholder="Password" name="pass" required><br><br>
<input type=submit value="login"><br><br>
<a id=lnk href="javascript:load1()">Mindpulpy Signup</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id=lnk href="javascript:load2()">Forgot Password?</a>
</center>
</form>
</body>
</html>