package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;

//数据库工具类
public class DbUtil {
	//加载驱动，并建立数据库连接
	private String dbUrl="jdbc:mysql://localhost:3306/news?&useSSL=FALSE&useUnicode=true&characterEncoding=UTF-8";
	private String dbUserName="root";
	private String dbPassword="seaw842439";
	private String jdbcName="com.mysql.jdbc.Driver";
	// 获取数据库连接
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
		}
	//释放连接
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
			}
		}
	//主方法
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("连接成功!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("False");
				e.printStackTrace();
				}
		}
	}
