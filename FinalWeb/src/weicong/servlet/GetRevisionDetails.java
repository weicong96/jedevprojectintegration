package weicong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Submissionchanged;
import ejb.SubmissionChangedBean;

/**
 * Servlet implementation class GetRevisionDetails
 */
@WebServlet("/GetRevisionDetails")
public class GetRevisionDetails extends HttpServlet {
	@EJB
	private SubmissionChangedBean changeEJB;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRevisionDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int submissionID = Integer.valueOf(request.getParameter("submissionID"));
		List<Submissionchanged> list = changeEJB.findByOriginalSubmissionID(submissionID);
		Gson son = new Gson();
		String text = son.toJson(list);
		PrintWriter pw = response.getWriter();
		pw.write(text);
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
