package tags;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IsRequiredTag extends SimpleTagSupport {
	
	private Map<String,String> fields;
	private String property;
	private Boolean inputType;
	private StringWriter sw = new StringWriter();
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter jw = getJspContext().getOut();
		String message = "";
		
		for(String key : fields.keySet()){	
			if(key.equals(property) && !fields.get(key).isEmpty()){
				if(inputType != null && inputType)
					message = "required";
				else
					message = "*";
			}
		}
		jw.println(message);
	}


	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Map<String, String> getFields() {
		return fields;
	}


	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}


	public StringWriter getSw() {
		return sw;
	}


	public void setSw(StringWriter sw) {
		this.sw = sw;
	}


	public Boolean getInputType() {
		return inputType;
	}


	public void setInputType(Boolean inputType) {
		this.inputType = inputType;
	}

}
