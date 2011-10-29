package com.sw.paging;
import com.opensymphony.xwork2.util.ValueStack;   
import java.io.IOException;   
import java.io.Writer;   
import java.util.logging.Level;   
import java.util.logging.Logger;   
import org.apache.struts2.components.Component;   
  
/**  
 * 分页逻辑Bean  
 * @author tangs  
 */  
public class Pages extends Component {   
    private String cpage;   
    private String total;   
    private String url;  
    private String param;
  
    public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getCpage() {   
        return cpage;   
    }   
  
    public void setCpage(String cpage) {   
        this.cpage = cpage;   
    }   
  
    public String getTotal() {   
        return total;   
    }   
  
    public void setTotal(String total) {   
        this.total = total;   
    }   
  
    public String getUrl() {   
        return url;   
    }   
  
    public void setUrl(String url) {   
        this.url = url;   
    }   
       
       
    public Pages(ValueStack arg0) {   
        super(arg0);   
    }   
  
    @Override  
    public boolean start(Writer writer) {   
        boolean result = super.start(writer);   
        try {   
            StringBuilder str = new StringBuilder();   
            boolean isValid = true;   
               
            //从ValueStack中取出数值   
            if (isValid) {   
                if (total.startsWith("%{") && total.endsWith("}")) {   
                    total = total.substring(2, total.length() -1);   
                    total = (String)this.getStack().findValue(total);   
                    isValid = total == null ? false : true;   
                } else {   
                    isValid = false;   
                }   
            }   
            if (isValid) {   
                if (cpage.startsWith("%{") && cpage.endsWith("}")) {   
                    cpage = cpage.substring(2, cpage.length() - 1);   
                    cpage = (String)this.getStack().findValue(cpage);   
                    isValid = cpage == null ? false : true;   
                } else {   
                    isValid = false;   
                }   
            }   
            if (isValid) {   
                if (url.startsWith("%{") && url.endsWith("}")) {   
                    url = url.substring(2, url.length() - 1);   
                    url = (String)this.getStack().findValue(url);   
                    isValid = url == null ? false : true;   
                } else {   
                    isValid = false;   
                }   
            } 
            if (isValid) {   
            	if(param !=null){
                    if (param.startsWith("%{") && param.endsWith("}")) {   
                    	param = param.substring(2, param.length() - 1);   
                    	param = (String)this.getStack().findValue(param);   
                        isValid = param == null ? false : true;   
                    } else {   
                        isValid = false;   
                    }   	
            	}
            }  
  
            if (isValid) {   
                Integer cpageInt = Integer.valueOf(cpage);   
                //当前页与总页数相等   
                if (cpage.equals(total)) {   
                    //如果total = 1，则无需分页，显示“[第1页] [共1页]”   
                    if ("1".equals(total)) {   
                        str.append("[第 " + cpage + " 页]");   
                        str.append(" [共 " + total + " 页]");   
                    } else {   
                    	if(param !=null && param.trim().length()>0){
                            //到达最后一页,显示“[首页] [上一页] [末页]”   
                            str.append("<a href='");   
                            str.append(url);   
                            str.append("?cpage=1&total="+total+"&url="+url+"&"+param);   
                            str.append("'>[首页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + (cpageInt - 1) + "&total=" + total+"&url="+url+"&"+param);   
                            str.append("'>[上一页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + total + "&total=" + total+"&url="+url+"&"+param);   
                            str.append("'>[末页]</a>");  
                    	}else{
                            //到达最后一页,显示“[首页] [上一页] [末页]”   
                            str.append("<a href='");   
                            str.append(url);   
                            str.append("?cpage=1&total="+total+"&url="+url);   
                            str.append("'>[首页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + (cpageInt - 1) + "&total=" + total+"&url="+url);   
                            str.append("'>[上一页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + total + "&total=" + total+"&url="+url);   
                            str.append("'>[末页]</a>");                      		
                    	}
                    }   
                } else {   
                    //当前页与总页数不相同   
                    if ("1".equals(cpage)) {
                    	if(param !=null && param.trim().length()>0){
                            str.append("<a href='");   
                            str.append(url);   
                            str.append("?cpage=1&total="+total+"&url="+url+"&"+param);   
                            str.append("'>[首页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + (cpageInt + 1) + "&total=" + total+"&url="+url+"&"+param);   
                            str.append("'>[下一页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + total + "&total=" + total+"&url="+url+"&"+param);   
                            str.append("'>[末页]</a>"); 
                    	}else{
                            str.append("<a href='");   
                            str.append(url);   
                            str.append("?cpage=1&total="+total+"&url="+url);   
                            str.append("'>[首页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + (cpageInt + 1) + "&total=" + total+"&url="+url);   
                            str.append("'>[下一页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + total + "&total=" + total+"&url="+url);   
                            str.append("'>[末页]</a>");                    		
                    	}
                        //第一页，显示“[首页] [下一页] [末页]”   
  
                    } else {   
                        //不是第一页，显示“[首页] [上一页] [下一页] [末页]”   
                    	if(param !=null && param.trim().length()>0){
                            str.append("<a href='");   
                            str.append(url);   
                            str.append("?cpage=1&total="+total+"&url="+url+"&"+param);   
                            str.append("'>[首页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + (cpageInt - 1) + "&total=" + total+"&url="+url+"&"+param);   
                            str.append("'>[上一页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + (cpageInt + 1) + "&total=" + total+"&url="+url+"&"+param);   
                            str.append("'>[下一页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + total + "&total=" + total+"&url="+url+"&"+param);   
                            str.append("'>[末页]</a>");   
                    	}else{
                            str.append("<a href='");   
                            str.append(url);   
                            str.append("?cpage=1&total="+total+"&url="+url);   
                            str.append("'>[首页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + (cpageInt - 1) + "&total=" + total+"&url="+url);   
                            str.append("'>[上一页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + (cpageInt + 1) + "&total=" + total+"&url="+url);   
                            str.append("'>[下一页]</a> <a href='");   
                            str.append(url);   
                            str.append("?cpage=" + total + "&total=" + total+"&url="+url);   
                            str.append("'>[末页]</a>");   	
                    	}

                    }   
                }   
            }   
              
            writer.write(str.toString());   
               
        } catch (IOException ex) {   
            Logger.getLogger(Pages.class.getName()).log(Level.SEVERE, null, ex);   
        }   
        return result;   
    }   
}