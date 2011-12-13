package com.sw.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sw.dao.UserDao;
import com.sw.pojo.TreeNodeRight;
import com.sw.pojo.User;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * <p>
 * Title: 用户类DAO实现
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * 
 * @author
 * @version
 */
public class UserDaoHibernate extends HibernateDaoSupport implements UserDao {

    public void delete(Integer id) {
        // getHibernateTemplate相当于getSession
        getHibernateTemplate().delete(
                getHibernateTemplate().get(User.class, id));
    }

    public void delete(User user) {
        getHibernateTemplate().delete(user);
    }

    /**
     * 查询全部用户
     * 
     * @return 全部用户
     */
    public List<User> findAll() {
        return (List<User>) getHibernateTemplate().find("from User");
    }

    /**
     * 根据id查找用户
     * 
     * @param id
     *            �?��查找的用户id
     */
    public User get(Integer id) {
        return (User) getHibernateTemplate().get(User.class, id);
    }

    /**
     * 增加用户
     * 
     * @param user
     *            �?��增加的员�?
     */
    public void save(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    /**
     * 修改用户
     * 
     * @param user
     *            �?��修改的用�?
     */
    public void update(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    public User findByNameAndPass(String name, String pass) {
        String[] args = { name, pass };
        List<User> users = getHibernateTemplate().find(
                "from User where loginName=? and password=?", args);
        if (users.size() >= 1) {
            return users.get(0);
        } else {
            return null;
        }
    }



    public void saveRight(List<TreeNodeRight> tl, int userId) {
        // 删除已有权限
        String sql = "delete from userright where userid=" + userId;
        Session s = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        s.createSQLQuery(sql).executeUpdate();
        s.setFlushMode(FlushMode.COMMIT);

        // 添加新权限
        String menuId = "";
        String sH = "insert into userright(userid,menuid,radd,rdelete,rmodify,rdownload,rprint)values(";
        String sD = "";
        if (tl != null && tl.size() > 0) {
            for (int i = 0; i < tl.size(); i++) {
                sD = "";
                TreeNodeRight t = tl.get(i);
                if (i == 0)
                    menuId += t.getMenuid();
                else
                    menuId += ","+t.getMenuid();
                
                sD = t.getUserid() + "," + t.getMenuid() + "," + t.getRadd()
                        + "," + t.getRdelete() + "," + t.getRmodify() + ","
                        + t.getRdownload() + "," + t.getRprint() + ")";

                sql = sH + sD;
                s.createSQLQuery(sql).executeUpdate();

            }
            s.setFlushMode(FlushMode.COMMIT);
        }
/* modify by wuwb
        // 添加目录权限
        s.createSQLQuery("insert into userright (userid,menuid)values("+userId+",1)").executeUpdate();
        
        if(menuId !=null && menuId.length()>0){
            sql = "select distinct parent_id from treenode where id in("+menuId+")";
            List l = s.createSQLQuery(sql).list();
            if(l !=null && l.size()>0){
                for (int i = 0; i < l.size(); i++) {
                    Integer iid = (Integer)l.get(i);
                    sql = "insert into userright (userid,menuid)values("+userId+","+iid+")";
                    s.createSQLQuery(sql).executeUpdate();
                }
            }            
        }
*/
    }//end of func

    public List<TreeNodeRight> findUserRight(int userId) {
        Session s = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        String q = "";
        if (userId == 2) {
            q = "select id,text, parent_id,expanded,isfolder,'1','1','1','1',openurl,'1' from treenode group by parent_id,id desc ";
        }else if (userId == 0) {
            q = "select id,text, parent_id,expanded,isfolder,'0','0','0','0',openurl,'0' from treenode group by parent_id,id desc ";
        } else {
            q = "select t1.id,t1.text, t1.parent_id,t1.expanded,t1.isfolder,"
            + "t2.radd,t2.rdelete,t2.rmodify,t2.rdownload,t1.openurl,t2.rprint from treenode t1 left join userright t2 "
            + " on t1.id=t2.menuid and t2.userid= " + userId
            + " order by t1.parent_id,t1.id desc";
        }//end

        try {
            List l = s.createSQLQuery(q).list();

            if (l.size() >= 1) {
                List<TreeNodeRight> t = new ArrayList<TreeNodeRight>();
                
                for (int i = 0; i < l.size(); i++) {
                    TreeNodeRight tnr = new TreeNodeRight();
                    Object o[] = (Object[]) l.get(i);
                    tnr.setId(((Integer) o[0]).intValue());
                    if (o[1] != null)
                        tnr.setText((String) o[1]);
                    if (o[2] != null)
                        tnr.setParentId(((Integer) o[2]).intValue());
                    if (o[3] != null)
                        tnr.setExpanded((String) o[3]);
                    if (o[4] != null)
                        tnr.setIsfolder((String) o[4]);
                    if (o[5] != null)
                        tnr.setRadd((String) o[5]);
                    if (o[6] != null)
                        tnr.setRdelete((String) o[6]);
                    if (o[7] != null)
                        tnr.setRmodify((String) o[7]);
                    if (o[8] != null)
                        tnr.setRdownload((String) o[8]);
                    if (o[9] != null)
                        tnr.setOpenurl((String) o[9]);
                    if (o[10] != null)
                        tnr.setRprint((String) o[10]);
                    
                    Iterator<TreeNodeRight> it = t.iterator();
                    boolean insert = false;
                    while(it.hasNext()){
                        TreeNodeRight temp = it.next();
                        if(tnr.getParentId().intValue() == temp.getId().intValue()){
                            int _index = t.indexOf(temp);
                            t.add(_index+1, tnr);
                            insert = true;
                            break;
                        }//end of insert
                    }//end of whiel
                    if(!insert)
                        t.add(tnr);
                }//end
                return t;
            }//endof list
        } catch (Exception e) {
            e.printStackTrace();
        }//endof try
        return null;
    }//endoffunc
}
