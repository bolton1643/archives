package com.sw.service;

import java.sql.Connection;
import java.util.List;

import com.sw.util.MetaDataRow;

public interface DataManager {
	int add(String sql) throws Exception;

	public List<Object[]> list(String tb,String where, String cpage,int pageSize)
			throws Exception;

	int update(String sql) throws Exception;

	void deleteById(String tb,String id) throws Exception;

	List getById(String tb,String id) throws Exception;

	int getTotalCount(String tb,String where) throws Exception;
	
	public boolean operateTable(String sql);

	public List<MetaDataRow> getMetaData(String tName);
	
	public Connection getConn();
}
