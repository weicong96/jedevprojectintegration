package weicong.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
 * Servlet implementation class ExportSubmissions
 */
@WebServlet("/ExportSubmissions")
public class ExportSubmissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private SubmissionEJB bean;
    public ExportSubmissions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teamCode = (String) request.getSession().getAttribute("teamCodeSession");
		if(teamCode == null)
			teamCode = "ACS_01";
		
		String[] folders = request.getParameterValues("files");
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyHHmmSS");
		String zipName = format.format(Calendar.getInstance().getTime());
		String zipPath = getServletContext().getRealPath("/")+File.separator+zipName;
		ArrayList<String> files = new ArrayList<String>();
		ArrayList<String> foldersList = new ArrayList<String>();
		ArrayList<String> teamList = new ArrayList<String>();
		
		String[] selected = request.getParameterValues("selected[]");
		String file = "";
		if(selected!=null && folders != null){
			
			for(String selectedID : selected){
				
				Submission sub = bean.retrieveById(Integer.parseInt(selectedID),true);
				
				for(String folder : folders){
					file = "";
					if(folder.equals("sourceCode")){
						file += sub.getSourceCode();
					}else if(folder.equals("compiled")){
						file += sub.getCompiled();
					}else if(folder.equals("icon")){
						file += sub.getIcon();
					}else if(folder.equals("screenshot")){	
						for(String scFile : sub.getScreenshot().split(",")){
							files.add(scFile);
							foldersList.add(folder);
							teamList.add(sub.getGroup().getTeamName());
						}
					}else if(folder.equals("video")){
						file += sub.getVideo();
					}
					if(!folder.equals("screenshot")){
						files.add(file);
						foldersList.add(folder);
						teamList.add(sub.getGroup().getGroupName());
					}
				}
				
			}
			addToZip(files.toArray(new String[]{}),foldersList.toArray(new String[]{}),zipPath,teamList.toArray(new String[]{}));
			
		}
		InputStream stream = new FileInputStream(zipPath+".zip");
		response.setContentType("application/zip"); 
		response.setHeader("Content-Disposition", "attachment;filename="+zipName+".zip");
		response.getOutputStream().write(IOUtils.toByteArray(stream));
	}
	private void addToZip(String[] fileNames,String[] foldersList,String zipName,String[] teamName){
		FileOutputStream fos = null;
		try {
			File zipFile = new File(zipName+".zip");
			fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			
			for(int i = 0;i < fileNames.length;i++){
				String fileName = fileNames[i];
				File file = new File(getServletContext().getRealPath("/")+File.separator+"files"+File.separator+foldersList[i]+File.separator+fileName);
				FileInputStream fis = new FileInputStream(file);
				ZipEntry zipEntry = new ZipEntry(foldersList[i]+File.separator+fileName);
				zos.putNextEntry(zipEntry);
	
				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zos.write(bytes, 0, length);
				}
				fis.close();
			}
			zos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
