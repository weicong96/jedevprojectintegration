package tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import model.Extrafield;
import model.Submission;

import com.google.gson.Gson;

public class GenerateTableTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter jw = this.getJspContext().getOut();
		ArrayList<Extrafield> inputFields = (ArrayList<Extrafield>)((PageContext) this.getJspContext()).getRequest().getAttribute("inputFields");
		Submission[] subList = (Submission[])((PageContext) this.getJspContext()).getRequest().getAttribute("SubmissionList");
		
		String html = "";
		
		html+="<tr>";
		html+="<td></td>";
		String[] defaultFields = {"Team Name","Entry Name","Description","Screenshot","Icon","Video","Source Code","Packaged File","Last Changed","Status"};
		String[] sortFields = {"groupCode","entryName","description","screenshot","icon","video","sourceCode","compiled","lastChanged","status"};
		
		for(int i = 0; i < defaultFields.length;i++){
			html+= "<th><a data-sortvalue='"+sortFields[i]+"'>";
			html+= defaultFields[i];
			html+= "</a></th>";
		}
		for(int i = 0; i < inputFields.size();i++){
			
			html+= "<th><a data-sortvalue='"+inputFields.get(i).getCamelCaseName()+"'>";
			html+= inputFields.get(i).getName().replace("[]", "");
			html+= "</a></th>";
		}
		html+="<td>Actions</td>";
		html+="</tr>";
		for(int i = 0;i < subList.length;i++){
			html+="<tr>";
			html+="<td><input type='checkbox' name='selected[]' value='"+subList[i].getSubmissionID()+"' /></td>";
			for(int j = 0; j < sortFields.length;j++){
				ManageSubmissionsLogic backend = new ManageSubmissionsLogic();
				html+="<td>";
				if(j == 0 || j == 1 || sortFields[j].equals("status") || sortFields[j].equals("lastChanged")){
					html+= this.getValueForDynamicFieldKey(sortFields[j],subList[i]);	
				}else{
					String value = this.getValueForDynamicFieldKey(sortFields[j],subList[i]);
					html+= backend.processUpdateStatus(value, subList[i].getSubmissionID(),sortFields[j]);
				}
				html+="</td>";
			}
			for(int j =0 ; j < inputFields.size();j++){
				ManageSubmissionsLogic backend = new ManageSubmissionsLogic();
				html+="<td>";
				
					String value = this.getValueForDynamicFieldKey(inputFields.get(j).getCamelCaseName(),subList[i]);
				
					html+= backend.processUpdateStatus(value,subList[i].getSubmissionID(),inputFields.get(j).getCamelCaseName());
				
				html+="</td>";
			}
			html+="<td>";
			html+="<a href='EditSubmission?id="+subList[i].getSubmissionID() +"' style='padding:10px'>Edit</a><a href='PreviewSubmission?id="+subList[i].getSubmissionID() +"' style='padding:10px'>View</a>";
			
			html+="</td>";
			html+="</tr>";	
		}
		jw.print(html);
	}
	private String getValueForDynamicFieldKey(String key,Submission sub){
		//Submission sub = (Submission)((PageContext) this.getJspContext()).getRequest().getAttribute("submission"); 
		String value = "";
		if(key.equals("entryName")){
			value = sub.getName();
		}else if(key.equals("description")){
			value = sub.getDescription();
		}else if(key.equals("compiled")){
			value = sub.getCompiled();
		}else if(key.equals("icon")){
			value = sub.getIcon();
		}else if(key.equals("screenshot")){
			value = sub.getScreenshot();
		}else if(key.equals("video")){
			value = sub.getVideo();
		}else if(key.equals("sourceCode")){
			value = sub.getSourceCode();
		}else if(key.equals("lastChanged")){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			value = format.format(sub.getDateSubmitted());
		}else if(key.equals("groupCode")){
			value = sub.getGroup().getGroupName();
		}else if(key.equals("status")){
			if(sub.getStatus() != null && sub.getStatus().equals("D"))
				value = "Deleted";
			else
				value = "";
		}else if(!key.equals("selectedImage")){
			Map<String,String> additional = new Gson().fromJson(sub.getAdditional(), new HashMap<String,String>().getClass());
			if(additional!=null)
				value = additional.get(key);
		}
		return value;
	}
}
