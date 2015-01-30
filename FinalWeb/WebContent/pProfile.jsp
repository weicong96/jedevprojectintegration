<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="database.DBAO" %>
<%@page import="bean.Group" %>
<%
	String username = (String)session.getAttribute("username");
DBAO dbao = new DBAO();
Group group = dbao.retrieveGroup(username);
%>
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
<tr><th style="width:900px">Welcome, <%=group.getTeamName()%></th><td><a href="login.jsp"><input type="button" value="Logout"></a></td></tr>
</table>

<h1 align="center">Profile Page</h1>
<table align="center">
<tr>
<td style="padding:10px"><a href="YourSubmissions"><input type="button" value="Add Submission items"/></a></td>

<td style="padding:10px"><a href="UserRetrieveBookingServlet"><input type="button" value="Book Consultation Slots"/></a></td>
	<td style="padding:10px"><a href="UserRetrievedBookedSlotsServlet"><input type="button" value="Retrieve Booking Slots"></a></td>
	<td style="padding:10px"><form action="CheckFeedbackServlet" method="POST"><input type="submit" value="Make Feedback"/></form></td>
	<td style="padding:10px"><a href="UserSendMessageToTeacherServlet"><input type="button" value="Send Messages"/></a></td>
	<td style="padding:10px"><a href="UserRetrieveMessagesP1.jsp"><input type="button" value="View Conversation"/></a></td>
	<td style="padding:10px"><a href="UserRetrieveStaff.jsp"><input type="button" value="Staff Details"/></a></td>
	<td style="padding:10px"><a href="game.jsp"><input type="button" value="Games"/></a></td></tr>
</table>

<table align="center">
<tr><td><b>Profile Picture</b></td></tr>
<tr><td><img src="profilePic/<%=group.getTeamPic()%>" width="250" height="200"></td></tr>
<tr><td style="width:260px"><input type="button" value="Change Profile Picture"></td><td><input type="button" value="Change Group Details"></td></tr>
</table>
</div>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>