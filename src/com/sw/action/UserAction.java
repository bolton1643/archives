package com.sw.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sw.jsonfactory.treenode.ConvertToJson;
import com.sw.pojo.TreeNodeRight;
import com.sw.pojo.User;
import com.sw.service.UserManager;

public class UserAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(UserAction.class);
	private static final long serialVersionUID = 1L;
	public static final String LOGIN_SUCCESS = "success";
	public static final String LOGIN_FAIL = "failure";

	public static final String RESBONSE_ERROR = "error";
	public static final String RESBONSE_SUCCESS = "success";
	public static final String FORWARD_ERROR = "error";
	public static final String FORWARD_SUCCESS = "success";
	public static final String RESULT_COLLECT = "resultList";
	
	private List <User>cl;
	private String message;
	
	private int userId;
	
	private User user;
	private String loginName;
	private String password;
	
	private List radd;
	private List rmodify;
	private List rdelete;
	private List rprint;
	private List rdownload;
	
	
	Log log = LogFactory.getLog(this.getClass());

	private UserManager userManager;
	
	protected void print(HttpServletResponse response, String info) throws IOException {
		try {
			response.getWriter().print(info);
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	public String login() throws Exception {
		Map session = ActionContext.getContext().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			int loginFlag = userManager.validLogin(loginName, password);
			if (loginFlag == UserManager.LOGIN_SUCCESS) {
				User user = userManager.getUserByNameAndPass(loginName, password);
				session.put("user", user);
				session.put("username", user.getUserName());
				//print(response, RESBONSE_SUCCESS);
				message = "success";
				logger.error(user.getUserName()+" 登录成功");
				if(user.getUserName() !=null && user.getUserName().equalsIgnoreCase("admin")){
					message = "admin";
				}
			} else if (loginFlag == UserManager.LOGIN_FAIL) {
				//print(response, RESBONSE_ERROR);
				message = "error";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String userList() throws Exception{
		this.setCl(userManager.userList());
		return SUCCESS;
	}
	public String addUser() throws Exception {
		userManager.registe(user);
		return SUCCESS;
	}
	
	public String getUserById() throws Exception {
		User u = new User();
		u.setUserId(this.getUserId());
		User u2 = userManager.getUserById(user);
		this.setUser(u2);
		return SUCCESS;
	}
	
	public String modifyUser() throws Exception {
		userManager.modifyUser(user);
		return SUCCESS;
	}

	public String userRight() throws Exception {
		User u2 = userManager.getUserById(user);
		this.setUser(u2);
		return SUCCESS;
	}

	public String saveRight() throws Exception {
		List <TreeNodeRight> tl = new ArrayList<TreeNodeRight>();
		TreeNodeRight t;
		Set s = new HashSet();
		
		if(radd !=null && radd.size()>0){
			for(int i = 0;i< radd.size();i++){
				int add = Integer.parseInt((String)radd.get(i));
				if(! s.contains(add)){
					s.add(add);
					t = new TreeNodeRight();
					t.setRadd("1");
					t.setUserid(user.getUserId());
					t.setMenuid(add);
					
					tl.add(t);
				}else{
					for(int j=0;j< tl.size();j++){
						t = tl.get(j);
						if(t.getMenuid()==add){
							t.setRadd("1");
							break;
						}else{
							
						}
					}
				}
			}
		}
		
		if(rdelete !=null && rdelete.size()>0){
			for(int i = 0;i< rdelete.size();i++){
				int delete = Integer.parseInt((String)rdelete.get(i));
				if(! s.contains(delete)){
					s.add(delete);
					t = new TreeNodeRight();
					t.setRdelete("1");
					t.setUserid(user.getUserId());
					t.setMenuid(delete);
					
					tl.add(t);
				}else{
					for(int j=0;j< tl.size();j++){
						t = tl.get(j);
						if(t.getMenuid()==delete){
							t.setRdelete("1");
							break;
						}else{
							
						}
					}
				}
			}
		}
		
		if(rmodify !=null && rmodify.size()>0){
			for(int i = 0;i< rmodify.size();i++){
				int modify = Integer.parseInt((String)rmodify.get(i));
				if(! s.contains(modify)){
					s.add(modify);
					t = new TreeNodeRight();
					t.setRmodify("1");
					t.setUserid(user.getUserId());
					t.setMenuid(modify);
					
					tl.add(t);
				}else{
					for(int j=0;j< tl.size();j++){
						t = tl.get(j);
						if(t.getMenuid()==modify){
							t.setRmodify("1");
							break;
						}else{
							
						}
					}
				}
			}
		}
		
		if(rdownload !=null && rdownload.size()>0){
			for(int i = 0;i< rdownload.size();i++){
				int download = Integer.parseInt((String)rdownload.get(i));
				if(! s.contains(download)){
					s.add(download);
					t = new TreeNodeRight();
					t.setRdownload("1");
					t.setUserid(user.getUserId());
					t.setMenuid(download);
					
					tl.add(t);
				}else{
					for(int j=0;j< tl.size();j++){
						t = tl.get(j);
						if(t.getMenuid()==download){
							t.setRdownload("1");
							break;
						}else{
							
						}
					}
				}
			}
		}
		
		if(rprint !=null && rprint.size()>0){
			for(int i = 0;i< rprint.size();i++){
				int print = Integer.parseInt((String)rprint.get(i));
				if(! s.contains(print)){
					s.add(print);
					t = new TreeNodeRight();
					t.setRprint("1");
					t.setUserid(user.getUserId());
					t.setMenuid(print);
					
					tl.add(t);
				}else{
					for(int j=0;j< tl.size();j++){
						t = tl.get(j);
						if(t.getMenuid()==print){
							t.setRprint("1");
							break;
						}else{
							
						}
					}
				}
			}
		}
		
		userManager.saveRight(tl,user.getUserId());
		return SUCCESS;
	}
	
	public String rightList() throws Exception {
		Map session = ActionContext.getContext().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html;charset=utf-8");
			// 获取流
			PrintWriter out = response.getWriter();
			// 调用业务
			List<TreeNodeRight> list ;
			// 获取请求参数
			
			list = userManager.findUserRight(user);

			// 将list转为json
			String json = "";
			if (list != null && list.size() > 0){
				json = ConvertToJson.ConverListToJsonRight(list);				
			}

				//json = "[{\"text\":\"节点1\"},{\"text\":\"节点2\"}]";
			// 将流打到客户端
			out.print(json);
			// 清空缓存
			out.flush();
			// 关闭流
			out.close();

		} catch (IOException e) {
			log.debug("加载权限出错" + e.getStackTrace());
		}
		print(response, RESBONSE_SUCCESS);
		return SUCCESS;
	}
	
	public String changePwd() throws Exception {
		return SUCCESS;
	}
	
	public String logout() throws Exception {
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JSON(serialize=false) 
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List getCl() {
		return cl;
	}
	public void setCl(List cl) {
		this.cl = cl;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List getRadd() {
		return radd;
	}
	public void setRadd(List radd) {
		this.radd = radd;
	}
	public List getRmodify() {
		return rmodify;
	}
	public void setRmodify(List rmodify) {
		this.rmodify = rmodify;
	}
	public List getRdelete() {
		return rdelete;
	}
	public void setRdelete(List rdelete) {
		this.rdelete = rdelete;
	}
	public List getRprint() {
		return rprint;
	}
	public void setRprint(List rprint) {
		this.rprint = rprint;
	}
	public List getRdownload() {
		return rdownload;
	}
	public void setRdownload(List rdownload) {
		this.rdownload = rdownload;
	}
}
