package richie.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConversationJPA;
import model.GroupJPA;
import model.MessageJPA;
import model.StaffJPA;
import bean.Group;
import database.DBAO;
import ejb.ConversationEJB;
import ejb.GroupEJB;
import ejb.MessageEJB;
import ejb.StaffEJB;

/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/SendMessageServletP1")
public class SendMessageServletP1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GroupEJB groupEJB;
	@EJB
	private StaffEJB staffEJB;
	@EJB
	private ConversationEJB convEJB;
	@EJB
	private MessageEJB msgEJB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageServletP1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<StaffJPA> list = staffEJB.findAll();
		request.setAttribute("staffList",list);
		System.out.println(list.size());
		request.getRequestDispatcher("sendMessagesP1.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		Message msg = new Message();
		msg.setMessageDetails("Testest");
		
		msg.setConversation(ejb.getConv(1));
		ejb.add(msg);
		*/
		
		/*ArrayList<MessageJPA> msg = ejb.findAll();
		for(int i =0;i < msg.size();i++){
			System.out.println(msg.get(i).getConversation().getStaff().getIdstaff().toString());
		}*/
		
		ConversationJPA conv = new ConversationJPA();
		
		Set<MessageJPA> messageset = new HashSet<MessageJPA>();
		Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	
    	Set<MessageJPA> message = new HashSet<MessageJPA>();
		((MessageJPA) message).setMessageDetails(request.getParameter("conversation"));
		((MessageJPA) message).setMessageTime(sdf.format(cal.getTime()));
		((MessageJPA) message).setMessageDate(Calendar.getInstance().getTime());
		((MessageJPA) message).setConversation(conv);
		int nextID = (Integer)convEJB.getMaxID()+1;
		
		
		String staffName = request.getParameter("recipent");
		String grpCode = null;
		DBAO dbao;
		try {
			dbao = new DBAO();
			Group group = dbao.retrieveGroup((String)request.getSession().getAttribute("username"));
			grpCode = group.getTeamCode();
			
			GroupJPA grp = groupEJB.findByPk(grpCode);
			StaffJPA staff = staffEJB.findByName(staffName);
			
			conv.setStaffJPA(staff);
			conv.setSender(grpCode);
			conv.setReciever(staff.getIdstaff());
			conv.setGroup(grp);
		//	conv.setIdconversation(nextID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conv.setMessages(message);
		convEJB.add(conv);
	//	msgEJB.add(message);
		
		
	}

}
