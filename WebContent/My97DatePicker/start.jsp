<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="jquery/jquery-3.4.1.js"></script><!-- 引用jquery.js -->
<title>进入登入页面</title>
</head>
<body>
	<form action="loginServlet" method="post">
		用户名:<input type="text" name="name" id="name"><br>
		密码:<input type="password" name="password" id="password"><br>
		<input type="submit" value="登录">
	</form>
</body>
</html>