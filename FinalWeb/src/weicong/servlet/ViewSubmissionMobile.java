package weicong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Submission;

import com.google.gson.Gson;

import ejb.SubmissionEJB;

/**
 * Servlet implementation class ViewSubmissionMobile
 */
@WebServlet("/ViewSubmissionMobile")
public class ViewSubmissionMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private SubmissionEJB bean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSubmissionMobile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter p  = response.getWriter();
		
		Object[] sub = bean.retrieveAll(new String[0], new String[0],"dateSubmitted",true,-1);
		List<Submission> list = (List<Submission>) sub[0];
		for(int i = 0; i < list.size();i++){
			list.get(i).getGroup().setDeadline(null);
			list.get(i).setFieldsupdated(null);
		}
		p.write(new Gson().toJson(list));
		
		p.flush();
		p.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
