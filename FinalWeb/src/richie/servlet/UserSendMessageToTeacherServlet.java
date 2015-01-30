package richie.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Conversation;
import bean.Group;
import bean.Message;
import bean.Staff;
import database.DBAO;
/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/UserSendMessageToTeacherServlet")
public class UserSendMessageToTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSendMessageToTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			DBAO mydb = new DBAO();
			ArrayList<Staff> staffList = mydb.retrieveAllStaffs();
			request.setAttribute("staffList",staffList);
			System.out.println(staffList.size());
			RequestDispatcher rd = request.getRequestDispatcher("sendMessagesP1.jsp");
			rd.forward(request,response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Conversation conversation = new Conversation();
		Message msg = new Message();
		
		String staffName = request.getParameter("recipent");
	try{
		DBAO mydb = new DBAO();
		Group group = mydb.retrieveGroup((String)request.getSession().getAttribute("username"));
		String grpCode = group.getTeamCode();
		System.out.println(grpCode);
		
		Staff staff = mydb.retrieveStaffDetails(staffName);
		String staffCode = staff.getIdstaff();
		System.out.println(staffCode);
		
		conversation.setSender(grpCode);
		conversation.setReciever(staffCode);
		conversation.setStaff_idstaff(staffCode);
		conversation.setGroup_teamCode(grpCode);
		
		int rowsAffected = mydb.CreateConversation(conversation);
		
		if(rowsAffected==1){
			conversation = mydb.retrieveConversationId();
			int converID = conversation.getIdconversation();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
			Calendar cal = Calendar.getInstance();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    	
	    	msg.setConversation_idconversation(converID);
	    	msg.setMessageDetails(request.getParameter("conversation"));
	    	msg.setMessageTime(sdf.format(cal.getTime()));
			msg.setMessageDate(dateFormat.format(date));
			int status = mydb.createMessage(msg);
			
			if(status==1){
				RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
				rd.forward(request,response);
				}
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
		
		
	}

}
