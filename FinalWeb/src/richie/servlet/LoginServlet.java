package richie.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Group;

import database.DBAO;

/**
 * Servlet implementation class VerifiesServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try
		{
			DBAO mydb = new DBAO();
			Group grp = mydb.checkandGetUsers(username, password);
			
			
			if(grp!=null){
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("teamCode", grp.getTeamCode());
				session.setAttribute("teamName", grp.getTeamName());
				
				RequestDispatcher rd ;
				String page = request.getParameter("page");
				if(page!=null){
					response.sendRedirect(page);
				}else{
					rd = request.getRequestDispatcher("pProfile.jsp");
					rd.forward(request,response);		
				}
				
			}else if(mydb.checkStaffUsers(username,password)){
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					
					RequestDispatcher rd;
					String page = request.getParameter("page");
					if(page != null)
						response.sendRedirect(page);
					else{
						rd = request.getRequestDispatcher("RetrieveStaff.jsp");
						rd.forward(request,response);
					}
					//RequestDispatcher rd = request.getRequestDispatcher("RetrieveStaff.jsp");
					//rd.forward(request,response);
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("PublicInvalidUser.jsp");
				rd.forward(request,response);
			}
		}catch(Exception ex)
		{
			System.out.println("Exception in dbao,msg="+ex.getMessage());
		}
	}
}
