<html>
<head>
<title>Staff Send Email</title>
<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
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
<script>
function passwordValidation(){
			 	$.ajax({
			 		  });
}
</script>
</head>
<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<h1 align="center">Send Email</h1>
<form action="mail" method="post">
<table align="center">
<tr><td>Your Email Address:</td><td><input type="text" style="width:100%" name="user" required/></td></tr>
<tr><td>Your Password:</td><td><input style="width:100%" type="password" name="pass" required/></td></tr>
<tr><td>To (Email Address):</td><td><input style="width:100%" type="email" name="to" required/></td></tr>
<tr><td>Subject:</td><td><input style="width:100%" type="text" name="subject" required/></td></tr>
<tr><td>Message:</td><td><textarea style="width:100%" rows="5" cols="50" name="message"  required></textarea></td></tr>
<tr><td></td><td align="right" ><input type="submit" value="Send Email" /></td></tr>
</table>
</form>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
 
 
</html>