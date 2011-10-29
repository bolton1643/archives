package com.sw.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.sw.util.SysConstant;
import com.sw.pojo.FileInfo;
import com.sw.pojo.User;
import com.sw.service.FileManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileManageAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(FileManageAction.class);
	private FileManager fileManager;
	private String cpage;
	private String total;
	private String url;

	private String name;
	private String uploadBy;
	private List<FileInfo> cl;
	private FileInfo c;
	

	private List fileFolder;//可以上传的文件夹
	private String uploadFolder;//选择的上传路径
	private String image[];

	@SuppressWarnings("unchecked")
	private boolean checkLogin() {
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user == null)
			return false;
		else
			return true;
	}

	public String getbyid() throws Exception {
		c = fileManager.getFileInfoById(c.getId());
		toListImageName();
		return SUCCESS;
	}
	public String view() throws Exception {
		c = fileManager.getFileInfoById(c.getId());
		toListImageName();
		return SUCCESS;
	}	
	public String list() throws Exception {
		try {
			String cp = this.getCpage();
			if (cp == null || cp.trim().length() <= 0)
				cp = "1";
			this.setCpage(cp);

			if (fileManager.findFileInfo(name, cpage, SysConstant.PAGE_SIZE) != null)
				this.setCl(fileManager.findFileInfo(name, cpage, SysConstant.PAGE_SIZE));
			int totalRecord = fileManager.getTotalCount(name);
			if (totalRecord % SysConstant.PAGE_SIZE != 0)
				totalRecord = totalRecord / SysConstant.PAGE_SIZE + 1;
			else
				totalRecord = totalRecord / SysConstant.PAGE_SIZE;
			this.setTotal(Integer.toString(totalRecord));
			this.setUrl("fmList.action");
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return SUCCESS;
		}
	}
	public String toStringImageName(){
		String str="";
		if(image !=null && image.length>0){
			for(int i=0;i<image.length-1;i++){
				str += image[i]+";";
			}
			str += image[image.length-1];
		}
		return str;
	}
	
	public void toListImageName(){
		if(c !=null && c.getImage()!=null && c.getImage().trim().length()>0){
			image = c.getImage().split(";");
		}
		
	}
	
	public String add()throws Exception{
		this.setFileFolder(fileManager.getFileFolder());
		return SUCCESS;
	}


	public String doAdd() throws Exception {
		if (!checkLogin())
			return "login";
		String imgName = toStringImageName();
		if(imgName !=null && imgName.length()>0)
			c.setFileName(imgName);
		if(uploadFolder !=null && uploadFolder.length()>0){
			c.setFilePath(uploadFolder);
		}
		c.setUploadDate(new Date());
		int result = fileManager.addFileInfo(c);
		if (-1 == result) {
			return SUCCESS;
		} else {
			return SUCCESS;
		}
	}
	
	public String modify() throws Exception {
		if (!checkLogin())
			return "login";
		String imgName = toStringImageName();
		if(imgName !=null && imgName.length()>0)
			c.setImage(imgName);
		
		c.setUploadDate(new Date());
		fileManager.updateFileInfo(c);
		return SUCCESS;
	}

	public String delete() throws Exception {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setId(c.getId());	
		fileManager.deleteFileInfo(fileInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		// 获取流
		PrintWriter out = response.getWriter();
		// 调用业务

		// 将list转为json
		String json = "success";
		// 将流打到客户端
		out.print(json);
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();
		return SUCCESS;
	}


	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public void setCl(List<FileInfo> cl) {
		this.cl = cl;
	}

	public void setC(FileInfo c) {
		this.c = c;
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FileInfo> getCl() {
		return cl;
	}

	public FileInfo getC() {
		return c;
	}

	public String[] getImage() {
		return image;
	}

	public void setImage(String[] image) {
		this.image = image;
	}

	public String getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}

	public String getUploadFolder() {
		return uploadFolder;
	}

	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}
	public List getFileFolder() {
		return fileFolder;
	}

	public void setFileFolder(List fileFolder) {
		this.fileFolder = fileFolder;
	}

}