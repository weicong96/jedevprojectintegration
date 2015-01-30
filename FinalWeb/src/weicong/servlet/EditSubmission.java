package weicong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Extrafield;
import model.Fieldsupdated;
import model.Submission;
import model.Submissionchanged;

import com.google.gson.Gson;

import ejb.ExtraBean;
import ejb.FieldsEJB;
import ejb.GroupEJB;
import ejb.SubmissionChangedBean;
import ejb.SubmissionEJB;

/**
 * Servlet implementation class EditSubmission
 */
@WebServlet("/EditSubmission")
public class EditSubmission extends AbstractServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private SubmissionEJB subBean;
	@EJB
	private GroupEJB groupBean;
	@EJB
	private FieldsEJB fieldBean;
	@EJB
	private SubmissionChangedBean changeBean;
	@EJB
	private ExtraBean extra;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSubmission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String teamCode = (String) request.getSession().getAttribute("username");
		if(teamCode==null)
			response.sendRedirect("login.jsp?page=EditSubmission?id="+request.getParameter("id"));
		
		Submission sub = subBean.retrieveById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("submission", sub);
		request.getSession(true).setAttribute("submissionID",new Integer(sub.getSubmissionID()));
		
		request.setAttribute("inputFields", extra.getRequiredSubmissionFields());
		super.redirect(request, response, "editSubmission.jsp", false);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Extrafield> namesAccept = extra.getRequiredSubmissionFields();//list of acceptable name values
		ArrayList<String> namesAcceptStr = new ArrayList<String>();
		String[] acceptable = new String[]{"entryName","description","compiled","sourceCode","icon","video","selectedImage","screenshot"};
		
		for(Extrafield item : namesAccept){
			namesAcceptStr.add(item.getCamelCaseName());
		}
		namesAcceptStr.addAll(Arrays.asList(acceptable));
		int id = ((Integer) session.getAttribute("submissionID")).intValue();
		Map<String,String> parameterValues = super.parseRequest(request, response, namesAcceptStr.toArray(new String[]{}),true,id);
				
		String name = parameterValues.get("entryName");
		String description = parameterValues.get("description");
		String compiled = parameterValues.get("compiled");
		String sourceCode = parameterValues.get("sourceCode");
		String icon = parameterValues.get("icon");
		String screenShots = parameterValues.get("screenshots");
		String video = parameterValues.get("video");
		String additional = parameterValues.get("additional");
		
		
		if(name != null && !name.isEmpty()){
			
			Submission sub = subBean.retrieveById(id);
			Submissionchanged change = new Submissionchanged(sub);
			changeBean.add(change,id);
			
			
			ArrayList<String> updated = new ArrayList<String>();
			//sub.setSubmissionID(id);
			
			if(!sub.getDescription().equals(description))
				updated.add("description");
			
			sub.setName(name);
			sub.setDescription(description);
			sub.setGroup(sub.getGroup());
			
			if(compiled != null && !compiled.equalsIgnoreCase("") && !sub.getCompiled().equals(compiled)){
				updated.add("compiled");
				sub.setCompiled(compiled);
			}
			if(sourceCode != null && !sourceCode.equalsIgnoreCase("") && !sub.getSourceCode().equals(sourceCode)){
				updated.add("sourceCode");
				sub.setSourceCode(sourceCode);
			}
			if(icon != null && !icon.equalsIgnoreCase("") && !sub.getIcon().equals(icon)){
				updated.add("icon");
				sub.setIcon(icon);
			}
			if(screenShots != null && !screenShots.equalsIgnoreCase("") && !sub.getScreenshot().equals(screenShots)){
				updated.add("screenshot");
				sub.setScreenshot(screenShots);
			}
			if(video != null && !video.equalsIgnoreCase("") && !sub.getVideo().equals(video)){
				updated.add("video");
				sub.setVideo(video);
			}
			if(additional !=null && !sub.getAdditional().equals(additional)){
				Gson son  = new Gson();
				Map<String,String> beforeAdditional = son.fromJson(sub.getAdditional(), new HashMap<String,String>().getClass());
				Map<String,String> changedAdditional = son.fromJson(additional, new HashMap<String,String>().getClass());
				for(String key : changedAdditional.keySet()){
					String valueBefore = beforeAdditional.get(key);
					if(!(valueBefore != null && changedAdditional.get(key).equals(valueBefore))){
						//got change
						updated.add(key);
					}
				}
				
				sub.setAdditional(additional);
				//updated.add("additional");
			}
			sub.setDateSubmitted(Calendar.getInstance().getTime());
			Gson gson = new Gson();
			String updatedJson = gson.toJson(updated);
			
			Fieldsupdated upEntity = sub.getFieldsupdated();
			if(upEntity == null){
				upEntity = new Fieldsupdated();
				upEntity.setFields(updatedJson);
				upEntity.setSubmission_submissionID(sub);
				upEntity = fieldBean.retrieveByPK(fieldBean.add(upEntity));//to make it have id
			}else{	
				upEntity.setFields(updatedJson);
				//upEntity.setSubmission_submissionID(sub);
			}
			
			sub.setFieldsupdated(upEntity);
			subBean.update(sub);	
			
			super.redirect(request, response, "editSubmissionDone.jsp", false);
		}
	}

}
