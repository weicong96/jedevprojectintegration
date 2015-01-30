package tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import model.Extrafield;

public class DynamicFields extends SimpleTagSupport {

	private Boolean edit;
	
	@Override
	public void doTag() throws JspException, IOException {
		String[] fileFormats = { "Text Field", "Paragraph of text", "File" };
		//String[] keyNames = { "Name", "Format", "Required", "Camel Case" };
		ServletRequest request = ((PageContext) getJspContext()).getRequest();
		ArrayList<Extrafield> map = (ArrayList<Extrafield>) (request
				.getAttribute("submissionFields"));
		boolean editMode = String.valueOf(request.getParameter("field"))
				.equals("additional");
		JspWriter jw = getJspContext().getOut();
		String code = "";
		String[] disabled = {"entryName","description","compiled","selectedImage","icon","screenshot[]","video","sourceCode","lastChanged"};
		for (int row = 0; row < map.size(); row++) {
			code += "<tr>";
			Extrafield colValues = map.get(row);
			if (editMode) {
				
				code += "<td>";
				code += "<input type='text' name='"
						+ colValues.getCamelCaseName() + "_name' value='"
						+ colValues.getName() + "' "+(Arrays.asList(disabled).contains(colValues.getCamelCaseName()) ? ("readonly") : "")+"/>";
				
				code += "<input type='hidden' name='"
						+ colValues.getCamelCaseName() + "_id' value='"
						+ colValues.getIdadditionalSettings() + "'/>";
				
				code += "</td>";

				code += "<td>";

				String options = "";
				boolean isFile = false;
				for (int i = 0; i < fileFormats.length; i++) {
					if (fileFormats[i].equals(colValues.getFormat())) {
						options += "<option value='" + fileFormats[i]
								+ "' selected>" + fileFormats[i] + "</option>";
					} else {
						if (!isFile
								&& !Arrays.asList(fileFormats).contains(
										colValues.getFormat())
								&& fileFormats[i].equals("File")) {
							options += "<option value='" + fileFormats[i]
									+ "' selected>" + fileFormats[i]
									+ "</option>";
							isFile = true;
						} else
							options += "<option value='" + fileFormats[i]
									+ "'>" + fileFormats[i] + "</option>";
					}
				}
				code += "<select name='" + colValues.getCamelCaseName()
						+ "_format'>" + options + "</select>";
				if (isFile)
					code += "<input type='text' name='"
							+ colValues.getCamelCaseName()
							+ "_fileFormat' value='" + colValues.getFormat()
							+ "'/>";
				else
					code += "<input type='hidden' name='"
							+ colValues.getCamelCaseName()
							+ "_fileFormat'"
							+ " disabled/>";
				code += "</td>";

				code += "<td>";
				code += "<input type='checkbox' name='"
						+ colValues.getCamelCaseName()
						+ "_required' value='"
						+ colValues.getCamelCaseName()
						+ "' "
						+ (Boolean.valueOf(colValues.getRequired()) ? "checked='checked'"
								: "") + "/>";
				code += "</td>";

				code += "<td>";
				code += "<input type='hidden' name='"
						+ colValues.getCamelCaseName()
						+ "_required' value='hidden"
						+ colValues.getCamelCaseName()
						+ "' "
						+ (Boolean.valueOf(colValues.getRequired()) ? ""
								: "disabled='disabled'") + "/>";
				code += "</td>";

			} else { 
				code += "<td>";
				code += colValues.getName();
				code += "</td>";
				code += "<td>";
				code += colValues.getFormat();
				code += "</td>";
				code += "<td>";
				code += colValues.getRequired();
				code += "</td>";
			}
			code += "</tr>";
		}
		jw.println(code);
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

}
