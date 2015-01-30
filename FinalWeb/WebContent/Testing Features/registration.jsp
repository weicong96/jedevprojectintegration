<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ICode Management System</title>
</head>
<body>
<form action="RegServlet" method="POST">
<h1>Registration</h1>
<table align="center">
<tr><td>Please fill in all blanks:</td></tr>
<tr><td>Team Name:</td><td><input type="text" name="teamName"></tr>
<tr><td>School Name:</td><td><input type="text" name="schName"></tr>
<tr><td>Teacher In-charge:</td><td><input type="text" name="tic"></td></tr>
<tr><td><br></td></tr>
<tr><td>Team Member Name:</td><td><input type="text"></td><td style="padding:25px"></td><td>Team Member Name:</td><td><input type="text"></td><td style="padding:25px"></td><td>Team Member Name:</td><td><input type="text"></td></tr>
<tr><td>Gender:</td><td><select style="width: 100%; " name="gender"><option value="M">Male<option value="F">Female</option></select></td><td></td><td>Gender:</td><td><select style="width: 100%; " name="gender"><option value="M">Male<option value="F">Female</option></select></td><td></td><td>Gender:</td><td><select style="width: 100%; " name="gender"><option value="M">Male<option value="F">Female</option></select></td></tr>
<tr><td>NRIC:</td><td><input type="text"></td><td></td><td>NRIC:</td><td><input type="text"></td><td></td><td>NRIC:</td><td><input type="text"></td></tr>
<tr><td>Contact No:</td><td><input type="text"></td><td></td><td>Contact No:</td><td><input type="text"></td><td></td><td>Contact No:</td><td><input type="text"></td></tr>
<tr><td>Email Address:</td><td><input type="text"></td><td></td><td>Email Address:</td><td><input type="text"></td><td></td><td>Email Address:</td><td><input type="text"></td></tr>
<tr><td>Food Preference:</td><td><input type="text"></td><td></td><td>Food Preference:</td><td><input type="text"></td><td></td><td>Food Preference:</td><td><input type="text"></td></tr>
<tr><td>I will like to attend:</td><td><select style="width: 100%; " name="attend"><option value="Competition">Competition<option value="Both">Competition & Workshop</option></select></td><td></td><td>I will like to attend:</td><td><select style="width: 100%; " name="attend"><option value="Competition">Competition<option value="Both">Competition & Workshop</option></select></td><td></td><td>I will like to attend:</td><td><select style="width: 100%; " name="attend"><option value="Competition">Competition<option value="Both">Competition & Workshop</option></select></td></tr>
</table>
</form>
</body>
</html>