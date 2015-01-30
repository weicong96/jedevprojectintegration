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
 * Servlet implementation class StaffUpdateServlet
 */
@WebServlet("/StaffUpdateServlet")
public class StaffUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffUpdateServlet() {
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
		String idStaff = request.getParameter("staffID");
		String staffName = request.getParameter("staffName");
		String staffType = request.getParameter("staffType");
		String contactNo = request.getParameter("contactNo");
		String emailAdd = request.getParameter("emailAdd");
		
		String password  = request.getParameter("staffPassword");
		
		Staff s = new Staff();
		s.setIdStaff(idStaff);
		s.setStaffName(staffName);
		s.setStaffType(staffType);
		s.setStaffContactNo(contactNo);
		s.setStaffEmailNo(emailAdd);
		
		StaffAccount sa = new StaffAccount();
		sa.setAccPassword(password);
		
		try
		{
			int status,status1;
			DBAO mydb = new DBAO();
			status = mydb.updateStaff(s);
			status1 = mydb.updateStaffAccount(sa,s);
					
			if(status>0 && status1>0){
				request.getRequestDispatcher("updateCompleted.jsp").forward(request,response);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception in dbao,msg="+ex.getMessage());
		}
		
	}

}
