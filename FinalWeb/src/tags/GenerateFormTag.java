package tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import model.Extrafield;
import model.Submission;

import com.google.gson.Gson;

public class GenerateFormTag extends SimpleTagSupport{
	//private Boolean edit=false;
	@Override
	public void doTag() throws JspException, IOException {
		Boolean edit = (Boolean)((PageContext) this.getJspContext()).getRequest().getAttribute("edit");
		if(edit == null)
			edit = false;
		String html = "";
		ArrayList<Extrafield> inputFields = (ArrayList<Extrafield>)((PageContext) this.getJspContext()).getRequest().getAttribute("inputFields");
		
		//boolean edit = Boolean.valueOf(((PageContext)this.getJspContext()).getRequest().getParameter("edit"));
		for(Extrafield field : inputFields){
			boolean required = Boolean.valueOf(field.getRequired());
			String inputType = "";
			boolean textArea = false;
			String camelName = field.getCamelCaseName();
			if(field.getFormat().equals("Text Field")){
				inputType= "text";
			}else if(field.getFormat().equals("Paragraph of text")){
				textArea = true;
			}else{
				inputType = field.getFormat();
			}
			html+= "<tr>";
			html+= "<td>";
			html+= field.getName().replace("[]", "")+" "+(required ? "*" : "");
			html+= "</td>";	
			html+= "</tr>";

			if( edit && !textArea && !inputType.equals("text")){
				html+= "<tr>";
				html+= "<td>";
				String value = getValueForDynamicFieldKey(field.getCamelCaseName().replace("[]", ""));
				if(value == null)
					value = "";
				html+= "<b>"+value+" </b>";
				html+= "</td>";	
				html+= "</tr>";
			}
			html+= "<tr>";
			html+= "<td>";
			String requiredStr = (	required ? "required" : "");
			
			if(textArea)
				html+= "<textarea name='"+camelName+"' "+requiredStr+">"+(edit ? getValueForDynamicFieldKey(field.getCamelCaseName()) : "")+"</textarea>";
			else if(!inputType.equals("text"))
				html+= "<input name='"+camelName+"' type='file' accept='"+(inputType)+"' "+(!edit && required ? "required" : "")+" "+(field.getName().contains("[]") ? "multiple ": " ")+" />";
			else
				html+= "<input name='"+camelName+"' type='text' value='"+(edit ? getValueForDynamicFieldKey(field.getCamelCaseName()) : "")+"' "+ (required ? "required" : "")+" />";
					/*
			 * 
			 * if(textArea)
				html+= "<textarea name='"+camelName+"' "+(!edit && required ? "required" : "")+">"+(edit ? this.getValueForDynamicFieldKey(inputFields.get(i).getCamelCaseName())  : "")+"</textarea>";
			else if(!type.equals("text"))
				html+= "<input name='"+camelName+"' type='file' accept='"+(type)+"' "+ ( !edit && required ? "required" : "")+(inputFields.get(i).getName().contains("[]") ? " multiple" : "")+"/>";
			else
				html+= "<input name='"+camelName+"' type='text' value='"+(edit ? this.getValueForDynamicFieldKey(inputFields.get(i).getCamelCaseName())  : "")+"' "+ ( !edit && required ? "required" : "")+"/>
			 * */
			html+= "</td>";
			html+= "</tr>";
			
		}
		/*
		for(int i = 0; i < inputFields.size();i++){
			if(inputFields.get(i).getCamelCaseName().equals("lastChanged"))
				continue;
			boolean required = Boolean.valueOf(inputFields.get(i).getRequired());
			String type = "";
			String camelName = inputFields.get(i).getCamelCaseName();
			boolean textArea = false;
			 //"Text Field", "Paragraph of text", "File"
			if(inputFields.get(i).getFormat().equals("Text Field")){
				type="text";
			}else if(inputFields.get(i).getFormat().equals("Paragraph of text")){
				textArea = true;
			}else{
				type = inputFields.get(i).getFormat();
			}
			if(inputFields.get(i).getCamelCaseName().equals("selectedImage")){
				html+="<tr>";				
				html+="<td>";	
				html+="<div id='iconFinder' style='display:none;'>We have found the following image files in your .apk file, choose ONE image as your application icon<div style='height:250px;overflow-y:scroll;'>	<table id='images' width='100%'><tr><th>Selection</th><th>Image Name</th><th>Folder</th><th>Image</th></tr></table></div>	OR<br></div>";
				html+="</td>";	
				html+="</tr>";	
			}else{
			html+="<tr>";
			html+="<td>";
			html+= inputFields.get(i).getName().replace("[]", "")+" "+ ( required ? "*" : "");
			html+="</td>";
			html+="</tr>";
			if(edit && !inputFields.get(i).getCamelCaseName().equals("entryName") && !inputFields.get(i).getCamelCaseName().equals("description")){
				html+="<tr>";
				html+="<td>";
				html+= "<b> Current : "+this.getValueForDynamicFieldKey(inputFields.get(i).getCamelCaseName())+"</b>";
				html+="</td>";
				html+="</tr>";	
			}
			html+="<tr>";
			html+="<td>";
			if(textArea)
				html+= "<textarea name='"+camelName+"' "+(!edit && required ? "required" : "")+">"+(edit ? this.getValueForDynamicFieldKey(inputFields.get(i).getCamelCaseName())  : "")+"</textarea>";
			else if(!type.equals("text"))
				html+= "<input name='"+camelName+"' type='file' accept='"+(type)+"' "+ ( !edit && required ? "required" : "")+(inputFields.get(i).getName().contains("[]") ? " multiple" : "")+"/>";
			else
				html+= "<input name='"+camelName+"' type='text' value='"+(edit ? this.getValueForDynamicFieldKey(inputFields.get(i).getCamelCaseName())  : "")+"' "+ ( !edit && required ? "required" : "")+"/>";
			html+="</td>";
			html+="</tr>";
			}
		}
		*/
		this.getJspContext().getOut().print(html);
		
	}
	private String getValueForDynamicFieldKey(String key){
		Submission sub = (Submission)((PageContext) this.getJspContext()).getRequest().getAttribute("submission"); 
			String value = "";
			if(key.equals("entryName")){
				value = sub.getName();
			}else if(key.equals("description")){
				value = sub.getDescription();
			}else if(key.equals("compiled")){
				value = sub.getCompiled();
			}else if(key.equals("icon")){
				value = sub.getIcon();
			}else if(key.equals("screenshot[]")){
				value = sub.getScreenshot();
			}else if(key.equals("video")){
				value = sub.getVideo();
			}else if(key.equals("sourceCode")){
				value = sub.getSourceCode();
			}else if(key.equals("lastChanged")){
				value = sub.getDateSubmitted().toString();
			}else if(!key.equals("selectedImage")){
				Map<String,String> additional = new Gson().fromJson(sub.getAdditional(), new HashMap<String,String>().getClass());
				if(additional!=null)
					value = additional.get(key);
			}
			return value;
		
	}
	
	
}
