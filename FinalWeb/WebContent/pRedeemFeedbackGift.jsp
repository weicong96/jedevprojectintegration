<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="database.DBAO" %>
<%@page import="bean.Group" %>
<%
	String username = (String)session.getAttribute("username");
String prizeName = (String)session.getAttribute("prizeName");
DBAO dbao = new DBAO();
Group group = dbao.retrieveGroup(username);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
<title>Feedback Portal</title>
<script>
window.onload=function() { 
//create the numer 
var randomNumber = Math.rand() * (100 + 1); 

//make sure there are no decial places 
randomNumber = Math.floor(randomNumber); 

//now put it on the page 
var elm = document.getElementById("random"); 
elm.innerHTML=randomNumber+""; 
}
</script>
</head>


<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
Welcome, <%=group.getTeamName()%>
<table align="center">
<tr><td align="center"><h1>Thank You!</h1></td></tr>
<tr><th>You are given a free gift.</th></tr>
</table>
<table align="center">
<tr><td><img src="reedemGift/<%=prizeName%>"></td></tr>
<tr><td><div id="random"></div></td></tr>
</table>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
</body>
</html>