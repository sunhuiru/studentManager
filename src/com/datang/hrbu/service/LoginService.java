package com.datang.hrbu.service;

import java.util.List;

import com.datang.hrbu.vo.User;

public interface LoginService {
     public User login(User user);

	public List<User> getUserList();
     
}
