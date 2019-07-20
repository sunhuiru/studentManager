package com.datang.hrbu.controller;


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

import com.datang.hrbu.dao.ConnectionUtil;
import com.datang.hrbu.service.LoginService;
import com.datang.hrbu.service.Impl.LoginServiceImpl;
import com.datang.hrbu.vo.User;

public class GlobalController extends HttpServlet {
	
	private Map<String,String>userMap=new HashMap<String,String>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("run in doget");
		//resp.setContentType("text/html;charset=UTF-8");
		resp.sendRedirect("ok.jsp");
		/*
		 * PrintWriter pw=resp.getWriter();
		 * pw.write("<p style='color:red;'>恭喜访问到后台了!</p>"); pw.flush(); pw.close();
		 */
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//System.out.println("run in dopost");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		System.out.println("URI="+req.getRequestURI());
		String uri=req.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/")+1, uri.indexOf("."));
		if(action.equals("register")) 
		{  //注册
			
			//userMap.put(username, password);   //暂时将用户信息放入Map里
			//resp.sendRedirect("register_success.jsp");
			Connection conn=ConnectionUtil.getConnection();
			PreparedStatement ps;			
			try {
				ps = conn.prepareStatement("insert into user(username,password) values(?,?)");
				ps.setString(1, username);
				ps.setString(2, password);
				int i=ps.executeUpdate();
				if(i==1) {
					resp.sendRedirect("register_success.jsp");
				}else {
					resp.sendRedirect("register_fail.jsp");
				}
			} catch (SQLException e) {	
				resp.sendRedirect("register_fail.jsp");
				e.printStackTrace();
			}						
		}else if(action.equals("login")) 
		{//========================================================
			//登录
			//根据用户名获取密码
			/*String existPassword=userMap.get(username);
			if(existPassword==null) 
			{//密码为空
				resp.sendRedirect("login_fail.jsp");
			}else 
			{
				if(existPassword.equals(password)) 
				{//密码对比一致
					resp.sendRedirect("login_success.jsp");
				}else {
				resp.sendRedirect("login_fail.jsp");
			}
			}
			//============================================
			Connection conn=ConnectionUtil.getConnection();
			PreparedStatement ps=null;	
			HttpSession session=req.getSession();
			try {
				ps = conn.prepareStatement("select * from user where username=?");
				ps.setString(1, username);
				ResultSet rs=ps.executeQuery();
				if(rs!=null&&rs.next())
				{
					String dbpwd=rs.getString(2);
					if(password.equals(dbpwd)) 
					{//密码对比一致
						session.setAttribute("username", username);
						PreparedStatement ps_second=null;
						ps_second=conn.prepareStatement("select * from user");
						ResultSet rsList=ps_second.executeQuery();						
						List<User> userList=new ArrayList<User>();
						while(rsList.next()) {
							  User user=new User();
							  user.setUsername(rsList.getString(1));
							  user.setAge(rsList.getInt(3));
							  userList.add(user);
						}
						session.setAttribute("userList", userList);
						resp.sendRedirect("login_success.jsp");
						//resp.sendRedirect("login_success.jsp");					
				}
				else {resp.sendRedirect("login_fail.jsp");}
			} catch (SQLException e) {
				resp.sendRedirect("login_fail.jsp");
				e.printStackTrace();
			}
			finally {
				if(ps!=null)
				{try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			}*/
			//=============================================
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			LoginService loginService=new LoginServiceImpl();
			User dbuser=loginService.login(user);
			if(dbuser==null)
			{
				
				resp.sendRedirect("login_fail.jsp");
			}
					else {
						HttpSession session=req.getSession();											
						session.setAttribute("user", dbuser);
						List<User> userList=loginService.getUserList();	
						session.setAttribute("userList", userList);
						resp.sendRedirect("login_success.jsp");
				}
	}
		else 
		{
			System.out.println("错误");
		}
		
	}

}
