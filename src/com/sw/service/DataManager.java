package com.sw.service;

import java.util.List;

public interface DataManager {
	int add(String sql) throws Exception;

	public List list(String tb,String where, String cpage,int pageSize)
			throws Exception;

	int update(String sql) throws Exception;

	void deleteById(String tb,String id) throws Exception;

	List getById(String tb,String id) throws Exception;

	int getTotalCount(String tb,String where) throws Exception;
}
