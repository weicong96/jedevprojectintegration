<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="database.DAO,bean.Updates"%>
<%
	String username = (String) session.getAttribute("username");
	DAO dbao = new DAO();
	//Updates[] update = dbao.retrieveIndividualUpdates(username, );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/body.css" rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div#body {
	text-align: left;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
</style>
<title>Messages</title>
</head>


<body>
	<div id='body'>
		<jsp:include page="includes/nav.jsp" />
		<form action="" method="POST">
			<br>

			<h1 align="center">Messages</h1>

			<table align="center" style="border-color: black;">
				<tr>
					<td><b>From: </b>
					</td>
					<td>(sender)</td>
				</tr>
				<tr>
					<td><b>Title: </b>
					</td>
					<td>(title)</td>
				</tr>
				<tr>
					<td><b>Date: </b>
					</td>
					<td>(date)</td>
				</tr>
				<tr>
					<td><br>
					</td>
				</tr>
				<tr>
					<td><b>Message: </b>
					</td>
					<td>(message: Hi, we have new updates of the website! Do take
						a look on the new features provided.)</td>
				<tr>
					<td><br>
					</td>
				</tr>
				<tr>
					<td><br>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="back">
					</td>
				</tr>
			</table>
		</form>
		<br> <br>
	</div>
	<jsp:include page="includes/footer.html" />

</body>
</html>