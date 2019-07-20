package com.datang.hrbu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection conn;

	public static Connection getConnection() {
		if(conn==null) {
			try {
				//driver类注册一下
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentManage?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC","root","sunhuiru");
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
   public static void main(String[] args) {
	System.out.println(ConnectionUtil.getConnection());
}
     
}
