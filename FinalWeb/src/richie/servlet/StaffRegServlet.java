package richie.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Staff;
import bean.StaffAccount;
import database.DBAO;

/**
 * Servlet implementation class StaffRegServlet
 */
@WebServlet("/StaffRegServlet")
public class StaffRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffRegServlet() {
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
		String name = request.getParameter("staffName");
		String staffID = request.getParameter("staffID");
		String staffType = request.getParameter("staffType");
		String contactNo = request.getParameter("contactNo");
		String emailAdd = request.getParameter("emailAdd");
		String password = request.getParameter("staffPassword");
		String cPassword = request.getParameter("staffCPassword");
		
	if(password.equals(cPassword)){
			
		Staff s =  new Staff();
		s.setStaffName(name);
		s.setIdStaff(staffID);
		s.setStaffType(staffType);
		s.setStaffContactNo(contactNo);
		s.setStaffEmailNo(emailAdd);
		
		StaffAccount sa = new StaffAccount();
		sa.setAccUserName(staffID);
		sa.setAccPassword(password);
		
			try
			{
				int status = 0;
				int status1 = 0;

				DBAO mydb = new DBAO();
				status = mydb.addStaff(s);
				status1 = mydb.addStaffAccount(sa,s);
					
				if(status>0 && status1>0){
					request.getRequestDispatcher("registerCompleted.jsp").forward(request,response);
				}
				
			}catch(Exception ex)
			{
				System.out.println("Exception in dbao,msg="+ex.getMessage());
			}
		}
		
		
	}

}
