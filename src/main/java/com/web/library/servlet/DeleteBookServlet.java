package com.web.library.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.service.BookService;

public class DeleteBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//请求编码(post)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		BookService bookService = new BookService();
		try {
			String id = request.getParameter("id");
			bookService.deleteBook(id);
			response.getWriter().print("删除成功！点击"
					+ "<h1><a href='"+request.getContextPath()+
					"/book/manageBook.jsp"+"'> 返回</a>图书管理界面");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
