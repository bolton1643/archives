package com.sw.service.impl;

import java.util.List;

import com.sw.dao.FileDao;
import com.sw.pojo.FileInfo;
import com.sw.service.FileManager;

public class FileManagerImpl implements FileManager {
	private FileDao fileDao;


	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	public int addFileInfo(FileInfo fileInfo) throws Exception {
		fileDao.saveOrUpdate(fileInfo);
		return fileInfo.getId();
	}

	public void deleteFileInfo(FileInfo fileInfo) throws Exception {
		fileDao.delete(fileInfo);
	}

	public void deleteFileInfoById(Integer id) throws Exception {
		FileInfo f = new FileInfo();
		f.setId(id);
		fileDao.delete(f);
	}
	
	public List<FileInfo> findFileInfo(String name, String cpage,int pageSize)
			throws Exception {
		return fileDao.findFileInfoByName(name, cpage,pageSize);
	}

	public int getTotalCount(String title) throws Exception {
		return fileDao.getTotalCount(title);
	}

	public FileInfo getFileInfoById(Integer id) throws Exception {
		return fileDao.get(id);
	}

	public void updateFileInfo(FileInfo fileInfo) throws Exception {
		fileDao.saveOrUpdate(fileInfo);
	}

	public List getFileFolder() throws Exception {
		return fileDao.getFileFolderList();
	}

}
