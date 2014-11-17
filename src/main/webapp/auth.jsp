<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/jquery-latest.js"></script>
<title>Mind Connectors</title>
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript">
var intTextBox=0;
function addParent(){
	  intTextBox = intTextBox + 1;
	  var contentID = document.getElementById('content');
	  var newTBDiv = document.createElement('div');
	  newTBDiv.setAttribute('id','strText'+intTextBox);
	  newTBDiv.innerHTML = "<input type='text' id='h" + intTextBox + "'    name='h" + intTextBox + "' placeholder='Header_Label'/>" + "<input type='text' id='hv"+ intTextBox + " ' name='hv"+intTextBox+"' placeholder='Header_Value'/>";
	  contentID.appendChild(newTBDiv);
}
function removeParent()
{
	var contentID = document.getElementById('content');
    contentID.removeChild(document.getElementById('strText'+intTextBox));
    intTextBox = intTextBox-1;
}	

function addHead()
{
  intTextBox = intTextBox + 1;
  var contentID = document.getElementById('content2');
  var newTBDiv = document.createElement('div');
  newTBDiv.setAttribute('id','strText'+intTextBox);
  newTBDiv.innerHTML = "<input type='text' id='sh" + intTextBox + "'    name='sh" + intTextBox + "' placeholder='Header_Label'/>" + "<input type='text' id='shv"+ intTextBox + " ' name='shv"+intTextBox+"' placeholder='Header_Value'/>";
  contentID.appendChild(newTBDiv);
}

function removeHead(){
	var contentID = document.getElementById('content2');
    contentID.removeChild(document.getElementById('strText'+intTextBox));
    intTextBox = intTextBox-1;
}

$(document).ready(function(){
	$('input[name=authen]').click(function(){
	   	if($('input:radio[name=authen]:checked').val() == "No Auth"){
			$('#req').show();
		  	$('#divid').hide();
		  	$('#apiid').hide();			
		  	$('#oauth2').hide();
	  		$('#oauth1').hide();
		}
	    else if($('input:radio[name=authen]:checked').val() == "Basic Auth"){
	 		$('#divid').show();
		  	$('#apiid').hide();
		  	$('#oauth2').hide();
			$('#req').hide();
			$('#oauth1').hide();
	   	}
		else if($('input:radio[name=authen]:checked').val() == "API keys"){
			$('#apiid').show();
			$('#divid').hide();
			$('#req').hide();
			$('#oauth2').hide();
			$('#oauth1').hide();
		}
		else if($('input:radio[name=authen]:checked').val() == "Oauth1"){
			$('#apiid').hide();
			$('#divid').hide();
			$('#req').hide();			
			$('#oauth2').hide();
			$('#oauth1').show();
		}
		else if($('input:radio[name=authen]:checked').val() == "Oauth2"){
		 	$('#apiid').hide();
			$('#divid').hide();
			$('#req').hide();
			$('#oauth1').hide();
			$('#oauth2').show();
			$('.rmethod').show();
			$('#select2').show();	
		}
		else if($('input:radio[name=authen]:checked').val() == "Signed Auth"){
		 	$('#sign').show();	
		}
	});
	
	 $('#timestamp').click(function () {
		    $("#stamp").toggle(this.checked);
		});
	 
	 $('#signonce').click(function () {
		    $("#nonce").toggle(this.checked);
		});
			$('#sign1').click(function(){
				$('#sign').show();
				$('#sign1').hide();
			});
			
			 $('#http').click(function () {
				    $("#suname").toggle(this.checked);
				    $("#spwd").toggle(this.checked);
		            $("#shead").toggle(this.checked);
		            $("#shead1").toggle(this.checked);

				});
			 $('#tformat').change(function() {


			if( $("#tformat").val() == "Unix"){
				$('#second').show();
				$('#utc').hide();
			}
			else if($("#tformat").val() == "UTC"){
				$('#utc').show();
				$('#second').hide();
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
.rmethod{
font-family:verdana;
font-size:18px;
margin-left:70px;
color:#FFFFFF;
font-weight:bold;

}
input[type='text']{
width:400px;
height:30px;
margin-left:100px;
font-family:verdana; 
font-weight:bold;
font-size:15px;
color:#FF9900;
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
margin-left:600px;
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
#req{
color:#FFFFFF;
font-size:14px;
font-family:verdana;
margin-left:100px;
}
#def{
color:#FFFFFF;
font-size:10px;
font-family:verdana;
}
#others{color:#FFFFFF;
font-size:15px;
font-family:verdana;
margin-left:100px;}

#pa{
  font-family:verdana;
  font-size:15px;
  color:#FFFFFF;
  margin-left:100px;
  }
 .redirect{
 font-family:verdana;
 font-size:16px;
 color:#FFFFFF;
 } 
  select{color:#FF9900;
font-size:15px;
background-color:#FFFFFF;
margin-left:100px;
font-family:verdana;
width:200px;
height:40px;
padding:10px;}

#txt1,#txt2,#txt3,#txt4{
font-family:verdana;
font-size:15px;
margin-left:100px;
color:#FF9900;
font-weight:bold;
width:300px;
height:100px;

}

#desc{
font-family:verdana;
font-size:15px;
font-weight:bold;
margin-left:100px;
color:#FFFFFF;
}

