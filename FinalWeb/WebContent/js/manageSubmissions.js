var checked = false;
$(function() {
	$('#selectAll').click(function() {
		if (checked) {
			$('#body table input:checkbox').prop('checked', false);
			checked = false;
		} else {
			$('#body table input:checkbox').prop('checked', true);
			checked = true;
		}
	});
	$('#downloadButton').click(function() {
		$('#downloadSelection').slideDown();
	});
	$("#deleteConfirm").click(function(){
		var items = $('input[name^="selected"]:checked');
		
		var values = [];
		for(var i = 0; i < items.length;i++){
			values[i] = $(items[i]).val();
		}
		console.log(values);
		$.ajax({
			async:false,
			url: "DeleteSubmission",
			data:{ id:values},
			dataType:'json',
			type:"POST",
			success: function(res){
				console.log(res);
			}
		});
		location.reload();
	});
	$('#entryDiv').on('click',"a[data-sortvalue]",function(){
		console.log("fdsfs");
		var sortValue = $(this).attr('data-sortvalue');
		console.log(sortValue+" dd");
		$.get("ManageSubmissions?sort="+sortValue+"&useJSON=true", function(data){
			$("#entryDiv").html("");
			$("#entryDiv").html(data);
		});
	});
	$("#entryDiv").on("click","img[data-value='true']",function(){
		var key = $(this).attr("data-key");
		var ele = $(this);
		/*
		 * Screenshot	Icon	Video	Source Code	Packaged File
		 * */
		$.ajax({
			type:"GET",
			url: "GetRevisionDetails?submissionID="+ele.attr("data-id"),
			success: function(res){
				var obj = $.parseJSON(res);
				var textToShow = "";
				
				if(obj.length >= 3){
					obj = obj.slice(-2);
				}
				var imageKeys = ["icon","screenshot"];
				var textKeys = ["sourceCode","compiled","description"];
				var videoKeys = ["video"];
				
				for(var i = 0; i < obj.length;i++){
					var item = obj[i];
					textToShow += item["dateSubmitted"]+" : <br>";
					
					if(isInArray(imageKeys, key)){
						if(key!="screenshot"){
							item[key] = item["name"]+"_"+item[key];
							textToShow += "<img style='margin-left:10px;' height='250px' src='files/"+key+"/"+item[key]+"'/><hr>";
						}else{
							var ss = item[key].split(",");
							for(var j = 0; j < ss.length;j++){
								var single = item["name"]+"_"+ss[j];
								if(!(single.indexOf("old") > -1) && i != (obj.length-1)){
									single = item["name"]+"_"+ss[j]+"old";
								}
								console.log(j);
								textToShow += "<img style='margin-left:10px;display:inline' height='150px' src='files/"+key+"/"+single+"'/>";
							}
							textToShow+="<hr>";
						}
					}else if(isInArray(textKeys, key)){
						textToShow += "<span style='margin-left:10px;'>"+item[key]+"</span><hr>";
					}else if(isInArray(videoKeys, key)){
						textToShow += "<video width='640' height='480' style='margin-left:10px;' controls><source src='files/"+key+"/"+item[key]+"'/></video><hr>";
					}
				}
				attachPopovers("img[data-value='true'][data-id='"+ele.attr("data-id")+"'][data-key='"+ele.attr("data-key")+"']",textToShow);
			}
		});
	});
});
function isInArray(array, search)
{
    return array.indexOf(search) >= 0;
}
function attachPopovers(element,content){
	$(element).popover({
		content: content,
		placement: "right",
		trigger: "click",
		html:true,
		title: "Changes",
		template: "<div class='popover popover-width' style='width:600px' role='tooltip'>" +
				"<div class='arrow'>" +
				"</div>" +
				"<h6 class='popover-title'></h6>" +
				"<div class='popover-content'></div>" +
				"</div>"
	});
}
function checkFiles() {
	var valid = false;
	var downloadValues = $('input[name="files"]:checked').val();
	var selection = $('input[name^="selected"]:checked').val();

	$("#validDownload").text("");
	$("#validSelection").text("");

	if (selection != null && selection.length != 0) {
		if (downloadValues != null && downloadValues.length != 0) {
			valid = true;
		} else {
			$('#downloadSelection').effect("shake");
			$("#validDownload").text("Please select valid download options!");
		}
	} else {
		$('#entrySelection').effect("shake");
		$("#validSelection").text("Please select valid entries!");
	}

	return valid;
}
