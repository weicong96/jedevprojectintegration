<%@page import="java.util.ArrayList"%>
<%@page import="model.Submission"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="ex" uri="WEB-INF/customtags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="css/viewSubmission.css" rel="stylesheet" type="text/css"/>
		
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
	<jsp:include page="includes/nav.jsp"/>
	
	
	<div id='body'>
	<form action="ViewSubmission" method="GET">
		<h1>[i.code] 2014 entries</h1><div class='pull-right'><input type='text' name='term' placeholder='Search'/><input type='submit'/></div>
		
		</form>
		<%
			ArrayList<Submission> list = (ArrayList<Submission>) request.getAttribute("submissionList");
			
			for(int i = 0; i < list.size();i++){
			 	request.setAttribute("submission",list.get(i));
				request.setAttribute("link", new Boolean(true));
			%>
			<jsp:include page="/includes/ViewSubmissionFragment.jsp"/>
		<% 
			}
		 %>
		
	</div>
	<ex:page pageNumber="${param.page}" currentUrl="${requestScope['javax.servlet.forward.query_string']}"/>
	
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>