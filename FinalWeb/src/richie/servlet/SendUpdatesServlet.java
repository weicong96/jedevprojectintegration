package richie.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Updates;
import database.DAO;

/**
 * Servlet implementation class VerifiesServlet
 */
@WebServlet("/SendUpdatesServlet")
public class SendUpdatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendUpdatesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String recipients = request.getParameter("teamName");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String attach = request.getParameter("attachFiles");
		
		try {
			DAO mydb = new DAO();
			Updates upd = new Updates();
			upd.setRecipient(recipients);
			upd.setSender("CHEWPH");
			upd.setTitle(title);
			upd.setContent(content);
			upd.setPicture(attach);
			
			/*if (test) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				RequestDispatcher rd = request
						.getRequestDispatcher("viewProfile.jsp");
				rd.forward(request, response);
			} else {
				PrintWriter pw = response.getWriter();
				pw.println("<HTML>");
				pw.println("<BODY>");
				pw.println("Invalid User");
				pw.println("</BODY>");
				pw.println("</HTML>");
			}*/
			
			
		} catch (Exception ex) {
			System.out.println("Exception in dbao,msg=" + ex.getMessage());
		}
		/*
		String username = request.getParameter("accUserName");
		String password = request.getParameter("accPassword");

		try {
			DAO mydb = new DAO();
			boolean test = mydb.checkUsers(username, password);
			System.out.println(test);

			if (test) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				RequestDispatcher rd = request
						.getRequestDispatcher("viewProfile.jsp");
				rd.forward(request, response);
			} else if(mydb.checkStaffUsers(username,password)){
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				RequestDispatcher rd = request.getRequestDispatcher("listOfSentMessages.jsp");
				rd.forward(request,response);
			} else {
				PrintWriter pw = response.getWriter();
				pw.println("<HTML>");
				pw.println("<BODY>");
				pw.println("Invalid User");
				pw.println("</BODY>");
				pw.println("</HTML>");
			}
		} catch (Exception ex) {
			System.out.println("Exception in dbao,msg=" + ex.getMessage());
		}*/
	}
}
