package weicong.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Extrafield;
import model.Submission;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.openjpa.lib.util.Files;

import util.Settings;

import com.google.gson.Gson;

import ejb.ExtraBean;
import ejb.SubmissionEJB;

public class AbstractServlet extends HttpServlet{
	Settings settings = null;
	@EJB
	private ExtraBean extrafield;
	@EJB
	private SubmissionEJB bean;
	public AbstractServlet(){
		
	}
	public void redirect(HttpServletRequest request,HttpServletResponse response,String url,boolean changeURL){
		
		if(!changeURL){	
			RequestDispatcher rd = request.getRequestDispatcher(url);
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.sendRedirect(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected Map<String, String> parseRequest(HttpServletRequest request,
			HttpServletResponse response,String[] fieldsToSave) throws IOException {
		return this.parseRequest(request, response, fieldsToSave,false,-1);
	}
	protected Map<String, String> parseRequest(HttpServletRequest request,
			HttpServletResponse response,String[] fieldsToSave,int id) throws IOException {
		return this.parseRequest(request, response, fieldsToSave,true,id);
	}
	protected Map<String, String> parseRequest(HttpServletRequest request,
			HttpServletResponse response,String[] fieldsToSave,boolean edit,int id) throws IOException {
		Map<String,String> returnedItems = new HashMap<String,String>();
		ArrayList<String> screenshotItems = new ArrayList<String>();
		String[] notAdditionalKeys = {"entryName","description","compiled","sourceCode","icon","screenshot[]","video"};
		if (!ServletFileUpload.isMultipartContent(request)) {
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
		
		try {
			List<FileItem> files = upload.parseRequest(request);
			Iterator iter = files.iterator();
			Iterator name = files.iterator();
			String namePrefix = "";
			int i = 0;
			String timePrefix = "";
			while(name.hasNext()){
				FileItem item = (FileItem) name.next();
				boolean inField = Arrays.asList(fieldsToSave).contains(item.getFieldName());
				if(inField || item.getFieldName().equalsIgnoreCase("timePrefix")){
					if(item.isFormField()){
						if(item.getFieldName().equalsIgnoreCase("entryName"))//need to get prefixes in advance
							namePrefix = item.getString(); 
						else if(item.getFieldName().equalsIgnoreCase("timePrefix"))
							timePrefix = item.getString();
					}
				}
			}
			while(iter.hasNext()){
				FileItem item = (FileItem) iter.next();
				String folderName = item.getString().replace("[]", "");//screenshot[] problem
				if(item.isFormField()){
					String[] defaultList = {"compiled","sourceCode","icon","screenshot[]","video"};//values to switch the key and value for due to selection of purpose field
					if(Arrays.asList(defaultList).contains(item.getString())){//reverse key value pair
						if(item.getString().equals("screenshot[]"))//don't add to map, add later because have duplicates to handle later
							screenshotItems.add(item.getFieldName().replace(timePrefix,""));
						else
							returnedItems.put(item.getString(), item.getFieldName().replace(timePrefix,""));//flip key value pair	
						FileInputStream fis = null ;
						try{
							fis = new FileInputStream(new File(Settings.getInstance().getServletPath()+"/files/temp/"+item.getFieldName()));
							String path = Settings.getInstance().getServletPath()+File.separator+"files"+File.separator+folderName+"/"+namePrefix+"_"+item.getFieldName().replace(timePrefix,"");
							System.out.println(path);
							saveToFile(path,fis);
						}catch(FileNotFoundException ex){
							if(edit){//not able to find file and is not file that is uploaded during current session

								System.out.println(item.getFieldName()+" : "+item.getString());
								Submission sub = bean.retrieveById(id,true);
								
								String previousFolder = "";
								for(int l = 0; l < notAdditionalKeys.length;l++){//looking for old value
									String value = item.getFieldName();
									if(this.getValueForDynamicFieldKey(notAdditionalKeys[l], sub).contains(value)){//check what type was the current value
										previousFolder = notAdditionalKeys[l].replace("[]", ""); 							
									}
								}
								if(previousFolder == null || previousFolder.isEmpty()){
									previousFolder = "additional";
								}
								String oldPath = (Settings.getInstance().getServletPath()+"/files/"+previousFolder+"/"+sub.getName()+"_"+item.getFieldName());
								String path = Settings.getInstance().getServletPath()+File.separator+"files"+File.separator+folderName+"/"+namePrefix+"_"+item.getFieldName().replace(timePrefix,"");
								this.moveFile(oldPath, path);
							}
						}
					}else{//for normal input fields, description etc.
						returnedItems.put(item.getFieldName(), item.getString());
					}
				}else{//save file here and put in map 
					
					strFileName = item.getName();
					strFileName = (new File(strFileName)).getName();
					
					String path = Settings.getInstance().getServletPath()+"/files/additional/"+namePrefix+"_"+strFileName;
					saveToFile(path,  item.getInputStream());
				}
				i++;
			}
			String screenshot = "";
			for(int j = 0; j < screenshotItems.size();j++){
				screenshot+=(j == 0 ? "" : ",")+screenshotItems.get(j);
			}
			
			Map<String,String> additionalFields = new HashMap<String,String>();
			Iterator keyItr = returnedItems.keySet().iterator();
			while(keyItr.hasNext()){
				String key = (String) keyItr.next();
				ArrayList<Extrafield> extras = extrafield.getRequiredSubmissionFields();
				ArrayList<String> acceptableExtra = new ArrayList<String>();
				for(Extrafield e : extras){
					acceptableExtra.add(e.getCamelCaseName());
				}
				if(!Arrays.asList(notAdditionalKeys).contains(key) && acceptableExtra.contains(key)){
					additionalFields.put(key, returnedItems.get(key));
					keyItr.remove();
				}
			}
			
			returnedItems.put("additional", new Gson().toJson(additionalFields));
			returnedItems.put("screenshots",screenshot);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnedItems;
	}
	private boolean moveFile(String oldFile,String newFile){
		File ofile = new File(oldFile);
		copy(ofile,new File(ofile.getAbsolutePath()+"old"));
		return ofile.renameTo(new File(newFile));
	}
	private void copy(File src,File dest){
		InputStream inStream = null;
		OutputStream outStream = null;
	 
	    	try{
	 
	    	    File afile =src;
	    	    File bfile =dest;
	 
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	 
	    	    	outStream.write(buffer, 0, length);
	 
	    	    }
	 
	    	    inStream.close();
	    	    outStream.close();
	 
	    	    System.out.println("File is copied successful!");
	 
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
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
			isFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	//acceptable values : {"entryName","description"}
	private String getValueForDynamicFieldKey(String key,Submission sub){
		String value = "";
		if(key.equals("entryName")){
			value = sub.getName();
		}else if(key.equals("description")){
			value = sub.getDescription();
		}else if(key.equals("compiled")){
			value = sub.getCompiled();
		}else if(key.equals("icon")){
			value = sub.getIcon();
		}else if(key.equals("screenshot[]")){
			value = sub.getScreenshot();
		}else if(key.equals("video")){
			value = sub.getVideo();
		}else if(key.equals("sourceCode")){
			value = sub.getSourceCode();
		}else if(key.equals("lastChanged")){
			value = sub.getDateSubmitted().toString();
		}else if(!key.equals("selectedImage")){
			Map<String,String> additional = new Gson().fromJson(sub.getAdditional(), new HashMap<String,String>().getClass());
			if(additional!=null)
				value = additional.get(key);
		}
		return value;
	}

}
