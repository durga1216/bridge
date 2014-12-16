<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mind Connectors</title>
	<link rel="shortcut icon" href="favicon.ico" />
	<script src="js/jquery-latest.js"></script>
	<%
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Cache-Control", "no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
    %>
	<script type="text/javascript">
	function toggle(div_id) {
		var el = document.getElementById(div_id);
		if ( el.style.display == 'none' ) {
			el.style.display = 'block';}
		else{
			el.style.display = 'none';}
	}
	function sub(dis) {
		var el = document.getElementById('blanket');
		if ( el.style.display == 'none' ) {
			el.style.display = 'block';}
		else{
			el.style.display = 'none';}
		var el = document.getElementById('popUpDiv');
		if ( el.style.display == 'none' ) {
			el.style.display = 'block';}
		else{
			el.style.display = 'none';}
		var el = document.getElementById(dis);
		if ( el.style.display == 'none' ) {
			el.style.display = 'block';}
		var el= document.getElementById('tit2');
		el.style.display='block';
		var el= document.getElementById('con_act');
		el.style.display='block';
	}
	function sub1(dis1) {
		var el = document.getElementById('blanket');
		if ( el.style.display == 'none' ) {
			el.style.display = 'block';}
		else {
			el.style.display = 'none';}
		var el = document.getElementById('popUpAct');
		if ( el.style.display == 'none' ) {
			el.style.display = 'block';}
		else{
			el.style.display = 'none';}
		var el = document.getElementById(dis1);
		if ( el.style.display == 'none' ) {
			el.style.display = 'block';}
	}	
	function blanket_size(popUpDivVar) {
		if (typeof window.innerWidth != 'undefined') {
			viewportheight = window.innerHeight;
		}
		else {
			viewportheight = document.documentElement.clientHeight;
		}
		if ((viewportheight > document.body.parentNode.scrollHeight) && (viewportheight > document.body.parentNode.clientHeight)) {
			blanket_height = viewportheight;
		}
		else {
			if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
				blanket_height = document.body.parentNode.clientHeight;
			}
			else {
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
		}
		else {
			viewportwidth = document.documentElement.clientHeight;
		}
		if ((viewportwidth > document.body.parentNode.scrollWidth) && (viewportwidth > document.body.parentNode.clientWidth)) {
			window_width = viewportwidth;
		}	
		else {
			if (document.body.parentNode.clientWidth > document.body.parentNode.scrollWidth) {
				window_width = document.body.parentNode.clientWidth;
			}
			else {
				window_width = document.body.parentNode.scrollWidth;
			}
		}
		var popUpDiv = document.getElementById(popUpDivVar);
		window_width=window_width/2-200;//200 is half popup's width
		popUpDiv.style.left = window_width + 'px';
	}	
	function popup(windowname) {
		toggle('blanket');
		blanket_size(windowname);
        window_pos(windowname);
		toggle(windowname);		
	}
	</script>
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
	#txt1,#msg{
		font-family:verdana;
		font-size:15px;
		color:#FF9900;
		font-weight:bold;
		width:300px;
		height:100px;
		}
	#tit{
		font-family:verdana;
		font-size:20px;
		font-weight:bold;
		margin-left:70px;
		color:#FFFFFF;
		font-weight:bold;
	}
	input[type='text'],input[type='password']{
		width:300px;
		height:20px;
		font-family:verdana; 
		font-weight:bold;
		font-size:15px;
		color:#FF9900;
		border-radius:5px;
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
	#side{
		font-family:verdana;
		font-size:20px;
		margin-left:70px;
		color:#FFFFFF;
	}
	
	select{
		font-family:verdana;
		font-size:17px;
		background-color:#fff;
		color:#FF9900;
		font-weight:bold;
		width:300px;
		height:40px;
		border-radius:5px;
		margin-left:200px;
	
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
		overflow-y:auto;
		box-shadow:2px 2px 5px 1px #000;
	
	}
	
	#popUpDiv {
		a position:relative; top:20px; left:20px; overflow-y:auto;
	}
	#popUpAct {
		position:absolute;
		background-color:#ff9900;
		width:500px;
		height:500px;
		border:3px solid #ff9900;
		z-index: 9002;
		overflow-y:auto;
		box-shadow:2px 2px 5px 1px #000;
	
	}
	
	#popUpAct {
		a position:relative; top:20px; left:20px
	}
	#inpop{
		margin:50px;
		overflow-y:auto;
		font-family:verdana;
		font-size:15px;
		color:#FFFFFF;
	}
	hr{
		color:#fff;
		background-color:#fff;
		height:1px;
	}
	</style>
