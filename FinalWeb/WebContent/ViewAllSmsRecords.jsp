<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	import="java.util.ArrayList, model.Sms"
	%>
<html>
<head>
<title>ViewAllSmsRecords</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<jsp:include page="includes/nav.jsp"/>
	<h1>View all SMS Records</h1>
	<input type='button' class='pull-right btn btn-primary' value='Send a SMS'/>
	<table width='100%' class='table table-striped'>
		<tr>
			<th>
				S/N
			</th>
			<th>
				Message
			</th>
			<th>
				Contact
			</th>
			<th>
				Purpose
			</th>
			<th>
		</tr>
			<%
			ArrayList<Sms> list = (ArrayList<Sms>)request.getAttribute("list");
				for(int i = 0; i < list.size();i++){
			 %>
		<tr>
			 	<td><%=i%></td>
			 	<td><%=list.get(i).getSmsMessage()%></td>
			 	<td><%=list.get(i).getContact()%></td>
			 	<td><%=list.get(i).getPurpose()%></td>
		</tr>
			 <%
			 }
			  %>
	</table>
	<jsp:include page="includes/footer.html"/>
</body>
</html>