<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>  
		<title></title>
		<link href="css/graph.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/compareofparticipant.css" rel="stylesheet" type="text/css" />
</head>


<body>
	<jsp:include page="includes/nav.jsp"/>
	
	
	<div id='body'>
		<br><br><br><br><br><br>
		<p1>Number of participants over years</p1>
		<br><br>
		
<table cellpadding="10" RULES=COLS FRAME=VSIDES>


<tr>

<td><h2>2011</h2></td>

<td><canvas id="2011" width="100" height="22"
style="border:1px solid #c3c3c3;"></canvas></td>

<td><p3>100</p3></td>

</tr>

<tr>

<td><h2>2012</h2></td>

<td><canvas id="2012" width="120" height="22"
style="border:1px solid #c3c3c3;"></canvas></td>

<td><p3>120</p3></td>

</tr>

<tr>

<td><h2>2013</h2></td>

<td><canvas id="2013" width="150" height="22"
style="border:1px solid #c3c3c3;"></canvas></td>

<td><p3>150</p3></td>

</tr>

<tr>

<td><h2>2014</h2></td>

<td><canvas id="2014" width="200" height="22"
style="border:1px solid #c3c3c3;"></canvas></td>

<p3><td>200</td></p3>

</tr>

</table>
<p><canvas id="myLine" width="350" height="380" style="border:0px solid #d3d3d3;"></canvas></p>

<p2>Number of Participants</p2>
<br><br><br>

<script>
		var c = document.getElementById("2011");
		var ctx = c.getContext("2d");
		ctx.fillStyle = "#CD5C5C";
		ctx.fillRect(0,0,100,75);
</script>



<script>
		var c = document.getElementById("2012");
		var ctx = c.getContext("2d");
		ctx.fillStyle = "rgb(234,32,86)";
		ctx.fillRect(0,0,120,75);
		</script>

<script>
		var c = document.getElementById("2013");
		var ctx = c.getContext("2d");
		ctx.fillStyle = "rgb(172,27,66)";
		ctx.fillRect(0,0,150,75);
		</script>
		
		<script>
		var x = document.getElementById("2014");
		var ctx = x.getContext("2d");
		ctx.fillStyle = "rgb(83,5,15)";
		ctx.fillRect(0,0,200,75);
		</script>

		<script>

		var c = document.getElementById("myLine");
		var ctx = c.getContext("2d");
		ctx.beginPath();
		ctx.moveTo(20, 20);
		ctx.lineTo(20, 370);
		ctx.lineTo(290, 370);
		ctx.stroke();
		
		</script>

	</div>
		
	</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>