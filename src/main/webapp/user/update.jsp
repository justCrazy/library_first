<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>用户修改密码</h1>
<c:choose>
	<c:when test="${empty sessionScope.sessionUsername }">您还未登录请<a href="${pageContext.request.contextPath }/user/login.jsp">登录</a></c:when>
	<c:otherwise>
		<form action="${pageContext.request.contextPath }/UpdateUserPasswordServlet?username=${sessionScope.sessionUsername }" method="post">
		<table border="1">
			<tr>
				<td>用户名：<input type="text" name="username" value="${sessionScope.sessionUsername }"></td>
			</tr>
			<tr>
				<td>原&nbsp;密&nbsp;码：<input type="text" name="oldPassword" value="${password.oldPassword }"/>${errors1.oldPassword }<br /></td>
			</tr>
			<tr>
				<td>新 &nbsp;密&nbsp;码：<input type="text" name="newPassword1" value="${password.newPassword1 }"/>${errors1.newPassword1 }</td>
			</tr>
			<tr>
				<td>再次输入：<input type="text" name="newPassword2" value="${password.newPassword2 }"/>${errors1.newPassword2 }</td>
			</tr>
			<tr>
				<td colspan="5">
					<input type="submit" value="确定">
				 	<input type="reset" value="重置">
				 </td>
			</tr>
			</table>
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>