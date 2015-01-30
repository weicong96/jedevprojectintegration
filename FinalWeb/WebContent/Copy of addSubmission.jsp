<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="util.Settings, java.util.Map"%>
<%@ taglib prefix="is" uri="WEB-INF/customtags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
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
		 %>
		<!--  enctype="multipart/form-data" -->

			<h1>Competition Entry for Team PHSS</h1>
				<is:valid />
				
				<label for="entryName">Entry Name
					<is:isRequried fields="${required}" property="Entry Name"/>
				</label><br>
				<input type="text" name="entryName" id="entryName"
					<is:isRequried fields="${required}" property="Entry Name" inputType="true"/>
				/><br>
				<label for="description">Short Description
					<is:isRequried fields="${required}" property="Description"/>
				</label><br>
				<textarea name="description" id="description" rows="5" style="width:100%" 
					<is:isRequried fields="${required}" property="Description"  inputType="true"/>
				></textarea><br>
				<hr>
				<label for="compiled">Compiled file 
					<is:isRequried fields="${required}" property="Compiled"/>
				</label>
				<input type="file" name="compiled" id="compiled" 
					<is:isRequried fields="${required}" property="Compiled"  inputType="true"/>
				/>
				
				<label for="icon">Icon 
					<is:isRequried fields="${required}" property="Icon"/>
				</label>
				<div id="iconFinder" style="display:none;">
				 	We have found the following image files in your .apk file, choose ONE image as your application icon
				 	
				 	<div style="height:250px;overflow-y:scroll;">	
				 	<table id="images" width="100%">
				 	
				 		<tr><th>Selection</th><th>Image Name</th><th>Folder</th><th>Image</th></tr>
				 	</table></div>	OR<br>
				</div><br>
			
				<input type="file" name="icon" id="icon" 
					<is:isRequried fields="${required}" property="Icon"  inputType="true"/>
				/>
				<label for="screenshot[]">Screenshots 
					<is:isRequried fields="${required}" property="Screenshots" />
				</label>
				<input type="file" name="screenshot[]" id="screenshot" accept="image/*" multiple 
					<is:isRequried fields="${required}" property="Screenshots"  inputType="true"/>
				/>
				
				
				<label for="video">Video 
					<is:isRequried fields="${required}" property="Video"/>
				</label>
				<input type="file" name="video" id="video" accept="video/*" 
					<is:isRequried fields="${required}" property="Video" inputType="true" />
				 />
				
				<label for="code">Source Code(.zip) 
					<is:isRequried fields="${required}" property="Source Code (.zip)"/>
				</label>
				<input type="file" name="code" id="code" 
					<is:isRequried fields="${required}" property="Source Code (.zip)"  inputType="true"/>
				/>
				 
				<br><br>
				<input type="submit" value="Submit" class="btn btn-success"/>
		</form>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>