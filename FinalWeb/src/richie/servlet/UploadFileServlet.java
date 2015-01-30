package richie.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadFileServlet
 */
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
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
		
		//Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart == false){
			PrintWriter pw = response.getWriter();
			pw.write("Error, non-Multipart Form");
			pw.close();
			return;
		}
		
		//Create a factory with disk based file items
		FileItemFactory factory = new DiskFileItemFactory();
		
		//Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		String data = "";
		InputStream isFileData  = null;
		
		//Parse the request
		try{
			List<FileItem> /* FileItem */ items = upload.parseRequest(request);
			
			//Process the upload items
			Iterator iter = items.iterator();
			
			while(iter.hasNext()){

				FileItem item = (FileItem) iter.next();
				if(!item.isFormField()){
				//File Uploaded
					isFileData = item.getInputStream();
					data = item.getName();
					data = (new File(data)).getName();
				}
			}
		}
			catch(FileUploadException e){
				e.printStackTrace();
				PrintWriter writer = response.getWriter();
				writer.write("Error in parsing data");
				writer.close();
				return;
			}
			
			String path = getServletContext().getRealPath("/")+File.separator+"profilePic"+File.separator+ data;
			saveToFile(path, isFileData);
			
			HttpSession session = request.getSession();
			session.setAttribute("data", data);
			session.setAttribute("usingUpload", "true");
			
			RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
			rd.forward(request, response);
		}

	private void saveToFile(String filename, InputStream isFile) {
		// TODO Auto-generated method stub
		File file = new File(filename);
		try{
			FileOutputStream fos = new FileOutputStream(file);
			byte [] buffer = new byte [1024];
			int read = isFile.read(buffer);
			while (read >0){
				fos.write(buffer);
				read = isFile.read(buffer);
			}
				fos.flush();
				fos.close();
		} catch(Exception e){
			e.printStackTrace();
			return;
		}
		
	}
		
	
}
