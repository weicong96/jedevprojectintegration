<!DOCTYPE HTML><%@page import="model.Submission"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>ViewSubmissionFragment</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<% 
		Submission sub = (Submission)request.getAttribute("submission");
		if(sub == null){
					response.sendRedirect("PreviewSubmission");
		}
	 %>
	 <div>
		 <div class="row">
		 	<div class="col-sm-1">
			 		<img src="files/icon/<%= sub.getName() %>_<%= sub.getIcon() %>" style="max-width:50px;"/>
				</div>
				<div class="col-sm-11">
				<%
					Boolean useLink = (Boolean)request.getAttribute("link");
					if(useLink != null && useLink){
				 %>
					<h1><a href='PreviewSubmission?id=<%= sub.getSubmissionID()%>'><%= sub.getName() %></a></h1>
				<% }else{ %>
					<h1><%= sub.getName() %></h1>
				<% } %>
				</div>
			</div>	
			<div class="col-sm-12">
			<span class="hidden-xs">by <%= sub.getGroup().getGroupName() %></span>
			<span class="visible-xs"><%= sub.getGroup().getGroupName() %></span>
		</div>
		<div id="description" class="col-xs-12">
			<%= sub.getDescription() %>
		</div>
		 </div>
	<!-- 
	<div class="container-fluid text-left">
	
		<div class="col-sm-12">
			<span class="hidden-xs">by <%= sub.getGroup().getGroupName() %></span>
			<span class="visible-xs"><%= sub.getGroup().getGroupName() %></span>
		</div>
		<div id="description" class="col-xs-12">
			<%= sub.getDescription() %>
		</div>
	</div> -->
</body>
</html>