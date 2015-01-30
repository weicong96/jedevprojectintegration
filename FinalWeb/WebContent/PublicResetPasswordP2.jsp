<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<link href="css/main320_480.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <script>
		 
		 	function passwordValidation(){
		 	 var pass = $('#password').val();
		 	 var status = false;
		 		  	  var cpass = $('#cpassword').val();
			 		  $.ajax({async: false,url:"PasswordValidationServlet?password="+pass+"&cpassword="+cpass,success:function(result){
					    	
					    	if(result.trim() === "true"){
					    		status = true;
					    	}else{
					    		status = false;
					    	} 	
					  }}).done(function(){
						  if(status){
						    		$("#passwordMatch").hide();
						 }else{
						    		$("#passwordMatch").show();
						    		}
						  });
						  return status;
			}
		 	$(function(){
		 		  $("#cpassword, #password").on('input',function(e){
					 passwordValidation();
		 		  });
		 	});
		 </script>


<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
<title>Reset Password</title>
</head>

<body>
<form action="ResetPasswordServlet" method="POST">
	<jsp:include page="includes/nav.jsp"/>
<div id='body'>
<table align="center">
<tr><td align="center"><h1>Reset Password</h1></td></tr>
</table>

<table align="center">
<tr><td>Enter your new password:</td><td><input type="password" name="password" id="password" required/></td></tr>
<tr><td>Enter your new password again:</td><td><input type="password" name="Cpassword" id="cpassword" required/></td></tr>
<tr><td></td><td><span id="passwordMatch" style="display:none">Password Not Match!</span></td></tr>
</table>
<br>
</div>

<table align="center">
<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form>

	<jsp:include page="includes/footer.html"/>
	
</body>
</html>