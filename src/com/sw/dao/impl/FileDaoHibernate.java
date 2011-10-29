package com.sw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sw.dao.FileDao;
import com.sw.pojo.FileInfo;
import com.sw.util.SysConstant;

public class FileDaoHibernate extends HibernateDaoSupport implements
		FileDao {

	public void delete(FileInfo fileInfo) {
		getHibernateTemplate().delete(fileInfo);
	}

	public List getFileFolderList() throws Exception {
		String sql = "select folder from filefolder";
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		Query query = s.createSQLQuery(sql);
		List l = query.list(); 
		if(s !=null) s.close();
		return l;
	}

	public List<FileInfo> findFileInfoByName(String name, String cpage,int pageSize) {
		String querySQL;
		if (name != null && name.trim().length() > 0) {
			querySQL = " from FileInfo where fileName like '%" + name
					+ "%'  order by id desc";
		} else {
			querySQL = " from FileInfo order by id desc";
		}
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		Query q = s.createQuery(querySQL);

		int start = Integer.parseInt(cpage);
		int limit = 0;
		if(pageSize<=0){
			start = (start - 1) * SysConstant.PAGE_SIZE;
			limit = SysConstant.PAGE_SIZE;			
		}else{
			start = (start - 1) * pageSize;
			limit = pageSize;				
		}

		q.setFirstResult(start);
		q.setMaxResults(limit);
		List l = q.list();
		if(s !=null) s.close();
		return l;
	}

	public FileInfo get(int id) {
		return (FileInfo) getHibernateTemplate().get(FileInfo.class, id);
	}

	public int getTotalCount(String name) throws Exception {
		String querySQL;
		if (name != null && name.trim().length() > 0) {
			querySQL = "select count(*) from FileInfo where filename like '%"
					+ name + "%'";
		} else {
			querySQL = "select count(*) from FileInfo ";
		}
		List ul = getHibernateTemplate().find(querySQL);
		if (ul != null && ul.size() >= 1) {
			return Integer.parseInt(ul.get(0).toString());
		}
		return 0;
	}

	public void saveOrUpdate(FileInfo fileInfo) {
		getHibernateTemplate().saveOrUpdate(fileInfo);
	}

}
