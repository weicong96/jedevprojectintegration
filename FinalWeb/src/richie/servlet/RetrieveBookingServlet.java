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

import ejb.BookingEJB;
import ejb.MessageEJB;

import model.BookingJPA;

/**
 * Servlet implementation class RetrieveBookingServlet
 */
@WebServlet("/RetrieveBookingServlet")
public class RetrieveBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookingEJB bookEJB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		ArrayList<BookingJPA> bookingList = bookEJB.findAll();
		
		String date,room,tutor;
		
		date=request.getParameter("datepicker");
		room=request.getParameter("roomNo");
		tutor=(String)session.getAttribute("username");

		List <BookingJPA> booklist = bookEJB.findbyDateRoom(date,room,tutor);
		
		request.setAttribute("booklist",booklist);
		request.setAttribute("bookingList",bookingList);
		session.setAttribute("date",date);
		session.setAttribute("room",room);
		RequestDispatcher rd = request.getRequestDispatcher("RetrieveBookingConsult.jsp");
		rd.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
