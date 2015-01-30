<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="database.DAO,bean.Updates"%>
<%@ page import="util.Links" %>
<%
	String username = (String) session.getAttribute("username");
	DAO dbao = new DAO();
	Updates[] update = dbao.retrieveStaffSentUpdates(username);
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
		<form action="sendUpdatesAndNews.jsp" method="POST">
			<br>

			<h1 align="center">Messages</h1>

			<table align="center" style="border-color: black;">

				<tr style="height: 30px">
					<td><a href="login.jsp" style="font-size: 15px; float: left;"><input
							type="button" value="Back">
					</a>
					</td>
					<td colspan="10" align="right"><input type="submit"
						value="New Message">
					</td>
				</tr>

				<tr style="height: 30px">
					<td style="width: 200px"><b>Sender</b></td>
					<td></td>
					<td style="width: 200px"><b>Recipient</b></td>
					<td></td>
					<td style="width: 200px"><b>Title</b></td>
					<td></td>
					<td style="width: 120px"><b>Date</b></td>
				</tr>
				<%
					for (int i = 0; i < update.length; i++) {
				%>
				<tr style="height: 30px">
					<td><a href="indiMessage.jsp"
						style="font-size: 15px; float: left;"><%=update[i].getSender()%></a></td>
					<td></td>
					<td><a href="indiMessage.jsp"
						style="font-size: 15px; float: left;"><%=update[i].getRecipient()%></a></td>
					<td></td>
					<td><a href="indiMessage.jsp"
						style="font-size: 15px; float: left;"><%=update[i].getTitle()%></a>
					</td>
					<td></td>
					<td><a href="indiMessage.jsp"
						style="font-size: 15px; float: left;"><%=update[i].getDate()%></a></td>
				</tr>
				<%
					}
				%>
				<tr style="height: 30px">
					<td><br /></td>
				</tr>
				<tr style="height: 30px">
					<td></td>
				</tr>
			</table>
		</form>
		<br> <br>
	</div>
	<jsp:include page="includes/footer.html" />

</body>
</html>