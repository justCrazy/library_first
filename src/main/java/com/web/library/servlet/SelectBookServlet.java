package com.web.library.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.service.BookService;

public class SelectBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BookService bookService = new BookService();
		try {
			String id = request.getParameter("id");
			request.setAttribute("bookList1", bookService.selectBook(id));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		request.getRequestDispatcher("/book/bookInfo.jsp").forward(request, response);;
	}

}
