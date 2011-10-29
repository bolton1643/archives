package com.sw.dao;

import java.util.List;

import com.sw.pojo.FileInfo;
public interface FileDao {
	FileInfo get(int id);

	void saveOrUpdate(FileInfo fileInfo);

	void delete(FileInfo fileInfo);

	List<FileInfo> findFileInfoByName(String name, String cpage,int pageSize);

	int getTotalCount(String name) throws Exception;
	
	List getFileFolderList()throws Exception;
}