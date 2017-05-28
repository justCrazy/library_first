<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高级查询</title>
</head>
<body>
	<h1 align="center">高级搜索</h1>
	<h2 align="right">
		<a href="<c:url value='/book/manageBook.jsp'/>">返回主菜单</a>
	</h2>
	<hr>
	<form action="<c:url value='/QueryBookServlet'/>" method="get">
		书籍名称：<input type="text" name="bname"> <br/>
		出版社：<input type="text"
			name="publisher"> <br/>
		作者：<input type="text" name="author"><br/>
		<input type="submit" value="搜索"> <input type="reset"
			value="重置">
	</form>
</body>
</html>