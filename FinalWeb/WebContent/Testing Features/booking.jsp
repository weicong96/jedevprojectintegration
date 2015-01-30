<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker")
    .datepicker({
      showOn: "button",
      buttonImage: "images/calendar.gif",
      buttonImageOnly: true,
      buttonText: "Select date"
    });

$(document).ready(function() {
   $("input[type='button']").click(function(){
      $(this).css('background-color','red');
    });
});
  
function change( el )
{
    if ( el.value === "Avaliable" )
        el.value = "Not Avaliable";
    else
        el.value = "Avaliable";
}
  </script>
</head>
<body>
<h1>Book for Consultation</h1>
Date: <input type="text" id="datepicker" id="yearRange"><br><br>Group Name: XXXX<br><br>
<table border="2">
<tr><td>Start Time</td><td>End Time</td><td>L630</td><td>L531</td></tr>
<tr><td>1.00PM</td><td>1.30PM</td><td><input onclick="return change(this);" type="button" value="Avaliable" style="width: 130px; "></td><td><input onclick="return change(this);" type="button" value="Avaliable" style="width: 130px; "></td></tr>
<tr><td>1.30PM</td><td>2.00PM</td><td><input onclick="return change(this);" type="button" value="Avaliable" style="width: 130px; "></td><td><input onclick="return change(this);" type="button" value="Avaliable" style="width: 130px; "></td></tr>
<tr><td>2.00PM</td><td>2.30PM</td><td><input onclick="return change(this);" type="button" value="Avaliable" style="width: 130px; "></td><td><input onclick="return change(this);" type="button" value="Avaliable" style="width: 130px; "></td></tr>
<tr><td>2.30PM</td><td>3.00PM</td><td><input onclick="return change(this);" type="button" value="Avaliable" style="width: 130px; "></td><td><input onclick="return change(this);" type="button" value="Avaliable" style="width: 130px; "></td></tr>
</table>
</body>
</html>