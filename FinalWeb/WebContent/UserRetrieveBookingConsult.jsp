<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="model.BookingJPA" %>
<%@page import="database.DBAO"%>
<%@page import="bean.Group" %>
<%
	String username = (String)session.getAttribute("username");
DBAO dbao = new DBAO();
Group group = dbao.retrieveGroup(username);

String date = (String)session.getAttribute("date");
if(date==null){
	date= " ";
}
String room = (String)session.getAttribute("room");
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
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  		<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  		<link rel="stylesheet" href="/resources/demos/style.css">
  		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
 <script>
  $(function() {
	  			$( ".datepicker" ).datepicker({
				      changeMonth: true,
				      changeYear: true,
				      yearRange: "2014:2016",
				      dateFormat: "dd-mm-yy",
				      minDate: "dateToday",
				      beforeShowDay: function(date) { 
        					var day = date.getDay();
        					return [(day > 2 && day < 4), ''];}
				       
	    });
});  
  </script>


</head>
<%List<BookingJPA> booklist = (List<BookingJPA>)request.getAttribute("booklist");%>
<%ArrayList<BookingJPA> bookingList = (ArrayList<BookingJPA>)request.getAttribute("bookingList");%>
<body>
	<jsp:include page="includes/nav.jsp"/>
<form action="UserRetrieveBookingServlet" method="GET">
<div id='body'>
<table align="center">
<tr><th style="width:900px">Welcome, <%=group.getTeamName()%></th><td><a href="login.jsp"><input type="button" value="Logout"></a></td></tr>
</table>

<table align="center">
<tr><td align="center"><h1>Avaliable Consultation Slots</h1></td></tr>
</table>

<table align="center">
<tr><td>Choose a date: </td><td><input type="text" name="datepicker" class="datepicker" value="<%=date%>"></td></tr>
<%String[]items = {"L532","L534"};%>
<tr><td>Choose a room: </td><td><select name="roomNo"><%for(int i=0; i<items.length; i++){%>
<option value="<%= items[i] %>" <%= items[i].equals(request.getParameter("roomNo")!= null ? request.getParameter("roomNo") : "" ) ? "selected" : "" %>><%= items[i]%></option>
<% } %></select></td></tr>
</table>
<br/>
<table align="center">
<tr><td><input type="submit"></td></tr>
</table>
</form>

<form action="BookSlotServlet" method="POST">
<br/>
<table border="0" align="center">
<tr><th style="width:80px"></th><th style="width:120px">Room No</th><th style="width:120px">Book Date</th><th style="width:120px">Start Time</th><th style="width:120px">Group Coming</th><th style="width:120px">Teacher on Duty</th><th></th></tr>
<%if(request.getParameter("datepicker")==null && request.getParameter("roomNo")==null){%>
<%for (int i=0; i<bookingList.size(); i++){%>
<tr><td></td><td><%=bookingList.get(i).getBookVenue()%></td><td><%=bookingList.get(i).getBookDate()%></td><td><%=bookingList.get(i).getBookTime()%></td>
		<td><%if(bookingList.get(i).getFk_groupCode()==null){%>No Group
		<%}else{%>
		<%=booklist.get(i).getFk_groupCode()%>
		<%} %></td>

<td><%=bookingList.get(i).getFk_staffID()%></td><td>
<%}}else if(booklist.size()==0){%>
<table align="center">
<tr><td><h4>There are no consultations slots avaliable at the moment!</h4></td></tr>
</table>
<% } else{%>
<%for (int i=0; i<booklist.size(); i++){%><tr><td><input type="checkbox" name="idforBookSlot" value="<%=booklist.get(i).getIdbooking()%>"></td><td><%=booklist.get(i).getBookVenue()%></td><td><%=booklist.get(i).getBookDate()%></td><td><%=booklist.get(i).getBookTime()%></td>

<td><%if(booklist.get(i).getFk_groupCode()==null){%>No Group
<%}else{%>
<%=booklist.get(i).getFk_groupCode()%>
<%} %></td>

<td><%=booklist.get(i).getFk_staffID()%></td></tr>
<%}}%>
</table>
<br>
<table align="center">
<tr><td><%if(booklist.size()==0){ %>
<input type="submit" value="Book Slot" hidden>
<%}
else{ %>
<input type="submit" name="BS" value="Book Slot">
<%}%></td></tr>
</table>
*Each Session is 30 min long.
</div>
</form>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>