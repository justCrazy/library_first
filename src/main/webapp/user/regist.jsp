<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript">
	function _change() {

		//获取元素
		var ele = document.getElementById("verifyCode");
		ele.src = "${pageContext.request.contextPath }/VerifyCodeServlet?xxx="
				+ new Date().getTime();
	}
</script>
</head>
<body>
	<h1>注册</h1>
	<p style="color: red; font: 900">${msg }</p>
	<form action="${pageContext.request.contextPath }/RegistServlet"
		method="post">
		用户名： <input type="text" name="username" value="${user.username }" />${errors.username }<br />
		密 &nbsp;码：<input type="password" name="password"
			value="${user.password }" />${errors.password }<br /> 验证码：<input
			type="text" name="verifyCode" value="${user.verifyCode }" size=3 />${errors.verifyCode }
		<img id="verifyCode"
			src="${pageContext.request.contextPath }/VerifyCodeServlet"> <a
			href="javascript:_change()">换一张</a><br /> 
			<input type="submit"value="注册">
			<input type="reset" value="重置">
	</form>
</body>
</html>