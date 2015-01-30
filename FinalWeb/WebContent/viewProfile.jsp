<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="database.DAO,,bean.Account,bean.Group,bean.School,bean.Registration,util.Links"%>
<%
	String username = (String) session.getAttribute("username");
	DAO dbao = new DAO();
	Group group = dbao.retrieveGroup(username);
	School sch = dbao.retrieveSchool(username);
	Registration[] reg = dbao.retrieveGroupMemberDetails(username);
%>
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
<title>View Profile Page</title>
</head>


<body>

	<div id='body'>
		<jsp:include page="includes/nav.jsp" />
		<form action="<%=Links.LINK_EDITPROFILE%>" method="POST">
			<br>

			<h1 align="center">View Profile</h1>

			<table align="center">
				<tr style="height: 30px">
					<td colspan="8" align="right"><a href="<%=Links.LINK_VIEWMESSAGES%>"
						style="font-size: 15px; float: right;">View Updates</a>
					</td>
				</tr>
				<tr style="height: 30px">
					<td><b>Account Username:</b></td>
					<td colspan="7" align="left"><%=username%></td>
				</tr>
				<tr style="height: 30px">
					<td><b>Team Name:</b></td>
					<td colspan="7" align="left"><%=group.getTeamName()%></td>
				</tr>
				<tr style="height: 30px">
					<td><b>School Name:</b></td>
					<td colspan="7" align="left"><%=sch.getSchoolName()%></td>
				</tr>
				<tr style="height: 30px">
					<td><b>School Email:</b></td>
					<td colspan="7" align="left"><%=sch.getSchoolEmail()%></td>
				</tr>
				<tr style="height: 30px">
					<td><b>Teacher In-charge:</b></td>
					<td colspan="7" align="left"><%=reg[0].getTic()%></td>
				</tr>
				<tr style="height: 30px">
					<td><br></td>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td style="width: 200px"><b>Team Member Name:* </b>
					</td>
					<td style="width: 200px"><%=reg[i].getMemberName()%></td>
					<td style="width: 30px">&nbsp;</td>
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td><b>Gender: </b>
					</td>
					<td><%=reg[i].getGender()%></td>
					<td>&nbsp;</td>
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td><b>Date of Birth: </b>
					</td>
					<td><%=reg[i].getDOB()%></td>
					<td>&nbsp;</td>
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td><b>NRIC: </b>
					</td>
					<td><%=reg[i].getNric()%></td>
					<td>&nbsp;</td>
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td><b>Contact No: </b>
					</td>
					<td><%=reg[i].getContactNo()%></td>
					<td>&nbsp;</td>
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td><b>Email Address: </b>
					</td>
					<td><%=reg[i].getEmailAdd()%></td>
					<td>&nbsp;</td>
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td><b>Food Preference: </b>
					</td>
					<td><%=reg[i].getFoodPre()%></td>
					<td>&nbsp;</td>
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td><b>Other Comments: </b>
					</td>
					<td><%=reg[i].getFoodOtherCom()%></td>
					<td>&nbsp;</td>
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<%
						for (int i = 0; i < reg.length; i++) {
					%>
					<td><b>I will like to attend: </b>
					</td>
					<td><%=reg[i].getAttendance()%></td>
					<td>&nbsp;</td>
					<!-- <%=reg[i].getAttendance()%> -->
					<%
						}
					%>
				</tr>
				<tr style="height: 30px">
					<td colspan="8" align="left"><b>* Represents the Leader</b></td>
				</tr>

			</table>
			<br>
			<table align="center">
				<tr>
					<td><input type="submit" value="Edit Profile"></td>
				</tr>
			</table>
		</form>
		<br> <br>
	</div>
	<jsp:include page="includes/footer.html" />

</body>
</html>