<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
input[type="text"]{
color:#FF9900;
font-size:20px;
background-color:#FFFFFF;
font-family:verdana;
width:300px;
height:25px;
padding:10px;
}
input[type="button"],input[type="submit"]{
color:#FFFFFF;
font-size:15px;
background-color:#FF9900;
font-family:verdana;
width:150px;
height:50px;
border:solid 2px;
border-color:#FFFFFF;
border-radius:20px;
padding:10px;
}
input[type="password"]{
color:#FF9900;
font-size:20px;
background-color:#FFFFFF;
font-family:verdana;
width:300px;
height:25px;
padding:10px;
}  

</style>
</head>
<body>
<center><h2>Mind Connectors</h2></center><br><br>
<form action="Login" method="post">
<center>
<input type="text" placeholder="UserName" name="user"><br><br>
<input type="password" placeholder="Password" name="pass"><br><br>
<input type=submit value="login">
</center>
</form>
</body>
</html>