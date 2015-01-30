package weicong.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ejb.smsEJB;

/**
 * Servlet implementation class SMSBroadcaster
 */
@WebServlet("/SMSBroadcaster")
public class SMSBroadcaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @EJB
       private smsEJB bean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SMSBroadcaster() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson son = new Gson();
		PrintWriter pw = response.getWriter();

		pw.println(son.toJson(bean.getAllSMSUnsent()));
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		bean.updateSmsTimeSent(id);
	}

}
