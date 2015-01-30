package weicong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Extrafield;
import model.Fieldsupdated;
import model.Submission;

import com.google.gson.Gson;

import ejb.ExtraBean;
import ejb.SubmissionEJB;

/**
 * Servlet implementation class ExportSubmissions
 */
@WebServlet("/ManageSubmissions")
public class ManageSubmissions extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SubmissionEJB bean;
	@EJB
	private ExtraBean extra;

	private final static int	 ITEMS_PER_PAGE = 10;

	public ManageSubmissions() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		bean.setDisplayInactive(true);
		Gson gson = new Gson();
		boolean displayDynamic = Boolean.valueOf(request.getParameter("useJSON"));

		String teacherUsername = (String) request.getSession().getAttribute(
				"username");
		if (teacherUsername == null && !displayDynamic) {
			super.redirect(request, response,
					"login.jsp?page=ManageSubmissions", true);
		}
		List<Extrafield> extraList = extra.getRequiredSubmissionFields();
		List<Submission> list = null;
		final String sortColumn = request.getParameter("sort");
		int page = Integer
				.valueOf(request.getParameter("page") != null ? request
						.getParameter("page") : "0");
		String sortOrder = request.getParameter("asc");
		Object[] returned = null;
		int resultCount = 0;
		if (sortColumn != null) {
			if(sortOrder == null)
				sortOrder = " ASC ";//ascending by default
			else
			sortOrder = request.getParameter("asc").equalsIgnoreCase("1") ? " ASC "
					: " DESC ";
			try {

				if (sortColumn.equalsIgnoreCase("groupCode")) {
					returned = bean.retrieveAll(new String[0], new String[0],
							"dateSubmitted", true, page,true, "teamCode");
				} else {
					returned = bean.retrieveAll(new String[0], new String[0],
							sortColumn, false, page);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				returned = bean.retrieveAll(new String[0], new String[0],
						"lastChanged", true, page);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		list = (List<Submission>) returned[0];
		resultCount = (Integer) returned[1];

		Submission[] subs = list.toArray(new Submission[] {});
		for (int i = 0; i < subs.length; i++) {
			Submission entity = ((List<Submission>) list).get(i);
			if (subs[i].getFieldsupdated() != null) {
				ArrayList<String> updated = new Gson().fromJson(subs[i]
						.getFieldsupdated().getFields(),
						new ArrayList<String>().getClass());
				Fieldsupdated field = subs[i].getFieldsupdated();

				if (updated.contains("description")) {
					entity.setDescription("true");
				} else {
					entity.setDescription("false");
				}
				if (updated.contains("compiled")) {
					entity.setCompiled("true");
				} else {
					entity.setCompiled("false");
				}
				if (updated.contains("sourceCode")) {
					entity.setSourceCode("true");
				} else {
					entity.setSourceCode("false");
				}
				if (updated.contains("icon")) {
					entity.setIcon("true");
				} else {
					entity.setIcon("false");
				}
				if (updated.contains("screenshot")) {
					entity.setScreenshot("true");
				} else {
					entity.setScreenshot("false");
				}
				if (updated.contains("video")) {
					entity.setVideo("true");
				} else {
					entity.setVideo("false");
				}

				for (int index = 0; index < extraList.size(); index++) {
					if (updated.contains(extraList.get(index)
							.getCamelCaseName())) {
						Map<String, String> additional = new HashMap<String, String>();
						additional.put(extraList.get(index).getCamelCaseName(),
								"true");
						entity.setAdditional(new Gson().toJson(additional));
					}
				}
			} else {
				entity.setDescription("false");
				entity.setCompiled("false");
				entity.setSourceCode("false");
				entity.setIcon("false");
				entity.setScreenshot("false");
				entity.setVideo("false");
			}
		}
		if (sortColumn != null) {
			String[] fields = { "description", "screenshot", "video",
					"compiled", "sourceCode", "icon", "lastChanged" };

			if (Arrays.asList(fields).contains(sortColumn)) {
				list = sort((List<Submission>) list, sortColumn, page);
			}
		}
		/*
		 * //need to prevent cirular entity problem causing stackoverflow error
		 * ArrayList<Submission> items = new ArrayList<Submission>(); for(int i
		 * = 0; i < list.size();i++){ items.add(list.get(i));
		 * items.get(i).getGroup().setConversations(null);
		 * items.get(i).getGroup().setDeadline(null);
		 * items.get(i).getGroup().setSubmissions(null);
		 * 
		 * if(items.get(i).getFieldsupdated() != null)
		 * items.get(i).getFieldsupdated().setSubmission_submissionID(null);
		 * else items.get(i).setFieldsupdated(null);
		 * 
		 * } String parsedJSON = gson.toJson(list.toArray(new Submission[]{}));
		 * 
		 * PrintWriter pw = response.getWriter(); pw.write(parsedJSON);
		 * pw.flush(); pw.close();
		 */
	
		request.setAttribute("SubmissionList",
				list.toArray(new Submission[] {}));
		request.setAttribute("count", resultCount);
		request.setAttribute("inputFields", extraList);

		if (displayDynamic) {
			super.redirect(request, response, "manageSubmissionsAjax.jsp", false);
		} else {
			super.redirect(request, response, "manageSubmissions.jsp", false);
		}
	}

	private List<Submission> sort(List<Submission> list, String column,
			int currentPage) {

		List<Submission> sorted = new ArrayList<Submission>();
		List<Submission> unsorted = new ArrayList<Submission>(list);
		List<Submission> entries = new ArrayList<Submission>(list);
		
		for (int i = 0; i < entries.size(); i++) {
			Submission sub = entries.get(i);
			String result = "";
			if (column.equalsIgnoreCase("description")) {
				result = sub.getDescription();
			} else if (column.equalsIgnoreCase("screenshot")) {
				result = sub.getScreenshot();
			} else if (column.equalsIgnoreCase("video")) {
				result = sub.getVideo();
			} else if (column.equalsIgnoreCase("compiled")) {
				result = sub.getCompiled();
			} else if (column.equalsIgnoreCase("sourceCode")) {
				result = sub.getSourceCode();
			} else if (column.equalsIgnoreCase("icon")) {
				result = sub.getIcon();
			}
			if (result.equals("true")) {
				sorted.add(sub);
				unsorted.remove(sub);
				System.out.println(list.get(i).getName());
			}
		}
		sorted.addAll(unsorted);
		int endIndex = (currentPage + 1) * ITEMS_PER_PAGE;
		if (endIndex > sorted.size())// prevent index out of bounds exception
			endIndex = sorted.size();

		sorted = sorted.subList(ITEMS_PER_PAGE * currentPage, endIndex);
		return sorted;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
