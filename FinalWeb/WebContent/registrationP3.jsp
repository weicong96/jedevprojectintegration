<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
			<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
     <link rel="stylesheet" href="jqwidgets/styles/jqx.base.css" type="text/css" />
     <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
		 
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
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
<title>Registration</title>
</head>


<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<table align="center">
<tr><td><h1>Create Username and Password</h1></td></tr>
<tr><td align="center">Step 3: Fill in your username and password.</td></tr>
</table>
<form action="AccServlet" method="POST">
<br>
<table align="center">
<tr><td>Username:</td><td><input type="text" name="username" required/></td></tr>
<tr><td>Password:</td><td><input type="password" name="password" id="password" required/></td></tr>
<tr><td>Confirm Password:</td><td><input type="password" name="Cpassword" id="cpassword" required/></td></tr>
<tr><td></td><td><span id="passwordMatch" style="display:none">Password Not Match!</span></td></tr>

</table>
<br>
<table align="center">
<tr><td><input type="submit" id="demo" style="background-image:url('button/submit.png'); width:103px; height:45px; border-radius:6px" value=""/><!--onclick="return passwordValidation()";--></td></tr>
</table>
</form>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>