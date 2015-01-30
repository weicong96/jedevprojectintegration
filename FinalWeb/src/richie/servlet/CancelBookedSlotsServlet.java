package richie.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.BookingEJB;
import javax.ejb.EJB;

/**
 * Servlet implementation class CancelBookedSlotsServlet
 */
@WebServlet("/CancelBookedSlotsServlet")
public class CancelBookedSlotsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookingEJB bookEJB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelBookedSlotsServlet() {
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
		int id = Integer.parseInt(request.getParameter("bookID"));
		bookEJB.CancelBookSlot(id);
		
		RequestDispatcher rd = request.getRequestDispatcher("UserCancelSlotsCompleted.jsp");
		rd.forward(request,response);
		
	}

}
