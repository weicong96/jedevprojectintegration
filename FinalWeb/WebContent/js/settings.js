$(function(){
		
		$("input[type='checkbox']").each(function(){
			var value= $(this).attr("value");
			$(this).on('change',function(){
				var checked = $(this).prop("checked");
				var element = $("input[type='hidden'][value='hidden"+value+"']");
				if(checked){
					element.attr("disabled",true);
					element.attr("checked",false);
				}else{
					element.attr("disabled",false);
					element.attr("checked",true);
				}
				$(this).attr("checked",checked);
			});
			$(this).trigger('change');
		});
		$("select").on('change',function(){
			var currentName = $(this).attr("name");
			var inputField = $("input[name='"+currentName.split("_")[0]+"_fileFormat']");
			
			if($("select[name='"+currentName+"'] option:selected").attr('value') === "File"){
				inputField.attr('disabled',false);//allow to be posted
				inputField.attr('type','text');
				console.log("File sleected");
			}else{
				inputField.attr('disabled',true);//allow to be posted
				inputField.attr('type','hidden');
			}
			console.log(currentName);
		});
		$("#addRow").click(function(){
			var highestid = 0;
			$('.newTextField').each(function(index){
				var item = $(this);
				var latestid = item.attr('name').split("_")[0].replace("new","");
				if(latestid > highestid)
					highestid = latestid;
			});
			highestid+=1;
			var newName = "new"+highestid+"_";
			$("#tableRows tr:last").after(
					"<tr><td><input type='text' class='newTextField' name='"+newName+"name'/></td><td>" +
						"<select name='"+newName+"format'>"+
						"<option value='Text Field'>Text Field</option>"+
						"<option value='Paragraph of text'>Paragraph of text</option>"+
						"<option value='File'>File</option>"+
						"</select>" +
						"<input type='hidden' name='"
						+newName
						+ "fileFormat'"
						+ " disabled/>"+
						"</td>" +
						"<td><input type='checkbox' value='new"+highestid+"' name='"+newName+"required' /></td>"+
						"<td><input type='hidden' value='hiddennew"+highestid+"' name='"+newName+"required' checked='checked'/></td></tr>"
						
			);
			$("select").on('change',function(){
				var currentName = $(this).attr("name");
				var inputField = $("input[name='"+currentName.split("_")[0]+"_fileFormat']");
				
				if($("select[name='"+currentName+"'] option:selected").attr('value') === "File"){
					inputField.attr('disabled',false);//allow to be posted
					inputField.attr('type','text');
					console.log("File sleected");
				}else{
					inputField.attr('disabled',true);//allow to be posted
					inputField.attr('type','hidden');
				}
				console.log(currentName);
			});
			$("input[type='checkbox']").each(function(){
				var value= $(this).attr("value");
				$(this).on('change',function(){
					var checked = $(this).prop("checked");
					var element = $("input[type='hidden'][value='hidden"+value+"']");
					if(checked){
						element.attr("disabled",true);
						element.attr("checked",false);
					}else{
						element.attr("disabled",false);
						element.attr("checked",true);
					}
					$(this).attr("checked",checked);
				});
				$(this).trigger('change');
			});
		});
});