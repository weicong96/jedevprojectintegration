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
<title>Reset Password</title>
</head>

<body>
<form action="ResetPasswordServlet" method="GET">
	<jsp:include page="includes/nav.jsp"/>
<div id='body'>
<table align="center">
<tr><td align="center"><h1>Reset Password</h1></td></tr>
</table>

<table align="center">
<tr><td>Please key in your username:</td><td><input type="text" name="username"></td></tr>
</table>
<br>
</div>

<table align="center">
<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>