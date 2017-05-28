package com.web.library.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.commomUtils.CommonUtils;
import com.web.library.domain.Book;
import com.web.library.service.BookService;

@SuppressWarnings("serial")
public class AddBookServlet extends HttpServlet {

	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");//请求编码(post)
		resp.setContentType("text/html;charset=utf-8");//响应编码
		
		//依赖UserService
		BookService bookService = new BookService();
		/**
		 * 1、封装表单数据（user对象里面）
		 */
		Book form = CommonUtils.toBean(req.getParameterMap(), Book.class);
		
		/*
		 * 添加表单校验
		 * 1.创建Map，用来装载所有的表单错误信息
		 * （校验过程中，若失败，向map中添加错误的信息，key为表单字段名称）
		 * 2.校验之后，查看map的长度是否大于0，若大于0，则说明存在错误信息
		 *  （保存到request雨中，转发到regist.jsp）
		 */
		
		//用来装载所有的错误信息
		java.util.Map<String,String> errors = new HashMap<String, String>() ;
		//bid校验
		String bid = form.getBid();
		if (bid == null || bid.trim().isEmpty()) {
			errors.put("bid", "书id不能为空！");
		}else if (bid.length()<1 || bid.length()>15) {
			errors.put("bid", "bid长度必须在1到15之间！" );
		}
		//书名校验
		String bname = form.getBname();
		if (bname == null || bname.trim().isEmpty()) {
			errors.put("bname", "书名不能为空！");
		}else if (bname.length()<3 || bname.length()>15) {
			errors.put("bname", "书名长度必须在3到15之间！" );
		}
		
		//作者校验
		String author = form.getAuthor();
		if (author == null || author.trim().isEmpty()) {
			errors.put("author", "作者名不能为空！");
		}else if (author.length()<1 || author.length()>15) {
			errors.put("author", "作者长度必须在1到15之间！" );
		}
		
		/*
		 * 判断map是否为空，不为空，则说明有错误
		 */
		if (errors!=null&&errors.size()>0) {
			/*
			 * 1.保存error到request域
			 * 2.保存form到request域（回显）
			 * 3.转发到regist.jsp
			 */
			req.setAttribute("errors", errors);
			req.setAttribute("book", form);
			req.getRequestDispatcher("/book/addBook.jsp").forward(req, resp);
			return;
		} 
		
		/*
		 * 2、调用userServlet的regist（）方法，传递form过去
		 * 3、得到异常，获取异常信息，保存到request域中，转发到regist.jsp中显示
		 * 4、没有异常，显示注册成功
		 */
		try {
			bookService.addBook(form);
			resp.getWriter().print("添加成功！点击"
					+ "<h1><a href='"+req.getContextPath()+
					"/book/manageBook.jsp"+"'> 返回</a>图书管理界面");
		} catch (Exception e) {
			//获取异常信息，保存到request域中
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("book", form);
			req.getRequestDispatcher("/book/addBook.jsp").forward(req, resp);
		}
	}


}
