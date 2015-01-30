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
 * Servlet implementation class TeacherSendMessageToUserServlet
 */
@WebServlet("/TeacherSendMessageToUserServlet")
public class TeacherSendMessageToUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherSendMessageToUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			DBAO mydb = new DBAO();
			ArrayList<Group> grouplist = mydb.retrieveAllGroups();
			request.setAttribute("grouplist",grouplist);
			System.out.println(grouplist.size());
			RequestDispatcher rd = request.getRequestDispatcher("TeacherSendMessages.jsp");
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
		
		String teamName = request.getParameter("recipent");
	try{
		DBAO mydb = new DBAO();
		Staff staff = mydb.retrieveStaff((String)request.getSession().getAttribute("username"));
		String staffID = staff.getIdstaff();
		System.out.println(staffID);
		
		Group group = mydb.retrieveGroupDetails(teamName);
		String teamCode = group.getTeamCode();
		System.out.println(teamCode);
		
		conversation.setSender(staffID);
		conversation.setReciever(teamCode);
		conversation.setStaff_idstaff(staffID);
		conversation.setGroup_teamCode(teamCode);
		
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
				RequestDispatcher rd = request.getRequestDispatcher("RetrieveStaff.jsp");
				rd.forward(request,response);
				}
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	}

}
