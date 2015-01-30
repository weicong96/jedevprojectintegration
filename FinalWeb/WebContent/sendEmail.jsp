<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<link href="css/main320_480.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="scripts/jquery-1.11.1.min.js"></script> 
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
<title>Send Email</title>
<script>
function sendEmail(){
var recipient = $('#recipient').val();
var subject = $('#subject').val();
var content = $('#content').val();
			$.ajax({url:"http://localhost:9090/EmailTesting/EmailSendingServlet",
					data: {recipient:recipient,subject:subject,content:content},
					type: "POST",
					//dataType: "jsonp",
					success: function(email){
						if(email.trim()==="true"){
							window.location= "Result.jsp";	
						}
					}
			});
}
</script>
</head>

<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<!--<form action="EmailSendingServlet" method="post">-->
<h1 align="center">Send Email</h1>
		<table border="0" width="35%" align="center">
			<tr>
				<td width="100%">To:</td>
				<td><input type="text" id="recipient" name="recipient" size="50"/></td>
			</tr>
			<tr>
				<td>Subject </td>
				<td><input type="text" id="subject" name="subject" size="50"/></td>
			</tr>
			<tr>
				<td>Content </td>
				<td><textarea rows="10" cols="51" id="content" name="content"></textarea> </td>
			</tr>
		</table>
<table align="center">
<tr><td><input type="button" value="Send" onClick="sendEmail()"/></td></tr>
</table>
	</form>
<br>
</div>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>