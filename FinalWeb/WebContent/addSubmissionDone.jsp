<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<link href="css/addSubmission.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
	<jsp:include page="includes/nav.jsp"/>
	
	
	<div id='body'>
		<h1>Submission Successful</h1>
		<p>Your submission has been successfully submitted. Your entry can be previewed <a href='PreviewSubmission?id=<%=request.getParameter("id")%>'>here</a></p>
		
		<p>You can still make changes to your entry at this <a href='YourSubmissions'>link</a> before 12 Nov 2014</p>
	</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>