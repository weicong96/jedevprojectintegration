<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="util.Links"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/body.css" rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Refresh" content="5;url=<%=Links.LINK_VIEWPROFILE %>">
<style>
div#body {
	text-align: left;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
</style>
<title>Edit Profile Page</title>
</head>


<body>
	<div id='body'>
		<jsp:include page="includes/nav.jsp" />
		Updates failed.
		You will be redirected back the the Profile Page shortly.
		Click <a herf= "<%=Links.LINK_VIEWPROFILE %>">here</a> if not directed.
	</div>
	<jsp:include page="includes/footer.html" />

</body>
</html>