</head>
<body>
<%
	String u = (String) request.getSession().getAttribute("id");
    if (u != null ) {
    }else{
    	response.sendRedirect("logout.jsp");
    }
%>
<br><br><center><div id="head">Mind-Pulpy Authentication</div></center><br><hr>
<br><br><div id='tit'>2.Authenticate your Account</div>
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<%
ResultSet r=null;ResultSet rs1 =null;ResultSet rs=null;String authen="";String txt1="";String txt2="";String txt3="";String txt4="";String a1="";String b1="";String b3="";
String authen1="";String atxt1="";String atxt2="";String atxt3="";String atxt4="";String tgtit="hh";String jstr="";
String actit="hh";String tid="hh";String aid="hh";int code=0;int code1=0;String tempid="";String sigskey="";String sigckey="";
String turl="";String aurl="";String[] tp=new String[5];String[] hd=new String[5];String rformat="";String type="";
String sigmessage="";	
try{
		PreparedStatement st1=conn.prepareStatement("select * from home order by tempid desc limit 1");
     	r=st1.executeQuery();
    	while(r.next()){
	    	tempid=r.getString("tempid");
	 	   	tid=r.getString("tid");
	 	   	aid=r.getString("aid");
	 	   	tgtit=r.getString("tgtit");
	 	   	actit=r.getString("actit");
 	   	}
	  	PreparedStatement ps = conn.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
      	ps.setString(1,tid);
      	rs=ps.executeQuery();
      	while(rs.next()){
    		authen=rs.getString("authen");
    		type=rs.getString("hoo");jstr=rs.getString("js");
    		turl=rs.getString("t1");
    		tp[1]=rs.getString("p1");tp[2]=rs.getString("p2");tp[3]=rs.getString("p3");tp[4]=rs.getString("p4");
    		txt1=rs.getString("txt1");txt2=rs.getString("txt2"); txt3=rs.getString("txt3");txt4=rs.getString("txt4");
    		sigckey=rs.getString("sigckey");sigskey=rs.getString("sigskey");rformat=rs.getString("rformat");
    		sigmessage=rs.getString("message");
      	}
	  	PreparedStatement ps1 = conn.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
      	ps1.setString(1,aid);
      	rs1=ps1.executeQuery();
      	while(rs1.next()){
    		authen1=rs1.getString("authen");
    		aurl=rs1.getString("t1");
    		hd[1]=rs1.getString("h1");hd[2]=rs1.getString("h2");hd[3]=rs1.getString("h3");hd[4]=rs1.getString("h4");
    		atxt1=rs1.getString("txt1");atxt2=rs1.getString("txt2"); atxt3=rs1.getString("txt3");atxt4=rs1.getString("txt4");
      	    
      	}
     	HttpSession session1=request.getSession();
     	session1.setAttribute("tid",tid);
     	session1.setAttribute("aid",aid);
     	session1.setAttribute("tempid",tempid);     
%>
<div id="blanket" style="display:none;"></div>
    <form action="TriggerAuth" method="post">
	<div id="popUpDiv" style="display:none;">
	<a style='font-size:20px;color:#000;float:right;' onclick="popup('popUpDiv')" ><img style='height:20px;width:20px'alt="close" src="images/close.png"></a><br>
	<%if(authen.equals("API keys")) {%>
	<div id=inpop><h3>Enter the Authentication details:</h3><br>*ApiKey(required):<br>
	<br><%=txt3 %><br><br><input type="text" name="apkey" placeholder=" *ApiKey"><br><br>
	*Parameter(required):<br><br>
	<%
	for(int i=1;i<5;i++){
		if(!tp[i].equals("null")){
			out.println("<br>*"+tp[i]+":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style='width:100px;border-radius:5px;'name='pv"+i+"' type='text'><br>");
		}
	}
	String[] slt=turl.split("@@");
	int nn=slt.length;String orurl="";
	if(!(nn==0)){
		for(int i=1,j=1;i<nn;i=i+2,j++){
			slt[i]="&nbsp;<input style='width:100px;border-radius:5px;'name='ndm"+j+"' type='text'>&nbsp;";
		}
		for(int k=0;k<nn;k++){
			orurl=orurl+slt[k];
		}
		out.println("<br>"+orurl+"<br>");
	}
	%>
	<br><br><input type="submit" id="inp" name="submit" value="Authenticate Trigger" onclick="javascript:sub('dis')"></div>
	<%}else if(authen.equals("No Auth")){%>
			<div id=inpop><h3>Enter the Parameter details:</h3><br>No Authentication(required):<br>
				<%
			for(int i=1;i<5;i++){
				if(!tp[i].equals("null")){
					out.println("<br>*"+tp[i]+":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style='width:100px;border-radius:5px;'name='pv"+i+"' type='text'><br>");
				}
			}
			String[] slt=turl.split("@@");
			int nn=slt.length;String orurl="";
			if(!(nn==0)){
				for(int i=1,j=1;i<nn;i=i+2,j++){
					slt[i]="&nbsp;<input style='width:100px;border-radius:5px;'name='ndm"+j+"' type='text'>&nbsp;";
				}
				for(int k=0;k<nn;k++){
					orurl=orurl+slt[k];
				}
				out.println("<br>"+orurl+"<br>");
			}
			if(!rformat.equals("rest")){
				%><br>Enter the json/xml Input:<br><textarea name="exreq" id="txt1"><%=jstr%></textarea><% 	
			}
			%>
			<br><br><br><br><br>
			<input type="submit" name="submit" onclick="javascript:sub('dis')" value="Authenticate Trigger" ></div>
	<%}else if(authen.equals("Basic Auth")){%>
		<div id=inpop><h3>Enter the Authentication details:</h3><br>*Basic Authentication(required):<br>
		<%
	String[] slt=turl.split("@@");
	int nn=slt.length;String orurl="";
	if(!(nn==0)){
	for(int i=1,j=1;i<nn;i=i+2,j++){
		slt[i]="&nbsp;<input style='width:100px;border-radius:5px;'name='tdm"+j+"' type='text'>&nbsp;";
	}
	for(int k=0;k<nn;k++){
		orurl=orurl+slt[k];
	}
	out.println("<br>"+orurl+"<br>");
	}
	if(!rformat.equals("rest")){
		%><br>Enter the json/xml Input:<br><textarea name="exreq" id="txt1"><%=jstr%></textarea><% 	
		if(type.equals("resthook")){
			%><br>Add this Redirect Url:<br><u>https://bridge-minddotss.rhcloud.com/mindpulpy/webhooks/<%=tempid %></u><%
		}	
	}
	%>
		<br><%=txt1 %><br><br><input type="text" name="uname" placeholder="UserName or ApiKey"><br><br><%=txt2 %><br><br><input type="password" name="pwd" placeholder="Password or Secret Key"><br><br><br>
		<input type="submit" name="submit" onclick="javascript:sub('dis')" value="Authenticate Trigger" ></div>
	<%}else if(authen.equals("Oauth2")){
	%>
	<div id=inpop><h3>Oauth 2 Authentication:</h3><br>
	1,Login into Your account by clicking the Authenticate Button..
	<br>2,Access the MindPulpy App to read the data..<br><br>
	*Parameter(required):<br>
	<%
	for(int i=1;i<5;i++){
		if(!tp[i].equals("null")){
			out.println("<br>*"+tp[i]+":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style='width:100px;border-radius:5px;'name='pv"+i+"' type='text'><br>");
		}
	}
	String[] slt=turl.split("@@");
	int nn=slt.length;String orurl="";
	if(!(nn==0)){
	for(int i=1,j=1;i<nn;i=i+2,j++){
		slt[i]="&nbsp;<input style='width:100px;border-radius:5px;'name='tdm"+j+"' type='text'>&nbsp;";
	}
	for(int k=0;k<nn;k++){
		orurl=orurl+slt[k];
	}
	out.println("<br>"+orurl+"<br>");
	}
	if(!rformat.equals("rest")){
		%><br>Enter the json/xml Input:<br><textarea name="exreq" id="txt1"><%=jstr%></textarea><% 	
		if(type.equals("resthook")){
			%><br>Add this Redirect Url:<br><u>https://bridge-minddotss.rhcloud.com/mindpulpy/webhooks/<%=tempid %></u><%
		}
	}
	%><br><br>
		<input type="submit" name="submit" onclick="javascript:sub('dis')" value="Authenticate Trigger" ></div>
	<%}else if(authen.equals("Oauth1")){
	%>
	<div id=inpop><h3>Oauth 1 Authentication:</h3><br><br>
	1,Login into Your account by clicking the Authenticate Button..
	<br>2,Access the MindPulpy App to read the data..<br><br>
	*Parameter(required):<br><br>
	<%
	for(int i=1;i<5;i++){
		if(!tp[i].equals("null")){
			out.println("<br>*"+tp[i]+":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style='width:100px;border-radius:5px;'name='pv"+i+"' type='text'><br>");
		}
	}
	%><br><br>
		<input type="submit" name="submit" onclick="javascript:sub('dis')" value="Authenticate Trigger" ></div>
	<%
	}else if(authen.equals("Signed Auth")){
	%>
	<div id=inpop><h3>Signed Authentication:</h3><br><br>
     Enter the API key:<br>
       <input type="text" name="sigckey" value=""><br><br>
     Enter the Secret Key:<br>
       <input type="password" name="sigskey" value=""><br><br>
     Enter Message(For Eg- <%=sigmessage%>):<br><br>
       <textarea name="authmsg" id="authmsg" value="" placeholder="You can also copy paste the message from eg,or enter new message"></textarea><br><br>
       
		<input type="submit" name="submit" onclick="javascript:sub('dis')" value="Authenticate Trigger" ></div>
	<%
	}else if(type.equals("webhook")){
		%>
		<div id=inpop><h3>Webhooks:</h3><br>
	    **ALERT**<br><br>
		1, Enter the MindPulpy Webhook Url into Your account.<br><br>
		2, Your webhook url :<br><u>https://bridge-minddotss.rhcloud.com/mindpulpy/webhooks/<%=tempid %></u><br><br>
		3, Method: POST &nbsp;&nbsp;&nbsp; Parameter_name: data &nbsp;&nbsp;&nbsp; Format: JSON.<br><br>
		4, <input type="checkbox"> Do the specified action in you account then click continue..<br><br><br>
	    <input type="submit" name="submit" onclick="javascript:sub('dis')" value="Webhook Trigger" ></div>
		<%
		}
	%>
	</div>

<div id="popUpAct" style="display:none;">
	<a style='font-size:20px;color:#000;float:right;'onclick="popup('popUpAct')" ><img style='height:20px;width:20px'alt="close" src="images/close.png"></a><br>
	<%if(authen1.equals("Basic Auth")){ 
	 %>
	<div id=inpop><h3>Enter the Authentication details:</h3><br>*Basic Authentication(required):<br>
	<%
	String[] slt=aurl.split("@@");
	int nn=slt.length;String orurl="";
	if(!(nn==0)){
	for(int i=1,j=1;i<nn;i=i+2,j++){
		slt[i]="&nbsp;<input style='width:100px;border-radius:5px;'name='adm"+j+"' type='text'>&nbsp;";
	}
	for(int k=0;k<nn;k++){
		orurl=orurl+slt[k];
	}
	out.println("<br>"+orurl+"<br>");
	}
	%>
	<br><%=atxt1 %><br><br><input type="text" name="uname" placeholder=" UserName or ApiKey"><br><br><%=atxt2 %><br><br><input type="password" name="pwd" placeholder="Password or Secret Key"><br><br>
	Header:<br><br>
	<%
	for(int i=1;i<5;i++){
		if(!hd[i].equals("null")){
			out.println("<br>*"+hd[i]+":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style='width:200px;border-radius:5px;'name='hd"+i+"' type='text'><br>");
		}
	}
	%>
	<br><br><input type="submit" name="submit" onclick="javascript:sub1('dis1')" value="Authenticate Action" ></div>
	<%} 
	else if(authen1.equals("API keys")){%>
		<div id=inpop><h3>Enter the Authentication details:</h3><br>*ApiKey(required):<br><br><%=atxt3 %><br><br><input type="text" name="apkey" placeholder=" *ApiKey"><br><br><br>
		<input type="submit" id="inp" name="submit" value="Authenticate Action" onclick="javascript:sub1('dis1')"></div>
	
	<%}else if(authen1.equals("Oauth2")){
	%>
	<div id=inpop><h3>Oauth Authentication:</h3><br><br>
	1,Login into Your account by clicking the Authenticate Button..
	<br>2,Access the MindPulpy App to read the data..<br>
	<%
	String[] slt=aurl.split("@@");
	int nn=slt.length;String orurl="";
	if(!(nn==0)){
	for(int i=1,j=1;i<nn;i=i+2,j++){
		slt[i]="&nbsp;<input style='width:100px;border-radius:5px;'name='adm"+j+"' type='text'>&nbsp;";
	}
	for(int k=0;k<nn;k++){
		orurl=orurl+slt[k];
	}
	out.println("<br>"+orurl+"<br>");
	}
	%><br><br><br>
		<input type="submit" name="submit" onclick="javascript:sub1('dis1')" value="Authenticate Action" ></div>
	<%}else if(authen1.equals("Oauth1")){
	%>
	<div id=inpop><h3>Oauth Authentication:</h3><br><br>
	1,Login into Your account by clicking the Authenticate Button..
	<br>2,Access the MindPulpy App to read the data..<br><br><br><br>
		<input type="submit" name="submit" onclick="javascript:sub1('dis1')" value="Authenticate Action" ></div>
	
	<%
	}
	}catch(Exception e){} %>
	
	</div></form>
	
	
