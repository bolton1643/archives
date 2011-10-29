package com.sw.pojo;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;

	private String userName;// ��¼��

	private String loginName;// �ǳ�

	private String password;

	private Contactinfo contactinfo;

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Contactinfo getContactinfo() {
		return contactinfo;
	}

	public void setContactinfo(Contactinfo contactinfo) {
		this.contactinfo = contactinfo;
	}

	@Override
	public String toString() {
		return super.toString() + ",[userId=" + userId + ",loginName=" + loginName + ",username=" + userName + "]";
	}
	
	
}