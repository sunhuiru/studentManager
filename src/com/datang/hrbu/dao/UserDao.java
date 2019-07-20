package com.datang.hrbu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.datang.hrbu.vo.User;

public class UserDao {
	/**
	 * 
	 * 
	 * 
	 */
    public User getUsersByUsername(String username) {
    	Connection conn=ConnectionUtil.getConnection();
    	PreparedStatement ps=null;
    	//String password= null;
    	User user=null;
    	try {
			ps= conn.prepareStatement("select * from user where username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				 user=new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				//user.setAge(rs.getString(3));
				//user.setTs(rs.getString(4));
				return user;
			}
			
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		if(ps!=null)
    		{
    			try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	return user;
        }
    public List<User> getUserList() {
    	Connection conn=ConnectionUtil.getConnection();
    	PreparedStatement ps=null;
    	List<User> userList=new ArrayList<User>();
    	
    	try {
			ps= conn.prepareStatement("select * from user");
			//ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				User user=new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				//user.setAge(rs.getString(3));
				//user.setTs(rs.getString(4));
				userList.add(user);
			}
			
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		if(ps!=null)
    		{
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
