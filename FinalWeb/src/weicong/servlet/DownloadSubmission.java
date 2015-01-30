package weicong.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Submission;

import org.apache.commons.io.IOUtils;

import ejb.SubmissionEJB;

/**
 * Servlet implementation class DownloadSubmission
 */
@WebServlet("/DownloadSubmission")
public class DownloadSubmission extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private SubmissionEJB bean;
    public DownloadSubmission() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Submission sub = bean.retrieveById(Integer.valueOf(request.getParameter("id")));
		
		String url = this.getServletContext().getRealPath("/")+File.separator+"files"+File.separator+"compiled"+File.separator+sub.getName()+"_"+sub.getCompiled();
		InputStream stream = new FileInputStream(url);
		response.setContentType("application/vnd.android.package-archive"); 
		response.setHeader("Content-Disposition", "attachment;filename="+sub.getCompiled());
		response.getOutputStream().write(IOUtils.toByteArray(stream));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
