<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>本科生毕业论文管理系统</title>
</head>
<body>
	<div style = "width:100%;">
		<p style="float:left">本科生毕业论文管理系统</p>
		<p style="float:right"><a href="login.jsp">退出</a></p>
	</div>
	<br/>
	<p>欢迎您，${name}</p>
	<a href="Inquire?id=${id}">毕业设计状态</a>
</body>
</html>