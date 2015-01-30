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
<script src="js/jquery.js"></script>
<script src="js/fileUpload.js"></script>
<script src="js/validateEditSubmission.js"></script>
<style>
	.glyphicon-remove{
		color:red;
	}
	.glyphicon-remove:hover{
		cursor:pointer;
	}
	.hideRow{
		display:none;
	}
</style>

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
			request.setAttribute("edit", true);
		 %>
		 
		 <%!
		 	private String displaySelectDefault(String name, String typeValue){
			   String[] items = {"Screenshot","Icon","Video","Source Code","Packaged File"};
			   String[] values = {"screenshot[]","icon","video","sourceCode","compiled"};
			   String html = "";
			   html+="<select name='"+name+"'>";
			   for(int i = 0; i < items.length;i++){
			   		if(typeValue.equals(items[i])){
			   			html+="<option value='"+values[i]+"'selected>"+items[i]+"</option>";
			   		}else{
			   				html+="<option value='"+values[i]+"'>"+items[i]+"</option>";
			   		}
			   }
			   html+="</select>";
			   return html;
		 	}
		  %>
		<form action="EditSubmission?id=<%= request.getParameter("id") %>" method="post" enctype="multipart/form-data" >
			<h1>Competition Entry for Team PHSS</h1>
				
				<is:valid />
				<table>
					<tr>
						<td>Entry Name * </td>
					</tr>
					<tr>
						<td><input type='text' name='entryName' value='<%=
							sub.getName()
						%>' required readonly/></td>
					</tr>
					<tr>
						<td>Description *</td>
					</tr>
					<tr>
						<td><textarea name='description' required><%= sub.getDescription() %></textarea></td>
					</tr>
					<tr>
						<td>Files *</td>
					</tr>
					<tr>
						<td><input type='file' id='allFiles' multiple/><input type='hidden' id='timePrefix' name='timePrefix'/></td>
					</tr>
					<tr>
						<td>
							<h2>Purpose of submitted files</h2>
						</td>
					</tr>
					<tr>
						<td>
						<span class='errorMsg' id='validPurpose'></span>
						<table id='matchPurpose' style='width:100%;' class='show'>
								<tr>
									<th>Files</th>
									<th>Purpose</th>
								</tr>
								<% 
								String singleSS[] = sub.getScreenshot().split(",");
								for(int i = 0; i < singleSS.length;i++){ %>
								<tr>
									<td><span class='glyphicon glyphicon-remove' data-fileValue="screenshot/<%=singleSS[i]%>"></span><%= singleSS[i] %></td>
									<td><%= displaySelectDefault(singleSS[i], "Screenshot") %></td>
								</tr>
								<% } %>
								<tr>
									<td><span class='glyphicon glyphicon-remove'></span><%= sub.getIcon() %></td>
									<td><%= displaySelectDefault(sub.getIcon(), "Icon") %></td>
								</tr>
								<tr>
									<td><span class='glyphicon glyphicon-remove'></span><%= sub.getVideo() %></td>
									<td><%= displaySelectDefault(sub.getVideo(), "Video") %></td>
								</tr>
								<tr>
									<td><span class='glyphicon glyphicon-remove'></span><%= sub.getSourceCode() %></td>
									<td><%= displaySelectDefault(sub.getSourceCode(), "Source Code") %></td>
								</tr>
								<tr>
									<td><span class='glyphicon glyphicon-remove'></span><%= sub.getCompiled() %></td>
									<td><%= displaySelectDefault(sub.getCompiled(), "Packaged File") %></td>
								</tr>
								
							</table><br>
							<p id='paraDescription'>You have submitted the following files, please state the purposes of these files.</p>
							
						</td>
					</tr>
					<is:generateForm/>
				</table>
				<br><br>
				<input type="submit" value="Submit" class="btn btn-success" onclick="return onSubmit();"/>
		</form>
	</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>