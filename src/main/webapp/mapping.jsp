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
<style type="text/css">
body{
background-color:#0099cc;
color:#fff;
font-family:verdana; 
font-size:19px;
}
input[type='button']{
width:200px;
height:30px;
color:#0099cc;
border-color:#fff;
border-radius:5px;
background-color:#fff;
}
input[type="text"]{
width:300px;
height:25px;
font-family:verdana; 
font-size:17px;
color:#0099cc;
border-radius:5px;
}
select{
width:200px;
height:30px;
background-color:#fff;
font-family:verdana; 
font-size:19px;
color:#0099cc;
border-radius:5px;
}
#main{
margin-left: auto;
    margin-right: auto;
}
#pa{
font-family:verdana; 
font-size:19px;
color:#fff;
}
#cond{
margin-left:100px;
}
#con1{
margin-left:250px;
}
hr{
color:#fff;
}
</style>
<script type="text/javascript" src="js/key1.js"></script>
<script type="text/javascript">
var respo2={"name1":"name","name2":"name","name3":"name","name4":"name"};
respo2= JSON.stringify(respo2);
var chfst=respo2.charAt(0);
	$(document).ready(function() {
		$('#b1').click(function(){
			var aa=$('#area').val();
			var myVar=JSON.parse(aa);
			var cod="";
			var n=1;
			Object.keys(myVar).forEach(function(k) {
				var tn=''+n;
				var hn=addParam(tn);
					cod+=k+"*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div id='map'>"+hn+"<div><br>";
				n++;
			});
			$("#con1").append(cod);
		});
	});
	function insertmap(){
		var para=arguments[0];var conid="#conten"+para;
		document.getElementById(para).value="@@x"+para+"@@";
		$(conid).append("<input  type=text>");
	}
	var intTextBox=0;
	function addCond(){
		  intTextBox = intTextBox + 1;
		  var contentID = document.getElementById('condcont');
		  var newTBDiv = document.createElement('div');
		  newTBDiv.setAttribute('id','strText'+intTextBox);
		  newTBDiv.innerHTML = "<input type='text' id='c" + intTextBox + "'    name='c" + intTextBox + "' placeholder='Condition_Name'/> &nbsp;&nbsp;&nbsp;<select name=cs1><option value=equals>equals</option><option value=equals>not equals</option></select>&nbsp;&nbsp;&nbsp;" + "<input type='text' id='cv"+ intTextBox + " ' name='cv"+intTextBox+"' placeholder='Condition_Value'/>";
		  contentID.appendChild(newTBDiv);
	}

	function removeCond(){
		var contentID = document.getElementById('condcont');
	    contentID.removeChild(document.getElementById('strText'+intTextBox));
	    intTextBox = intTextBox-1;
		
	}
	/* <input id='"+tn+"' type='text'>&nbsp;&nbsp;<input type='button' value='insert field' Onclick=\"insertmap('"+tn+"')\">&nbsp;&nbsp;<div id='conten"+tn+"'></div> */
</script>
</head>
<body><div id=main><center><h2>Filter and Mapping</h2></center><br>
<div id="cond">Filter</div><center>
	<a id='pa' href="javascript:addCond()">Add Condition</a>&nbsp;&nbsp;&nbsp;<a id='pa' href="javascript:removeCond()">Remove Condition</a><br><br>
	<div id="condcont"></div><br><hr><br></center>
	<div id="cond">Sample Json Response</div>
	<center>
	<textarea id="area" rows="7" cols="70" placeholder="Enter the sample json Action response"></textarea>
	<br>
	<br>
	<input id="b1" type="button" value=test>
	<br></center>
	<hr>
	<br>
	<div id="cond">Mapping Fields</div>
	<div id=con1></div></div>
</body>
</html>