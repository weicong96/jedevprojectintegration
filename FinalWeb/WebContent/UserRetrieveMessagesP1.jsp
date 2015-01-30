<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="database.DBAO" %>
<%@page import="bean.Staff" %>
<%@page import="bean.Group" %>
<%@page import="bean.Conversation" %>
<%@page import="java.util.ArrayList"%>
<%String username = (String)session.getAttribute("username");
DBAO dbao = new DBAO();

Group group = dbao.retrieveGroup(username); 
String grpCode = group.getTeamCode();

ArrayList<Conversation> convoList = dbao.retrieveConversationStaff(grpCode);
/*for(int i=0; i<convoList.size(); i++){
	ArrayList<Staff> staffsList = dbao.retrieveStaffByStaffCode(convoList.get(i).getStaff_idstaff());
}*/
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
<title>Choose Conversation</title>
</head>
<body>
	<jsp:include page="includes/nav.jsp"/>
<div id='body'>
<form action="RetrieveMessages" action="GET">
<table align="center">
<%=grpCode%>
<%=convoList.size()%>
<tr><th style="width:900px">Welcome, <%=group.getTeamName()%></th><td><a href="login.jsp"><input type="button" value="Logout"></a></td></tr>
</table>
<h1 align="center">Choose Conversation</h1>
<table align="center">
<tr><%for(int i=0; i<convoList.size(); i++){ %><td style="width:100px">
<input type="radio" name="ConvoName" class="teacherName" value="<%=convoList.get(i).getStaff_idstaff()%>"><%=convoList.get(i).getStaff_idstaff()%></input>
<%} %></td></tr>
</table>
<br>
<br>
<table align="center">
<tr><td><input type="submit" value="View Messages"></td></tr>
</table>
</form>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>