<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
		<link href="css/main320_480.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="timeanddate/date_time.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.0";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome to ICode Management System</title>
<style>
div#body{
text-align:left;
font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
}
</style>
</head>


<body>
	<jsp:include page="includes/nav.jsp"/>
	
			<div id='body'>
			<div id="header">
			<!--<img src="images/icode-logo.png" class="navLeft"/><br>
			<span>[i.code] 2014 :: i Code to Give</span><br>
			<span>Attractive prizes to be won!</span>-->
			</div>

<table align="center">
<tr><td span style="font-size: 13pt; color:black"><b><span id="date_time"></span></b>
<script type="text/javascript">window.onload = date_time('date_time');</script></td>
</table>

<table align="center">
<tr><th><h2>Theme of the Year</h2></th></tr>
<tr><td>[i.code] 2014 :: i Code to Give</td></tr>
<tr><td></td></tr>
</table>

<table align="center">
<tr align="center"><td><h3>Facebook</h3></td><td><h3>Twitter</h3></td><td><h3>Instagram</h3></td></tr>
<tr align="center"><td><div class="fb-like-box" data-href="https://www.facebook.com/idotcode?ref=stream" data-height="300" data-colorscheme="light" data-show-faces="true" data-header="true" data-stream="false" data-show-border="true"></div></td>
<td><a class="twitter-timeline" href="https://twitter.com/nyptweets" data-widget-id="549104693985546240">Tweets by @nyptweets</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script></td>
<td><!-- SnapWidget -->
<script src="http://snapwidget.com/js/snapwidget.js"></script>
<iframe src="http://snapwidget.com/in/?u=bnlwc2l0fGlufDIwMHwzfDN8fHllc3w1fGZhZGVJbnxvblN0YXJ0fG5vfHllcw==&ve=281214" title="Instagram Widget" class="snapwidget-widget" allowTransparency="true" frameborder="0" scrolling="no" style="border:none; overflow:hidden; width:100%;"></iframe></td></tr>
</table>
</div>
<br>
	<jsp:include page="includes/footer.html"/>
</body>
</html>