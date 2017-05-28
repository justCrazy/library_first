<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书添加</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty sessionScope.sessionUsername }">您还未登录请<a
				href="${pageContext.request.contextPath }/user/login.jsp">登录</a>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${sessionScope.sessionIdentity!='a' }">您不是管理员，无法添加图书<br/>
				<a
						href="${pageContext.request.contextPath }/book/manageBook.jsp">返回</a>
				</c:when>
				<c:otherwise>
					<h1>添加</h1>
					<p style="color: red; font: 900">${msg }</p>
					<form action="${pageContext.request.contextPath }/AddBookServlet"
						method="post">
						书编号： <input type="text" name="bid" value="${book.bid }" />${errors.bid }<br />
						书名：<input type="text" name="bname" value="${book.bname }" />${errors.bname}<br />
						出版社：<input type="text" name="publisher" value="${book.publisher }" /><br />
						出版日期：<input type="text" name="publishDate"
							value="${book.publishDate }" /><br /> 作者：<input type="text"
							name="author" value="${book.author }" />${errors.author}<br />
						描述：<input type="text" name="description"
							value="${book.description }" /><br /> <input type="submit"
							value="添加"> <input type="reset" value="重置">
					</form>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

</body>
</html>