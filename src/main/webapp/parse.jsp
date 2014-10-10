<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Parse</title>
</head>
<%@ page import="java.sql.*" %>
<%@ include file="conn.jsp" %>
<body>
<form action="Temp" method="post" enctype="multipart/form-data">
<br><br><center><select name=no><option value=dummy>choose the id</option>
<%
try{
	PreparedStatement ps=conn.prepareStatement("select * from title");
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		String id=rs.getString("appid");
		%>
		<option value="<%=id%>"><%=id%></option>
		<%
	}
}catch(Exception e){
	out.println();
}
%>
</select><br><br><input type="file" name="logo">
<br><br><input type="submit"></center>
</form>
</body>
</html>