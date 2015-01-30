package weicong.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Sms;

import ejb.smsEJB;

/**
 * Servlet implementation class SendSMSRecord
 */
@WebServlet("/SendSMSRecord")
public class SendSMSRecord extends AbstractServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private smsEJB bean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendSMSRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.redirect(request, response, "SendSMSRecord.jsp", false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contacts = request.getParameter("contact");
		String content = request.getParameter("content");
		String remarks = request.getParameter("remarks");
		
		for(String item : contacts.split(",")){

			Sms sms = new Sms();
			sms.setContact(item);
			sms.setSmsMessage(content);
			sms.setPurpose(remarks);
			sms.setSmsTimeRequested(Calendar.getInstance().getTime());
			bean.add(sms);
		}
		
		
	}

}
