package com.sw.dao;

import java.sql.Connection;
import java.util.List;


public interface DataDao {
	int add(String sql) throws Exception;

	public List list(String tb,String where, String cpage, int pageSize) throws Exception;

	int update(String sql) throws Exception;

	void deleteById(String tb, String id) throws Exception;

	List getById(String tb, String id) throws Exception;

	int getTotalCount(String tb,String where) throws Exception;
	
	Connection getConn();
}
