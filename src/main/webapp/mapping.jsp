<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!-- <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script> -->
<script src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/key1.js"></script>
<script type="text/javascript" src="js/value.js"></script>
<style type="text/css">
body {
	background-color: #ff9900;
	color: #fff;
	font-family: verdana;
	font-size: 19px;
}

input[type='button'],input[type='submit'] {
	width: 200px;
	height: 30px;
	color: #ff9900;
	border-color: #fff;
	border-radius: 5px;
	background-color: #fff;
}

input[type="text"] {
	width: 300px;
	height: 25px;
	font-family: verdana;
	font-size: 17px;
	color: #ff9900;
	border-radius: 5px;
}

select {
	width: 200px;
	height: 30px;
	background-color: #fff;
	font-family: verdana;
	font-size: 19px;
	color: #ff9900;
	border-radius: 5px;
}

#main {
	margin-left: auto;
	margin-right: auto;
}

#pa {
	font-family: verdana;
	font-size: 19px;
	color: #fff;
}

#cond {
	margin-left: 100px;
}

#map {
	margin-top: 10px;
}

#con1 {
	margin-left: 260px;
}

#con2 {
	margin-left: 250px;
}

#uniq,#parpol {
	margin-left: 260px;
}

hr {
	color: #fff;
}
</style>
<%
	String u = (String) request.getSession().getAttribute("id");
	if (u != null) {
	} else {
		response.sendRedirect("logout.jsp");
	}
	String respo = (String) request.getSession().getAttribute("xml1");
	//String respo="{\"test1\":\"num1\",\"test2\":\"num2\"}";
	//String respo="<test>nmae</test><test1>nmaesdd</test2>";
	respo = respo.replaceAll("\"", "'");
	//char chfirst=respo.charAt(0);
	String respfmt = (String) request.getSession().getAttribute(
			"respfmt");
	String tempid = (String) request.getSession()
			.getAttribute("tempid");
%>
<script type="text/javascript">
var respo2=<%=respo%>;
var respf="<%=respfmt%>";
var chfst='<';
<%-- var chh='<%=chfirst%>'; --%>
respo2= JSON.stringify(respo2);
if(respf=="json"){
	chfst=respo2.charAt(0);
}
function load(){
	window.open("<%=request.getContextPath()%>/Loadres", "tese", "toolbar=no, menubar=no,location=no, directories=no, status=no, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400");
}
function load1(){
	window.open("<%=request.getContextPath()%>/Login", "tese", "toolbar=no, menubar=no,location=no, directories=no, status=no, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400");	
}
</script>
<script type="text/javascript">
	/* 	var respo2 = {
	 "name1" : "name",
	 "name2" : {
	 "name11" : "name",
	 "name12" : "name"
	 },
	 "name3" : "name",
	 "name4" : "name"
	 };
	 respo2 = JSON.stringify(respo2);
	 var chfst = respo2.charAt(0); */
	$(document).ready(function() {
		var unid = document.getElementById('uniq');
		var uniq = addParam(0);
		var newTBU = document.createElement('div');
		newTBU.setAttribute('id', 'uuni');
		newTBU.innerHTML = uniq;
		unid.appendChild(newTBU);
		$('#b1').click(function() {
			var aa = $('#area').val();
			if (aa == '') {
				alert("Enter the sample request");
			} else {
				var conid = document.getElementById('con1');
				conid.removeChild(document.getElementById('map'));
				var cod = addValue(aa);
				console.log(cod);
				$("#con1").append(cod);
			}
			/* var n=1;
			Object.keys(myVar).forEach(function(k) {
				var keyval=JSON.stringify(myVar[k]);
				console.log(keyval);
				var hn=addParam(n);
					cod+="<div id='map"+n+"'>"+k+"*<br>"+hn+"<div><br>";
				n++;
			}); */

		});
	});
	function insertmap() {
		var para = arguments[0];
		var conid = "#conten" + para;
		document.getElementById(para).value = "@@x" + para + "@@";
		$(conid).append("<input  type=text>");
	}
	var intTextBox = 0;
	function addCond() {
		intTextBox = intTextBox + 1;
		var contentID = document.getElementById('condcont');
		var newTBDiv = document.createElement('div');
		newTBDiv.setAttribute('id', 'strText' + intTextBox);
		newTBDiv.setAttribute('style', 'margin:10px;');
		newTBDiv.innerHTML = "<input type='text' id='c" + intTextBox + "'    name='c" + intTextBox + "' placeholder='Condition_Name'/> &nbsp;&nbsp;&nbsp;<select name=cs1><option value=equals>equals</option><option value=equals>not equals</option></select>&nbsp;&nbsp;&nbsp;"
				+ "<input type='text' id='cv"+ intTextBox + " ' name='cv"+intTextBox+"' placeholder='Condition_Value'/>";
		contentID.appendChild(newTBDiv);
	}

	function removeCond() {
		var contentID = document.getElementById('condcont');
		contentID.removeChild(document.getElementById('strText' + intTextBox));
		intTextBox = intTextBox - 1;

	}
	var tem = 0;
	function addmpar() {
		tem = tem + 1;
		var contentID = document.getElementById('con2');
		var newTBDiv = document.createElement('div');
		newTBDiv.setAttribute('id', 'strText' + tem);
		newTBDiv.setAttribute('style', 'margin:10px;');
		var inhtm = addParam(tem);
		newTBDiv.innerHTML = inhtm;
		contentID.appendChild(newTBDiv);
	}

	function removempar() {
		var contentID = document.getElementById('con2');
		contentID.removeChild(document.getElementById('strText' + tem));
		tem = tem - 1;

	}
	/* <input id='"+tn+"' type='text'>&nbsp;&nbsp;<input type='button' value='insert field' Onclick=\"insertmap('"+tn+"')\">&nbsp;&nbsp;<div id='conten"+tn+"'></div> */
