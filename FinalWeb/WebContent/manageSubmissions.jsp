
<%@page
	import="model.Submission,java.util.ArrayList,tags.ManageSubmissionsLogic"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="is" uri="WEB-INF/customtags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular.min.js"></script>
<script src="js/manageSubmissions.js"></script>

<script>
	
</script>
<style>
.popover{
	display:inline-block;
	overflow-x: auto;
	max-width:600px !important;
}
.popover img{
	display:inline;
}
.submissionEntriesTable tr:nth-of-type(even) {
	background-color: #f9f9f9;
}

.submissionEntriesTable {
	width: 100%;
}
table th{
	padding:10px 5px;
}
table td {
	text-align: center;
	padding:12px;
}

table td img {
	vertical-align: middle;
}

.marginBtn {
	margin: 0 10px;
}
#page li{
	padding:0.4em;
}
.currentPage{
	text-decoration:underline;
}
#entrySelection td, #entrySelection th{
	max-width:300px;
}
</style>
<title>Insert title here</title>

</head>


<body>
	<jsp:include page="includes/nav.jsp" />

	<%
		Submission[] list = (Submission[]) request
				.getAttribute("SubmissionList");
		ManageSubmissionsLogic backend = new ManageSubmissionsLogic();
		
		if (list == null) {
			response.sendRedirect("ExportSubmissions");
		}
	%>
	<div id='body' class="container-fluid">
		<h1>Manage Submitted entries </h1>
		<a href='SettingsServlet'>Manage Submission options</a>

		<form action="ExportSubmissions" method="post">
			<input type="button" value="Select All" id="selectAll"
				class="btn btn-default" /> <input type="button"
				value="Delete Selected" id="deleteButton" data-toggle="modal"
				data-target="#myModal" style="text-align: center; float: right;"
				class="btn btn-danger marginBtn" /> <input type="button"
				value="Download Options" style="text-align: center; float: right;"
				id="downloadButton" class="btn btn-default marginBtn" />
			<div id="downloadSelection" style='display: none;'>

				<h3>Files to Download</h3>
				<span id="validDownload" class="errorMsg"></span><BR> <input
					type="checkbox" name="files" value="compiled" />Compiled File <input
					type="checkbox" name="files" value="video" />Video <input
					type="checkbox" name="files" value="screenshot" />Screenshot <input
					type="checkbox" name="files" value="icon" />Icon <input
					type="checkbox" name="files" value="sourceCode" />Source Code <br> <input
					class="btn btn-info" value="Download" type="submit"
					onclick="return checkFiles();" />
			</div>
			<span id="validSelection" class="errorMsg"></span>
			<div id='entryDiv'>
			<jsp:include page = "manageSubmissionsAjax.jsp"/>
			</div>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">Confirm changes?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-danger" id="deleteConfirm">Delete
								Selected Items</button>
						</div>
					</div>
				</div>
			</div>
			<div style='display:none' id='popover'>
				HELLO SOME TEXT
			</div>
		</form>
		<is:page pageNumber="${param.page}" currentUrl="${requestScope['javax.servlet.forward.query_string']}"/>
	</div>
	<jsp:include page="includes/footer.html" />

</body>
</html>