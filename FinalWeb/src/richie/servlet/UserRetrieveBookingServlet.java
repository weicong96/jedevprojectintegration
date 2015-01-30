package richie.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class RetrieveBookingServlet
 */
@WebServlet("/UserRetrieveBookingServlet")
public class UserRetrieveBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookingEJB bookEJB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRetrieveBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		ArrayList<BookingJPA> bookingList = bookEJB.findByAvaliable();
		
		String date,room;
		
		date=request.getParameter("datepicker");
		room=request.getParameter("roomNo");

		List <BookingJPA> booklist = bookEJB.findbyUDateRoom(date,room);
		
		request.setAttribute("booklist",booklist);
		request.setAttribute("bookingList",bookingList);
		session.setAttribute("date",date);
		session.setAttribute("room",room);
		RequestDispatcher rd = request.getRequestDispatcher("UserRetrieveBookingConsult.jsp");
		rd.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
