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

	<h1 align="center">查询</h1>
	<h2 align="right"><a href="<c:url value='/book/manageBook.jsp'/>">返回主菜单</a></h2>
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

		<c:forEach items="${pb.beanList}" var="book" begin="0">
			<tr>
				<td>${book.bid }</td>
				<td>${book.bname }</td>
				<td>${book.publisher }</td>
				<td>${book.publishDate }</td>
				<td>${book.author }</td>
				<td>${book.description }</td>
				<td><a
					href="<c:url value='/SelectBookServlet?id=${book.bid }'/>">详细信息</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<%--
			给出分页相关链接
			 --%>
	<center>
		第${pb.pc }页/共${pb.tp }页 <a
			href="<c:url value='/FindAllBookServlet?pc=1'/>">首页</a>
		<c:if test="${pb.pc>1 }">
			<a href="<c:url value='/FindAllBookServlet?pc=${pb.pc-1 }'/>">上一页</a>
		</c:if>
		<%--计算begin和end --%>
		<c:choose>
			<%--如果总页数不足10页，那么把所有页数显示出来 --%>
			<c:when test="${pb.tp<=10 }">
				<c:set var="begin" value="1" />
				<c:set var="end" value="${pb.tp }" />
			</c:when>
			<%-- 如果页数大于10页，通过公式计算出begin和end--%>
			<c:otherwise>
				<c:set var="begin" value="${pb.pc-5 }" />
				<c:set var="end" value="${pb.pc+4 }" />
				<%--头溢出 --%>
				<c:if test="${begin<1 }">
					<c:set var="begin" value="1" />
					<c:set var="end" value="10" />
				</c:if>
				<%--尾溢出 --%>
				<c:if test="${end>pb.tp }">
					<c:set var="begin" value="${pb.tp-9 }" />
					<c:set var="end" value="${pb.tp }" />
				</c:if>
			</c:otherwise>
		</c:choose>
		<%--循环显示所有页码列表 --%>
		<c:forEach var="i" begin="${begin }" end="${end }">
		<c:choose>
			<c:when test="${i eq pb.pc}">
				[${i }]
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/FindAllBookServlet?pc=${i }'/>">[${i }]</a>
			</c:otherwise>
		</c:choose>
	
</c:forEach>
		<c:if test="${pb.pc<pb.tp }">
			<a href="<c:url value='/FindAllBookServlet?pc=${pb.pc+1 }'/>">下一页</a>
		</c:if>
		<a href="<c:url value='/FindAllBookServlet?pc=${pb.tp }'/>">尾页</a>
	</center>

</body>
</html>