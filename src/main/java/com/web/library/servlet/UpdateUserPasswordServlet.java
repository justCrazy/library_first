package com.web.library.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.commomUtils.CommonUtils;
import com.web.library.domain.Password;
import com.web.library.service.PasswordService;

@SuppressWarnings("serial")
public class UpdateUserPasswordServlet extends HttpServlet {

	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");// 请求编码(post)
		response.setContentType("text/html;charset=utf-8");// 响应编码

		// 依赖UserService
		PasswordService passwordService = new PasswordService();
		/**
		 * 1、封装表单数据（book对象里面）
		 */
		Password form = CommonUtils.toBean(request.getParameterMap(), Password.class);

		/*
		 * 添加表单校验 1.创建Map，用来装载所有的表单错误信息 （校验过程中，若失败，向map中添加错误的信息，key为表单字段名称）
		 * 2.校验之后，查看map的长度是否大于0，若大于0，则说明存在错误信息 （保存到request雨中，转发到regist.jsp）
		 */

		// 用来装载所有的错误信息
		java.util.Map<String, String> errors1 = new HashMap<String, String>();
		// bid名校验
		String oldPassword = form.getOldPassword();
		String username = (String) request.getParameter("username");
		form.setUsername(username);
		form.setOldPassword(oldPassword);
		if (oldPassword == null || oldPassword.trim().isEmpty()) {
			errors1.put("oldPassword", "原密码不能为空！");
		} else if (oldPassword.length() < 3 || oldPassword.length() > 15) {
			errors1.put("oldPassword", "原密码长度必须在1到15之间！");
		} 
		// 书名校验
		String newPassword1 = form.getNewPassword1();
		if (newPassword1 == null || newPassword1.trim().isEmpty()) {
			errors1.put("newPassword1", "newPassword1名不能为空！");
		}

		// 作者名校验
		String newPassword2 = form.getNewPassword2();
		if (!newPassword2.equals(newPassword1)) {
			errors1.put("newPassword2", "两次密码输入不同！");
		}

		/*
		 * 判断map是否为空，不为空，则说明有错误
		 */
		if (errors1 != null && errors1.size() > 0) {
			/*
			 * 1.保存error到request域 2.保存form到request域（回显） 3.转发到regist.jsp
			 */
			request.setAttribute("errors1", errors1);
			request.setAttribute("password", form);
			request.getRequestDispatcher("/user/update.jsp").forward(request, response);
			return;
		}
		try {
			Password password = passwordService.update(form);
			boolean a = password.isSame();
			if (a) {
				response.getWriter().print(
						"修改成功！" + "<h1><a href='" + request.getContextPath() + "/user/login.jsp" + "'>点击返回登录页面</a>");
			} else {
				response.getWriter().print(
						"修改失败，原密码与初始密码不同！" + "<h1><a href='" + request.getContextPath() + "/user/update.jsp" + "'>点击返回修改页面</a>");
//				request.setAttribute("password", form);
//				request.getRequestDispatcher("/user/update.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			// 获取异常信息，保存到request域中
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("password", form);
			request.getRequestDispatcher("/user/update.jsp").forward(request, response);
		}
	}
}