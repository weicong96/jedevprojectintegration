<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/body.css" rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div#body {
	text-align: left;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
</style>
<title>Send Updates And News</title>
</head>


<body>
	<div id='body'>
		<jsp:include page="includes/nav.jsp" />
		<form action="" method="POST">
			<br>

			<h1 align="center">Send Updates And News To Participants</h1>

			<table align="center">
				<tr>
					<td><b>Recipients(Teams):</b>
					</td>
					<td><input type="text" name="teamName" style="width: 350px;"><input
						type="submit" value="Select Teams" style="float: right;">
					</td>
				</tr>
				<tr>
					<td><b>Title:</b>
					</td>
					<td><input type="text" name="title" style="width: 450px;">
					</td>
				</tr>
				<tr>
					<td><b>Contents:</b>
					</td>
					<td><textarea
							style="resize: none; width: 450px; height: 200px;" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td><b>Attach Picture:</b>
					</td>
					<td><input type="text" name="attachFiles"
						style="width: 362px;"><input type="submit"
						value="Select Picture" style="float: right;">
					</td>
				</tr>
				<tr>
					<td><br>
					</td>
				</tr>
			</table>
			<table align="center">
				<tr>
					<td><input type="submit" value="Send">
					</td>
				</tr>
			</table>
		</form>
		<br> <br>
	</div>
	<jsp:include page="includes/footer.html" />

</body>
</html>