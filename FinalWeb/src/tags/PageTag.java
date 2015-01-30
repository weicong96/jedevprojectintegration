package tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTag extends SimpleTagSupport{
	private Integer pageNumber;
	private String currentUrl;
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter jw = getJspContext().getOut();
	//	int results = this.getJspContext().getAttribute("currentUrl");
		jw.print("<div id='page' class='text-center'>");
		jw.print("Pages : <br>");
		jw.print("<ul class='pagination'>");
		ArrayList<String> pages = new ArrayList<String>();
		int index = 0;
		int totalPages = 0;
		int count = ((Integer) ((PageContext)this.getJspContext()).getRequest().getAttribute("count"));
		totalPages = count / 10;
		totalPages += ((count%10 > 0) ? 1 : 0); 
		for( int i = 1; i < totalPages+1;i++){
			String targetUrl = breakTag(i);
			if((pageNumber+1) == i){
				pages.add("<li class='currentPage'><a href='"+targetUrl+"'><b>"+(i)+"</b></a></li>");
				index = i-1;
			}else{
				pages.add("<li><a href='"+targetUrl+"'>"+(i)+"</a></li>");
			}
		}
		int before = index - 3;
		boolean beforeMore = false;
		boolean afterMore = false;
		if(before<=-1){
			before = 0;
		}else{
			beforeMore = true;
			jw.print("<li><a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
		}
		int after = index + 4;
		if(after > pages.size()){
			after = pages.size();
		}else{
			afterMore = true;
			}
		for(int i = before;i< after;i++){
			jw.print(pages.get(i));
		}
		if(afterMore){
			jw.print("<li><a href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");
			
		}
		jw.print("</ul>");
		jw.print("</div>");
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	private String breakTag(int page){
		String target = "";
		if(currentUrl!=null){
			if(!currentUrl.isEmpty()){
				if(currentUrl.matches(".*page=[0-9]")){
					target = currentUrl.replaceAll("page=[0-9]", "page="+(page-1));
				}else{
					target = currentUrl+"&page="+(page-1);
				}
			}else{
				target = currentUrl+"page="+(page-1);
			}
		}
		return ((PageContext)this.getJspContext()).getRequest().getAttribute("javax.servlet.forward.request_uri")+"?"+target;
	}
	public String getCurrentUrl() {
		return currentUrl;
	}
	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}
	
}
