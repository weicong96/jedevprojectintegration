package richie.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Group;

import model.BookingJPA;
import database.DBAO;
import ejb.BookingEJB;

/**
 * Servlet implementation class UserRetrievedBookedSlots
 */
@WebServlet("/UserRetrievedBookedSlotsServlet")
public class UserRetrievedBookedSlotsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookingEJB bookEJB;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRetrievedBookedSlotsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		try{
			DBAO dbao = new DBAO();
			Group group = dbao.retrieveGroup(username);
			String teamName = group.getTeamName();
			String teamCode = teamName.substring(0,3).toUpperCase();

			List<BookingJPA> bookingList = bookEJB.findByUBookedSlots(teamCode);
			request.setAttribute("bookingList",bookingList);
			RequestDispatcher rd = request.getRequestDispatcher("UserRetrieveBookedSlots.jsp");
			rd.forward(request,response);
		}
		catch(Exception e)
		{
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
