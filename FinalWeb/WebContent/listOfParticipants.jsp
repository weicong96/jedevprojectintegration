<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="bean.Registration,bean.School,database.ReportManager,java.util.ArrayList"%>
<!DOCTYPE HTML>

 
<html>
<head>
<title></title>
<link href="css/listofparticipant.css" rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
	<jsp:include page="includes/nav.jsp" />


	<div id='body'>
			
<!-- 			Print web page function -->

			<script>
				function myFunction() {
					window.print();
				}
				
				
			</script>
			
			<div id='input'>
			
			<form method="get" action ="getListOfParticipant">
			
			<br><br><br>
			
			Select the school:
			<select name="school" id="school">
			
			<option>All</option>
			
<!-- 			Retrieve the school name from database -->
				<%
					ReportManager mgr = new ReportManager();
					School[] schools = mgr.getAllSchools();

					for (int s = 0; s < schools.length; s++){
				%>

				<option><%= schools[s].getSchoolName() %></option>
				<% } 
				%>
				
			</select>
			
			<input type="submit" onclick="getSchoolName()" value='Click Me!'>
			
			<!-- 			Button to click for printing the page -->

			<button onclick="myFunction()">Print this page</button>
			
			</form>
			
			</div>
		

		<br>

		<table cellpadding="10" RULES=COLS FRAME=VSIDES>

			<tr>
			
				<th>Name</th>
				<th>School</th>
				<th>Teacher in-charge</th>

			</tr>
			<%
			String sklName = (String) request.getAttribute("schoolName");
			
				if(sklName == null || sklName.equals("All")){
				
				
				ArrayList<Object> participants = new ArrayList<Object>();
				
				participants = mgr.getListOfNonSpecificParticipants();

					for (int i = 0; i < participants.size(); i++) {
	
						for (int ii = 0; ii < 1; ii++) {
	
							Registration r1 = new Registration();
							School s1 = new School();
							Object[] arr = new Object[2];
							arr = (Object[]) participants.get(i);
	
							r1 = (Registration) arr[0];
							s1 = (School) arr[1];
							
				%>			
			<tr>
				<td><%=r1.getMemberName()%></td>
				<td><%=s1.getSchoolName()%></td>
				<td><%=r1.getTic()%></td>
			</tr>
					<%		
							
						}
					}
				}

				else{
				ArrayList<Object> participants = new ArrayList<Object>();
				participants = (ArrayList<Object>)request.getAttribute("participantsList");
				
				for (int i = 0; i < participants.size(); i++) {
				
				System.out.println("2nd try"+participants.size());
				
					for (int ii = 0; ii < 1; ii++) {

						Registration r1 = new Registration();
						School s1 = new School();
						Object[] arr = new Object[2];
						arr = (Object[]) participants.get(i);

						r1 = (Registration) arr[0];
						s1 = (School) arr[1];
						
			%>

			<tr>
				<td><%=r1.getMemberName()%></td>
				<td><%=s1.getSchoolName()%></td>
				<td><%=r1.getTic()%></td>
			</tr>
				
			<%
				}
				}
				}
			%>
		</table>
		
		
<BR>
<BR>

	</div>
	<jsp:include page="includes/footer.html" />

</body>
</html>