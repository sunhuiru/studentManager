package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.datang.hrb.vo.User;

public class UserDao {
	public User getUsersByUsername(String username) {
		//湖区数据库连接
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps=null;
		User user= null;
		try {
			ps = conn.prepareStatement("select * from user where username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setAge(rs.getInt(3));
				user.setTs(rs.getDate(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//返回密码
		return user;
	}
	public List<User> getUserList(){
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps=null;
		List<User> userList = new ArrayList<User>();
		try {
			ps = conn.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setAge(rs.getInt(3));
				user.setTs(rs.getDate(4));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return userList;
		
	}

}
