<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
	<%
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Cache-Control", "no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
    %>
<script src="js/jquery-latest.js"></script>
<style>
body{
background-color:#FF9900;}
.head{
font-family:verdana;
font-size:28px;
font-weight:bold;
color:#FFFFFF;
}
#ful{
width:100%;
height:350px;
color:#fff;
font-family:verdana; 
font-weight:bold;
font-size:18px;
}
input[type="text"]{
width:200px;
height:25px;
font-family:verdana; 
font-size:19px;
color:#FF9900;
border-radius:5px;
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
#para,#cond{
float:left;
height:100%;
width:600px;
margin-left:70px;
font-family:verdana;
color:#FF9900;
font-size:18px;
}
h4{
font-family:verdana; 
font-size:19px;
color:#fff;
}
#txt1{
font-family:verdana;
font-size:15px;
color:#FF9900;
font-weight:bold;
width:300px;
height:150px;
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
#res{
margin-left:30px;
float:left;
height:100%;
}
hr{
color:#fff;
background-color:#fff;
height:1px;
}
#txt2{
font-family:verdana;
font-size:15px;
margin-left:70px;
color:#FF9900;
font-weight:bold;
width:600px;
height:120px;
}
#sheet{
color:#fff;
font-size:18px;
}
#name{
font-family:verdana;
font-size:18px;
margin-left:70px;
color:#FFFFFF;
}
#pa{
  font-family:verdana;
  font-size:18px;
  color:#FFFFFF;  
  }
</style>
<%
String u = (String) request.getSession().getAttribute("id");
if (u != null ) {
}else{
	response.sendRedirect("logout.jsp");
}
String respo= (String) request.getSession().getAttribute("xml1");
//String respo="{\"name\":[{\"sname\":{\"bname\":\"mind\"}},{\"sname\":\"dots\"}]}";
//String respo="<?xml version=\"1.0\" encoding=\"UTF-8\"?><search><total_items>115</total_items><page_size><page_count11>12</page_count11></page_size><page_count>12</page_count><page_number>1</page_number></search>";
char chfirst=respo.charAt(0);
%>
<script type="text/javascript" src="js/keys.js"></script>
<script type="text/javascript">
var respo2='<%=respo%>';
var chfst='<%=chfirst%>';
function load(){
	window.open("<%=request.getContextPath()%>/Loadres", "tese", "toolbar=no, menubar=no,location=no, directories=no, status=no, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400");
}
function load1(){
	window.open("<%=request.getContextPath()%>/Login", "tese", "toolbar=no, menubar=no,location=no, directories=no, status=no, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400");
}
var intTextBox=0;

function addCond(){
	  intTextBox = intTextBox + 1;
	  var contentID = document.getElementById('condcont');
	  var newTBDiv = document.createElement('div');
	  newTBDiv.setAttribute('id','strText'+intTextBox);
	  newTBDiv.innerHTML = "<input type='text' id='c" + intTextBox + "'    name='c" + intTextBox + "' placeholder='Condition_Name'/> &nbsp;&nbsp;&nbsp;<style>font-family:verdana;color:#FFFFFF;> is equals to </style>&nbsp;&nbsp;&nbsp;" + "<input type='text' id='cv"+ intTextBox + " ' name='cv"+intTextBox+"' placeholder='Condition_Value'/>";
	  contentID.appendChild(newTBDiv);
}

function removeCond(){
	var contentID = document.getElementById('condcont');
    contentID.removeChild(document.getElementById('strText'+intTextBox));
    intTextBox = intTextBox-1;
	
}
</script>
</head>
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<%
		ResultSet r=null;ResultSet rs =null; 
		String actit="";String tgtit="";String tid="";String aid="";String tempid="";
		String rformat="";String[] tp=new String[5]; 
		String note="Guide";String type="";
%>
<%
	try{
	PreparedStatement st1=conn.prepareStatement("select * from home order by tempid desc limit 1");
	r=st1.executeQuery();
	while(r.next()){
		tempid=r.getString("tempid");
		tid=r.getString("tid");
		aid=r.getString("aid");
		tgtit=r.getString("tgtit");
		actit=r.getString("actit");
		type=r.getString("type");
	}
		  PreparedStatement ps = conn.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
	      ps.setString(1,aid);
	      rs=ps.executeQuery();
	      while(rs.next()){
	    			rformat=rs.getString("rformat");
	    			tp[1]=rs.getString("p1");tp[2]=rs.getString("p2");tp[3]=rs.getString("p3");tp[4]=rs.getString("p4");
	    			note=rs.getString("note");
	      }
%>
<body>
<form action="Parse" method="post">
<br><br><center><div class="head">Mind-Pulpy Mapping</div></center><br>
<hr><br><center><a id='pa' href="javascript:load()">Sample Trigger response(For XML)</a>&nbsp;&nbsp;&nbsp;
<a id='pa' href="javascript:load1()">Sample Trigger response(For JSON)</a></center><br><br>
<div id="cond">Check Condition</div>
	&nbsp;&nbsp;&nbsp;<a id='pa' href="javascript:addCond()">Add Condition</a>&nbsp;&nbsp;&nbsp;<a id='pa' href="javascript:removeCond()">Remove Condition</a><br><br>
  
  <div id="condcont"></div>
<div id=ful>
<div id=para>
		<br><h3>Action Parameter:</h3>
<%	
	String sheet= (String) request.getSession().getAttribute("sheetname");
	//String sheet="dssfas@@jhbjhjbhj@@jhbhjbjh@@jhbjhb@@";
	if (sheet != null ) {
		String[] sh=sheet.split("@@");
		out.println("<div id='sheet'>Select your spreadsheet:&nbsp;&nbsp;<select name='sheet'>");
		for(int i=0;i<sh.length;i++){
			out.println("<option value='"+sh[i]+"'>"+sh[i]+"</option>");
		}
		out.println("</select></div><br>");
	}
	if(rformat.equals("rest")){
		for(int i=1;i<5;i++){
				if(!tp[i].equals("null")){
						out.println("<br>*"+tp[i]+":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;&lt;----Map With Trigger (x"+i+") Tag----&gt;&gt;<br>");
				}
		}
	}else{
		%><textarea name="exres" id="txt1"  placeholder="Give Original xml or json structure 

Json Example:
	{'root':{Stable:@@map x1@@}}
Xml Example:
	&lt;root&gt;
		&lt;stable&gt;@@map x1@@&lt;/stable&gt;
	&lt;/root&gt;"></textarea>&nbsp;&nbsp;&nbsp;&lt;&lt;--Map trigger nodes--&gt;&gt;<% 	
	}
%>
</div>
<div id=res>
	<h3>Trigger Response:</h3>
	<%if(type.equals("polling")){
		%><h4>Enter the Polling parent Tag:</h4><input type=text name="parpol">
		<h4>Enter the Unique Polling Element:</h4><input type=text name="unipol"><br><%
	}%>
	<input type="text" style="display:none;" id="ptag" name="ptag" placeholder=" parant Tag"><br><br>
	<a id='pa' href="javascript:addParam()">Add_Tag</a>&nbsp;
    <a id='pa' href="javascript:removeParam()">Remove_Tags</a><br>
	<div id="content"></div>
</div>
</div>
<div id="name">User Guide:</div><br>
<textarea name="note" id="txt2" readonly><%=note%></textarea>
<br><br><br>
<%		
		}
		catch(Exception e){
			out.println(e);
		}
%>
<center><input type="submit" value="Continue"></center>
</form>
</body>
</html>