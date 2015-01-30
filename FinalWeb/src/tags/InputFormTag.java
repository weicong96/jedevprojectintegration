package tags;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class InputFormTag extends SimpleTagSupport{
	private String name;
	private String value;//default value if any
	private String [] mapKey = {
			"Entry Name",
			"Description",
			"Compiled",
			"Icon",
			"Screenshots",
			"Video",
			"Source Code (.zip)"	
	};
	private String[] mapValues = {
			""
	};
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pc = (PageContext)this.getJspContext();
		Map<String,String> map = (Map<String, String>) pc.getRequest().getAttribute("required");
		
		
	}
}
