package weiching.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ReportManager;


/**
 * Servlet implementation class getListOfParticipant
 */
@WebServlet("/getListOfParticipant")
public class getListOfParticipant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getListOfParticipant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		ReportManager mgr = new ReportManager();
		String schoolName = null;
		ArrayList<Object> participants = new ArrayList<Object>();
		
		//get dropdown list selected value
		schoolName = request.getParameter("school");
		

		
		if(schoolName == null || schoolName== "All"){
			
			
		
				//pass dropdownlist value into the method
				participants = mgr.getListOfNonSpecificParticipants();
				
		
				
				
				if(participants != null) {
					
					

					
					//set request attribute name for the arraylist
					request.setAttribute("participantsList", participants);
					request.setAttribute("schoolName", schoolName);
					
					
					//set the destination of the request
					
					
					rd = request.getRequestDispatcher("listOfParticipants.jsp");
					
					//send request

					rd.forward(request, response);
				}
					else
						System.out.println("Error");
		
			}
				
			
			
			else
			{
				
				participants = mgr.getListOfParticipants(schoolName);
			
				
			
			
				if(participants != null) {
				
				
				
				
				//set request attribute name for the arraylist
				request.setAttribute("participantsList", participants);
				request.setAttribute("schoolName", schoolName);
				
		
				
				//set the destination of the request
		
				
				rd = request.getRequestDispatcher("listOfParticipants.jsp");
				
				//send request
				
				rd.forward(request, response);
			}
			else
	
				System.out.println("Error");
			}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
