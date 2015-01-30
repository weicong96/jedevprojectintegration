package richie.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBAO;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		
		try{
			DBAO mydb = new DBAO();
			boolean test = mydb.checkUserUsername(username);
			boolean test1 = mydb.checkStaffUsername(username);
			
			if(test){
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				RequestDispatcher rd = request.getRequestDispatcher("PublicResetPasswordP2.jsp");
				rd.forward(request,response);
			}
			else if(test1)
			{
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				RequestDispatcher rd = request.getRequestDispatcher("PublicResetPasswordP2.jsp");
				rd.forward(request,response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("PublicResetPasswordNSU.jsp");
				rd.forward(request,response);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password = request.getParameter("password");
		
		try{
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("username");
			DBAO mydb = new DBAO();
			
			boolean test = mydb.checkUserUsername(username);
			boolean test1 = mydb.checkStaffUsername(username);
			
			if(test){
				int no = mydb.updatePassword(username,password);
				if(no==1){
					RequestDispatcher rd = request.getRequestDispatcher("PublicResetPasswordP3.jsp");
					rd.forward(request,response);
				}
			}
			else if(test1){
				int no = mydb.updateStaffPassword(username,password);
				if(no==1){
					RequestDispatcher rd = request.getRequestDispatcher("PublicResetPasswordP3.jsp");
					rd.forward(request,response);
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
