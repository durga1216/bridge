<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/jquery-latest.js"></script>
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
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
height:50px;
background-color:#fff;
border-radius:5px;
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
</script>
</head>
<body>
<form action="MethodStorage" method="get">
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<%ResultSet resultset =null;ResultSet rs=null;
ResultSet rs2=null; ResultSet rs3=null;%>
<%
    try{
    PreparedStatement ps = conn.prepareStatement("select * from title");
       resultset =ps.executeQuery() ;
       PreparedStatement ps1 = conn.prepareStatement("select * from title");
       rs =ps1.executeQuery() ;
       String appid="";String title="";String mode="";String title1="";String appid1="";String mode1="";String app="";String app1="";
String id="";String id1="";
%>

<br><br><center><div id="head">Mind-Pulpy Cloud Connectors</div></center><br><hr>
<br><br><br><div id='tit'>1.Choose trigger and action &nbsp;&nbsp;&nbsp; (OR) &nbsp;&nbsp;&nbsp; <a style="color:#fff;" href="javascript:load()">Click here to Submit New Cloud Connect Request</a></div>
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