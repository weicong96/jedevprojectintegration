package weicong.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Sms;
import ejb.smsEJB;

/**
 * Servlet implementation class ViewAllSms
 */
@WebServlet("/ViewAllSms")
public class ViewAllSms extends AbstractServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private smsEJB bean; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllSms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Sms> list = bean.getAllSmsRecords();
		request.setAttribute("list",list);
		
		super.redirect(request, response, "ViewAllSmsRecords.jsp", false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
