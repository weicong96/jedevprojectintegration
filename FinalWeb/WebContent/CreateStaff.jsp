<%@page import="bean.School"%>
<%@page import="database.DBAO"%>
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
    <script type="text/javascript" src="scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="jqwidgets/jqxinput.js"></script>
     <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
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
<title>Registration</title>
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
				$("#staffID").on('input',function(e){
					$('#username').val($('#staffID').val());
					
		 		});
		 	});
</script>
</head>


<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<form action="StaffRegServlet" method="POST">
<br>
<table align="center">
<tr><td align="center"><h1>Create Staff Accounts</h1></td></tr>
<tr><th>Please fill in all details to have an account</th></tr>
</table>
<br>
<table align="center">
<tr><th>Please fill in all details:</th></tr>
<tr><td>Name:</td><td><input type="text" name="staffName" required></td></tr>
<tr><td>Staff ID:</td><td><input id="staffID" type="text" name="staffID"  required></td></tr>
<tr><td>Staff Type:</td><td><select style="width: 100%; " name="staffType"><option value="Lecturer">Lecturer</option><option value="Senior Lecturer">Senior Lecturer</option><option value="Manager">Manager</option><option value="Asst Manager">Asst Manager</option></select></td>
<tr><td>Contact Number:</td><td><input type="text" name="contactNo" required></td></tr>
<tr><td>Email Address:</td><td><input type="email" name="emailAdd" required></td></tr>
<tr><td><hr></td><td><hr></td></tr>
<tr><td>Use StaffID to Login:</td><td><input id="username" type="text" name="username" disabled></td></tr>
<tr><td>Password:</td><td><input id="password" type="password" name="staffPassword" required></td></tr>
<tr><td>Confirm Password:</td><td><input id="cpassword" type="password" name="staffCPassword" required></tr>
<tr><td></td><td><span id="passwordMatch" style="display:none">Password Not Match!</span></td></tr>
</table>
<br>
<table align="center">
<tr><td><a href="RegisterS1.jsp"></a></td><td style="width:180px"><td><input type="submit" id="demo" style="background-image:url('button/submit.png'); width:103px; height:45px; border-radius:6px" value=""/></td></tr>
</table>
</form>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>