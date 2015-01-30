<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
		
		<meta name="viewport" content="width=device-width, intial-scale=1.0">
			<link rel="stylesheet" href="css/bootstrap.css">
			<link rel="stylesheet" href="css/bootstrap-theme.css">
			<script src="js/jqueryui.js"></script>
			<link rel="stylesheet" href="css/jqueryui.css">

				<script src="js/bootstrap.js"></script>
				
<link href="css/font-awesome.css" rel="stylesheet">
			<div class="navbar navbar-default navbar-static-top navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a href="home.html" class="navbar-brand">[i.code]</a>
				<button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				
				</div>
				<div class="collapse navbar-collapse navHeaderCollapse">
					<ul class="nav navbar-nav navbar-left">
						<li class="active"><a>Home</a></li>
						<li><a href='about.html'>About</a></li>
						<li><a href='ViewSubmission'>Entries</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<%
							String teamCode = (String)request.getSession().getAttribute("username");
							if(teamCode==null){
						 %>
						<li><a href='login.jsp'>Login</a></li>
						<li><a href='RegisterS1.jsp'>Sign Up</a></li>
						<%
							}else{
						 %>
						 
						<li><a>Welcome, <%= teamCode %> </a></li>
						<li><a href='logout.jsp'>Logout </a></li>
						 <%
						 	}
						  %>
					</ul>
				</div>
			</div>
		</div>
		<div class="container-fluid text-center mainBody">
		
		<img src="images/banner.png" class="img-responsive center-block"/>