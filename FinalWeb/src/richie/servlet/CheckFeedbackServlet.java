package richie.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Feedback;
import database.DBAO;

/**
 * Servlet implementation class CheckFeedbackServlet
 */
@WebServlet("/CheckFeedbackServlet")
public class CheckFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckFeedbackServlet() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		try{
			Feedback fb = new Feedback();
			DBAO mydb = new DBAO();
			fb = mydb.retrieveFeedback(username);
			boolean status = mydb.checkFeedback(fb.getFk_groupCode());
			
			if(status == true){
				RequestDispatcher rd = request.getRequestDispatcher("pRedeemFeedbackGift.jsp");
				rd.forward(request,response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("pProvideFeedback.jsp");
				rd.forward(request,response);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in dbao,msg="+e.getMessage());
		}
		}

}
