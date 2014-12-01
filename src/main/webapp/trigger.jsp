<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
	<script src="js/jquery-latest.js"></script>
<script type="text/javascript">
var TextBox=0;
var intTextBox=0;
function addTrigger(){
	TextBox = TextBox + 1;
	    var nameID= document.getElementById('name');
	    var newDiv=document.createElement('div');
	    newDiv.setAttribute('id','strText'+TextBox);
	    newDiv.innerHTML ="<br>Enter Trigger or Action Name&nbsp;&nbsp;&nbsp;<input type='text' name='name" + TextBox + "'  placeholder='Name of the Trigger in Display'>";
	    nameID.appendChild(newDiv);
  		var contentID = document.getElementById('content1');
  		var newTBDiv = document.createElement('div');
  		newTBDiv.setAttribute('id','strText'+TextBox);
  		newTBDiv.innerHTML = "<br><br><input type='text' style='width:400px;margin-left:350px;' id='t" + TextBox + "'    name='t" + TextBox + "' placeholder='Enter the Trigger Method Url here'/>&nbsp;<a id='pa' href='javascript:addParent();'>Add Parameter</a><a id='pa' href='javascript:removeParent();'>Remove Parameter</a><br><br><br><div id='cont"+TextBox+"'></div><br>";
  		contentID.appendChild(newTBDiv);
  	
  	
}
function removeTrigger(){
	var contentID = document.getElementById('content1');
    contentID.removeChild(document.getElementById('strText'+TextBox));
    TextBox = TextBox-1;
}

function addField(){
	var contentID = document.getElementById('content2');
		var newTBDiv = document.createElement('div');
		newTBDiv.setAttribute('id','strText'+TextBox);
		newTBDiv.innerHTML = "<input type='text' id='f" + intTextBox + "'    name='f" + intTextBox + "' placeholder='Field_Label'/>";
		contentID.appendChild(newTBDiv);
}
function removeField()
{
	var contentID = document.getElementById('cont'+TextBox);
    contentID.removeChild(document.getElementById('strText'+intTextBox));
    intTextBox = intTextBox-1;
}


function addParent(){
	intTextBox = intTextBox + 1;
	var contentID = document.getElementById("cont"+intTextBox);
	var newTBDiv = document.createElement('div');
	newTBDiv.setAttribute('id','strText'+intTextBox);
   	newTBDiv.innerHTML = "<input type='text' id='p" + intTextBox + "'    name='p" + intTextBox + "' placeholder='Param_Label'/>" + "<input type='text' id='pv"+ intTextBox + " ' name='pv"+intTextBox +"' placeholder='Param_Value'/><br>";
	contentID.appendChild(newTBDiv);
}
function removeParent()
{
	var contentID = document.getElementById('cont'+intTextBox);
    contentID.removeChild(document.getElementById('strText'+intTextBox));
    intTextBox = intTextBox-1;
}

$(document).ready(function(){
	$('#rformat').on('change', function() {

	var target = $('#rformat option:selected').val();
	if(target == "json"){
		$('#bjson').show();
	}
	
	});

});

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
#tit{
font-family:verdana;
font-size:20px;
font-weight:bold;
margin-left:70px;
color:#FFFFFF;
font-weight:bold;
}
input[type='text']{
width:350px;
height:30px;
margin-left:100px;
font-family:verdana; 
font-weight:bold;
font-size:15px;
color:#FF9900;
}
#name,#jsnote{
font-family:verdana;
font-size:18px;
margin-left:100px;
color:#FFFFFF;
}
input[type='password']{
width:400px;
height:30px;
margin-left:100px;
font-family:verdana; 
font-weight:bold;
font-size:15px;
color:#FF9900;
}
input[type="submit"]{

color:#FFFFFF;
font-size:15px;
background-color:#FF9900;
margin-left:250px;
font-family:verdana;
width:110px;
height:40px;
border:solid 2px;
border-color:#FFFFFF;
border-radius:50px;
padding:10px;
}
input[type="radio"]
 {
   width:20px; 
   height: 20px;
   color:#FFFFFF;
   font-size:20px;
   font-family:verdana;
   margin-left:60px;
 }
 label{
font-family:verdana;
font-size: 16px;
color:#FFFFFF;
margin-left:30px;
}
#txt1,#js{
font-family:verdana;
font-size:15px;
margin-left:200px;
color:#FF9900;
font-weight:bold;
width:600px;
height:120px;
}
#pa{
  font-family:verdana;
  font-size:15px;
  color:#FFFFFF;
  margin-left:100px;
  }
select{
font-family:verdana;
font-size:17px;
color:#FF9900;
font-weight:bold;
width:350px;
height:50px;
}
#trig,#field{
font-family:verdana;
  font-size:18px;
  color:#FFFFFF;
  margin-left:100px;
}
</style>
</head>
<body>
<form action="Trigger" method="post">
<br><br><center><div id="head">Mind-Bridge</div></center>
<br><br><div id="tit">2.Choose the Trigger or Action Fields</div><br>
<br><div id="name"></div>
<br><br><a id='trig' href="javascript:addTrigger();">Add Trigger or Action Method</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="trig" href="javascript:removeTrigger();">Remove Trigger or Action Method</a></center></br></br>
<br><div id="content1"></div><br>



<br><div id="name">Request Method</div>
<br><select name="rmethod" style="margin-left:250px;">
<option>Choose the Request Method</option>
<option value="Get">Get</option>
<option value="Post">Post</option>
<option value="Put">Put</option>
<option value="Delete">Delete</option>
</select><br><br>
<br><div id="name">Request and Response Format</div>
<br><select name="rformat" id="rformat" style="margin-left:250px;">
<option>Choose the Request Format</option>
<option value="rest">REST</option>
<option value="xml">XML</option>
<option value="json">JSON</option>
</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<select name="resformat">
<option>Choose the Response Format</option>
<option value="rest">REST</option>
<option value="xml">XML</option>
<option value="json">JSON</option>
</select><br><br>
<div id="bjson" style="display:none">
<div id="jsnote">Build Sample JSON Structure.Please enclose variables with @@dummy_variable@@ in JSON Structure</div><br><br>
<textarea id="js" name="js" placeholder="@@Sample JSON Structure@@"></textarea><br><br>
<a id="field" href="javascript:addField();">Add JSON Field Label</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="trig" href="javascript:removeField();">Remove Field</a><br><br>
<div id="content2"></div>
</div><br><br>
<div id="name">Notes:</div><br>
<textarea name="note" id="txt1"  placeholder="Add notes for guide the users" required></textarea>
<br><br><br>
<input type="submit" name="new" value="Add New">
<input type="submit" name="finish" value="Finish">
</form>
</body>
</html>