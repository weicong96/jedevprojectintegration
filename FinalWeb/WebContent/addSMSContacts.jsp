<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	import="java.util.ArrayList, bean.Group,bean.Registration"
	%>
<html>
<head>
<title>addSMSContacts</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  
<link rel="stylesheet" href="css/bootstrap.css">
			<link rel="stylesheet" href="css/bootstrap-theme.css">
			<script src="js/jqueryui.js"></script>
			<link rel="stylesheet" href="css/jqueryui.css">
 <script src="js/bootstrap.js"></script>
 
<style>
	
</style>
<script>
	$(function(){
		$("#accordion").accordion();
		
		$("#selectLeaders").click(function(){
			$("input[data-leader='Y']").attr('checked','true');
		});
		
		$("#selectMembers").click(function(){
			$("input").attr('checked','true');
		});
		$("#close").click(function(){
			var checked = $('input[type="checkbox"][name="contactNames[]"]:checked');
			var text  = "";
			checked.each(function(){
			 var i = checked.index(this);
					var ele = checked.eq(i);
				if(i == 0)
				text += ele.val();
				else 
				text += ","+ele.val();	
			});
			
			window.opener.$("textarea[name='contact']").val(text);
			window.close();
		});
		
	});
	
</script>
</head>
<body>
	
	<div id='body'>
			<div style='width:100%;text-align:right'>
			<input type='button' class='btn btn-primary' style='margin:10px;text-align:right' id='selectMembers' value='Select all Members'/>
			
			<input type='button' class='btn btn-primary' style='margin:10px;text-align:right' id='selectLeaders' value='Select all Leaders'/>
			<input type='button' class='btn btn-success' style='margin:10px;text-align:right' id='close' value='Close'/>
			</div>
			<div id='accordion'>
			<%
				ArrayList<Group> grpList = (ArrayList<Group>)request.getAttribute("groups");
			 
			 	for(int i =0 ; i < grpList.size();i++){
			 		
			 %>
			 	<h3><%=  grpList.get(i).getTeamName()%></h3>
			 	<div>
			 		<% 
			 			ArrayList<Registration> studentList = grpList.get(i).getStudents();
			 		for(int j = 0;j< studentList.size();j++){%>
			 			<input type='checkbox' name='contactNames[]' value='<%= studentList.get(j).getContactNo()%>' data-leader='<%= studentList.get(j).getLeaderStatus()%>'/><%= studentList.get(j).getLeaderStatus().equalsIgnoreCase("Y") ? "<b>Leader</b>" : ""%> <%= studentList.get(j).getMemberName()%>(<%= studentList.get(j).getContactNo()%>)<br>
			 		<% }%>
			 		
			 	</div>
			 	
			 <%
			 	}
			  %>
			  </div>
	</div>
</body>
</html>