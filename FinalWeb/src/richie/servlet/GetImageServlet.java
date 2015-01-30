package richie.servlet;
/*package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Group;
import database.DBAO;


@WebServlet("/GetImageServlet")
public class GetImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		DBAO mydb = new DBAO ();
		mydb.retrieveGroup(username);
		
		Group grp = new Group();
		String filename = grp.getTeamPic();
		
		OutputStream os = response.getOutputStream();
		
			File file = new File(filename);
			try{
				FileInputStream fos = new FileInputStream(file);
				byte [] buffer = new byte [1024];
				int read = isFile.read(buffer);
				
				while (read >0){
					
					fos.write(buffer);
					read = isFile.read(buffer);
				
				}
					((Flushable) fos).flush();
					fos.close();
			} catch(Exception e){
				e.printStackTrace();
				return;
			}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
*/
