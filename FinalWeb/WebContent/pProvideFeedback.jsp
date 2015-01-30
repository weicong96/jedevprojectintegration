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
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
<title>Feedback Portal</title>
</head>


<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
Welcome, <%=group.getTeamName()%>
<table align="center">
<tr><td align="center"><h1>Provide Feedback</h1></td></tr>
<tr><th>Step 1 : Complete the feedback form</th></tr>
</table>
<br>
<form action="FeedbackServlet" method="POST">
<table align="center">
<tr><th width="300px">Question</th><th>Ranking</th></tr>
<tr><td>Do you like the Website design?</td><td><select style="width:100%" name="design"><option value="StronglyAgree">1-Strongly Agree</option><option value="Agree">2-Agree</option><option value="Disagree">3-Disagree</option><option value="StronglyAgree">4-Strongly Disagree</option></select></td></tr>
<tr><td>Is the Website user friendly?</td><td><select style="width:100%" name="friendly"><option value="StronglyAgree">1-Strongly Agree</option><option value="Agree">2-Agree</option><option value="Disagree">3-Disagree</option><option value="StronglyAgree">4-Strongly Disagree</option></select></td></tr>
<tr><td>Which is your favorite feature?</td><td><select style="width:100%" name="favourFea"><option value="Registration">Registration</option><option value="Messages">Messages</option><option value="Profile">Profile</option><option value="Booking">Booking</option><option value="Submissions">Submissions</option><option value="Games">Games</option></select></td></tr>
<tr><td>Any other comments ?</td><td><textarea style="resize: none" name="comments" required></textarea></td></tr>
</table>
<br>
<table align="center">
<tr><td><input type="submit" id="demo" style="background-image:url('button/submit.png'); width:103px; height:45px; border-radius:6px" value=""/></td></tr>
</table>
</form>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
</body>
</html>