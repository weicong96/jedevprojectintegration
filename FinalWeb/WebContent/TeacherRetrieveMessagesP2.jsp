<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="database.DBAO"%>
<%@page import="bean.Staff"%>
<%@page import="bean.Group"%>
<%@page import="bean.Message"%>
<%@page import="bean.Conversation"%>
<%@page import="java.util.ArrayList"%>
<%String username = (String)session.getAttribute("username");
DBAO dbao = new DBAO();

Staff staff = dbao.retrieveStaff(username); 
String teacherCode = staff.getIdstaff();
String grpCode = (String)session.getAttribute("grpCode");

ArrayList<Message> TeacherSenderList = dbao.retrieveTeacherSenderDetails(teacherCode);
%>

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
<title>View Messages</title>
</head>
<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<%=TeacherSenderList.size()%>
<form action="TeacherRetrieveMessages" method="POST">
<h1 align="center">View Messages</h1>
<table align="center">
<tr><td><b>Conversation with:</b></td><td> <%=grpCode%></td></tr>
</table>
<br>

<table align="center">
  <tr>
    <th style="width:500px"></th>
  </tr>
 <%for(int i =0; i<TeacherSenderList.size(); i++){ 
Conversation conversation = TeacherSenderList.get(i).getConversation();%>
  <tr>
  	<%if(conversation.getReciever().equals(teacherCode)){%>
    <td align="left" bgcolor="#79BAEC"><%=TeacherSenderList.get(i).getMessageDetails()%></td>
    <%} %>
     <%if(conversation.getSender().equals(teacherCode)){%>
    <td align="right" bgcolor="#B6B6B4"><%=TeacherSenderList.get(i).getMessageDetails()%></td>
    <%} %>
  </tr>
  <tr>
  	<%if(conversation.getReciever().equals(teacherCode)){%>
    <td align="left" bgcolor="#79BAEC"><b><%=TeacherSenderList.get(i).getMessageTime()%></b></td>
    <%} %>
    <%if(conversation.getSender().equals(teacherCode)){%>
    <td align="right" bgcolor="#B6B6B4"><b><%=TeacherSenderList.get(i).getMessageTime()%></b></td>
    <%} %>
  </tr>
  <tr>
  	<%if(conversation.getReciever().equals(teacherCode)){%>
  	<td align="left" bgcolor="#79BAEC"><b><%=TeacherSenderList.get(i).getMessageDate()%></b></td>
  	<%} %>
  	<%if(conversation.getSender().equals(teacherCode)){%>
  	<td align="right" bgcolor="#B6B6B4"><b><%=TeacherSenderList.get(i).getMessageDate()%></b></td>
  	<%}%>
  </tr>
  <tr>
  	<td><br></td>
  </tr>
  <%} %>
</table>
<br>

<table align="center">
<tr><td align="left">Enter you message here:</td></tr>
<tr><td><textarea name="conversation" style="resize:none; width:500px"; rows="5"></textarea></td></tr>
<tr><td align="center"><input type="submit" value="Send"></td></tr>
</table>
<br>
<br>
</form>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>