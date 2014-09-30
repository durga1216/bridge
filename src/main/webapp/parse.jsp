<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Parse</title>
</head>
<body>
<%
String url="https://@@minddots@@.cloudant.com/@@crud@@/sbfhjfb/@@mmm@@";
//String url="https://fasfasdf/sfdasdf";
String[] slt=url.split("@@");
int nn=slt.length;String orurl="";
if(!(nn==0)){
String n[]={"mind","data","sfdasfasdfasdf"};
int mm=n.length;
for(int i=1,j=0;i<nn;i=i+2,j++){
	slt[i]="<input type='text'>";
}
for(int k=0;k<nn;k++){
	orurl=orurl+slt[k];
}
}else
{
	orurl=url;
	}
out.println(orurl);
%>
</body>
</html>