<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>欢迎登陆图书管理系统</h1>
<c:choose>
	<c:when test="${empty sessionScope.sessionUsername }">您还未登录请<a href="${pageContext.request.contextPath }/user/login.jsp">登录</a></c:when>
	<c:otherwise>
		欢迎${sessionScope.sessionUser }登录，点击进入<a href="${pageContext.request.contextPath }/book/manageBook.jsp">图书管理</a>页面
	</c:otherwise>
</c:choose>
</body>
</html>