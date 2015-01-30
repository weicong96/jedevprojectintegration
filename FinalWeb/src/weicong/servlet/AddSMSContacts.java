package weicong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Group;
import database.DBAO;

/**
 * Servlet implementation class addSMSContacts
 */
@WebServlet("/AddSMSContacts")
public class AddSMSContacts extends AbstractServlet {
	private static final long serialVersionUID = 1L;
    public AddSMSContacts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBAO manager = null;
		try {
			manager = new DBAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Group> list = manager.retrieveGroup();//find all and get the contacts
		
		request.setAttribute("groups", list);
		super.redirect(request, response, "addSMSContacts.jsp", false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
