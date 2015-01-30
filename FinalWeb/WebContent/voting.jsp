<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="bean.Registration,bean.School,database.ReportManager,java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<link href="css/voting.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
	<jsp:include page="includes/nav.jsp"/>
	
	
	<div id='body'>
	
	<div id='instruction'>
		Have you check out our gallery?<br>
		or you want to have a look of the entry?<br><br>
		Step 1 : Choose the school that you want to check.<br>
		
		
		<select name="school" style="width: 315px; ">
				<option>Nil</option>
				<%
					ReportManager mgr = new ReportManager();
					School[] schools = mgr.getAllSchools();

					for (int s = 0; s < schools.length; s++){
				%>

				<option><%= schools[s].getSchoolName() %></option>
				
				
				<% } %>


			</select>
	</div>
	
	<div id='viewing'>
		Step 2 : You can view the entry right below.<br><br>
			<table style="height: 98px; width:300px; "><tr><td>VIDEO HERE</td></tr></table>
			Step 3 : If you like it,please give it a thumb up <br>
		(limited a like per person)<br>
		<img src="images/thumbup.jpg"/>	
		
	
	</div>
		

		
	</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>