<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<body>
	<script type="text/javascript">
		function submit(){
		}
	</script>
	<h1>test web</h1>
	<a href="<%=basePath%>Servlet/Detail?detail">Detail</a>
	<a href="<%=basePath %>login.jsp">Login</a>
</body>

</html>