#sign{
font-family:verdana;
font-size:18px;
color:#FFFFFF;
margin-left:100px;
}
#stamp{
margin-left:90px;
font-size:18px;
color:#FFFFFF;
}
#nonce{
margin-left:90px;
font-size:18px;
color:#FFFFFF;
}

#sigtext{
margin-left:100px;
font-size:18px;
font-family:verdana;
width:350px;
height:100px;
color:#FF9900;
padding:10px;
}


#sign1{
color:#FFFFFF;
font-size:20px;
font-family:verdana;
margin-left:100px;
}
#shead,#shead1{
color:#FFFFFF;
font-size:20px;
font-family:verdana;
margin-left:100px;

}

#ba{
color:#FFFFFF;
font-size:15px;
font-family:verdana;
margin-left:100px;
}
#ba1{
color:#FFFFFF;
font-size:18px;
font-family:verdana;
margin-left:100px;

}

</style>
</head>
<body>
<form action="Auth" method="post">
<br><br><center><div id="head">Mind-Bridge</div></center>
<br><br><br><br><div id="tit">1.Choose Trigger Authentication Scheme</div><br><br>
<div id="inline_content">
<input type="radio" name="authen"  value="No Auth">
<label for="rd1">No Auth</label>
<input type="radio" name="authen"   value="Basic Auth">

<label for="rd1">Basic Auth</label>
<input type="radio" name="authen"   value="API keys" >

<label for="rd1">API keys</label>
<input type="radio" name="authen"  value="Oauth1">
<label for="rd1">OAuth1</label>

<input type="radio" name="authen"  value="Oauth2">
<label for="rd1">OAuth2</label>

<input type="radio" name="authen"  value="Signed Auth">
<label for="rd1">Signed Auth</label>

<br></div><div id="req" style="display:none">Fine! Go ahead</div>

<div id="divid" style="display:none"><br>
<br><br><div id="desc">Add Description</div><br><br>
<textarea name="txt1" id="txt1" placeholder="Description About Auth"></textarea>
<textarea name="txt2" id="txt2" placeholder="Description about App"></textarea><br><br>
<br><div id="others">If Http headers, Please leave username & password fields and add your credentials in header</div>
<br><br><input type="text" name="b1" value="" placeholder="UserName_Label" >
<input type="text" name="b2" value="" placeholder="UserName_Value" >
<br><br><input type="password" name="b3" value="" placeholder="Password_Label">
<input type="password" name="b4" value="" placeholder="Password_Value"><br><br><br>
<a id='pa' href="javascript:addParent();">Add Header</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id='pa' href="javascript:removeParent();">Remove Header</a></center><br><br>
<br><div id="content"></div><br>
</div>


<div id="apiid" style="display:none"><br>
<div id="desc">Add Description</div><br><br>
<textarea name="txt3" id="txt3" placeholder="Description About Auth"></textarea>
<textarea name="txt4" id="txt4" placeholder="Description about App"></textarea><br><br>
<br><br><input type="text" name="a1" value="" placeholder=" APIkey_Label" >
<input type="text" name="a2" value="" placeholder=" API_Key" ><br/><br/>
</div>
<br><br><div id="oauth1" style="display:none"><br>
<center><div class='redirect'>*Put Redirect Url As <b><u>https://bridge-minddotss.rhcloud.com/OauthCall</u></b> in Your App</div></center><br>
<input type="text" name="ockey" value="" placeholder=" Oauth_consumer_key">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="oskey" value="" placeholder=" Oauth_secret_key"><br><br>
<input type="text" name="ourl1" value="" placeholder=" Request_token_Url">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="ourl2" value="" placeholder=" Authorization_Url"><br><br>
<input type="text" name="ourl3" value="" placeholder=" Access_Token_Url">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="osmeth" value="HMAC-SHA1" placeholder="Signature_Method"><br><br>
<select name="oreq">
<option value="dummy"> Request Method</option>
    <option value="GET"> GET</option>
    <option value="POST"> POST</option>
    <option value="PUT"> PUT</option>
    <option value="DELETE"> DELETE</option>
