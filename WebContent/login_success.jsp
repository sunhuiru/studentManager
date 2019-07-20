<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入对应的包 -->
    <%@ page import = "java.util.List" %>
    <%@ page import = "java.util.ArrayList" %>
    <%@ page import = "com.datang.hrb.vo.*" %>
    <!-- session.getAttribute返回的是Object类型需要强制转换为我们使用的类型 -->
    <%/* String username = (String)session.getAttribute("username"); */
    	User user = (User)session.getAttribute("user");
    %>

	<%List<User>userList = (ArrayList<User>)session.getAttribute("userList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jsp中引用变量需要在%后加上"=" -->
	登陆成功!欢迎<%=user.getUsername() %>
	<br/>
	<!-- 页面的战士也是使用Java语法 -->
	所有用户信息如下：
	<table border=1 >
	 <tr><td>用户名</td><td>年龄</td><td>时间戳</td><</tr>
	 <%
	 	for(User tempUser:userList){
	 		%>
	 		<tr><td><%=tempUser.getUsername() %></td><td><%=tempUser.getAge() %></td><td><%=tempUser.getTs() %></td><</tr>
	 	<% }
	 %>
	</table>
</body>
</html>