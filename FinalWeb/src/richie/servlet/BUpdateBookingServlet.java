package richie.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.BookingEJB;
import ejb.MessageEJB;

import model.BookingJPA;

/**
 * Servlet implementation class CreateBookingServlet
 */
@WebServlet("/BUpdateBookingServlet")
public class BUpdateBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookingEJB bookEJB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BUpdateBookingServlet() {
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
		String status = "Update";
		String id  = request.getParameter("idforBookSlot");
		int ids = Integer.parseInt(id);
		
		if(status.equals(request.getParameter("Update"))){
				BookingJPA book = bookEJB.findbyBookingID(ids);
				HttpSession session = request.getSession();
				session.setAttribute("book",book);
				RequestDispatcher rd = request.getRequestDispatcher("UpdateBookingConsult.jsp");
				rd.forward(request,response);
		}
		else{
			int deleted;
			deleted = bookEJB.DeleteBookC(ids);
			if(deleted>0){
				RequestDispatcher rd = request.getRequestDispatcher("RetrieveStaff.jsp");
				rd.forward(request,response);
		}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request,response);
			}
		}
}
	}
