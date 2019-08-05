<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>本科生毕业论文管理系统</title>
</head>
<body>
	<form action = "Login" method = "post">
		用户名:<input type="text" name="username" required=""></input><br /><br />
		密  码：<input type="password" name="password" required=""></input><br /><br />
		用户类型：<input type="radio" name="type" value="student" required="">学生</input>&nbsp;
		<input type="radio" name="type" value="teacher">教师</input>&nbsp;
		<input type="radio" name="type" value="admin">管理员</input><br />
		<input type="submit" value = "登录" />
	</form>
</body>
</html>