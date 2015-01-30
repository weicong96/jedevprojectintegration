package weicong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Submission;
import ejb.SubmissionEJB;

@WebServlet("/ViewSubmission")
public class ViewSubmission extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SubmissionEJB bean;
	public ViewSubmission() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter p = response.getWriter();
		int page = Integer.valueOf(request.getParameter("page") != null ? request.getParameter("page") : "0");
		String term = request.getParameter("term");
		String school = request.getParameter("schoolRange");
		Object[] result = null;
		try {
			if(term != null){
				result = bean.retrieveAll(new String[]{"name"}, new String[]{"%"+term+"%"}, "dateSubmitted", true, page);
			}else{
				result = bean.retrieveAll(new String[0], new String[0],"dateSubmitted",true,page);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Submission> tempList = (List<Submission>) result[0];
		
		int resultCount = (Integer)result[1];
		ArrayList<Submission> list = new ArrayList<Submission>();
		
		for(int i = 0; i < tempList.size();i++){
			list.add(tempList.get(i));
		}
		//Collections.reverse(list);
		request.setAttribute("count", resultCount);
		request.setAttribute("submissionList", list);
		
		super.redirect(request, response, "viewSubmission.jsp", false);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
