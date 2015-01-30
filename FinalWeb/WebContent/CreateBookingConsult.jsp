<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String username = (String)session.getAttribute("username");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<head>
		<title>Booking for Consultation</title>
<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
<link href="css/main320_480.css" rel="stylesheet" type="text/css"/>		
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" href="timePicker/jquery.timepicker.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script type="text/javascript" src="timePicker/jquery.timepicker.min.js"></script>
<script type="text/javascript" src="timePicker/jquery.timepicker.js"></script>

<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
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
	    	$('#durationExample').timepicker({
    'minTime': '1:00pm',
    'maxTime': '4.30pm',
    'showDuration': false
});
	  });
  
  </script>
</head>

<body>
	<jsp:include page="includes/nav.jsp"/>
<form action="CreateBookingServlet" method="POST">
<div id='body'>
Welcome,<%=username %>
<table align="center">
<tr><td align="center"><h1>Creating Booking Consultation</h1></td></tr>
</table>
<br>
<table border="0" align="center">
<tr><th>Room No :</th><td><select style="width:100%" name="roomNo"><option value="L532">L532</option><option value="L534">L534</option></select></td></tr>
<tr><th>Date Avaliable :</th><td><input class="datepicker" type="text" name="sDate" id="datepicker"></td></tr>
<tr><th>Time Avaliable :</th><td><input id="durationExample" type="text" name="timeSelection"></td></tr>
</table>
<br>
<table align="center">
<tr><td><input type="submit" id="demo" style="background-image:url('button/submit.png'); width:103px; height:45px; border-radius:6px" value=""/></td></tr>
</table>
<br>
</div>
</form>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>