package weicong.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Deadline;
import model.GroupJPA;
import ejb.DeadlineEJB;
import ejb.GroupEJB;

/**
 * Servlet implementation class ExportSubmissions
 */
@WebServlet("/ExtendDeadline")
public class ExtendDeadline extends AbstractServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    private GroupEJB bean;
    @EJB
    private DeadlineEJB deadlineEJB;
    
    public ExtendDeadline() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<GroupJPA> list = bean.retrieveAll();
		for(int i =0 ; i< list.size();i++){
			if(list.get(i).getSubmissions() != null){
				for(int j = 0;j <list.get(i).getSubmissions().size();j++){
					System.out.println(list.get(i).getSubmissions().get(j));
				}
			}
		}
		final String[] columns = {"teamName","submittedEntries","deadline","reason"};
		final String orderByColumn = request.getParameter("sort");
		if(orderByColumn != null){
			if(Arrays.asList(columns).contains(orderByColumn)){
				Collections.sort(list,new Comparator<GroupJPA>(){

					@Override
					public int compare(GroupJPA object1, GroupJPA object2) {
						int result = 0;
						String value1 = null;
						String value2 = null;
						Date date1 = null;
						Date date2 = null;
						if(orderByColumn.equals(columns[0])){
							value1 = object1.getGroupName();
							value2 = object2.getGroupName();
						}else if(orderByColumn.equals(columns[1])){
							value1 = String.valueOf(object1.getSubmissions().size());
							value2 = String.valueOf(object2.getSubmissions().size());
						}else if(orderByColumn.equals(columns[2])){
							if(object1.getDeadline() != null && object2.getDeadline() != null){
								date1 = object1.getDeadline().getDeadLine();
								date2 = object2.getDeadline().getDeadLine();
							}
						}else if(orderByColumn.equals(columns[3])){
							if(object1.getDeadline() != null && object2.getDeadline() != null)
								
							if(object1.getDeadline().getReason() != null && object2.getDeadline().getReason() != null){
								
							value1 = object1.getDeadline().getReason();
							value2 = object2.getDeadline().getReason();
							}
						}
						
						if(date1 != null && date2 != null){
							result = date1.after(date2) ? 1 : -1;
						}else if(value1 != null && value2 != null){
							result = value1.compareTo(value2);
						}
						return result;
					}
					
				});
			}
		}
		request.setAttribute("GroupList", list.toArray(new GroupJPA[]{}));		
		super.redirect(request, response, "extendDeadline.jsp", false);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateTime = request.getParameter("dateTime");
		String reason = request.getParameter("reason");
		String[] selected = request.getParameterValues("selected[]");
		if(dateTime != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			try {
				Date date = format.parse(dateTime);
				for(int i = 0; i < selected.length;i++){
					GroupJPA grp = bean.retrieveById(selected[i]);
					if(grp != null){
						
						Deadline d = new Deadline();
						d.setGroup_groupCode(grp);
						d.setDeadLine(date);
						d.setReason(reason);
						deadlineEJB.update(d);
					}
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.redirect(request, response, "ExtendDeadline?vmsg=deadline_saved", true);
			
		}
	}

}
