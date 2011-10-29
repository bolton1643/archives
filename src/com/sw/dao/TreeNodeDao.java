package com.sw.dao;

import java.util.List;

import com.sw.pojo.TreeNode;
import com.sw.pojo.TreeNodeRight;

public interface TreeNodeDao {

	/**
	 * 查找子节�?
	 * 
	 * @param parentid
	 * @return
	 */
	List<TreeNode> findChildren(String parentid);

	List<TreeNodeRight> findChildren(String parentid,int userid);
	
	int save(TreeNode treeNode);

	void update(TreeNode treeNode);

	TreeNode get(int id);

	TreeNode delete(TreeNode treeNode);
	
	void updateExpanded(Integer id);
}
