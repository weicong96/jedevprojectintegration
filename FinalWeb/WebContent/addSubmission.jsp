<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="util.Settings, java.util.Map"%>
<%@ taglib prefix="is" uri="WEB-INF/customtags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
		<script src="js/json.js"></script>
		<script src="js/fileUpload.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>

</script>
</head>


<body class="container-fluid">
	<jsp:include page="includes/nav.jsp"/>
	
		<form action="AddSubmission" class="text-left" method="post" enctype="multipart/form-data" id="submissionForm">
		<%
			//Map<String,String> required =// (Map<String,String>) request.getAttribute("RequiredList");
			//request.setAttribute("required", required);
			
			if(request.getSession(true).getAttribute("teamCode")== null){
				response.sendRedirect("login.jsp?page=AddSubmission");
			}
		 %>
		<!--  enctype="multipart/form-data" -->

			<h1>Competition Entry for Team <%=
				request.getSession(true).getAttribute("teamName")
			 %></h1>
				<is:valid />
				<table>
					<tr>
						<td>Entry Name *</td>
					</tr>
					<tr>
						<td><input type='text' name='entryName' required/>
						<span id="validAlreadyExists" class='errorMsg'></span></td>
					</tr>
					<tr>
						<td>Description *</td>
					</tr>
					<tr>
						<td><textarea name='description' required></textarea></td>
					</tr>
					<tr>
						<td>Files *</td>
					</tr>
					<tr>
						<td><input type='file' id='allFiles' multiple required/><input type='hidden' id='timePrefix' name='timePrefix'/></td>
					</tr>
					<tr>
						<td>
							<h2  id='paraHeading'>Purpose of submitted files</h2>
						</td>
					</tr>
					<tr>
						<td>
							<p id='paraDescription'>You have submitted the following files, please state the purposes of these files.</p>
							
						<span class='errorMsg' id='validPurpose'></span>
							<table id='matchPurpose' style='width:100%'>
								<tr>
									<th>Files</th>
									<th>Purpose</th>
								</tr>
							
							</table>
						</td>
					</tr>
					<is:generateForm/>
				</table>
				<br><br>
				<input type="submit" value="Submit" class="btn btn-success" onclick="return onSubmit();"/>
		</form>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>