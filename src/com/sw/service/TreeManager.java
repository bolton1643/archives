package com.sw.service;

import java.util.List;

import com.sw.pojo.TreeNode;
import com.sw.pojo.TreeNodeRight;
import com.sw.util.MetaDataRow;

public interface TreeManager {

	List<TreeNode> findChildren(String parid);

	List<TreeNodeRight> findChildren(String parid, int userid);

	int addTreeNode(TreeNode treeNode) throws Exception;

	void updateTreeNode(TreeNode treeNode) throws Exception;

	TreeNode deleteTreeNode(TreeNode treeNode) throws Exception;

	TreeNode getTreeNodeById(Integer id) throws Exception;

	void updateExpanded(Integer id) throws Exception;
	
	public boolean operateTable(String sql);

	public List<MetaDataRow> getMetaData(String tName);
}
