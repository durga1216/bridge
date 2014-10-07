<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection("jdbc:mysql://127.12.212.2:3306/bridge", "admin7R9w6e8", "5n4gq2Pz4q_b");
//Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/bridge","root","root");
%>
