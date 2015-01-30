<!DOCTYPE html>
<html lang="en">
<head>
    <title id='Description'>New Registration</title>
    <%@page import="bean.School"%>
	<%@page import="database.DBAO"%>
    <script type="text/javascript" src="scripts/jquery-1.11.1.min.js"></script>   
	<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="jqwidgets/styles/jqx.base.css" type="text/css" />
    <script type="text/javascript" src="jqwidgets/jqxcore.js"></script>
    <link rel="stylesheet" href="jqwidgets/styles/jqx.bootstrap.css" type="text/css"/>
    
    <script type="text/javascript" src="jqwidgets/jqxexpander.js"></script> 
    <script type="text/javascript" src="jqwidgets/jqxvalidator.js"></script> 
    <script type="text/javascript" src="jqwidgets/jqxbuttons.js"></script> 
    <script type="text/javascript" src="jqwidgets/jqxcheckbox.js"></script> 
    <script type="text/javascript" src="jqwidgets/globalization/globalize.js"></script> 
    <script type="text/javascript" src="jqwidgets/jqxcalendar.js"></script> 
    <script type="text/javascript" src="jqwidgets/jqxdatetimeinput.js"></script> 
    <script type="text/javascript" src="jqwidgets/jqxmaskedinput.js"></script> 
    <script type="text/javascript" src="jqwidgets/jqxinput.js"></script> 
    <script type="text/javascript" src="scripts/demos.js"></script> 
    <script type="text/javascript">
    
    var changed1 = false;
    var changed2 = false;
        $(document).ready(function () {
        	  var area = new Array(<%
        	            
                  	DBAO db = new DBAO();
                  	School[] schs = db.getAllSchools();
                  
                  	String complete = "";
                  	for(int i = 0; i<schs.length; i++){
                  		if(i == 0){
                  			complete+="'"+schs[i].getSchoolName()+"'";
                  		}else{
                  			complete+=", '"+schs[i].getSchoolName()+"'";
                  		}
                  	}
                  	out.println(complete);
                  %>);
                  $("#schInput").jqxInput({ placeHolder: " ", width: 171, height: 18, minLength: 1, source: area });
        	
            $("#register").jqxExpander({ toggleMode: 'none',width: '380px', showArrow: false});
            $('#sendButton').jqxButton({ width: 60, height: 25});
            $('#acceptInput').jqxCheckBox({ width: 130});
            $('#sendButton').on('click', function () {
                return $('#testForm').jqxValidator('validate');
            });
            $('.text-input').jqxInput();
            $('#birthInput').jqxDateTimeInput({  width: 200, height: 22, value: new Date(2002, 0, 1) });
            $('#birthInput1').jqxDateTimeInput({  width: 200, height: 22, value: new Date(2002, 0, 1) });
            $('#birthInput2').jqxDateTimeInput({  width: 200, height: 22, value: new Date(2002, 0, 1) });
            // initialize validator.
            $("input[data-reg^='teamMember']").on('change',function(){
            	if(!changed1){
            	var value = $(this).attr('data-reg').replace('teamMember','');
            	//console.log(value);
            	$("input[data-reg='teamMember1']").jqxValidator({
                    hintType: 'label',
                    animationDuration: 0, 
                    rules: [{ input: '#teamMemNameInput'+(value), message: ' Name is required!', action: 'keyup', rule: 'required' },
                            { input: '#teamMemNameInput'+(value), message: 'Your real name must contain only letters!', action: 'keyup', rule: 'notNumber' },
                            { input: '#teamMemNameInput'+(value), message: 'Your real name must be between 6 and 100 characters!', action: 'keyup', rule: 'length=6,100' },
                            { input: '#contactNoInput'+(value), message: 'Your contact number must be 8 digits', action: 'keyup, blur', rule: 'length=8,8' },
                        	{ input: '#nricInput'+(value), message: 'Your NRIC must be 9 characters!', action: 'keyup', rule: 'length=9,9' },
                            { input: '#emailInput'+(value), message: 'E-mail is required!', action: 'keyup, blur', rule: 'required' },
                            { input: '#emailInput'+(value), message: 'Invalid e-mail!', action: 'keyup', rule: 'email' },
                        	]
            	});
            	changed1 = true;
            	}
            	console.log("changed teamMember1 field");
            });
            $('#testForm').jqxValidator({
                hintType: 'label',
                animationDuration: 0,
             rules: [
					{ input: '#teamInput', message: 'Team Name is required!', action: 'keyup, blur', rule: 'required' },
					{ input: '#teamInput', message: 'Your team name must be between 3 and 12 characters!', action: 'keyup, blur', rule: 'length=3,12' },
                    { input: '#schInput', message: 'School Name is required!', action: 'keyup, blur', rule: 'required' },
                    { input: '#TICInput', message: 'Teacher Name is required!', action: 'keyup, blur', rule: 'required' },
                    { input: '#teamMemNameInput', message: ' Name is required!', action: 'keyup, blur', rule: 'required' },
                    { input: '#teamMemNameInput', message: 'Your real name must contain only letters!', action: 'keyup', rule: 'notNumber' },
                    { input: '#teamMemNameInput', message: 'Your real name must be between 6 and 100 characters!', action: 'keyup', rule: 'length=6,100' },
                    { input: '#contactNoInput', message: 'Your contact number must be 8 digits', action: 'keyup, blur', rule: 'length=8,8' },
                    {
                        input: '#birthInput', message: 'Your birth date must be between 1/1/1994 and 1/1/2002.', action: 'valueChanged', rule: function (input, commit) {
                        var date = $('#birthInput').jqxDateTimeInput('value');
                        var result = date.getFullYear() >= 1994 && date.getFullYear() <= 2002;
                        // call commit with false, when you are doing server validation and you want to display a validation error on this field. 
                        return result;
                    }
                    },
                    {
                        input: '#birthInput1', message: 'Your birth date must be between 1/1/1994 and 1/1/2002.', action: 'valueChanged', rule: function (input, commit) {
                        var date = $('#birthInput1').jqxDateTimeInput('value');
                        var result = date.getFullYear() >= 1994 && date.getFullYear() <= 2002;
                        // call commit with false, when you are doing server validation and you want to display a validation error on this field. 
                        return result;
                    }
                    },
                    {
                        input: '#birthInput2', message: 'Your birth date must be between 1/1/1994 and 1/1/2002.', action: 'valueChanged', rule: function (input, commit) {
                        var date = $('#birthInput2').jqxDateTimeInput('value');
                        var result = date.getFullYear() >= 1994 && date.getFullYear() <= 2002;
                        // call commit with false, when you are doing server validation and you want to display a validation error on this field. 
                        return result;
                    }
                    },
                    { input: '#nricInput', message: 'Your NRIC must be 9 characters!', action: 'keyup', rule: 'length=9,9' },
                    { input: '#emailInput', message: 'E-mail is required!', action: 'keyup, blur', rule: 'required' },
                    { input: '#emailInput', message: 'Invalid e-mail!', action: 'keyup', rule: 'email' },
                    { input: '#contactNoInput', message: 'Your Contact No must be 8 characters!', action: 'keyup', rule: 'length=8,8' },
                    { input: '#acceptInput', message: 'You have to accept the terms', action: 'change', rule: 'required', position: 'right:0,0'}]
            });
        });
    </script>
    <style type="text/css">
        .text-input
        {
            height: 21px;
            width: 200px;
        }
        .register-table
        {
            margin-top: 10px;
            margin-bottom: 10px;
            margin-left:auto; 
   			margin-right:auto;
   			
        }
        .register-table td, 
        .register-table tr
        {
            margin: 0px;
            padding: 2px;
            border-spacing: 0px;
            border-collapse: collapse;
            font-family: Verdana;
            font-size: 12px;
        }
        h3 
        {
            display: inline-block;
            margin: 0px;
        }
        body
        {
        	background-color:#ADD8E6;
        }
    </style>
