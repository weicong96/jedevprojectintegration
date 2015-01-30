<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="model.BookingJPA" %>
<%@page import="database.DBAO"%>
<%@page import="bean.Group" %>
<%
	String username = (String)session.getAttribute("username");
DBAO dbao = new DBAO();
Group group = dbao.retrieveGroup(username);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Avaliable Consultation Slots</title>
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
</head>

<%List<BookingJPA> bookingList = (List<BookingJPA>)request.getAttribute("bookingList");%>
<body>
	<jsp:include page="includes/nav.jsp"/>
<div id='body'>

<table align="center">
<tr><th style="width:900px">Welcome, <%=group.getTeamName()%></th><td><a href="login.jsp"><input type="button" value="Logout"></a></td></tr>
</table>

<table align="center">
<tr><td align="center"><h1>Your Booked Consultation Slots</h1></td></tr>
</table>

<form action="CancelBookedSlotsServlet" method="POST">
<table border="0" align="center">
<tr><th style="width:80px"></th><th style="width:120px">Room No</th><th style="width:120px">Book Date</th><th style="width:120px">Start Time</th><th style="width:120px">Teacher on Duty</th><th></th></tr>
<%if(bookingList.size()==0){%>
<table align="center">
<tr><td><h4>You did not booked any consultation slots.</h4></td></tr>
</table>
<%} else{ %>
<%for (int i=0; i<bookingList.size(); i++){%>
<tr><td><input type="checkbox" name="bookID" value="<%=bookingList.get(i).getIdbooking()%>"></td><td><%=bookingList.get(i).getBookVenue()%></td><td><%=bookingList.get(i).getBookDate()%></td><td><%=bookingList.get(i).getBookTime()%></td>
		<td><%=bookingList.get(i).getFk_staffID()%></td></tr>
<%}}%>
</table>
<br>
<table align="center">
<tr><td><%if(bookingList.size()==0){ %>
<input type="submit" value="Delete Slot" hidden>
<%}
else{ %>
<input type="submit" name="DS" value="Delete Slot">
<%}%></td></tr>
</table>
*Each Session is 30 min long.
</div>
</form>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>