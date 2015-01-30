<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	import="model.Submission"%>
<%@ taglib prefix="is" uri="WEB-INF/customtags.tld"%>
<html>
<head>
<title>yourSubmissions</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<jsp:include page="includes/nav.jsp" />
	<div id="body">
		<h1>Your Submissions</h1>
		<a href='AddSubmission' class='btn btn-primary pull-right' style='margin:15px'>Add Submission</a>
		<table class='table table-striped'>
			<tr>
				<th>Entry Name</th>
				<th>Time Submitted</th>
				<th>Actions</th>
			</tr>
			<%
				
				Submission[] items = (Submission[])request.getAttribute("submissionList");
				if(items.length  == 0){%>
				<tr>
					<td colspan='3' style='text-align:center;'>No Submissions yet</td>
			 	</tr>
				
			<%}else{
				for(int i = 0; i < items.length;i++){
			 %>
			 	<tr>
					<td><%= items[i].getName() %></td>
					<td><%= items[i].getDateSubmitted() %></td>
					<td><a style='padding:10px;' href='EditSubmission?id=<%= items[i].getSubmissionID()%>'>Edit</a><a style='padding:10px;' href='PreviewSubmission?id=<%= items[i].getSubmissionID()%>'>View</a></td> 		
			 	</tr>
			 <%
			 }
			 }
			  %>
		</table>
		<is:page pageNumber="${param.page}" currentUrl="${requestScope['javax.servlet.forward.query_string']}"/>
	
	</div>
	<jsp:include page="includes/footer.html" />
</body>
</html>