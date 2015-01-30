<%@page import="model.Submission"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="ex" uri="WEB-INF/customtags.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link href="css/previewSubmission.css" rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<title>Insert title here</title>
<script>
		<%if (request.getParameter("div") != null) {%>
		$(document).ready(function (){
			$('html,body').animate({
   scrollTop: $("#<%=request.getParameter("div")%>").offset().top
		});
	});
<%}%>
	
</script>
</head>


<body>
	<jsp:include page="includes/nav.jsp" />


	<div id='body' class="container-fluid">
		<%
			Submission sub = (Submission) request.getAttribute("submission");
			if (sub == null) {
				response.sendRedirect("PreviewSubmission");
			}
			request.setAttribute("sub", sub);
		%>
		<jsp:include page="includes/ViewSubmissionFragment.jsp" />
		<ex:isNull entity="${sub}" property="compiled">
			<div id='compiled'>
				<h2>Download G.A.M.M.A Android Application</h2>
				<a href="DownloadSubmission?id=${sub.submissionID}">Download
					Android Application</a>
			</div>
		</ex:isNull>
		<ex:isNull entity="${sub}" property="video">
			<div id="video">
				<h2 class="text-left">Video</h2>
				<video width="640" height="480" controls> <source
					src="files/video/${sub.name}_${sub.video}"> Your browser
				does not support the video tag. </video>
			</div>
		</ex:isNull>
		<ex:isNull entity="${sub}" property="screenshot">
			<div id="screenshots">
				<div class="row">
					<h2 class="col-xs-12">Screenshots</h2>
					<div style="width: 100%;">
						<c:forTokens items="${sub.screenshot}" delims=","
							var="screenshotSingle">
							<img class="col-xs-3"
								src="files/screenshot/${sub.name}_${screenshotSingle}" />
						</c:forTokens>

					</div>
				</div>
			</div>
		</ex:isNull>


	</div>

	<jsp:include page="includes/footer.html" />

</body>
</html>