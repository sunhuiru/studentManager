package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null) {
			// 创建数据库连接
			System.out.println("new connection");
			try {
				// 注册driver类
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/studentManager?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC",
						"root", "root");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(ConnectionUtil.getConnection());
	}

}
