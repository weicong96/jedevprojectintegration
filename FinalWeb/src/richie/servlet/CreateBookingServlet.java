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
 * Servlet implementation class CreateBookingServlet
 */
@WebServlet("/CreateBookingServlet")
public class CreateBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookingEJB bookEJB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBookingServlet() {
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
		BookingJPA book = new BookingJPA();
		HttpSession session = request.getSession();
		
		book.setBookVenue(request.getParameter("roomNo"));
		book.setBookDate(request.getParameter("sDate"));
		book.setBookTime(request.getParameter("timeSelection"));
		String staffID = (String) session.getAttribute("username");
		book.setFk_staffID(staffID);
		List<BookingJPA> list= bookEJB.checkIfSlotAvaliable(book);
		if(list.size()!=1)
		{
			bookEJB.add(book);
			RequestDispatcher rd= request.getRequestDispatcher("RetrieveStaff.jsp");
			rd.forward(request,response);
		}
		else
		{
			RequestDispatcher rd= request.getRequestDispatcher("homepage.jsp");
			rd.forward(request,response);
		}
	
	}

}
