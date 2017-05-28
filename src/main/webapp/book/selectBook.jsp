<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书搜索</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty sessionScope.sessionUsername }">您还未登录请<a
				href="${pageContext.request.contextPath }/user/login.jsp">登录</a>
		</c:when>
		<c:otherwise>
			<h1 align="center">查询</h1>
			<hr>
			<table border="1" width="70%" align="center">
			<tr>
				<th>书本ID</th>
				<th>书名</th>
				<th>出版社</th>
				<th>出版日期</th>
				<th>作者</th>
				<th>描述</th>
			</tr>
			<c:forEach items="${requestScope.bookList}" var="book" begin="0">
				<tr>
					<td>${book.bid }</td>
					<td>${book.bname }</td>
					<td>${book.publisher }</td>
					<td>${book.publishDate }</td>
					<td>${book.author }</td>
					<td>${book.description }</td>
					<td><a
						href="<c:url value='/SelectBookServlet?id=${book.bid }'/>">详细信息</a>
				</tr>
				<br>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>

</body>
</html>