</head>
<body class='default'>
	<jsp:include page="includes/nav.jsp"/>
<br/>
<table align="center">
<tr><td>    <div id="register">
        <div><h3>Registration</h3></div>
        <div style="overflow: hidden;">
            <div id="testForm">
            <form action="RegServlet" method="POST">
                <table class="register-table">
                	<tr>
                    	<th valign="top">Please fill in all details</th>
                    </tr>
                    <tr>
                    	<td><hr/></td>
                    	<td><hr/></td>
                    </tr>
                	<tr>
                		<td valign="top">Team Name:</td>
                		 <td valign="top"><input type="text" id="teamInput" name="teamName" class="text-input" /></td>
                	</tr>
                    <tr><!-- From DB -->
                        <td valign="top" >School Name:</td>
                        <td valign="top"><input type="text" id="schInput" name="schName" class="text-input" /></td>
                    </tr>
                    <tr>
                        <td valign="top" >Teacher In-charge:</td>
                        <td valign="top"><input type="text" id="TICInput" name="tic" class="text-input" /></td>
                    </tr>
                     <tr>
                    	<td><hr/></td>
                    	<td><hr/></td>
                    </tr>
                    <tr>
                    	<td><b>Team Leader</b></td>
                    </tr>
                    <tr>
                    	<td><hr/></td>
                    	<td><hr/></td>
                    </tr>
                    <tr>
                        <td valign="top">Team Member Name:*</td>
                        <td valign="top"><input type="text" id="teamMemNameInput" name="memberName" class="text-input" /></td>
                    </tr>
                    <tr>
                        <td valign="top">Gender:</td>
                        <td valign="top"><select id="gender" name="gender"><option value="M">Male</option><option value="F">Female</option></select></td>
                    </tr>
                    <tr>
                        <td valign="top">Date of Birth:</td>
                        <td valign="top"><div id="birthInput" name="dob"></div></td>
                    </tr>
                    <tr>
                        <td valign="top">NRIC:</td>
                        <td valign="top"><input type="text" id="nricInput" name="nric" class="text-input"/></td>
                    </tr>
                    <tr>
                        <td valign="top">Contact No:</td>
                        <td valign="top"><input type="text" id="contactNoInput" name="contactNo" class="text-input"/></td>
                    </tr>
                    <tr>
                        <td valign="top">E-mail Address:</td>
                        <td valign="top"><input type="text" id="emailInput" placeholder="someone@mail.com" name="emailAdd" class="text-input" /></td>
                    </tr>
                    <tr>
                        <td valign="top">Food Preference:</td>
                        <td valign="top"><select id="foodP" name="foodPre"><option value="Halal">Halal</option><option value="Vegetarian">Vegetarian</option></select></td>
                    </tr>
                    <tr>
                        <td valign="top">Allergic Comments:</td>
                        <td valign="top"><textarea rows="5" cols="25" style="resize:none" name="allergicC"></textarea></td>
                    </tr>
                    <tr>
                        <td valign="top">I will like to attend:</td>
                        <td valign="top"><select id="attendance" name="attendance"><option value="Competition">Competition</option><option value="Both">Competition & Workshop</option></select></td>
                    </tr>
                    <tr>
                    	<td><hr/></td>
                    	<td><hr/></td>
                    </tr>
                    <tr>
                    	<td><b>Team Member</b></td>
                    </tr>
                    <tr>
                    	<td><hr/></td>
                    	<td><hr/></td>
                    </tr>
                    <tr>
                        <td valign="top">Team Member Name:</td>
                        <td valign="top"><input data-reg="teamMember1" type="text" id="teamMemNameInput1" name="memberName" class="text-input" /></td>
                    </tr>
                    <tr>
                        <td valign="top">Gender:</td>
                        <td valign="top"><select data-reg="teamMember1" id="gender" name="gender"><option value="M">Male</option><option value="F">Female</option></select></td>
                    </tr>
                    <tr>
                        <td valign="top">Date of Birth:</td>
                        <td valign="top"><div data-reg="teamMember1" id="birthInput1" name="dob"></div></td>
                    </tr>
                    <tr>
                        <td valign="top">NRIC:</td>
                        <td valign="top"><input data-reg="teamMember1"  type="text" id="nricInput1" name="nric" class="text-input"/></td>
                    </tr>
                    <tr>
                        <td valign="top">Contact No:</td>
                        <td valign="top"><input data-reg="teamMember1"  type="text" id="contactNoInput1" name="contactNo" class="text-input"/></td>
                    </tr>
                    <tr>
                        <td valign="top">E-mail Address:</td>
                        <td valign="top"><input type="text" id="emailInput1" placeholder="someone@mail.com" name="emailAdd" class="text-input" /></td>
                    </tr>
                    <tr>
                        <td valign="top">Food Preference:</td>
                        <td valign="top"><select id="foodP" name="foodPre"><option value="Halal">Halal</option><option value="Vegetarian">Vegetarian</option></select></td>
                    </tr>
                    <tr>
                        <td valign="top">Allergic Comments:</td>
                        <td valign="top"><textarea rows="5" cols="25" style="resize:none" name="allergicC"></textarea></td>
                    </tr>
                    <tr>
                        <td valign="top">I will like to attend:</td>
                        <td valign="top"><select id="attendance" name="attendance"><option value="Competition">Competition</option><option value="Both">Competition & Workshop</option></select></td>
                    </tr>
                    <tr>
                    	<td><hr/></td>
                    	<td><hr/></td>
                    </tr>
                    <tr>
                    	<td><b>Team Member</b></td>
                    </tr>
                    <tr>
                    	<td><hr/></td>
                    	<td><hr/></td>
                    </tr>
                    <tr>
                        <td valign="top">Team Member Name:</td>
                        <td valign="top"><input data-reg="teamMember1" type="text" id="teamMemNameInput2" name="memberName" class="text-input" /></td>
                    </tr>
                    <tr>
                        <td valign="top">Gender:</td>
                        <td valign="top"><select id="gender" name="gender"><option value="M">Male</option><option value="F">Female</option></select></td>
                    </tr>
                    <tr>
                        <td valign="top">Date of Birth:</td>
                        <td valign="top"><div id="birthInput2" name="dob"></div></td>
                    </tr>
                    <tr>
                        <td valign="top">NRIC:</td>
                        <td valign="top"><input type="text" id="nricInput2" name="nric" class="text-input"/></td>
                    </tr>
                    <tr>
                        <td valign="top">Contact No:</td>
                        <td valign="top"><input type="text" id="contactNoInput2" name="contactNo" class="text-input"/></td>
                    </tr>
                    <tr>
                        <td valign="top">E-mail Address:</td>
                        <td valign="top"><input type="text" id="emailInput2" placeholder="someone@mail.com" name="emailAdd" class="text-input" /></td>
                    </tr>
                    <tr>
                        <td valign="top">Food Preference:</td>
                        <td valign="top"><select id="foodP" name="foodPre"><option value="Halal">Halal</option><option value="Vegetarian">Vegetarian</option></select></td>
                    </tr>
                    <tr>
                        <td valign="top">Allergic Comments:</td>
                        <td valign="top"><textarea rows="5" cols="25" style="resize:none" name="allergicC"></textarea></td>
                    </tr>
                    <tr>
                        <td valign="top">I will like to attend:</td>
                        <td valign="top"><select id="attendance" name="attendance"><option value="Competition">Competition</option><option value="Both">Competition & Workshop</option></select></td>
                    </tr>
                     <tr>
                        <td colspan="2" style="padding: 5px;"><div id="acceptInput" style="margin-left: 50px;">I accept terms</div></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right;"><input type="submit" value="Submit" id="sendButton"/></td>
                    </tr>
                   </table>
                        </form>
               </div>
               </div>
        </div></td></tr>
     </table>
     	<jsp:include page="includes/footer.html"/>
</body>
</html>