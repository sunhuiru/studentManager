package com.datang.hrbu.service.Impl;

import java.util.List;

import com.datang.hrbu.dao.UserDao;
import com.datang.hrbu.service.LoginService;
import com.datang.hrbu.vo.User;


public class LoginServiceImpl implements LoginService{
	UserDao userDao=new UserDao();
	@Override
	public User login(User user) {
		//
		UserDao userDao=new UserDao();
		User dbUser=userDao.getUsersByUsername(user.getUsername());
		if(dbUser!=null&&dbUser.getPassword()!=null&&dbUser.getPassword().equals(user.getPassword())) {
			return user;
		}else {
			return null;
		}
	}
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}



}
