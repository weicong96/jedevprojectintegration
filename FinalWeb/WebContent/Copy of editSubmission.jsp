<%@page import="model.Submission,java.lang.Math,java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="is" uri="WEB-INF/customtags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
	<jsp:include page="includes/nav.jsp"/>
	
	
	<div id='body'>
		<%
			Submission sub = (Submission) request.getAttribute("submission");
			if(sub == null){
			 	response.sendRedirect("EditSubmission");
			}
			Map<String,String> required = (Map<String,String>) request.getAttribute("RequiredList");
			request.setAttribute("required", required);
			
		 %>
		<form action="EditSubmission?id=<%= request.getParameter("id") %>" method="post" enctype="multipart/form-data" >
			<h1>Competition Entry for Team PHSS</h1>
				<label for="entryName">Entry Name 
					<is:isRequried fields="${required}" property="Entry Name"/></label><br>
				<input type="text" name="entryName" id="entryName" value="<%= sub.getName() %>" readonly/><br>
				<label for="description">Short Description
					<is:isRequried fields="${required}" property="Description"/>
				</label><br>
				<textarea name="description" id="description" rows="5" width="100%" 
					<is:isRequried fields="${required}" property="Description"  inputType="true"/>
				><%= sub.getDescription() %></textarea>
				<hr>
				<label for="compiled">Compiled file 
					<is:isRequried fields="${required}" property="Compiled"/>
				</label><br>
				<b><%= sub.getCompiled() %></b><br>
				<input type="file" name="compiled" id="compiled" accept=".apk"/><br>
				<label for="screenshot">Screenshots
					<is:isRequried fields="${required}" property="Screenshots"/> 
				</label><br>
				<% 
				String[] screenShots = sub.getScreenshot().split(",");
				for(int counter = 0; counter < screenShots.length;counter++){ %>
						<!-- <img src="files/screenshot/<%= sub.getGroup().getGroupCode()+"_"+screenShots[counter] %>"/><br>---->
					<span class="imgPreview" data-img="<%= sub.getGroup().getGroupCode()+"_"+screenShots[counter] %>"><b><%= screenShots[counter] %></b></span><br>
				<% } %>
				<input type="file" name="screenshot[]" id="screenshot" accept="image/*" multiple/><br>
				
				<label for="icon">Icon 
					<is:isRequried fields="${required}" property="Icon"/>
				</label><br>
				<b><%= sub.getIcon() %></b><br>
				<input type="file" name="icon" id="icon"/><br>
				<label for="video">Video
					<is:isRequried fields="${required}" property="Video"/>
				 </label><br>
				<b><%= sub.getVideo() %></b><br>
				<input type="file" name="video" id="video" accept="video/mp4"/><br>
				<label for="code">Source Code 
					<is:isRequried fields="${required}" property="Source Code (.zip)"/>
				</label><br>
				<b><%= sub.getSourceCode() %></b><br>
				<input type="file" name="code" id="code"/><br>
				
				<input type="hidden" name="id" value="<%= sub.getSubmissionID() %>"/>
				<br>
				<input type="submit" value="Submit" class="btn btn-success"/>
		</form>
	</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>