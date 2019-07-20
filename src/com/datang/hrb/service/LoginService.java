package com.datang.hrb.service;

import java.util.List;

import com.datang.hrb.vo.User;

public interface LoginService {
	//定义login方法
	public User login(User user);

	public List<User> getUserList();
}
