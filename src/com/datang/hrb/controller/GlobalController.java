package com.datang.hrb.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.dao.ConnectionUtil;
import com.datang.hrb.service.LoginService;
import com.datang.hrb.service.Impl.LoginServiceImpl;
import com.datang.hrb.vo.User;

public class GlobalController extends HttpServlet {

	private Map<String, String> userMap = new HashMap<String, String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("run in doget");
		/*
		 * resp.setContentType("text/html;charset=UTF-8"); PrintWriter pw =
		 * resp.getWriter(); pw.write("<p style='color:red'>恭喜访问到后台了</p>"); pw.flush();
		 * pw.close();
		 */
		resp.sendRedirect("ok.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		/* System.out.println("run in dopost"); */
		System.out.println("URI=====" + req.getRequestURI());
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf("."));
		if (action.equals("register")) {// 注册
			Connection conn = ConnectionUtil.getConnection();

			try {
				PreparedStatement ps = conn.prepareStatement("insert into user(username,password) values(?,?)");
				ps.setString(1, username);
				ps.setString(2, password);
				int i = ps.executeUpdate();
				if (i == 1) {
					resp.sendRedirect("register_success.jsp");
				} else {
					resp.sendRedirect("register_fail.jsp");
				}
			} catch (SQLException e) {
				resp.sendRedirect("register_fail.jsp");
				e.printStackTrace();
			}

		} else if (action.equals("login")) {// 登录
			LoginService loginService = new LoginServiceImpl();
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			User dbuser = loginService.login(user);				
			if (dbuser==null) {
				resp.sendRedirect("login_fail.jsp");
			}else {
				HttpSession session = req.getSession();
				session.setAttribute("user", dbuser);
				List<User> userList = loginService.getUserList();
				session.setAttribute("userList", userList);
				resp.sendRedirect("login_success.jsp");
			}

		}
	}
}
