package weicong.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Submission;
import ejb.SubmissionEJB;

/**
 * Servlet implementation class YourSubmissions
 */
@WebServlet("/YourSubmissions")
public class YourSubmissions extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private SubmissionEJB submission;
    /**
     * @see HttpServlet#HttpServlet()
     */
	private final static int ITEMS_PER_PAGE = 10;
    public YourSubmissions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String teamCode = (String)request.getSession().getAttribute("teamCode");
		if(teamCode == null)
			super.redirect(request, response, "login.jsp?page=YourSubmissions", true);
		ArrayList<Submission> list = submission.getGroupSubmissions(teamCode);
		int currentPage = Integer.valueOf(request.getParameter("page") != null ? (String)request.getParameter("page") : "0");
		//Paging here
		int endIndex = (currentPage+1) * ITEMS_PER_PAGE;
		if(endIndex > list.size())//prevent index out of bounds exception
			endIndex = list.size();
		int count = list.size();
		list = new ArrayList<Submission>(list.subList(ITEMS_PER_PAGE * currentPage, endIndex));
		
		if(count == 0)
			count = 1;
		request.setAttribute("submissionList", list.toArray(new Submission[]{}));
		request.setAttribute("count", count);
		super.redirect(request, response, "yourSubmissions.jsp", false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
