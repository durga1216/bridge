<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/jquery-latest.js"></script>
<title>Insert title here</title>
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
font-size:20px;
font-weight:bold;
margin-left:100px;
color:#FFFFFF;
font-weight:bold;
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
input[type="suit"]{

color:#FFFFFF;
font-size:15px;
background-color:#FF9900;
margin-left:300px;
font-family:verdana;
width:300px;
height:50px;
border:solid 2px;
border-color:#FFFFFF;
border-radius:50px;
padding:10px;
}
select{
font-family:verdana;
font-size:17px;
color:#FF9900;
background-color:#fff;
font-weight:bold;
width:350px;
height:40px;
}
input[type="text"]{
font-family:verdana;
font-size:17px;
color:#FF9900;
font-weight:bold;
width:400px;
height:30px;
}
#ins{
font-family:verdana;
font-size:15px;
color:#FFFFFF;
margin-left:250px;
}
#blanket {
   background-color:#000;
   opacity: 0.65;
   *background:none;
   position:absolute;
   z-index: 9001;
   top:0px;
   left:0px;
   width:100%;
}

#popUpDiv {
	position:absolute;
	background-color:#ff9900;
	width:500px;
	height:500px;
	border:3px solid #ff9900;
	z-index: 9002;
	box-shadow:2px 2px 5px 1px #000;

}

#popUpDiv { a position:relative; top:20px; left:20px}
#popUpAct {
	position:absolute;
	background-color:#ff9900;
	width:500px;
	height:500px;
	border:3px solid #ff9900;
	z-index: 9002;
	box-shadow:2px 2px 5px 1px #000;

}

