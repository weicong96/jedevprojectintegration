<%@page import="bean.Feedback"%>
<%@page import="database.DBAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String username = (String)session.getAttribute("username");
DBAO dbao = new DBAO();
Feedback [] feedback = dbao.retrieveAllFeedback();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script>
function myConfirm() {
    var x;
    if (confirm("Are you sure you want to delete all records?") == true) {
       
    } else {
        
    }
}
</script>
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
<title>Retrieve Feedback</title>
</head>

<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<form action="DeleteOneFeedbackServlet" method="POST">
<br>
Welcome,<%=username %>
<table align="center">
<tr><td align="center"><h1>Retrieve Feedback</h1></td></tr>
</table>
<table align="center">
<tr><td></td><td width="160px"><b>Team Code</b></td><td width="250px"><b>1. Do you like the Website design?</b></td><td width="250px"><b>2. Is the Website user friendly?</b></td><td width="240px"><b>3. Which is your favorite feature?</b></td><td><b>Comments</b></td></tr>
<%if(feedback.length==0){%>
<table align="center">
<tr><td><h4>There is no feedback at the moment.</h4></td></tr>
</table>
<% } else{%>
<%for(int i=0; i<feedback.length; i++){ %><tr><td><input name="checkboxS" type="checkbox" value=<%=feedback[i].getIdfeedback()%>></td><td><%=feedback[i].getFk_groupCode()%></td><td><%=feedback[i].getDesignQns()%></td><td><%=feedback[i].getFriendlyQns()%></td><td><%=feedback[i].getFavourFeatures()%></td><td><%=feedback[i].getComments()%></td></tr>
<tr><td></td></tr>
<%} }
%></table>
<br>
<%if(feedback.length==0){ %>
<input type="submit" value="Delete Selected" hidden>
<%}
else{ %>
<input type="submit" value="Delete Selected">
<input type="submit" name="DeleteAll" value="Delete All Feedback">
<%}%>
</form>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>