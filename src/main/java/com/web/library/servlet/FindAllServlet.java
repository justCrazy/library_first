package com.web.library.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.service.BookService;

@SuppressWarnings("serial")
public class FindAllServlet extends HttpServlet {

	
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		findAll(request, response);
//	}
//	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		/*调用service得到所有用户
//		 * 保存到requset域中
//		 * 转发到selectBook.jsp
//		 */
//		request.setAttribute("bookList", bookService.findAllbook());
//		return "f:/book/selectBook.jsp";
//	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BookService bookService = new BookService();
		try {
			request.setAttribute("bookList", bookService.findAllbook());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		request.getRequestDispatcher("/book/selectBook.jsp").forward(request, response);
	}

}
