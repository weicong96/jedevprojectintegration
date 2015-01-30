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
<title>Registration</title>
</head>


<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<table align="center">
<tr><td align="center"><h1>Registration</h1></td></tr>
<tr><th>Step 1 : Selection of taking a group photo or upload a group photo</th></tr>
</table>
<br>
<table align="center">
<tr><td><a href="registrationP1.jsp"><input type="image" src="button/TakeGP.png"></a></td><td><a href="registrationP1UP.jsp"><input type="image" src="button/uploadGP.png"></a></td>
</table>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>