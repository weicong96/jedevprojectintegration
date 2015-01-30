$(document).ready(function(){
	$('#paraHeading, #paraDescription, #matchPurpose').hide();
	$(".show").show();
});
function onSubmit(){
	/*
	 * "<select name='"+json[0][i]+"'>" +
			    			"<option value='screenshot[]'>Screenshot</option>" +
			    			"<option value='icon'>Icon</option>" +
			    			"<option value='video'>Video</option>" +
			    			"<option value='sourceCode'>Source Code</option>" +
			    			"<option value='compiled'>Packaged File</option>"+
			    			"</select>" +
	 * */
	var names = ["Screenshot","Icon","Video","Source Code","Packaged File"];
	var items = ["screenshot[]","icon","video","sourceCode","compiled"];
	var hasField = [false,false,false,false,false];
	var fieldInvalid = "";
	var valid = true;
	$.each($("#matchPurpose select"),function(index,item){
		for(var i = 0;i< items.length;i++){
			var element = $(item).find(":selected");
			if(element.attr('value') == items[i]){
				hasField[i] = true;
			}
		}
	});
	for(var i = 0; i < hasField.length;i++){
		if(!hasField[i]){
			if(fieldInvalid=="")
				fieldInvalid += names[i];
				else
				fieldInvalid += "," + names[i];
		}
	}
	if(fieldInvalid!=""){
		$("#validPurpose").text("Please select a file for "+fieldInvalid);
		$("#validPurpose").effect('shake');
		valid = false;
	}else
		$("#validPurpose").text("");

	return valid;
}
$(function(){
	$('input[name="entryName"]').on('keyup',function(event){
		var txtValue = $(this).val();
		$.ajax({
			url:'AjaxCheckExistsServlet',
			type: 'POST',
			data : {entryName : txtValue},
			success: function(res){
				$('#validAlreadyExists').text("");
				
				if(res.trim()=="true")
				$('#validAlreadyExists').text("Another submission with the same name already exists, please change your entry name");
				
			}
		});
	});
	$("#allFiles").on("change",function(event){
		var data = new FormData();
		jQuery.each($(this)[0].files, function(i, file) {
		    data.append("files", file);
		});
		$.ajax({
		    url: 'AjaxSubmissionServlet',
		    data: data,
		    cache: false,
		    contentType: false,
		    processData: false,
		    type: 'POST',
		    success: function(data){
		    	var json = $.parseJSON(data);
		    	$(".hideRow").show();
		    	for(var i = 0; i < json[0].length;i++ ){
			    	$("#matchPurpose tr:last").after("<tr><td>"+json[0][i].replace(json[1],"")+"<input type='hidden' value='"+json[0][i]+"' name='file'/></td><td>" +
			    			"<select name='"+json[0][i]+"'>" +
			    			"<option value='screenshot[]'>Screenshot</option>" +
			    			"<option value='icon'>Icon</option>" +
			    			"<option value='video'>Video</option>" +
			    			"<option value='sourceCode'>Source Code</option>" +
			    			"<option value='compiled'>Packaged File</option>"+
			    			"</select>" +
			    			"</td></tr>").on('change','select',function(){
			    				
			    				var selects = $('select');
			    				var items = {};
			    				var ss = "";
		    					console.log("etnered"+($(this)));
			    				for(var i =0;i< selects.length;i++){
			    					var item = selects[i];
			    					var name = item.name;
			    					console.log(item.value+" name:"+item.name);
			    					if(item.value == "screenshot[]"){
			    						if(ss == "")//first entry
			    							ss += name;
			    						else
			    							ss += ","+name;	
			    					}else{
			    						items[item.value] = name ;
			    					}
			    				}
			    				if(ss != "")
			    				items["screenshot[]"] = ss;
			    				var jsonItem = JSON.stringify(items);
			    				$.ajax({
			    					url:"CheckFormatServlet",
			    					data: {data:jsonItem},
			    					type: "POST",
			    					success : function(response){
			    						var invalids = response.split(",");
			    						var invalidFields = "";
			    						for(var i  =0; i < invalids.length;i++){
			    							if(i == 0)
			    								invalidFields += invalids[i];
			    							else
			    								invalidFields += ","+invalids[i];
			    						}
			    						if(invalids.length != 0){
			    							$("#validPurpose").text("Please select a file for "+invalids);
			    							$("#validPurpose").effect('shake');
			    						}else
			    							$("#validPurpose").text("");
			    					}
			    				});
			    			});
			    	
		    	}
		    	$("#timePrefix").val(json[1]);
		    	$('#paraHeading, #paraDescription, #matchPurpose').show();
		    	
		    }
		});
		
	});

});