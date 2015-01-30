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

import model.BookingJPA;
import ejb.BookingEJB;

/**
 * Servlet implementation class CreateBookingServlet
 */
@WebServlet("/UpdateBookingServlet")
public class UpdateBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookingEJB bookEJB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookingServlet() {
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
		BookingJPA book = new BookingJPA();
		BookingJPA books = (BookingJPA) session.getAttribute("book");
		String username = (String)session.getAttribute("username");
		String venue = books.getBookVenue();
		String date = books.getBookDate();
		String time = books.getBookTime();
		
		book.setBookVenue(request.getParameter("roomNo"));
		book.setBookDate(request.getParameter("sDate"));
		book.setBookTime(request.getParameter("timeSelection"));
		String staffID = (String) session.getAttribute("username");
		book.setFk_staffID(staffID);
		bookEJB.UpdateSlots(book,venue,date,time,username);
		
		RequestDispatcher rd = request.getRequestDispatcher("updateConsultCompleted.jsp");
		rd.forward(request,response);
	}

}
