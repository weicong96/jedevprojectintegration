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
$(function(){
    $("form td:nth-of-type(5) input:text").change(function(){
    	 $("form td:nth-of-type(5) input:text").prop('required',true); 
    });
    
    $("form td:nth-of-type(8) input:text").change(function(){
    	 $("form td:nth-of-type(8) input:text").prop('required',true); 
    });
       
});
            $(document).ready(function () {
                var area = new Array(<%
                
                	DBAO db = new DBAO();
                	School[] schs = db.getAllSchools();
                
                	String complete = "";
                	for(int i = 0; i<schs.length; i++){
                		if(i == 0){
                			complete+="'"+schs[i].getSchoolName()+"'";
                		}else{
                			complete+=", '"+schs[i].getSchoolName()+"'";
                		}
                	}
                	out.println(complete);
                %>);
                $("#schName").jqxInput({ placeHolder: " ", width: 171, height: 18, minLength: 1, source: area });
            });
	            $(function() {
	  			$( ".datepicker" ).datepicker({
				      changeMonth: true,
				      changeYear: true,
				      yearRange: "-20:-13",
				      dateFormat: "dd-mm-yy" 
	    });
	  });
	function ValidateEmail(email) {
        var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        return expr.test(email);
    };
    $("#demo").live("click", function () {
        if (!ValidateEmail($("#emailAdd").val())) {
            alert("Invalid email address.");
        }
        else {
            alert("Valid email address.");
        }
    });
</script>
</head>


<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<form action="RegServlet" method="POST">
<br>
<table align="center">
<tr><td align="center"><h1>Registration</h1></td></tr>
<tr><th>Step 2: Please fill in all the particulars</th></tr>
</table>
<table align="center">
<tr><th>Please fill in all blanks:</th></tr>
<tr><td><b>Team Name:</b></td><td><input type="text" name="teamName" required></tr>
<tr><td><b>School Name:</b></td><td><input type="text" name="schName" id="schName" required></tr>
<tr><td><b>Teacher In-charge:</b></td><td><input type="text" name="tic" required></td></tr>
<tr><td><br></td></tr>
<tr><td><b>Team Member Name:*</b></td><td><input type="text" name="memberName" required></td><td style="padding:25px"></td><td>Team Member Name:</td><td><input type="text" name="memberName"></td><td style="padding:25px"></td><td>Team Member Name:</td><td><input type="text" name="memberName"></td></tr>
<tr><td>Gender:</td><td><select style="width: 100%; " name="gender"><option value="M">Male<option value="F">Female</option></select></td><td></td><td>Gender:</td><td><select style="width: 100%; " name="gender"><option value="M">Male<option value="F">Female</option></select></td><td></td><td>Gender:</td><td><select style="width: 100%; " name="gender"><option value="M">Male<option value="F">Female</option></select></td></tr>
<tr><td>Date of Birth:</td><td><input type="text" name="dob" class="datepicker" required></td><td></td><td>Date of Birth</td><td><input type="text" name="dob" class="datepicker"></td><td></td><td>Date of Birth:</td><td><input type="text" name="dob" class="datepicker"></td></tr>
<tr><td>NRIC:</td><td><input type="text" name="nric" required></td><td></td><td>NRIC:</td><td><input type="text" name="nric"></td><td></td><td>NRIC:</td><td><input type="text" name="nric"></td></tr>
<tr><td>Contact No:</td><td><input type="text" name="contactNo" required></td><td></td><td>Contact No:</td><td><input type="text" name="contactNo"></td><td></td><td>Contact No:</td><td><input type="text" name="contactNo"></td></tr>
<tr><td>Email Address:</td><td><input type="email" name="emailAdd" required></td><td></td><td>Email Address:</td><td><input type="email" name="emailAdd"></td><td></td><td>Email Address:</td><td><input type="email" name="emailAdd"></td></tr>
<tr><td>Food Preference:</td><td><select style="width:100%; " name="foodPre" required><option value="Halal">Halal</option><option value="Vegetarian">Vegetarian</option></select></td><td></td><td>Food Preference:</td><td><select style="width:100%; " name="foodPre"><option value="Halal">Halal</option><option value="Vegetarian">Vegetarian</option></select></td><td></td><td>Food Preference:</td><td><select style="width:100%; " name="foodPre"><option value="Halal">Halal</option><option value="Vegetarian">Vegetarian</option></select></td></tr>
<tr><td>Allergic Comments:</td><td><textarea style="resize:none; width:96%;" name="allergicC" required></textarea></td><td></td><td>Allergic Comments:</td><td><textarea style="resize:none; width:96%;" name="allergicC"></textarea></td><td></td><td>Allergic Comments:</td><td><textarea style="resize:none; width:96%;" name="allergicC"></textarea></td></tr>
<tr><td>I will like to attend:</td><td><select style="width: 100%; " name="attendance"><option value="Competition">Competition<option value="Both">Competition & Workshop</option></select></td><td></td><td>I will like to attend:</td><td><select style="width: 100%; " name="attendance"><option value="Competition">Competition<option value="Both">Competition & Workshop</option></select></td><td></td><td>I will like to attend:</td><td><select style="width: 100%; " name="attendance"><option value="Competition">Competition<option value="Both">Competition & Workshop</option></select></td></tr>
<tr><td><b>* Represents the Leader</b></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
</table>
<br>
<table align="center">
<tr><td><a href="RegisterS1.jsp"><input type="button" style="background-image:url('button/back.png'); width:103px; height:45px; border-radius:6px" value=""/></a></td><td style="width:730px"><td><input type="submit" id="demo" style="background-image:url('button/submit.png'); width:103px; height:45px; border-radius:6px" value=""/></td></tr>
</table>
</form>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>