package jolie.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Links;
import bean.Registration;
import database.DAO;

/**
 * Servlet implementation class VerifiesServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
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
	
		String[] nric = request.getParameterValues("nric");
		String[] contactNo = request.getParameterValues("contactNo");
		String[] email = request.getParameterValues("emailAdd");
		String[] foodPre = request.getParameterValues("foodPre");
		String[] comment = request.getParameterValues("allergicC");
		String[] attendance = request.getParameterValues("attendance");
		
		ArrayList<Registration> list = new ArrayList<Registration>();
		for (int i = 0; i < contactNo.length; i++) {
			
			Registration re = new Registration();
			re.setNric(nric[i]);
			re.setContactNo(contactNo[i]);
			re.setEmailAdd(email[i]);
			re.setFoodPre(foodPre[i]);
			re.setFoodOtherCom(comment[i]);
			re.setAttendance(attendance[i]);
			list.add(re);
		}

		try {
			DAO mydb = new DAO();
			int test = 0;
			for (int i = 0; i < list.size(); i++) {
				test = mydb.updateParticipantProfile(list.get(i));
			}

			if (test != 0) {
				RequestDispatcher rd = request
						.getRequestDispatcher(Links.LINK_VIEWPROFILE);
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request
						.getRequestDispatcher(Links.LINK_EDITPROFILEFAILED);
				rd.forward(request, response);
			}

		} catch (Exception ex) {
			System.out.println("Exception in dbao,msg=" + ex.getMessage());
		}
	}
}
