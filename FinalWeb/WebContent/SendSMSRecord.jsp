<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>SendSMSRecord</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
	<jsp:include page="includes/nav.jsp"/>
	<div id='body'>
	<h1>Send SMS</h1>
	<form action="SendSMSRecord" method="POST">
		<table width='100%'>
			<tr>
				<td>
					Contacts
				</td>
				<td>
					<textarea type='text' name='contact'></textarea><a href="javascript:window.open('AddSMSContacts','Window','width=500,height=600')"  target="_blank">Add Contacts</a>
				</td>
			</tr>
			<tr>
			
				<td style='vertical-align:top'>
					Content
				</td>
				<td>
					<textarea name='content' rows='10'></textarea>
				</td>
			</tr>
			<tr>
				<td>
					Remarks
				</td>
				<td>
					<input type='text' name='remarks'/>
				</td>
			</tr>
		</table>
	<input type='submit' class='btn btn-primary pull-right' value='Send SMS'/>
	</form>
	</div>
	<jsp:include page="includes/footer.html"/>
</body>
</html>