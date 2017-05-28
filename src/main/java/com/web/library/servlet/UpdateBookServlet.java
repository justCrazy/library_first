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
public class UpdateBookServlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		request.setCharacterEncoding("UTF-8");//请求编码(post)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		//依赖UserService
		BookService bookService = new BookService();
		/**
		 * 1、封装表单数据（book对象里面）
		 */
		Book form = CommonUtils.toBean(request.getParameterMap(), Book.class);
		
		/*
		 * 添加表单校验
		 * 1.创建Map，用来装载所有的表单错误信息
		 * （校验过程中，若失败，向map中添加错误的信息，key为表单字段名称）
		 * 2.校验之后，查看map的长度是否大于0，若大于0，则说明存在错误信息
		 *  （保存到request雨中，转发到regist.jsp）
		 */
		
		//用来装载所有的错误信息
		java.util.Map<String,String> errors1 = new HashMap<String, String>() ;
		//bid名校验
		String bid = form.getBid();
		if (bid == null || bid.trim().isEmpty()) {
			errors1.put("bid", "bid名不能为空！");
		}else if (bid.length()<1 || bid.length()>15) {
			errors1.put("bid", "用户名长度必须在1到15之间！" );
		}
		//书名校验
		String bname = form.getBname();
		if (bname == null || bname.trim().isEmpty()) {
			errors1.put("bname", "书名不能为空！");
		}else if (bname.length()<3 || bname.length()>15) {
			errors1.put("bname", "书名长度必须在3到15之间！" );
		}
		
		//作者名校验
		String  author = form.getAuthor();
		if (author == null || author.trim().isEmpty()) {
			errors1.put("author", "作者名不能为空！");
		}else if (author.length()<1 || author.length()>15) {
			errors1.put("author", "作者名名长度必须在1到15之间！" );
		}
		
		/*
		 * 判断map是否为空，不为空，则说明有错误
		 */
		if (errors1!=null&&errors1.size()>0) {
			/*
			 * 1.保存error到request域
			 * 2.保存form到request域（回显）
			 * 3.转发到regist.jsp
			 */
			request.setAttribute("errors1", errors1);
			request.setAttribute("book", form);
			request.getRequestDispatcher("/book/bookInfo.jsp").forward(request, response);
			return;
		} 
		
		/*
		 * 2、调用userServlet的regist（）方法，传递form过去
		 * 3、得到异常，获取异常信息，保存到request域中，转发到regist.jsp中显示
		 * 4、没有异常，显示注册成功
		 */
		try {
			bookService.updateBook(form);
			response.getWriter().print("修改成功！"
					+ "<h1><a href='"+request.getContextPath()+
					"/book/manageBook.jsp"+"'>点击返回管理页面</a>");
		} catch (Exception e) {
			//获取异常信息，保存到request域中
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("book", form);
			request.getRequestDispatcher("/book/bookInfo.jsp").forward(request, response);
		}
	
	}


}
