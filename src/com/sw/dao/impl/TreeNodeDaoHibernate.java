package com.sw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sw.dao.TreeNodeDao;
import com.sw.pojo.TreeNode;
import com.sw.pojo.TreeNodeRight;

public class TreeNodeDaoHibernate extends HibernateDaoSupport implements
		TreeNodeDao {

	public TreeNode delete(TreeNode treeNode) {
		Session s = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		TreeNode t = get(treeNode.getId());

		s.clear();
		getHibernateTemplate().delete(t);

		// if(s !=null) s.close();
		return t;
	}

	public TreeNode get(int id) {
		return (TreeNode) getHibernateTemplate().get(TreeNode.class, id);
	}

	public void update(TreeNode treeNode) {
		getHibernateTemplate().saveOrUpdate(treeNode);
		return;

	}

	@SuppressWarnings("unchecked")
	public List<TreeNode> findChildren(String parentid) {
		int parentId = Integer.parseInt(parentid);
		// List<TreeNode> treeNodes = getHibernateTemplate().find("from TreeNode
		// where parentId=?",parentId);
		List<TreeNode> treeNodes = getHibernateTemplate()
				.find("from TreeNode ");
		if (treeNodes.size() >= 1) {
			return treeNodes;
		} else {
			return null;
		}
	}

	public List<TreeNodeRight> findChildren(String parentid, int userid) {
		int parentId = Integer.parseInt(parentid);
		// List<TreeNode> treeNodes = getHibernateTemplate().find("from TreeNode
		// where parentId=?",parentId);

		Session s = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String q = "from TreeNode where id in(select menuId from UserRight
		// where userId="+userid+")";
		String q = "";
		if(userid ==2){
			q = "select id,text, parent_id,expanded,isfolder,'1','1','1','1',openurl,'1' from treenode  ";	
		}else {
			 q = "select t1.id,text, parent_id,expanded,isfolder,radd,rdelete,rmodify,rdownload,openurl,rprint from treenode t1,userright t2 where t1.id = t2.menuid and t2.userid="
					+ userid;	
		}
		
		System.out.println(q);
		try{
		List l = s.createSQLQuery(q).list();
		
		if (l.size() >= 1){
			List<TreeNodeRight> t = new ArrayList<TreeNodeRight>();
			for(int i=0;i<l.size();i++){
				TreeNodeRight tnr = new TreeNodeRight();
				Object o[] = (Object[])l.get(i);
				tnr.setId(((Integer)o[0]).intValue());
				if(o[1] !=null) tnr.setText((String)o[1]);
				if(o[2] !=null) tnr.setParentId(((Integer)o[2]).intValue());
				if(o[3] !=null) tnr.setExpanded((String)o[3]);
				if(o[4] !=null) tnr.setIsfolder((String)o[4]);
				if(o[5] !=null) tnr.setRadd((String)o[5]);
				if(o[6] !=null) tnr.setRdelete((String)o[6]);
				if(o[7] !=null) tnr.setRmodify((String)o[7]);
				if(o[8] !=null) tnr.setRdownload((String)o[8]);
				if(o[9] !=null) tnr.setOpenurl((String)o[9]);
				if(o[10] !=null) tnr.setRprint((String)o[10]);
				
				t.add(tnr);
			}
			return t;	
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;

	}

	public int save(TreeNode treeNode) {
		getHibernateTemplate().saveOrUpdate(treeNode);
		return treeNode.getId();
	}

	public void updateExpanded(Integer id) {
		String querySQL = "select count(*) from treenode where parent_id = "
				+ id;
		Session s = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		List ul = s.createSQLQuery(querySQL).list();
		if (ul != null && ul.size() >= 1) {
			int subMenuCount = Integer.parseInt(ul.get(0).toString());
			TreeNode t = get(id);
			s.clear();
			if (subMenuCount > 0) {
				t.setExpanded("1");
				getHibernateTemplate().update(t);
			} else {
				t.setExpanded("0");
				getHibernateTemplate().update(t);
			}
		}
		// if(s !=null) s.close();
		return;
	}

}
