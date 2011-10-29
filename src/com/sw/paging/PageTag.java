package com.sw.paging;

import com.opensymphony.xwork2.util.ValueStack;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
import org.apache.struts2.components.Component;   
import org.apache.struts2.views.jsp.ComponentTagSupport;   
  
/**  
 * 分页标签  
 * @author tangs  
 */  
public class PageTag extends ComponentTagSupport {   
    private String cpage;  //当前页   
    private String total;  //总页数   
    private String url;  //请求地址   
    private String param;
    public void setCpage(String cpage) {   
        this.cpage = cpage;   
    }   
  
    public void setTotal(String total) {   
        this.total = total;   
    }   
  
    public void setUrl(String url) {   
        this.url = url;   
    }   
  
    @Override  
    public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {   
        return new Pages(arg0); //返回Pages Component，分页的逻辑处理都在这个Component中   
    }   
  
    //获得参数   
    protected void populateParams() {   
        super.populateParams();   
           
        Pages pages = (Pages)component;   
        pages.setCpage(cpage);   
        pages.setTotal(total);   
        pages.setUrl(url); 
        pages.setParam(param);
    }

	public void setParam(String param) {
		this.param = param;
	}   
}