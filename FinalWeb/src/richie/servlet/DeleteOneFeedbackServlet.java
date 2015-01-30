package richie.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBAO;

/**
 * Servlet implementation class DeleteFeedbackServlet
 */
@WebServlet("/DeleteOneFeedbackServlet")
public class DeleteOneFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOneFeedbackServlet() {
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
		String status="Delete All Feedback";
		if(status.equals(request.getParameter("DeleteAll"))){
			try{
				DBAO mydb = new DBAO();
				mydb.deleteFeedback();
				RequestDispatcher rd =  request.getRequestDispatcher("FBDeleteSuccessfully.jsp");
				rd.forward(request,response);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		else{
			try{
				int id = Integer.parseInt(request.getParameter("checkboxS"));
				DBAO mydb = new DBAO();
				mydb.deleteOneFeedback(id);
				RequestDispatcher rd =  request.getRequestDispatcher("FBDeleteSuccessfully.jsp");
				rd.forward(request,response);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
	}

}
}