package richie.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Feedback;
import bean.Group;
import database.DBAO;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackServlet() {
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
		String designQns = request.getParameter("design");
		String friendlyQns = request.getParameter("friendly");
		String favourFeatures = request.getParameter("favourFea");
		String comments = request.getParameter("comments");
		
		Feedback fb=new Feedback();
		fb.setFavourFeatures(favourFeatures);
		fb.setFriendlyQns(friendlyQns);
		fb.setDesignQns(designQns);
		fb.setComments(comments);
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		try
		{
			int status = 0;
			DBAO mydb = new DBAO();
			Group g = mydb.retrieveGroup(username);
			status = mydb.addFeedback(fb, g);
			
			if(status>0){
				if(status/2==0){
					System.out.println("Free Thumbdrive");
					String prizeName = "Gift1";
					session.setAttribute("prizeName",prizeName);
					request.getRequestDispatcher("pRedeemFeedbackGift.jsp").forward(request,response);
				}
				else{
					System.out.println("Free Waterbottle");
					String prizeName = "Gift2";
					session.setAttribute("prizeName",prizeName);
				request.getRequestDispatcher("pReedemFeedbackGift.jsp").forward(request,response);
			}
			}
		}catch(Exception ex)
		{
			System.out.println("Exception in dbao,msg="+ex.getMessage());
		}
	}
}
