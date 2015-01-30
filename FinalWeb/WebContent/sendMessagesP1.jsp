<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="database.DBAO" %>
<%@page import="bean.Staff" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String username = (String)session.getAttribute("username");
%>
<html>
<head>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="jqwidgets/styles/jqx.base.css" type="text/css" />
    <script type="text/javascript" src="scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="jqwidgets/jqxinput.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  	<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  	<link rel="stylesheet" href="/resources/demos/style.css">
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
<title>Send Message</title>
<script>
$(document).ready(function () {
                var area = new Array(<%ArrayList<Staff> staffList = (ArrayList<Staff>)request.getAttribute("staffList");
                	String complete="";
					for(int i = 0; i<staffList.size(); i++){
                		if(i == 0){
                			complete+="'"+staffList.get(i).getStaffName()+"'";
                		}else{
                			complete+=", '"+staffList.get(i).getStaffName()+"'";
                		}
                	}
                	out.println(complete);%>);
                $("#staffName").jqxInput({ placeHolder: " ", width: 171, height: 18, minLength: 1, source: area });
            });
</script>
</head>
<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>
<!--<form action="SendMessageServletP1" method="POST">-->

<form action="UserSendMessageToTeacherServlet" method="POST">
<h1 align="center">Send Messages</h1>
<table align="center">
<tr><td width="160px"><b>To:</b></td><td><input id="staffName" type="text" name="recipent" style="width:200px"></td></tr>
<tr><td><b>Message:</b></td><td><textarea name="conversation" style="resize:none; width:300px"; rows="5"></textarea></td></tr>
</table>

<table align="center">
<tr><td><input type="submit" value="Send"/></td></tr>
</table>
</form>
<br>
<br>
</div>
	<jsp:include page="includes/footer.html"/>
	
</body>
</html>