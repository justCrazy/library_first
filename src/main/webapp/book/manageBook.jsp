<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统</title>
</head>
<body>
	<h1 align="center">管理主页</h1>
	<hr>
	<c:choose>
		<c:when test="${empty sessionScope.sessionUsername }">您还未登录请<a
				href="${pageContext.request.contextPath }/user/login.jsp">登录</a>
		</c:when>
		<c:otherwise>
				<tr>
					<td>用户名：${sessionScope.sessionUsername }</td>
				</tr>
				<tr>
					<td><a href="<c:url value='/user/update.jsp?username=${sessionScope.sessionUsername }'/>">点击管理</a></td>
				</tr>
				<tr>
					<td><a href="<c:url value='/user/login.jsp'/>">退出系统</a></td>
				</tr>
			<table border="1" align="center">
				
				<tr>
					<td><a href="<c:url value='/book/addBook.jsp'/>">添加图书</a></td>
					<td><a href="<c:url value='/FindAllBookServlet'/>">查询图书</a></td>
					<td><a href="<c:url value='/book/query.jsp'/>">高级查询</a></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>



</body>
</body>
</html>