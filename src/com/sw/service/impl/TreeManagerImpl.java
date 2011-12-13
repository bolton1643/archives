package com.sw.service.impl;

import java.util.List;

import com.sw.dao.MetaData;
import com.sw.dao.TreeNodeDao;
import com.sw.pojo.TreeNode;
import com.sw.pojo.TreeNodeRight;
import com.sw.service.TreeManager;
import com.sw.util.MetaDataRow;

public class TreeManagerImpl implements TreeManager{

	private TreeNodeDao treeDao;  // 必须与配置文件中的名字一�?
	private MetaData metaData;

	public TreeNodeDao getTreeDao() {
		return treeDao;
	}


    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

	public void setTreeDao(TreeNodeDao treeDao) {
		this.treeDao = treeDao;
	}

	public boolean operateTable(String sql){
		return metaData.operateTable(sql);
	}

	public List<MetaDataRow> getMetaData(String tName){
		return metaData.getMetaData(tName);
	}
	

	public List<TreeNode> findChildren(String parid) {
		return treeDao.findChildren(parid);
	}

	public List<TreeNodeRight> findChildren(String parid,int userid) {
		return treeDao.findChildren(parid,userid);
	}

	public int addTreeNode(TreeNode treeNode) throws Exception {
		return treeDao.save(treeNode);
	}



	public TreeNode deleteTreeNode(TreeNode treeNode) throws Exception {
		return treeDao.delete(treeNode);
	}



	public TreeNode getTreeNodeById(Integer id) throws Exception {
		return treeDao.get(id);
	}



	public void updateTreeNode(TreeNode treeNode) throws Exception {
		treeDao.update(treeNode);
	}



	public void updateExpanded(Integer id) throws Exception {
		treeDao.updateExpanded(id);
	}

}
