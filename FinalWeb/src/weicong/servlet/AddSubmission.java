package weicong.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Extrafield;
import model.GroupJPA;
import model.Submission;
import util.Settings;
import ejb.ExtraBean;
import ejb.GroupEJB;
import ejb.SubmissionEJB;

/**
 * Servlet implementation class AddSubmision
 */
@WebServlet("/AddSubmission")
public class AddSubmission extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SubmissionEJB bean;
	@EJB
	private GroupEJB groupBean;
	@EJB
	private ExtraBean extra;
	public AddSubmission() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teamCodeSession = (String) request.getSession().getAttribute("teamCode");
		boolean canSubmit = groupBean.getDeadlineOver(teamCodeSession);
		String url = "";
		if(canSubmit){
			url = "addSubmission.jsp";
		}else{
			String deadline = Settings.getInstance().getMainDeadLine();
			Date deadlineTime;
			try {
				deadlineTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(deadline);
				Date currentTime = Calendar.getInstance().getTime();
				if(currentTime.before(deadlineTime)){
					url = "addSubmission.jsp";
				}else{
					url = "deadline.jsp";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Map<String,String> requiredStr = new HashMap<String,String>();
		/*Map<String,Boolean> field = Settings.getInstance().getRequiredSubmissionFields();
		for(String key : field.keySet()){
			if(field.get(key))
				requiredStr.put(key, "*");
			else
				requiredStr.put(key, "");
		}
		request.setAttribute("RequiredList", requiredStr);
		*/
		request.setAttribute("inputFields", extra.getRequiredSubmissionFields());
		this.redirect(request, response, url, false);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teamCode = (String) request.getSession().getAttribute("teamCode");
	
		ArrayList<Extrafield> namesAccept = extra.getRequiredSubmissionFields();//list of acceptable name values
		ArrayList<String> namesAcceptStr = new ArrayList<String>();
		String[] acceptable = new String[]{"entryName","description","compiled","sourceCode","icon","video","selectedImage","screenshot"};
		
		for(Extrafield item : namesAccept){
			namesAcceptStr.add(item.getCamelCaseName());
		}
		namesAcceptStr.addAll(Arrays.asList(acceptable));
		Map<String,String> parameterValues = super.parseRequest(request, response, namesAcceptStr.toArray(new String[]{}));
		
		String name = parameterValues.get("entryName");
		String description = parameterValues.get("description");
		String compiled = parameterValues.get("compiled");
		String sourceCode = parameterValues.get("sourceCode");
		String icon = parameterValues.get("icon");
		String screenShots = parameterValues.get("screenshots");
		String video = parameterValues.get("video");
		String additional = parameterValues.get("additional");
		if(name != null && !name.isEmpty()){
			Submission sub = new Submission();
			sub.setName(name);
			sub.setDescription(description);
			sub.setCompiled(compiled);
			sub.setSourceCode(sourceCode);
			sub.setScreenshot(screenShots);
			
				sub.setIcon(icon);
			
			sub.setVideo(video);
			GroupJPA group = groupBean.retrieveById(teamCode);
			sub.setGroup(group);
			
			sub.setDateSubmitted(Calendar.getInstance().getTime());
			
			sub.setAdditional(additional);
			int id = bean.add(sub);
			//HttpSession session = request.getSession(true);
			//session.setAttribute("submissionID", new Integer(id));
			
			this.redirect(request, response, "addSubmissionDone.jsp?id="+id, true);
		}
	}
	
}
