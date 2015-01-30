<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	import="java.util.Map"%>
<%@ taglib prefix="is" uri="WEB-INF/customtags.tld"%>
<html>
<head>
		<title></title><script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
		
		<script src="js/settings.js" type="text/javascript"></script>
		<link rel="stylesheet" href="css/settings.css">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body class="container-fluid">
	<jsp:include page="includes/nav.jsp"/>
	<div id='body'>
	<form action="SettingsServlet" method="POST">
		<% if(request.getParameter("field") != null){ %>
			<input type="submit" class="btn btn-success pull-right" value="Save Settings"/>
		<% } %>
		<h1>Manage Submission Options</h1>
				<is:valid error="false"/>
		<h4>Deadline</h4> <a class="btn btn-link" href='SettingsServlet?field=deadline'>Edit</a>
		
		<p class="value">
		<% if(request.getParameter("field") != null && request.getParameter("field").equals("deadline")){ %>
		Deadline : <input type="datetime-local" name="deadline" pattern="dd-mm-yyyy" value="<%= request.getAttribute("deadline") %>" required />  <br>
			
		<% }else{ %>
			<%= request.getAttribute("deadline") %>
		<% } %>
		</p>
		
		<h4>Additional fields</h4><a class="btn btn-link" href='SettingsServlet?field=additional'>Edit</a>
		<% if(request.getParameter("field")!= null && request.getParameter("field").equals("additional")){%>
		<input type='button' class='pull-right btn btn-primary' value='Add Row' id='addRow'/>
		<% } %>
		<table id='tableRows' style='width:100%'>
			<tr>
				<th>Field</td>
				<th>File Format</td>
				<th>Required</td>
			</tr>
			<is:dynamic/>
		</table>
		
	</form>   
	</div>
	<jsp:include page="includes/footer.html"/>
</body>
</html>