#popUpAct { a position:relative; top:20px; left:20px}
#inpop{
margin:50px;
font-family:verdana;
font-size:15px;
color:#FFFFFF;

}
</style>
<script type="text/javascript">
function toggle(div_id) {
	var el = document.getElementById(div_id);
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
	else {el.style.display = 'none';}
}
function sub(dis) {
	var el = document.getElementById('blanket');
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
	else {el.style.display = 'none';}
	var el = document.getElementById('popUpDiv');
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
	else {el.style.display = 'none';}
	var el = document.getElementById(dis);
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
	var el= document.getElementById('tit2');
	el.style.display='block';
	var el= document.getElementById('con_act');
	el.style.display='block';
}
function sub1(dis1) {
	var el = document.getElementById('blanket');
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
	else {el.style.display = 'none';}
	var el = document.getElementById('popUpAct');
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
	else {el.style.display = 'none';}
	var el = document.getElementById(dis1);
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
}
function blanket_size(popUpDivVar) {
	if (typeof window.innerWidth != 'undefined') {
		viewportheight = window.innerHeight;
	} else {
		viewportheight = document.documentElement.clientHeight;
	}
	if ((viewportheight > document.body.parentNode.scrollHeight) && (viewportheight > document.body.parentNode.clientHeight)) {
		blanket_height = viewportheight;
	} else {
		if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
			blanket_height = document.body.parentNode.clientHeight;
		} else {
			blanket_height = document.body.parentNode.scrollHeight;
		}
	}
	var blanket = document.getElementById('blanket');
	blanket.style.height = blanket_height + 'px';
	var popUpDiv = document.getElementById(popUpDivVar);
	popUpDiv_height=blanket_height/2-200;//200 is half popup's height
	popUpDiv.style.top = popUpDiv_height + 'px';
}
function window_pos(popUpDivVar) {
	if (typeof window.innerWidth != 'undefined') {
		viewportwidth = window.innerHeight;
	} else {
		viewportwidth = document.documentElement.clientHeight;
	}
	if ((viewportwidth > document.body.parentNode.scrollWidth) && (viewportwidth > document.body.parentNode.clientWidth)) {
		window_width = viewportwidth;
	} else {
		if (document.body.parentNode.clientWidth > document.body.parentNode.scrollWidth) {
			window_width = document.body.parentNode.clientWidth;
		} else {
			window_width = document.body.parentNode.scrollWidth;
		}
	}
	var popUpDiv = document.getElementById(popUpDivVar);
	window_width=window_width/2-200;//200 is half popup's width
	popUpDiv.style.left = window_width + 'px';
}
function popup(windowname) {
	blanket_size(windowname);
        window_pos(windowname);
	toggle('blanket');
	toggle(windowname);		
}
</script>
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
		 window.location.replace("Home.jsp?app="+app+"&app1="+app1);

		
	});	
	
	$('#tgmeth').change(function(){
		$('#actmeth').attr('disabled',false);

		
	});

	$('#actmeth').change(function(){
		$('#con_trig').show();
		$('#con_trig').attr('disabled',false);
		$('#tit1').show();


	});
	
	$('#con_trig').click(function(){
	});
});
</script>
</head>
<body onload="load()">
<form action="TriggerAction" method="get">
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;ResultSet rs=null;
ResultSet rs2=null; ResultSet rs3=null;%>
<%
    try{
  Class.forName("com.mysql.jdbc.Driver").newInstance();
 // Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/bridge","root","root");
 Connection conn=DriverManager.getConnection("jdbc:mysql://127.8.57.130:3306/bridge", "adminBK7JQt3", "_81yufNHCMzH");
       PreparedStatement ps = conn.prepareStatement("select * from title");
       resultset =ps.executeQuery() ;
       PreparedStatement ps1 = conn.prepareStatement("select * from title");
       rs =ps1.executeQuery() ;
       String appid="";String title="";String mode="";String title1="";String appid1="";String mode1="";String app="";String app1="";
String id="";String id1="";
%>
<div id="blanket" style="display:none;"></div>
	<div id="popUpDiv" style="display:none;">
	<a style='font-size:20px;color:#000;float:right;'href="" onclick="popup('popUpDiv')" ><img style='height:20px;width:20px'alt="close" src="images/close.png"></a><br><br>
	<div id=inpop><h2>Enter the Authentication details:</h2><br><br>*ApiKey(required):<br><br>Enter the Apikey for the eventfull app Or get new Apikey from eventfull..<br><br><input type="text" name="apit1"placeholder=" *ApiKey"><br><br><br><input type="button" id="inp" value="Continue" onclick="javascript:sub('dis')"></div>
	</div>
	<div id="popUpAct" style="display:none;">
	<a style='font-size:20px;color:#000;float:right;'href="" onclick="popup('popUpAct')" ><img style='height:20px;width:20px'alt="close" src="images/close.png"></a><br><br>
	<div id=inpop><h2>Enter the Authentication details:</h2><br><br>*Basic Authentication(required):<br><br>1,Enter the Domain Name Or you can create an account in firebase..<br><br>https:// &nbsp;<input type="text" name="apit2" placeholder=" *Domain name" style="width:150px;height:20px;">&nbsp; .firebaseio.com/<br><br>2,Enter the Secret Key of your app..<br><br><input type="text" name="apit3" placeholder=" *SecretKey"><br><br><br><input type="button" onclick="javascript:sub1('dis1')" value="Continue" ></div>
	</div>
<br><br><br><div id='tit'>1.Choose trigger and action</div>
<br><br><center><div id="head">Mind-Bridge</div></center>
<br><br>
<div id="ins">a.Choose a trigger -> b. Choose an action -> c.Choose Trigger Method -> d.Choose Action Method</div><br><br>
<select name="select1" id="select1"  style="margin-left:250px;" >
       <option><center>Choose Trigger app here...</center></option>
        <%  while(resultset.next()){ 
        appid=resultset.getString("appid");
        title=resultset.getString("tit");
        mode=resultset.getString("mode");
        if("Trigger".equals(mode)){%>
            <option value="<%=appid%>"><%=title%>&nbsp;&nbsp;</option>
        <%}} %></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
   <select name="select2" id="select2">
      <option><center>Choose an Action app here...</center></option> 
       <% while(rs.next()){ 
        appid1=rs.getString("appid");
        title1=rs.getString("tit");
        mode1=rs.getString("mode");
        if("Action".equals(mode1)){%>
            <option value="<%=appid1%>"><%=title1%>&nbsp;&nbsp;</option>
        <%}} %>
   </select> <br><br>     

  <% 
       app=request.getParameter("app");
       PreparedStatement st=conn.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
       st.setString(1,app);
       rs2=st.executeQuery();
       app1=request.getParameter("app1");
       PreparedStatement st1=conn.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
       st1.setString(1,app1);
       rs3=st1.executeQuery();
       String t1="";String mode2="";String act_t1="";String mode3="";String accname="";String dis="";String dis1="";
  %>    
<br><br><select name="tgmeth" id="tgmeth" onchange="change()" style="margin-left:250px;height:40px;" >
    <option><center>Choose Trigger Method</center></option>
    <% while(rs2.next()){
        id=rs2.getString("appid");
        dis=rs2.getString("tit");
    	t1=rs2.getString("name");
        mode2=rs2.getString("mode");
        if("Trigger".equals(mode2)){ %>
   <option value="<%=t1 %>" ><%=t1 %></option>
    	        <%}} %></select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <select name="actmeth" id="actmeth" onchange="change()" style="height:40px;" >
    <option><center>Choose Action Method</center></option>
    <% while(rs3.next()){
    	id1=rs3.getString("appid");
    	dis1=rs3.getString("tit");
    	act_t1=rs3.getString("name");
    	mode3=rs3.getString("mode");
    	 if("Action".equals(mode3)){%>
   <option value="<%=act_t1 %>" ><%=act_t1%></option>
    	        <%}}%></select><input type="text" name="app1" value="<%=app1%>" style="display:none"><input type="text" name="app" value="<%=app %>" style="display:none"><br><br><br><br>
    	  
  <div id='tit1' style="display:none;color:#fff;margin-left:100px;"><h3>2,verify the Trigger Account</h3></div>	        
  <br><br><input type="button" Onclick="javascript:popup('popUpDiv')" name="con_trig" id="con_trig" value="Connect a <%=dis %>  account" style="display:none;margin-left:250px;"><div id="dis" style="display:none;margin-left:300px;color:#fff;">Account is working</div><br><br>	
  <div id='tit2' style="display:none;color:#fff;margin-left:100px;"><h3>3,verify the Action Account</h3></div>
  <br><br><input type="button" Onclick="javascript:popup('popUpAct')" name="con_act" id="con_act" value="Connect an  <%=dis1 %> account" style="display:none;margin-left:250px;"><div id="dis1" style="display:none;margin-left:300px;color:#fff;">Account is working</div>
  <br><br><br><br><center><input type="submit" value="Continue"></center>
  
   <% 
       
    }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
   %>

</body>
</html>