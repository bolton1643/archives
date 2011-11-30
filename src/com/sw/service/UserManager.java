package com.sw.service;

import java.util.List;

import com.sw.pojo.TreeNodeRight;
import com.sw.pojo.User;


public interface UserManager {
	// 登陆失败
	public static final int LOGIN_FAIL = 0;
	// 登陆成功
	public static final int LOGIN_SUCCESS = 1;
	
	// 注册失败
	public static final int REGISTE_FAIL = 0;
	// 注册成功
	public static final int REGISTE_SUCCESS = 1;

	/**
	 * 验证登陆
	 * 
	 * @param name
	 *            登陆用的用户�?
	 * @param pass
	 *            登陆用的密码
	 * @return 登陆后的身份确认:0为登陆失败，1为登陆成�?
	 */
	int validLogin(String name, String pass);
	
	/**
	 * 注册新用�?
	 * @param user
	 */
	void registe(User user);
	
	/**
	 * 根据姓名和密码查找用�?
	 * @param name
	 * @param pass
	 * @return 
	 */
	User getUserByNameAndPass(String name,String pass);
	
	/**
	 * 修改密码
	 * @param userId
	 * @param newPwd
	 */
	void changePwd(Integer userId,String newPwd);
	
	List<User> userList();
	
	public User getUserById(User user);
	
	public void modifyUser(User user);
	
	public List userRight(User user);
	
	public List<TreeNodeRight> findUserRight(User user);
	
	public void saveRight(List <TreeNodeRight> tl,int userId);
}
