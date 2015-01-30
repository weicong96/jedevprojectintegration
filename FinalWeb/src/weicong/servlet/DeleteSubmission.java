package weicong.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.SubmissionEJB;

/**
 * Servlet implementation class DeleteSubmission
 */
@WebServlet("/DeleteSubmission")
public class DeleteSubmission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private SubmissionEJB bean;
    public DeleteSubmission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter pw = response.getWriter();
		pw.println("Done"+request.getParameterValues("id[]"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] values = request.getParameterValues("id[]");
		for(int i = 0; i <values.length;i++){
			Integer id = Integer.valueOf(values[i]);
			bean.remove(id);
		}
		response.getWriter().println("Done");
	}

}
