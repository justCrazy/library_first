package com.web.library.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.commomUtils.CommonUtils;
import com.web.library.domain.User;
import com.web.library.service.UserService;

@SuppressWarnings("serial")
public class RegistServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");//请求编码(post)
		resp.setContentType("text/html;charset=utf-8");//响应编码
		
		//依赖UserService
		UserService userService = new UserService();
		/**
		 * 1、封装表单数据（user对象里面）
		 */
		User form = CommonUtils.toBean(req.getParameterMap(), User.class);
		
		/*
		 * 添加表单校验
		 * 1.创建Map，用来装载所有的表单错误信息
		 * （校验过程中，若失败，向map中添加错误的信息，key为表单字段名称）
		 * 2.校验之后，查看map的长度是否大于0，若大于0，则说明存在错误信息
		 *  （保存到request雨中，转发到regist.jsp）
		 */
		
		//用来装载所有的错误信息
		java.util.Map<String,String> errors = new HashMap<String, String>() ;
		//用户名校验
		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		}else if (username.length()<2 || username.length()>15) {
			errors.put("username", "用户名长度必须在2到15之间！" );
		}
		//密码校验
		String password = form.getPassword();
		if (password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		}else if (password.length()<3 || password.length()>15) {
			errors.put("password", "密码长度必须在3到15之间！" );
		}
		
		//验证码校验
		String sessionVerifyCode =  (String) req.getSession().getAttribute("session_vcode");
		String verifyCode = form.getVerifyCode();
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		}else if (verifyCode.length()!=4) {
			errors.put("verifyCode", "验证码长度必须为4位！" );
		}
		else if (!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
			errors.put("verifyCode", "验证码错误！" );
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
			req.setAttribute("user", form);
			req.getRequestDispatcher("/user/regist.jsp").forward(req, resp);
			return;
		} 
		
		/*
		 * 2、调用userServlet的regist（）方法，传递form过去
		 * 3、得到异常，获取异常信息，保存到request域中，转发到regist.jsp中显示
		 * 4、没有异常，显示注册成功
		 */
		try {
			userService.regist(form);
			resp.getWriter().print("注册成功！"
					+ "<h1><a href='"+req.getContextPath()+
					"/user/login.jsp"+"'>点击登录</a>");
		} catch (Exception e) {
			//获取异常信息，保存到request域中
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("user", form);
			req.getRequestDispatcher("/user/regist.jsp").forward(req, resp);
		}
	}
}
