package com.sw.service.impl;

import java.util.List;

import com.sw.dao.DataDao;
import com.sw.service.DataManager;

public class DataManagerImpl implements DataManager {
	private DataDao dataDao;
	
	public DataDao getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}

	public int add(String sql) throws Exception {
		return dataDao.add(sql);
	}

	public List list(String tb,String where, String cpage, int pageSize) throws Exception {
		return dataDao.list(tb,where,cpage,pageSize);
	}

	public int update(String sql) throws Exception {
		return dataDao.update(sql);
	}

	public void deleteById(String tb, String id) throws Exception {
		dataDao.deleteById(tb,id);
	}

	public List getById(String tb, String id) throws Exception {
		return dataDao.getById(tb,id);
	}

	public int getTotalCount(String tb,String where) throws Exception {
		return dataDao.getTotalCount(tb,where);
	}

}
