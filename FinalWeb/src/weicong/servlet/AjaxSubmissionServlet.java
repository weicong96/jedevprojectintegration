package weicong.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.Settings;

import com.google.gson.Gson;

/**
 * Servlet implementation class AjaxUploadServlet
 */
@WebServlet("/AjaxSubmissionServlet")
public class AjaxSubmissionServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxSubmissionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String teamCodeSession = "ACS_01";
		ArrayList<String> fileNames = new ArrayList<String>();
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter pw = response.getWriter();
			pw.write("Error, non-Multipart form");
			pw.close();
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items;
		String strFileName = "";
		long timeNow = Calendar.getInstance().getTimeInMillis();
		
		try {
			items = upload.parseRequest(request);
			InputStream[] inputSList = new InputStream[items.size()];// contents of file
			Iterator iterator = items.iterator();
			int i = 0;
			while(iterator.hasNext()){
				FileItem item = (FileItem)iterator.next();
				if(!item.isFormField()){
					inputSList[i] = item.getInputStream();
					strFileName = item.getName();
					strFileName = (new File(strFileName)).getName();
					
					String fullName = strFileName;
					String path = "";
					path = Settings.getInstance().getServletPath()+"/files/temp/"+teamCodeSession+timeNow+"_"+fullName;
					saveToFile(path, inputSList[i]);
					
					fileNames.add(teamCodeSession+timeNow+"_"+fullName);
				}
				i++;
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		pw.write(new Gson().toJson(new Object[]{fileNames,teamCodeSession+timeNow+"_"}));
		pw.close();
	}
	protected Map<String, String> parseRequest(HttpServletRequest request,
			HttpServletResponse response,String[] fieldsToSave,boolean temp) throws IOException {
		Map<String,String> myMap = new HashMap<String,String>();
		//String[] complusory = new String[]{"entryName","description","compiled","sourceCode","icon","video","selectedImage","screenshot[]"};
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart == false) {
			PrintWriter pw = response.getWriter();
			pw.write("Error, non-Multipart form");
			pw.close();
			return null;
		}
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		String strFileName = "";
		InputStream[] inputSList = new InputStream[fieldsToSave.length];// contents of file
		int i = 0;
		try {
			ArrayList<String> screenshotFiles = new ArrayList<String>();
			List<FileItem> items = upload.parseRequest(request);
			// Process the uploaded items
			Iterator iter = items.iterator();
			Iterator name = items.iterator();
			String namePrefix = "";
			while(name.hasNext()){
				FileItem item = (FileItem) name.next();
				boolean inField = Arrays.asList(fieldsToSave).contains(item.getFieldName());
				if(inField){
					if(item.isFormField())
						if(item.getFieldName().equalsIgnoreCase("entryName"))
							namePrefix = item.getString(); 
				}
			}
			Map<String,String> additional = new HashMap<String,String>();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				boolean inField = Arrays.asList(fieldsToSave).contains(item.getFieldName());
				if(inField){
					if (!item.isFormField()) {
						// File Uploaded
							inputSList[i] = item.getInputStream();
							
							strFileName = item.getName();
							strFileName = (new File(strFileName)).getName();
							
							String fullName = strFileName;
							String subFolder = item.getFieldName().equalsIgnoreCase("screenshot[]") ? "screenshot" : item.getFieldName();
							String path = "";
							if(!temp)
								path = Settings.getInstance().getServletPath()+File.separator+"files"+File.separator+subFolder +File.separator+namePrefix +"_"+fullName;	
							else{
								path = Settings.getInstance().getServletPath()+File.separator+"files"+File.separator+"temp" +File.separator+ namePrefix+"_"+fullName;	
								myMap.put("filePath", path);
							}
							saveToFile(path, inputSList[i]);
							
							if(item.getFieldName().equals("screenshot[]")){
								screenshotFiles.add(fullName);
							}else{
								myMap.put(item.getFieldName(), fullName);
							}
							
							i++;
					}else{
						myMap.put(item.getFieldName(), item.getString());//value fields
					}

				//	if(!Arrays.asList(complusory).contains(item.getFieldName())){//if not inside complusory field, means it is somewhere else.
					//	additional.put(item.getFieldName(), myMap.get(item.getFieldName()));
					//}
				}
			}
			String screenshot = "";
			for(int j = 0; j < screenshotFiles.size();j++){
				screenshot+=(j == 0 ? "" : ",")+screenshotFiles.get(j);
			}
			myMap.put("screenshots",screenshot);
			myMap.put("additional", new Gson().toJson(additional));
		} catch (FileUploadException e) {
			e.printStackTrace();
			PrintWriter pw = response.getWriter();
			pw.write("Error in parsing data");
			pw.close();
		}
		return myMap;
	}

	private void saveToFile(String filename, InputStream isFile) {
		File file = new File(filename);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int read = isFile.read(buffer);
			while (read > 0) {
				fos.write(buffer);
				read = isFile.read(buffer);
			}
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}


}
