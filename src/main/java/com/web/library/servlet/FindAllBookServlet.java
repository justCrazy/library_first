package com.web.library.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.domain.Book;
import com.web.library.domain.PageBean;
import com.web.library.service.BookService;

public class FindAllBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BookService bookService = new BookService();
		/*
		 * 1.获取页面传递的pc(当前页码) 2.给定ps(每一页记录数)的值
		 * 3.使用pc和ps调用service方法，得到PageBean，保存到request域中 4.转发到selectBook.jsp
		 */
		int pc = getPc(request);// 得到pc
		int ps = 2;// 给定ps的值，每页10行记录
		try {
			PageBean<Book> pb = bookService.findAll(pc, ps);
			request.setAttribute("pb", pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		request.getRequestDispatcher("/book/list.jsp").forward(request, response);
	}

	/**
	 * 获取pc
	 * 
	 * @param request
	 * @return
	 */
	private int getPc(HttpServletRequest request) {
		/*
		 * 1.得到pc 如果pc参数不存在，说明pc=1 如果pc参数存在，需要转换为int类型即可
		 */

		String value = request.getParameter("pc");
		if (value == null || value.trim().isEmpty()||Integer.parseInt(value)==0) {
			return 1;
		}
		return Integer.parseInt(value);
	}
}
