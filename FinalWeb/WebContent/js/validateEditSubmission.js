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
	$("span.glyphicon-remove").click(function(){
		var id = $(this).attr('data-fileValue');
		var parent = $(this).parent().parent();
		$.ajax({
			data:{id:id},
			url: "DeleteFileServlet",
			type: "POST",
			success:function(res){
				if(res.trim()=="true"){
					parent.fadeOut();
					parent.find("select").attr('disabled','true');
				}
			}
		});
		console.log("clicked!");
	});
});