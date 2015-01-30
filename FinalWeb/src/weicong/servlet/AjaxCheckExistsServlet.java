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
 * Servlet implementation class AjaxCheckExistsServlet
 */
@WebServlet("/AjaxCheckExistsServlet")
public class AjaxCheckExistsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private SubmissionEJB subBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxCheckExistsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		boolean exists = false;
		String entryName = request.getParameter("entryName");
		if(entryName != null){
			//go to db and see if got any entries with current name
			exists = subBean.checkExists(entryName);
		}
		writer.write(String.valueOf(exists));
		writer.flush();
		writer.close();
	}

}
