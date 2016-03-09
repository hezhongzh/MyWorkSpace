<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><title>Login Page</title>
	<link rel="stylesheet"	href="./css/main.css" type="text/css"/>

</head>
<body>
	<form action="Servlet/Login">
		username：<input type="text"  id="username" name="username" alt="please input your username" ></input>
		<br/>
		password：<input type="text"  id="password" name="password" alt="please input your password"/>
		<br/>
		<input type="submit" value="login"/>
	</form>

</body>

</html>