<%@page import="model.GroupJPA,model.Submission,java.util.List,java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="is" uri="WEB-INF/customtags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link href="css/exportSubmission.css" rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script>
	var checked = false;
	$(function(){
		$('#selectAll').click(function(){
			if(checked){
	        	$('#body table input:checkbox').prop('checked',false);
				checked = false;
			}else{
				$('#body table input:checkbox').prop('checked',true);
				checked = true;
			}
		});
		$('#changeDeadline').click(function(){
			$('#changeDateDiv').slideToggle();
		});
	});
	function validate(){
		var elements = $('input[type="checkbox"][name="selected[]"]:checked');
		if(elements.length == 0){
			$('#validMsg').removeClass("hide");
			return false;
		}
		return true;
	}
		
	
	
</script>

<title>Insert title here</title>

</head>


<body>
	<jsp:include page="includes/nav.jsp" />

	<%
	GroupJPA[] list = (GroupJPA[])request.getAttribute("GroupList");
		if(list == null){
		   response.sendRedirect("ExtendDeadline");
		}
	%>
	<div id='body'>
		<h1>Manage Team Deadline</h1> 
		<is:valid/>
		<form action="ExtendDeadline" method="post">
		<input type="button" value="Select All" id="selectAll"  class='btn btn-default'/> 
		<input type="button" style="float:right;" value="Change Submission Deadline" id="changeDeadline" class='btn btn-default'/>
		<br><span id="validMsg" class="errorMsg hide">Please select at least one entry</span>
		
		<div id="changeDateDiv" style="display:none;text-align:center;width:100%">
			Deadline : <input type="datetime-local" name="dateTime" pattern="dd-mm-yyyy" required />  <br><br>
			Reason : <input type='text' name='reason' required/><br><br>
			<input type="submit" value="Change deadline" onclick="return validate()"/>
		</div> 
		
		<table width="100%">
			<tr>
				<th></th>
				<th><a href='ExtendDeadline?sort=teamName'>Team Name</a>
				</th>
				<th><a href='ExtendDeadline?sort=submittedEntries'>Submitted Entries</a>
				</th>
				<th><a href='ExtendDeadline?sort=deadline'>Deadline</a>
				</th>
				<th><a href='ExtendDeadline?sort=reason'>Reason</a>
				</th>
			</tr>

			<%
				for(int i = 0; i < list.length;i++){
					GroupJPA item = list[i];
			 %>
			  
				<tr>
					<td><input type="checkbox" name="selected[]" value="<%= item.getGroupCode() %>" /></td>
					
					<td><%= item.getGroupName() %></td>
					<td><% 
					 		List<Submission> submissionList = item.getSubmissions();
							if(submissionList != null){
								for(int j =0; j < submissionList.size();j++){
									%>
									<a href='PreviewSubmission?id=<%= submissionList.get(j).getSubmissionID()%>'><%= submissionList.get(j).getName() %></a><br>
									<%
								}					 		
					 		}
					%></td>
					<td>
						<%
							SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:aa");
							if(item.getDeadline() != null && item.getDeadline().getDeadLine() != null)
							out.println(f.format(item.getDeadline().getDeadLine()));
						 %>
					</td>
					<td>
						<%
							if(item.getDeadline() != null && item.getDeadline().getReason() != null)
								out.println(item.getDeadline().getReason());
						 %>
					</td>
				</tr>
			<%
				}
			 %>
		</table>
		</form>
		
	</div>
	<jsp:include page="includes/footer.html" />


	
</body>
</html>