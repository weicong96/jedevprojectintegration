package weicong.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Submission;
import ejb.SubmissionEJB;

/**
 * Servlet implementation class ViewSubmissionFragment
 */
@WebServlet("/ViewSubmissionFragment")
public class ViewSubmissionFragment extends AbstractServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    private SubmissionEJB bean;
    public ViewSubmissionFragment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Submission sub = bean.retrieveById(Integer.valueOf(request.getParameter("id")));
		request.setAttribute("submission", sub);
		super.redirect(request, response, "includes/ViewSubmissionFragment.jsp", false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
