package com.sw.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.sw.util.FileUtil;
import com.sw.util.PropertiesReader;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(UploadAction.class);
	
	private String p;
	private String fileName;
	private String cid;//用来存字段名称

	private List<File> file;
	// private File[]file;
	private List<String> fileFileName;

	private List<String> fileContentType;

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws Exception {

		if (file == null || file.size() <= 0)
			return "input";

		try {
			String resultFileName = "";
			String tmpFileName = "";
			for (int i = 0; i < file.size(); i++) {
				System.out.println("~~~~~~~~~~");
				InputStream is = new FileInputStream(file.get(i));

				String root = ServletActionContext.getRequest().getRealPath(
						"/page/upload");

//				System.out.println(root);
				File destFile = new File(root, this.getFileFileName().get(i));
				int dupicateFile = 0;

				String strName = this.getFileFileName().get(i);
				int pointLoc = strName.lastIndexOf(".");

				tmpFileName = strName;

				String strExtName = strName.substring(pointLoc);
				strName = strName.substring(0, pointLoc);

				while (destFile.exists()) {
					tmpFileName = strName + dupicateFile + strExtName;
					boolean b = destFile.renameTo(new File(root, tmpFileName));
					// System.out.println(b);
					if (b) {
						break;
					}
					dupicateFile++;
				}
				OutputStream os = new FileOutputStream(destFile);
				resultFileName += tmpFileName + ";";

				byte[] buffer = new byte[400];

				int length = 0;

				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}

				is.close();
				os.close();
			}

			PropertiesReader reader = PropertiesReader.getIntance();
			FileUtil.delAllFile(reader.getProperty("struts.multipart.saveDir"));

			if (resultFileName != null && resultFileName.length() > 0)
				resultFileName = resultFileName.substring(0, resultFileName
						.length() - 1);
			this.setFileName(resultFileName); // 
			// return SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
}
