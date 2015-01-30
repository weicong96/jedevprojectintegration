package listener;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
	private boolean debug;
	
	private String[] public_pages = { 
			"ViewSubmission",
			""
	};
	
	public String[] participant_pages = {"AddSubmission"};
	public String[] admin_pages = {"ManageSubmissions"};
	
	public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = ((HttpServletRequest) request);
		HttpServletResponse httpRes = ((HttpServletResponse) response);
		String url = httpReq.getRequestURI().split("/")[2];
		if(url.contains(".jsp") || !url.contains(".")){
			HttpSession session = httpReq.getSession();
			String username = (String) session.getAttribute("username");
			String teamCode = (String) session.getAttribute("teamCode");
			String teamName = (String) session.getAttribute("teamName");
			if(Arrays.asList(participant_pages).contains(url) && username == null && teamCode== null && teamName== null){//group
				this.redirect(httpReq, httpRes, "login.jsp?page="+url, true);
			}else if(Arrays.asList(admin_pages).contains(url) && username == null){//admin
				this.redirect(httpReq, httpRes, "login.jsp?page="+url, true);	
			}
		}
		System.out.println(httpReq.getRequestURI()+" loaded");
		chain.doFilter(request, response);
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
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		boolean debugMode = Boolean.valueOf(fConfig.getInitParameter("debug"));
		this.debug = debugMode;
	}

}
