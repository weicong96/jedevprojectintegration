<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>logout</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%
 request.getSession(true).removeAttribute("username");
 request.getSession(true).removeAttribute("teamCode");
 request.getSession(true).removeAttribute("teamName");
		
	response.sendRedirect("login.jsp");
	 %>
</body>
</html>