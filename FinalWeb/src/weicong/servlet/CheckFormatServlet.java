package weicong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class CheckFormatServlet
 */
@WebServlet("/CheckFormatServlet")
public class CheckFormatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckFormatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> map =request.getParameterMap();	
		PrintWriter pw = response.getWriter();
		pw.println(request.getParameter("data"));
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> map =request.getParameterMap();	
		JsonObject newObj = new JsonParser().parse(request.getParameter("data")).getAsJsonObject();
		String[] keys = new String[]{"screenshot[]","icon","video","sourceCode","compiled"};
		String[][] expected = new String[][]{
								new String[]{"jpeg","jpg","png","gif"},
								new String[]{"jpeg","jpg","png","gif"},
								new String[]{"mp4"},
								new String[]{"zip"},
								new String[]{"apk"}};
		PrintWriter pw = response.getWriter();
		ArrayList<String> uncorrect = new ArrayList<String>();
		for(int i = 0; i < keys.length;i++){
			JsonElement ele = newObj.get(keys[i]);
			if(ele!=null){//got such an element
				String value = ele.getAsString();
				if(value != null && value.split("\\.").length != 0){//got such value and with a
					if(keys[i].equals("screenshot[]")){
						 String[] files = value.split(",");
						 for(int s =0 ; s < files.length;s++){
							 String ext = files[s].split("\\.")[files[s].split("\\.").length-1];
							 if(!Arrays.asList(expected[i]).contains(ext) && !uncorrect.contains(keys[i])){//if it contains ext, means pass?
									uncorrect.add(keys[i]);

									 System.out.println(files[s]+" "+ext+"");
							}
						 }
					}else{
						String ext = value.split("\\.")[value.split("\\.").length - 1];//last item
						if(!Arrays.asList(expected[i]).contains(ext)){//if it contains ext, means pass?
							uncorrect.add(keys[i]);
						}
					}
				}
			}
		}
		String text  ="";
		for(int i = 0; i < uncorrect.size();i++){
			if(i == 0)
				text+= uncorrect.get(i);
			else
				text += ","+uncorrect.get(i);
		}
		pw.println(text);
		
		pw.close();
		
	}

}
