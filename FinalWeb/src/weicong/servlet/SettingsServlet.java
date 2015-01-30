package weicong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Extrafield;
import util.Settings;
import ejb.ExtraBean;

/**
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/SettingsServlet")
public class SettingsServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ExtraBean extra;
	public SettingsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Settings s = Settings.getInstance();
		request.setAttribute("deadline", s.getMainDeadLine());
		ArrayList<Extrafield> map = extra.getRequiredSubmissionFields();
		request.setAttribute("submissionFields", map);
		request.setAttribute("servletPath", s.getServletPath());
		this.redirect(request, response, "settings.jsp", false);
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String deadline = request.getParameter("deadline");
		String[] requiredFields = request
				.getParameterValues("submissionFields");
		String[] notRequired = request.getParameterValues("notRequired");

		Settings s = Settings.getInstance();
		if (deadline != null) {
			s.setMainDeadLine(deadline);
		} else {
			Map<String,String[]> dataMap = request.getParameterMap();
			ArrayList<Map<String, String>> settings = new ArrayList<Map<String, String>>();
			ArrayList<String> namesSubmitted = new ArrayList<String>();
			for (String parameter : dataMap.keySet()) {// get distinct
														// entries,identify how
														// many set of items are
														// there
				String camelPrefix = parameter.split("_")[0];
				if (!namesSubmitted.contains(camelPrefix))
					namesSubmitted.add(camelPrefix);
			}
			for (int i = 0; i < namesSubmitted.size(); i++) {
				String requiredTxt = "";
				if(dataMap.get((namesSubmitted.get(i)+"_required"))[0].startsWith("hidden")){
					requiredTxt = "false";
				}else{
					requiredTxt= "true";
				}
				
				Extrafield ef = new Extrafield();
				try{
					if(dataMap.get(namesSubmitted.get(i) + "_id")[0] != null){
						int id = Integer.valueOf(dataMap.get(namesSubmitted.get(i) + "_id")[0]);
						ef.setIdadditionalSettings(id);
					}
				}catch(NullPointerException ex){}
				ef.setName(String.valueOf(dataMap.get(namesSubmitted
						.get(i) + "_name")[0]));
				ef.setRequired(requiredTxt);
				ef.setCamelCaseName(convertToCamelCase(String
						.valueOf(dataMap.get(namesSubmitted.get(i)
								+ "_name")[0])));
				String formatlist = String.valueOf(dataMap.get(namesSubmitted
						.get(i) + "_format")[0]);
				if(!formatlist.equals("File"))
					ef.setFormat(formatlist);
				else
					 ef.setFormat(String.valueOf(dataMap.get(namesSubmitted
								.get(i) + "_fileFormat")[0]));

				System.out.println(i);
				extra.update(ef);
				//extra.add();
			}
			
		}

		/*
		 * else if(requiredFields != null){ for(String required : requiredFields
		 * ){ map.put(required, true); }
		 * 
		 * if(notRequired != null){ for(String notrequired : notRequired ){
		 * map.put(notrequired.replace("hidden", "").trim(), false); } }else
		 * System.out.println("REquired is null"); //
		 * s.setRequiredSubmissionFields(map); }
		 */

		Settings.saveSettings(s, s.getDir());

		this.redirect(request, response,
				"SettingsServlet?vmsg=changes_saved_settings", true);
	}

	private String convertToCamelCase(String text) {
		String result = "";
		int i = 0;
		for (String item : text.split(" ")) {
			if (i == 0) {
				char[] charactersFront = item.toCharArray();
				charactersFront[0] = Character.toLowerCase(charactersFront[0]);
				result += String.valueOf(charactersFront);
			} else {
				char[] characters = item.toCharArray();
				characters[0] = Character.toUpperCase(characters[0]);
				result += String.valueOf(characters);
			}
			i++;
		}
		return result;
	}
}
