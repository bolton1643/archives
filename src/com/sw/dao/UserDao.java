package com.sw.dao;

import java.util.List;

import com.sw.pojo.TreeNodeRight;
import com.sw.pojo.User;

public interface UserDao {
	/**
	 * 根据id查找用户
	 * 
	 * @param id
	 *            �?��查找的用户id
	 */
	User get(Integer id);

	/**
	 * 增加用户
	 * 
	 * @param user
	 *            �?��增加的员�?
	 */
	void save(User user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 *            �?��修改的用�?
	 */
	void update(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 *            �?��删除的用户id
	 */
	void delete(Integer id);

	/**
	 * 删除用户
	 * 
	 * @param user
	 *            �?��删除的用�?
	 */
	void delete(User user);

	/**
	 * 查询全部用户
	 * 
	 * @return 全部用户
	 */
	List<User> findAll();

	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param name
	 *            用户的用户名
	 * @param pass
	 *            用户的密�?
	 * @return 符合用户名和密码的用户集�?
	 */
	User findByNameAndPass(String name, String pass);
	
	List userRight(int userId);
	
	List findUserRight(int userId);
	
	public void saveRight(List <TreeNodeRight> tl,int userId);
}
