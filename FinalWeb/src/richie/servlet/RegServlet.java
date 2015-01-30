package richie.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.misc.BASE64Decoder;
import bean.Group;
import bean.Registration;
import bean.School;
import database.DBAO;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String [] memberName =request.getParameterValues("memberName");
		String [] gender = request.getParameterValues("gender");
		String [] DOB = request.getParameterValues("dob");
		String [] nric = request.getParameterValues("nric");
		String [] contactNo = request.getParameterValues("contactNo");
		String [] emailAdd =  request.getParameterValues("emailAdd");
		String [] foodPre = request.getParameterValues("foodPre");
		String [] foodOtherCom = request.getParameterValues("allergicC");
		String [] attendance = request.getParameterValues("attendance");
		
		//boolean required = request.getParameterValues("memberName").length == request.getParameterValues("gender")
		System.out.println(memberName.length+" name length");	
				
		ArrayList<Registration> list = new ArrayList <Registration>();
		for(int i=0; i<memberName.length; i++){
			if(!memberName[i].equals("")){
				Registration r = new Registration();
				r.setMemberName(memberName[i]);
				r.setGender(gender[i]);
				r.setDOB(DOB[i]);
				r.setNric(nric[i]);
				r.setContactNo(contactNo[i]);
				r.setEmailAdd(emailAdd[i]);
				r.setFoodPre(foodPre[i]);
				r.setFoodOtherCom(foodOtherCom[i]);
				r.setAttendance(attendance[i]);
				r.setTic(request.getParameter("tic"));
					if(i==0){
						r.setLeaderStatus("Y");
					}
					else{
						r.setLeaderStatus("N");
					}
				list.add(r);
			}
		}
		
		HttpSession session = request.getSession(false);
		String data = (String) session.getAttribute("data");
		if(session.getAttribute("usingUpload")!=null&&((String)session.getAttribute("usingUpload")).equalsIgnoreCase("true")){
			String folder = getServletContext().getRealPath("/")+File.separator+"profilePic"+File.separator ;
			renameFile(folder+data, folder+request.getParameter("teamName") + ".png");
			System.out.println(folder);
		}else{
			//System.out.println("PNG image data on Base64: " + data); 
			FileOutputStream output = new FileOutputStream(new File(getServletContext().getRealPath("/")+File.separator+"profilePic"+File.separator+ request.getParameter("teamName") + ".png")); 
			output.write(new BASE64Decoder().decodeBuffer(data)); 
			output.flush(); 
			output.close();
			System.out.println(data);
		}
		Group g = new Group();
		
		String teamCodesub,teamCode,teamCodeOne = "";
		teamCodesub = request.getParameter ("teamName");
		teamCodeOne = teamCodesub.substring(0,3);
		teamCode = teamCodeOne.toUpperCase();
		g.setTeamCode(teamCode);
		g.setTeamName(request.getParameter("teamName"));
		g.setTeamPic(request.getParameter("teamName") + ".png");
		
		School s = new School();
		String schName = request.getParameter("schName");
		String schCode = "";
		for(int i=0; i<schName.length(); i++){
			char c = schName.charAt(i);
			if(Character.isUpperCase(c))
			{
				schCode += c;
			}
		}
		s.setSchoolCode(schCode);
		
		try
		{
			int status = 0;
			int status1 = 0;

			DBAO mydb = new DBAO();
			status1 = mydb.addGroup(g);
			for(int i=0; i<list.size(); i++){
				status = mydb.addRegistartion(list.get(i),g,s);
			}
			if(status>0 && status1 >0){
				request.getRequestDispatcher("registrationP3.jsp").forward(request,response);
				HttpSession sessions = request.getSession();
				sessions.setAttribute("teamCode", g.getTeamCode());
			}
			
		}catch(Exception ex)
		{
			System.out.println("Exception in dbao,msg="+ex.getMessage());
		}
	}
	private void renameFile(String oldName, String newName){
		File old = new File(oldName);
		File _new = new File(newName);
		old.renameTo(_new);
	}
}
