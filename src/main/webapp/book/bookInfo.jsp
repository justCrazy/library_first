<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍详细信息</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty sessionScope.sessionUsername }">您还未登录请<a
				href="${pageContext.request.contextPath }/user/login.jsp">登0录</a>
		</c:when>
		<c:otherwise>
			<h1 align="center">书籍详细信息</h1>
			<hr>
			<h2 align="right"><a href="<c:url value='/book/manageBook.jsp'/>">返回主菜单</a></h2>
			<form action="${pageContext.request.contextPath }/UpdateBookServlet"
				method="post">
				<c:forEach items="${requestScope.bookList1}" var="book" begin="0">
		书编号： <input type="text" name="bid" value="${book.bid }" />${errors1.bid }<br />
		书名：<input type="text" name="bname" value="${book.bname }" />${errors1.bname }<br /> 
		出版社：<input type="text" name="publisher" value="${book.publisher }" />
					<br /> 
		出版日期：<input type="text" name="publishDate"
						value="${book.publishDate }" />
					<br /> 
		作者：<input type="text" name="author" value="${book.author }" />${errors1.author }<br /> 
		描述：<input type="text" name="description" value="${book.description }" />
					<br />

					<c:choose>
						<c:when test="${sessionScope.sessionIdentity!='a' }">您不是管理员，无法对图书进行操作
			</c:when>
						<c:otherwise>
							<input type="submit" value="更新信息">
							<input type="reset" value="重置">
					<td><a
						href="<c:url value='/DeleteBookServlet?id=${book.bid }'/>">删除图书</a></td>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			</form>
		</c:otherwise>
	</c:choose>


</body>
</html>