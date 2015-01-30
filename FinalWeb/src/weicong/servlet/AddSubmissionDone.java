package weicong.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddSubmissionDone
 */
@WebServlet("/AddSubmissionDone")
public class AddSubmissionDone extends AbstractServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddSubmissionDone() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.redirect(request, response, "addSubmissionDone.jsp", false);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
