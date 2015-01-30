<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
<title>Registration</title>
</head>


<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<form action="UploadFileServlet" method="post" enctype="multipart/form-data">
<table align="center">
<tr><td align="center"><h1>Registration</h1></td></tr>
<tr><th>Step 1: Upload a picture and submit and proceed with Registration</th></tr>
</table>
<br>
<table align="center">
<tr><th>Upload a group picture:</th></tr>
<tr><td>File Name: <input accept="image/*" type="file" name="file" required/><br/>
<br/></td></tr>
</table>
<br>
<table align="center">
<tr><td><a href="RegisterS1.jsp"><input type="button" id="demo" style="background-image:url('button/back.png'); width:103px; height:45px; border-radius:6px" value=""/></a></td><td style="width:175px"></td><td><a href="Registration.jsp"><input type="image" src="button/next.png"></a></td></tr>
</table>
</form>
<br>
<br>
</div>
<script type="text/javascript"> 
var video = document.getElementById('videoID'); 
var canvas = document.getElementById('canvasID'); 
var context = canvas.getContext('2d'); 
window.URL = window.URL || window.webkitURL; navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

navigator.getUserMedia({ video : true }, 
		function(stream) { video.src = window.URL.createObjectURL(stream); }, 
		function(e) { console.log('An error happened:', e); });
		
function capture() { context.drawImage(video, 0, 0, canvas.width, canvas.height); };
function send() { var imageData = canvas.toDataURL(); var xmlhttp = new XMLHttpRequest(); xmlhttp.open("POST", "imageServlet", true); xmlhttp.send(imageData); };
</script>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>