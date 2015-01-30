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

import model.BookingJPA;

/**
 * Servlet implementation class BookSlotServlet
 */
@WebServlet("/BookSlotServlet")
public class BookSlotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookingEJB bookEJB;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSlotServlet() {
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
		String id  = request.getParameter("idforBookSlot");
		int ids = Integer.parseInt(id);
		
		BookingJPA book = bookEJB.findbyBookingID(ids);
		HttpSession session = request.getSession();
		session.setAttribute("book",book);
		RequestDispatcher rd = request.getRequestDispatcher("ConfirmBookingSlot.jsp");
		rd.forward(request,response);
	}

}
