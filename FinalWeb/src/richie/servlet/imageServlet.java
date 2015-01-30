package richie.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class imageServlet
 */
@WebServlet("/imageServlet")
public class imageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageServlet() {
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
		try 
		{ 
			StringBuffer buffer = new StringBuffer(); 
			Reader reader = request.getReader(); 
			int current; 
			
			while((current = reader.read()) >= 0) 
				buffer.append((char) current); 
			
			String data = new String(buffer); 
			data = data.substring(data.indexOf(",") + 1);
			
			HttpSession session = request.getSession();
			session.setAttribute("data", data);
			} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
	}

}
