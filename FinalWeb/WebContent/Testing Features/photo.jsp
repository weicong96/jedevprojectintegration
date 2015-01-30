<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>Capturing Images with HTML5</title>

<table align="center"> 
<tr><td><div><video id="videoID" autoplay style="border: 2px solid black; width:200px; height:150px"></video></div></td>
<td><div><canvas id="canvasID" style="border: 2px solid black; width:200px; height:150px"></canvas></div></td></tr>
</table>

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
</head> 
<body>
<form>
<table align="center">
<tr><td><div><input type="button" value="Take photo" onclick="capture()" style="width: 200px; height: 30px;"/> <input type="button" value="Send" onclick="send()" style="width: 200px; height: 30px;"/></div>
</td></tr>
</table>
</form>
</body>
</html>