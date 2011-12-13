package com.sw.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sw.dao.DataDao;
import com.sw.util.SysConstant;

public class DataDaoHibernate extends HibernateDaoSupport implements
        DataDao {

    public int add(String sql) throws Exception {
        Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        Session s = super.getSession();
        Query q = s.createSQLQuery(sql);
        int result = q.executeUpdate();
//        if(s !=null) s.close();
        
        return result;
    }

    public List list(String tb,String where, String cpage, int pageSize) throws Exception {
        String sql = "select * from "+ tb ;
        if(where !=null && where.trim().length()>0){
            sql += " where ("+ where +" )";
        }
        sql += " order by id desc";
        Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        Session s = super.getSession();
//        s.setFlushMode(FlushMode.COMMIT);
        Query q = s.createSQLQuery(sql);
        
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
        
//        if(s !=null) s.close();
//        releaseSession(s);
        return l;
    }

    public int update(String sql) throws Exception {
        Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        Session s = super.getSession();
        Query q = s.createSQLQuery(sql);
        int result = q.executeUpdate();
//        if(s !=null) s.close();
        
        return result;
    }

    public void deleteById(String tb, String id) throws Exception {
        Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        Session s = super.getSession();
        String sql = "delete from "+ tb+" where id="+id;
        Query q = s.createSQLQuery(sql);
        int result = q.executeUpdate();
//        if(s !=null) s.close();
        
        return ;
    }

    public List getById(String tb, String id) throws Exception {
        Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        Session s = super.getSession();
        String sql = "select * from "+ tb+" where id="+id;
        Query q = s.createSQLQuery(sql);
        List l = q.list();
//        if(s !=null) s.close();
        return l;
    }

    public int getTotalCount(String tb,String where) throws Exception {
        String querySQL = "select count(*) from "+tb;
        if(where !=null && where.trim().length()>0){
            querySQL += " where ("+ where +" )";
        }
        Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        Session s = super.getSession();
//        s.setFlushMode(FlushMode.COMMIT);
        List ul = s.createSQLQuery(querySQL).list();
        if (ul != null && ul.size() >= 1) {
            return Integer.parseInt(ul.get(0).toString());
        }
//        releaseSession(s);
//        if(s !=null) s.close();
        return 0;
    }
    
    @Override
    public Connection getConn() {
        Session s = getHibernateTemplate().getSessionFactory().openSession();
        return s.connection();
    }//endof
}
