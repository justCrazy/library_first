package com.web.library.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.commomUtils.CommonUtils;
import com.web.library.domain.User;
import com.web.library.service.UserService;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");//请求编码(post)
		resp.setContentType("text/html;charset=utf-8");//响应编码
		
		//依赖UserService
		UserService userService = new UserService();
		/*
		 * 1.封装表单数据到UserFORM中
		 * 2.调用service中的Login（）方法，得到返回的User user对象
		 *  >抛出异常：获取异常，保存到request域中，再保存form，转发到login.jsp
		 *  >若果没有异常：保存值到session中，重定向到welcome.jsp
		 */
		User form = CommonUtils.toBean(req.getParameterMap(), User.class);
//		System.out.println("表单传递的："+form.getUsername()+form.getIdentity());
		try {
			User user = userService.login(form);
			req.getSession().setAttribute("sessionUsername", user.getUsername() );
			req.getSession().setAttribute("sessionIdentity", user.getIdentity() );
//			System.out.println("数据库中的："+user.getUsername()+user.getIdentity());
			resp.sendRedirect(req.getContextPath()+"/user/welcome.jsp");
		} catch (Exception e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("user", form);
			req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
		}
	}
}
