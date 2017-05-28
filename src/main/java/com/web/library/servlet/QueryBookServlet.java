package com.web.library.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.commomUtils.CommonUtils;
import com.web.library.domain.Book;
import com.web.library.domain.PageBean;
import com.web.library.service.BookService;
@SuppressWarnings("serial")
public class QueryBookServlet extends HttpServlet {
	
		@SuppressWarnings("unchecked")
		public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("text/html;charest=utf-8");
			BookService bookService = new BookService();
			//System.out.println(getUrl(request));
			/*
			 * 1.获取页面传递的pc(当前页码) 2.给定ps(每一页记录数)的值
			 * 3.使用pc和ps调用service方法，得到PageBean，保存到request域中 4.转发到selectBook.jsp
			 */
			
			
			
			
			int pc = getPc(request);// 得到pc
			int ps = 2;// 给定ps的值，每页10行记录
			Book url = CommonUtils.toBean(request.getParameterMap(), Book.class);
			
			/*
			 * 处理get请求方式的编码问题
			 */
			url = encoding(url);
//			url.setBname(request.getParameter("bname"));
//			url.setAuthor(request.getParameter("author"));
//			url.setPublisher(request.getParameter("publisher"));
			try {
				PageBean<Book> pb = bookService.queryBook(url,pc, ps);
				pb.setUrl(getUrl(request));;
				request.setAttribute("pb", pb);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			request.getRequestDispatcher("/book/queryList.jsp").forward(request, response);
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
			if (value == null || value.trim().isEmpty()) {
				return 1;
			}
			return Integer.parseInt(value);
		}

		/**
		 * 截取url
		 *   /项目名/Servlet路径？参数字符串
		 * @param request
		 * @return
		 */
		private String getUrl(HttpServletRequest request){
			
			String contextPath  = request.getContextPath();//获取项目名
			String servletPath = request.getServletPath();//获取servlet路径
			String queryString = request.getQueryString();//获取？之后的参数部分
			
			//判断参数部分是否包含pc参数，若有，则截取下去，不需要
			if (queryString.contains("&pc=")) {
				int index = queryString.lastIndexOf("&pc=");
				queryString = queryString.substring(0,index);
			}
			return contextPath+servletPath+"?"+queryString;
		}
		
		/**
		 * 处理编码问题
		 * @param url
		 * @return
		 * @throws UnsupportedEncodingException
		 */
		@SuppressWarnings("unused")
		private Book encoding(Book url) throws UnsupportedEncodingException{
			String bname = url.getBname();
			String publisher = url.getPublisher();
			String author = url.getAuthor();
			
			if (bname!=null&&!bname.trim().isEmpty()) {
				bname = new String(bname.getBytes("ISO-8859-1"),"UTF-8");
				url.setBname(bname);
			}
			
			if (publisher!=null&&!publisher.trim().isEmpty()) {
				publisher = new String(publisher.getBytes("ISO-8859-1"),"UTF-8");
				url.setPublisher(publisher);
			}
			
			if (author!=null&&!author.trim().isEmpty()) {
				author = new String(author.getBytes("ISO-8859-1"),"UTF-8");
				url.setAuthor(author);
			}
			return url;
		}
	}