<br><br><div id="side">Select the <%=tgtit%> Account</div><br><br>
<select name="tgacc">
<option>Choose Exisiting Account</option>
<option>Choose Different New Account</option>
</select><br><br><br>
<input type="button" Onclick="javascript:popup('popUpDiv')" name="con_trig" id="con_trig" value="Connect a <%=tgtit %> account" style="margin-left:300px;">	
<%code=(Integer)request.getAttribute("code");
if(code==200){%>
	<div id="disp" style="margin-left:600px;color:#fff;"><img src="images/tick.jpg" width="20" height="20">&nbsp;&nbsp; Account is working</div><br><br>	
<% }
else if(code==0){%>
   	<div id="disp" style="margin-left:600px;color:#fff;">Please check your Account!!!</div><br><br>	

<%} 
else{%>
	<div id="disp" style="margin-left:600px;color:#fff;">!! Account is Not working</div><br><br>	

<% }
%>
<div id="side">Select the <%=actit%> Account</div><br><br>
<select name="actacc">
<option>Choose Exisiting Account</option>
<option>Choose Different New Account</option>
</select><br><br><br>
<input type="button" Onclick="javascript:popup('popUpAct')" name="con_act" id="con_act" value="Connect an <%= actit %> account" style="margin-left:300px;"><div id="disp1" style="display:none;margin-left:300px;color:#fff;">Account is working</div>
<%code1=(Integer)request.getAttribute("code1");
if(code1==200){%>
	<div id="disp1" style="margin-left:600px;color:#fff;"><img src="images/tick.jpg" width="20" height="20">&nbsp;&nbsp; Account is working</div><br>


<% }
else if(code1==0){%>
   	<div id="disp1" style="margin-left:600px;color:#fff;">Please check your Account!!!</div><br><br>	
   
<%} 
else{%>
	<div id="disp1" style="margin-left:600px;color:#fff;">!! Account is Not working</div><br><br>	

<% }
%>
<br><form>
<center><input type="submit" value="Continue" formAction="res.jsp"></center><br><br></form>
</body>
</html>