<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<link href="css/main320_480.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
<title>Login Page</title>
</head>

<body>
	<jsp:include page="includes/nav.jsp"/>
<div id='body'>
<table align="center">
<tr><td align="center"><h1>Login Page</h1></td></tr>
</table>
<form action="LoginServlet<%= request.getParameter("page") != null ? "?page="+request.getParameter("page") : "" %>" method="POST">
<table align="center">
<tr><td>Username:</td><td><input type="text" name="username" required></td></tr>
<tr><td>Password:</td><td><input type="password" name="password" required></td></tr>
<tr><td></td><td align="right"><a href="PublicResetPasswordP1.jsp">Forget Password?</a></td></tr>
</table>
<br>
<table align="center">
<tr><td><input type="submit" value="Login"/></td></tr>
</table>
</form>
<br>
</div>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>