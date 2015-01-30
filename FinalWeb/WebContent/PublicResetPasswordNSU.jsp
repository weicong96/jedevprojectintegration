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
<title>No Such User!</title>
</head>

<body>
	<jsp:include page="includes/nav.jsp"/>
<div id='body'>
<table align="center">
<tr><td align="center"><h1>No Such User!</h1></td></tr>
</table>

<table align="center">
<tr><td>No such user found!</td></tr>
</table>
<br>
</div>

<table align="center">
<tr><td><a href="login.jsp"><input type="submit" value="Back to Login Page"></a></td></tr>
</table>
</form>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>