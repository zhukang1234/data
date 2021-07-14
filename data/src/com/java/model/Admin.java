package com.java.model;

// 管理员Model类
public class Admin {
	//私有成员
	private int adminId;
	private String password;
	//默认构造方法
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int adminId, String password) {
		super();
		this.adminId = adminId;
		this.password = password;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
