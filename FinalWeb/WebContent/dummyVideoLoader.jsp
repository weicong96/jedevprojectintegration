
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
		$(document).ready(function(){
			 $("#video").bind("loadedmetadata", function () {
		        var width = this.videoWidth;
		        var height = this.videoHeight;
		        	document.write(width+","+height);
		    });
		});
		$(document).ajaxComplete(function(){
			document.write("haah");
		});
	</script>
	<video id="video" width="100%">
		<source src="files/video/<%= request.getParameter("videoName")%>"></source>
	</video>
	