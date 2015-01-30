package tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/*
 * Used for server side validation , when data is not valid, page will redirect to webpage.jsp?vmsg=??
 * This tag will display validation message as requested in get parameter
 * */
public class ValidTag extends SimpleTagSupport{
	
	private boolean error;
	private String[] requiredKeys = {
									"all_required",
									"changes_saved_settings",
									"deadline_saved"
									};
	private String[] requiredMsg = {
									"Please fill in all required fields",
									"Changes saved",
									"Deadline changes saved!"
									};
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pc = ((PageContext)getJspContext());
		String[] msg = pc.getRequest().getParameterValues("vmsg");
		ArrayList<String> text = new ArrayList<String>();
		if(msg!=null){
		for(int i = 0; i < requiredKeys.length;i++){
			String key = requiredKeys[i];
			if(Arrays.asList(msg).contains(key)){
				text.add(requiredMsg[i]);
			}
		}
		}
		JspWriter jw = getJspContext().getOut();
		jw.print("<ul>");
		for(int i = 0; i < text.size();i++){
			if(error)
				jw.print("<li class='errorMsg' style='display:block;'>"+text.get(i)+"</li>");
			else
				jw.print("<li style='display:block;color:#5cb85c'>"+text.get(i)+"</li>");
			
		}
		jw.print("</ul>");
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}

}
