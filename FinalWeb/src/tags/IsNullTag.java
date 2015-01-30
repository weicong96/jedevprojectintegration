package tags;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import model.Submission;

public class IsNullTag extends SimpleTagSupport{
	
	private Submission entity;
	private String property;
	
	private StringWriter sw = new StringWriter();
	@Override
	public void doTag() throws JspException, IOException {
	
		    try {
				for (PropertyDescriptor pd : Introspector.getBeanInfo(Submission.class).getPropertyDescriptors()) {
					if (pd.getReadMethod() != null && !"class".equals(pd.getName())&& pd.getName().equals(property)){
						Object value =pd.getReadMethod().invoke(entity);
					  if(value != null){
						  String strValue = (String) value;
						  if(!strValue.isEmpty() && !strValue.equals("")){
								 getJspBody().invoke(sw);
								 getJspContext().getOut().println(sw.toString());
								  break;
						  }
					  }
					}
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	}

	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}

	public Submission getEntity() {
		return entity;
	}

	public void setEntity(Submission entity) {
		this.entity = entity;
	}

	public StringWriter getSw() {
		return sw;
	}

	public void setSw(StringWriter sw) {
		this.sw = sw;
	}

}
