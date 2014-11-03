<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript"></script>
<style>
body{
background-color:#FF9900;
}
#head{
font-family:verdana;
font-weight:bold;
font-size:25px;
color:#FFFFFF;
}
#tit{
font-family:verdana;
font-size:15px;
margin-left:100px;
color:#FFFFFF;
font-weight:bold;
}
#desc{font-family:verdana;
font-size:15px;
margin-left:100px;
color:#FFFFFF;
font-weight:bold;}
#txt1{
font-family:verdana;
font-size:15px;
margin-left:100px;
color:#FF9900;
font-weight:bold;
width:400px;
height:150px;

}
input[type='text']{
width:400px;
height:30px;
margin-left:100px;
font-family:verdana; 
font-weight:bold;
font-size:15px;
color:#FF9900;
}
input[type='file']{
font-family:verdana; 
font-weight:bold;
font-size:15px;
color:#FFFFFF;
margin-left:100px;

}
input[type="submit"]{

color:#FFFFFF;
font-size:20px;
background-color:#FF9900;
margin-left:380px;
font-family:verdana;
width:140px;
height:50px;
border:solid 2px;
border-color:#FFFFFF;
border-radius:50px;
padding:10px;
}
#tit1{
font-family:verdana;
font-size:20px;
font-weight:bold;
margin-left:70px;
color:#FFFFFF;
font-weight:bold;
}
input[type="radio"]
 {
   width:20px; 
   height: 20px;
   color:#FFFFFF;
   font-size:20px;
   font-family:verdana;
   margin-left:100px;
 }
 label{
font-family:verdana;
font-size: 16px;
color:#FFFFFF;
margin-left:26px;
}
</style>
</head>
<body>
<form action="Title" method="post" enctype="multipart/form-data">
<br><br><center><div id="head">Mind-Bridge</div></center>
<br><br><br><div id="tit1">1.Trigger App Details</div>
<br><br><br><div id="tit">Title*</div><br><input type="text" name="app1" id="app1" placeholder="Enter the App title">
<br><br><br><div id="desc">Description*</div><br><textarea name="descr" id="txt1"  placeholder="Min 3 sentence of description"></textarea>
<br><br><br><div id="tit">Logo</div><br><input type="file"  name="selectFile">
<input type="radio" name="mode"  value="Trigger">
<label for="rd1">Trigger</label>
<input type="radio" name="mode"   value="Action">
<label for="rd1">Action</label>
<br><br><br><input type="submit" name="submit" value="Continue">
</form>
</body>
</html>