</select><br/>
</div>
<br><div id="oauth2" style="display:none"><br>
<center><div class='redirect'>*Put Redirect Url As <b><u>https://bridge-minddotss.rhcloud.com/OauthCall</u></b> in Your App</div></center><br>
<input type="text" name="ckey" value="" placeholder=" Client_ID_KEY">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="cseckey" value="" placeholder=" Client_Secret_Key"><br><br>
<input type="text" name="sname" value="" placeholder=" Scope_Label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="svalue" value="" placeholder=" Scope_Value"><br><br>
<input type="text" name="aurl" value="" placeholder=" Authorization_URL">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="tokenurl" value="" placeholder=" Access_Token_URL"><br><br>
<input type="text" name="tlabel" value="" placeholder=" Access_Token_Label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br><br>
<div class="rmethod">Token Replacement</div><br>
<input type="radio" name="treplace"  value="Authorization:Bearer">
<label for="rd2">Authorization:Bearer</label>
<input type="radio" name="treplace"   value="Authorization:header">
<label for="rd2">Authorization:header</label>
<input type="radio" name="treplace"   value="Authorization:User&Pwd" >
<label for="rd2">Authorization:User&Pwd</label>
<input type="radio" name="treplace"   value="QueryString" >
<label for="rd2">Query String</label>
<br><br>
<center><div id="def">* Tokens placed in headers will look like Authorization: Bearer <token> and tokens in querystrings will look like ?access_token=token</div></center><br>
<input type="text" name="el" value="" placeholder="Extra_Field_Label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="ev" value="" placeholder="Extra_Field_Value">

<br/>
<div class="rmethod">Access Token Method</div><br>
<select name="select2"  id="select2" onChange="change()" style="display:none">
    <option value="GET">GET</option>
    <option value="POST">POST</option>
    <option value="PUT">PUT</option>
    <option value="DELETE">DELETE</option>
</select><br/>
</div>

<br><div id='sign' style="display:none;">Select a Message Digest Algorithm<br><br>
<select name="sig" id="sig">
<option value="HMAC-SHA1">HMAC-SHA1</option>
<option value="HMAC-SHA256">HMAC-SHA256</option>
<option value="MD5">MD5</option>
</select><br><br>
<input type="text" name="sigckey" value="" placeholder="Signed_consumer_key">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="sigskey" value="" placeholder="Signed_secret_key"><br><br>
<div id="ba1">Signature Format</div><br>
<select name="sformat" id="sformat">
<option value="URL-Encoded">URL-Encoded</option>
<option value="HexaDecimal">HexaDecimal</option>
</select><br><br>
<div id="ba1">Timestamp Format</div><br>
<select name="tformat" id="tformat">
<option value="Unix">Unix</option>
<option value="UTC">UTC</option>
</select><br><br>
<input type="text" name="second" id="second" placeholder="Add Seconds to Unix timestamp (optional)" style="display:none"><br>
<input type="text" name="utc" id="utc" placeholder="Enter the UTC Format" style="display:none"><br><br>
<div id="ba">Dynamic param values (i.e timestamp,nonce), mention like @@timestamp@@,@@nonce@@ in below text area</div><br><br>
<textarea id="sigtext" name="sigtext" placeholder="Enter the Message here"></textarea><br><br>
<input type="checkbox" name="http" id="http" value="basic">Send signature Http headers<br><br>
<a id='shead' href="javascript:addHead();" style="display:none">Add Header</a>&nbsp;&nbsp;&nbsp;&nbsp;<a id='shead1' href="javascript:removeHead();" style="display:none">Remove Header</a><br><br>
<div id="content2"></div>
</div><br>

</div>



<input type="submit" name="submit" value="Continue">
</form>
</body>
</html>
