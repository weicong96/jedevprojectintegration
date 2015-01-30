package richie.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RetrieveMessages
 */
@WebServlet("/RetrieveMessages")
public class RetrieveMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String teacherCode = null;
		teacherCode = request.getParameter("ConvoName");
		try{
			//DBAO mydb = new DBAO();
			//Staff s = mydb.retrieveStaffDetails(teacherName);
			//String teacherCode = s.getIdstaff();
			HttpSession session = request.getSession();
			session.setAttribute("teacherCode",teacherCode);
			System.out.println(teacherCode);
			
			if(teacherCode!=null){
				RequestDispatcher rd = request.getRequestDispatcher("UserRetrieveMessagesP2.jsp");
				rd.forward(request,response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("UserMustSelectConvo.jsp");
				rd.forward(request,response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
