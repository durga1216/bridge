<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thread Test</title>
<script src="js/jquery-latest.js"></script>
<script type="text/javascript">
function call(){
	$.ajax({
		url:"Axtest",
		type:"Get",
		success: function(){
			alert("success");
		},
		error: function(){
			alert("error");
		}
	});
}
</script>
</head>
<body>
<input type="button" Onclick="call()" value=start>
</body>
</html>