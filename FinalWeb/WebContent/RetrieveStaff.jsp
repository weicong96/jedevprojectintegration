<%@page import="bean.Staff"%>
<%@page import="database.DBAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String username = (String)session.getAttribute("username");
DBAO dbao = new DBAO();
Staff[] staff = dbao.retrieveAllStaff();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}

#schName{
	border:1px solid silver;
	border-radius : 0px;
}
</style>
<title>Staff Details</title>
</head>

<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<form action="StaffRegServlet" method="POST">
<br>
Welcome,<%=username %>
<table align="center">
<tr><td align="center"><h1>Staff Details</h1></td></tr>
</table>
<table align="center">
<tr><td width="160px"><b>Staff Number</b></td><td width="160px"><b>Staff Name</b></td><td width="150px"><b>Staff Type</b></td><td width="150px"><b>Contact No</b></td><td><b>Email Address</b></td></tr>
<%for(int i=0; i<staff.length; i++){ %><tr><td><%=staff[i].getIdstaff()%></td><td><%=staff[i].getStaffName()%></td><td><%=staff[i].getStaffType()%></td><td><%=staff[i].getStaffContactNo()%></td><td><%=staff[i].getStaffEmailNo()%></td></tr>
<%}
%></table>
</form>
<a href="ManageSubmissions"><input type="button" value="Manage Submissions"></a>
<a href="ExtendDeadline"><input type="button" value="Extend Deadlines"></a>
<a href="CreateStaff.jsp"><input type="button" value="Create Staff"></a>
<a href="UpdateStaff.jsp"><input type="button" value="Update Staff Details"></a>
<a href="CreateBookingConsult.jsp"><input type="button" value="Create Consultation Slots"></a>
<a href="RetrieveBookingServlet"><input type="button" value="Retrieve Consultation Slots"></a>
<a href="RetrieveFeedback.jsp"><input type="button" value="Retrieve Feedback"></a>
<a href="sendEmail.jsp"><input type="button" value="Send Email"></a>
<a href="TeacherSendMessageToUserServlet"><input type="button" value="Send Messages"></a>
<a href="TeacherRetrieveMessagesP1.jsp"><input type="button" value="View Messages"></a>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>