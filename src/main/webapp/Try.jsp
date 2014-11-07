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
</head>
<body>
<%for(int i=0;i<3;i++){ %>
<form action="Title" method="get">
<input type="text" name=t<%=i%>>
<input style="display:none;" type="text" value="autive" name=test>
<input type="Submit" name=s<%=i%>> 
</form>
<%}%>
</body>
</html>