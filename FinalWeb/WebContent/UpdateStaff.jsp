<%@page import="bean.Staff"%>
<%@page import="bean.StaffAccount"%>
<%@page import="database.DBAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String username = (String)session.getAttribute("username");
DBAO db = new DBAO();
Staff s = new Staff();
s = db.retrieveStaff(username);
StaffAccount sa = new StaffAccount();
sa = db.retrieveStaffAccount(username);
%>
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
<title>Update Staff Details</title>
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
<form action="StaffUpdateServlet" method="POST">
<br>
Welcome,<%=username%>
<table align="center">
<tr><td align="center"><h1>Update Staff Details</h1></td></tr>
<tr><th>Please  update your details</th></tr>
</table>
<br>
<table align="center">
<tr><th></th></tr>
<tr><td>Name:</td><td><input type="text" name="staffName" value="<%=s.getStaffName()%>" required></td></tr>
<tr><td>Staff ID:</td><td><input id="staffID" type="text" name="staffID" value="<%=s.getIdstaff()%>" readonly></td></tr>
<%String[]items = {"Lecturer","Senior Lecturer","Asst Manager","Manager"};%>
<tr><td>Staff Type:</td><td><select name="staffType" style="width:100%"><%for(int i=0; i<items.length; i++){%>
<option value="<%= items[i] %>" <%= items[i].equals(s.getStaffType()!= null ? s.getStaffType() : "" ) ? "selected" : "" %>><%= items[i]%></option>
<% } %></select></td></tr>
<tr><td>Contact Number:</td><td><input type="text" name="contactNo" value="<%=s.getStaffContactNo()%>" required></td></tr>
<tr><td>Email Address:</td><td><input type="email" name="emailAdd" value="<%=s.getStaffEmailNo()%>" required></td></tr>
<tr><td><hr></td><td><hr></td></tr>
<tr><td>Use StaffID to Login:</td><td><input id="username" type="text" name="username" value="<%=s.getIdstaff()%>" disabled></td></tr>
<tr><td>Password:</td><td><input id="password" type="password" name="staffPassword" value="<%=sa.getAccPassword()%>" required></td></tr>
<tr><td>Confirm Password:</td><td><input id="cpassword" type="password" name="staffCPassword" value="<%=sa.getAccPassword()%>" required></tr>
<tr><td></td><td><span id="passwordMatch" style="display:none">Password Not Match!</span></td></tr>
</table>
<br>
<table align="center">
<tr><td><a href="RetrieveStaff.jsp"><input type="button" style="background-image:url('button/back.png'); width:103px; height:45px; border-radius:6px" value=""/></a></td><td style="width:180px"><td><input type="submit" id="demo" style="background-image:url('button/submit.png'); width:103px; height:45px; border-radius:6px" value=""/></td></tr>
</table>
</form>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>