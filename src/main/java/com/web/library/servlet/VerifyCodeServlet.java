package com.web.library.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.library.VerifyCode.VerifyCode;;

@SuppressWarnings("serial")
public class VerifyCodeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * 1.创建验证码类
		 */
		VerifyCode vc = new VerifyCode();
		/*
		 * 2.得到验证码图片
		 */
		BufferedImage image = vc.getImage();
		/*
		 * 3.将图片的文本保存到session中
		 */
		req.getSession().setAttribute("session_vcode", vc.getText());
		/*
		 * 4.把图片响应给客户端
		 */
		VerifyCode.output(image, resp.getOutputStream());
	}
}