</script>
</head>
<%@ page import="java.sql.*"%>
<%@include file="conn.jsp"%>
<%
	ResultSet r = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	String actit = "";
	String tgtit = "";
	String tid = "";
	String aid = "";
	String rformat = "";
	String[] tp = new String[5];
	String resformat = "";
	String note = "Guide";
	String type = "";
%>
<%
	try {
		PreparedStatement st1 = conn
				.prepareStatement("select * from home where tempid='"
						+ tempid + "'");
		r = st1.executeQuery();
		while (r.next()) {
			tempid = r.getString("tempid");
			tid = r.getString("tid");
			aid = r.getString("aid");
			tgtit = r.getString("tgtit");
			actit = r.getString("actit");
			type = r.getString("type");
		}
		PreparedStatement ps1 = conn
				.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
		ps1.setString(1, tid);
		rs2 = ps1.executeQuery();
		while (rs2.next()) {
			resformat = rs2.getString("resformat");
		}
		PreparedStatement ps = conn
				.prepareStatement("select * from title t1 JOIN auth t2 on t1.appid=t2.appid JOIN triger t3 ON t1.appid=t3.appid where t1.appid=?");
		ps.setString(1, aid);
		rs = ps.executeQuery();
		while (rs.next()) {
			rformat = rs.getString("rformat");
			tp[1] = rs.getString("p1");
			tp[2] = rs.getString("p2");
			tp[3] = rs.getString("p3");
			tp[4] = rs.getString("p4");
			note = rs.getString("note");
		}
		conn.close();
%>
<body>
	<form action="Parse" method="post">
		<div id=main>
			<center>
				<h2>Filter and Mapping</h2>
			</center>
			<br>
			<div id="cond">Response From Trigger :</div>
			<center>
				<a id='pa' href="javascript:load()">To Read XML</a>&nbsp;&nbsp;&nbsp;
				<a id='pa' href="javascript:load1()">To Read JSON</a> <br>
				<hr>
				<input type="text" style="display: none;" id="ptag" name="ptag"
					placeholder=" parant Tag"> <input type="text"
					style="display: none;" name="resf" value="<%=resformat%>"
					placeholder=" parant Tag"> <br>
			</center>
			<div id="cond">Filter :</div>
			<center>
				<a id='pa' href="javascript:addCond()">Add Condition</a>&nbsp;&nbsp;&nbsp;<a
					id='pa' href="javascript:removeCond()">Remove Condition</a><br>
				<br>
				<div id="condcont"></div>
				<br>
				<hr>
				<br>
			</center>
			<%
				String sheet = (String) request.getSession().getAttribute(
							"sheetname");
					//String sheet="dssfas@@jhbjhjbhj@@jhbhjbjh@@jhbjhb@@";
					if (sheet != null) {
						String[] sh = sheet.split("@@");
						out.println("<div id='cond'>Select your spreadsheet:&nbsp;&nbsp;<select name='sheet'>");
						for (int i = 0; i < sh.length; i++) {
							out.println("<option value='" + sh[i] + "'>" + sh[i]
									+ "</option>");
						}
						out.println("</select></div><br><hr><br>");
					}
			%>
			<div id="cond">Sample Json Request :</div>
			<center>
				<textarea id="area" rows="7" cols="70"
					placeholder="Enter the sample json Action response"></textarea>
				<br> <br> <input id="b1" type="button" value=test>
				<br>
			</center>
			<hr>
			<br>
			<div id="cond">Mapping Fields :</div>
			<center>
				<a id='pa' href="javascript:addmpar()">Add Parameter</a>&nbsp;&nbsp;&nbsp;<a
					id='pa' href="javascript:removempar()">Remove Parameter</a><br>
				<br> <br>
			</center>
			<%
				if (resformat.equals("xml")) {
						out.println("<div id=parpol><input type=text name='parpol'></div>");
					}
			%>
			<div id=uniq>
				Unique Id :<br>
			</div>
			<div id=con2></div>
			<div id=con1>
				<div id=map></div>
			</div>
		</div>
		<br>
		<%
			} catch (Exception e) {
				out.println(e);
			}
		%>
		<center>
			<input type="submit" value="Continue">
		</center>
		<br> <br>
	</form>
</body>
</html>