package richie.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import ejb.ConversationEJB;
import ejb.GroupEJB;
import ejb.MessageEJB;
import ejb.StaffEJB;

/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/TeacherSendMessageServlet")
public class TeacherSendMessageServlet extends HttpServlet {
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
    public TeacherSendMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<GroupJPA> list = groupEJB.findAll();
		request.setAttribute("grouplist",list);
		System.out.println(list.size());
		//request.getRequestDispatcher("TeacherSendMessages.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConversationJPA conv = new ConversationJPA();
		
		MessageJPA message=new MessageJPA();
		Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	
		message.setMessageDetails(request.getParameter("conversation"));
		message.setMessageTime(sdf.format(cal.getTime()));
		message.setMessageDate(Calendar.getInstance().getTime());
		message.setConversation(conv);
		
		conv.setMessages((Set<MessageJPA>) message);
		
		String teamName = request.getParameter("recipent");
		String staffCode = null;
		
			staffCode = (String)request.getSession().getAttribute("username");
			
			StaffJPA staff = staffEJB.findByPk(staffCode);
			
			GroupJPA grp = groupEJB.findByName(teamName);
			
			conv.setStaffJPA(staff);
			conv.setGroup(grp);
		
			msgEJB.add(message);
			convEJB.add(conv);
	}

}
