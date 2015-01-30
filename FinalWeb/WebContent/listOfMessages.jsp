<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Send Message</title>
</head>
<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<form>
<h1 align="center">Messages</h1>
<table align="center">
<tr align="center"><th>Sender</th><th>New Messages</th><th></th><th></th></tr>
<tr><td>Team Mochi</td><td>Hello !!!</td><td><input type="button" value="Send Messages"></td><td><input type="button" value="Delete Messages"></td></tr>
</table>
<br>
<br>
</div>
</form>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>