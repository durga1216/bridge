<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
hr{
color:#fff;
background-color:#fff
}
</style>
</head>
<body><br>
<center><h1>Mind Connectors</h1></center>
<br><br><br><br>
<form action="Login" method="post">
<center>
<div class="result">${alert}</div><br>
<input type="text" placeholder="UserName" name="user"><br><br>
<input type="password" placeholder="Password" name="pass"><br><br>
<input type=submit value="login">
</center>
</form>
</body>
</html>