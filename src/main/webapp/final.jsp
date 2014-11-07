<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/jquery-latest.js"></script>
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
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
h4{
font-family:verdana;
font-weight:bold;
color:#FFFFFF;
font-size:20px;
margin-left:300px;
}
#lin{
text-decoration:none;
font-family:verdana;
font-weight:bold;
font-size:22px;
}
h2{
font-family:verdana;
font-weight:bold;
font-size:22px;
color:#FFFFFF;
}
img{
width:35px;
height:35px;
border-radius:7px;
}
select{
font-family:verdana;
font-weight:bold;
font-size:17px;
color:#ff9900;
border-radius:5px;
padding-right:5px;
padding-left:5px;
border:solid 1px #fff;
background-color:#fff;
}
input[type="submit"]{
padding-right:5px;
padding-left:5px;
border:solid 1px #fff;
border-radius:5px;
background-color:#fff;
font-family:verdana;
font-size:17px;
color:#ff9900;
font-weight:bold;
}
input[type="button"]{
padding-right:5px;
padding-left:5px;
border-radius:5px;
font-family:verdana;
font-size:17px;
color:#fff;
font-weight:bold;
}
gn{
border:solid 1px #0f0;
background-color:#0f0;
}
rd{
border:solid 1px #f00;
background-color:#f00;
}
hr{
color:#fff;
background-color:#fff;
height:1px;
}
</style>
</head>
<%@ page import="java.sql.*" %>
<%@include file="conn.jsp" %>
<body><br><br><br>
<center><div id=head>Mind-Pulpy</div><br><hr><br>
<h2>Your Connecters is Running successfully..!! <a id=lin href="index.jsp">&lt;----Click here for Connect More----&gt;</a></h2>
</center>
<%	String id = (String) request.getSession().getAttribute("id");
	try{
		 int i=1;
		 PreparedStatement st1=conn.prepareStatement("select tempid from trig_all where userid='"+id+"'");
		 ResultSet rs1=st1.executeQuery();
		 while(rs1.next()){
				String tempid=rs1.getString("tempid");
				PreparedStatement st2=conn.prepareStatement("select * from home where tempid=?");
				st2.setString(1, tempid);
				ResultSet rs2=st2.executeQuery();
				while(rs2.next()){
						String tt=rs2.getString("tgtit");
						String aa=rs2.getString("actit");
						String tid=rs2.getString("tid");
						String aid=rs2.getString("aid");
						String userid=rs2.getString("userid");
						String state=rs2.getString("state");
						%>
							<form action="ThreadHandler" method="get">
							<h4><%=i%>, &nbsp; <img src="Title?appid=<%=tid%>"> <%=tt%> &nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="Title?appid=<%=aid%>"> <%=aa%>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%if(state.equals("Active")){ %>
							<input id=gn type="button" value=" <%=state%> ">
							<%}else{ %>
							<input id=rd type="button" value=" <%=state%> ">
							<%} %>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<select name="state"><option value=Active>Active</option><option value=Inactive>Inactive</select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<select name="time"><option value=15>15 Minutes</option><option value=10>10 Minutes</select>
							<input style="display:none;" type=text name=tempid value=<%=tempid%>>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value=" Apply "></h4>
							</form>
						<%
				}
				i++;
		}
	}
	catch(Exception e){
		out.println(e);
	}

%>
</body>
</html>