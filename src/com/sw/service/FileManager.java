package com.sw.service;

import java.util.List;

import com.sw.pojo.FileInfo;

public interface FileManager {
	int addFileInfo(FileInfo fileInfo) throws Exception;

	public List<FileInfo> findFileInfo(String name, String cpage,int pageSize)
			throws Exception;

	void updateFileInfo(FileInfo fileInfo) throws Exception;

	void deleteFileInfoById(Integer id) throws Exception;

	void deleteFileInfo(FileInfo fileInfo) throws Exception;

	FileInfo getFileInfoById(Integer id) throws Exception;

	int getTotalCount(String name) throws Exception;
	
	List getFileFolder()throws Exception;
}
