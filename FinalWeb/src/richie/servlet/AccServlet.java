package richie.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.Group;
import database.DBAO;

/**
 * Servlet implementation class AccServlet
 */
@WebServlet("/AccServlet")
public class AccServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccServlet() {
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
		Group g = new Group();
		
		if(request.getParameter("password").equals(request.getParameter("Cpassword"))){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			Account acc = new Account();
			acc.setAccUserName(username);
			acc.setAccPassword(password);
			
			HttpSession session = request.getSession(false);
			String teamCode = (String) session.getAttribute("teamCode");
			g.setTeamCode(teamCode);
			System.out.println(teamCode);
			try
			{
				int status = 0;
				DBAO mydb = new DBAO();
				status = mydb.addAccount(acc, g);
				
				if(status>0){
					request.getRequestDispatcher("registerCompleted.jsp").forward(request,response);
				}
				
			}catch(Exception ex)
			{
				System.out.println("Exception in dbao,msg="+ex.getMessage());
			}
		}
		else{
			System.out.println("Cannot");
			
		}
	}

}
