package com.web.library.service;

import com.web.library.dao.user.DaoFactory;
import com.web.library.dao.user.UserDao;
import com.web.library.domain.User;
import com.web.library.service.LibraryException;

public class UserService {

	private UserDao userDao = DaoFactory.getUserDao();

	public void regist(User user) throws LibraryException {// 该user对应的表单的user
		User _user = userDao.findByUsername(user.getUsername());// _user对应数据库的user
		if (_user != null)
			throw new LibraryException("用户名" + user.getUsername() + ",已经被注册过了！");
		userDao.add(user);
	}

	/**
	 * 登录功能
	 * 
	 * @param user
	 * @return
	 * @throws UserException
	 */
	/*
	 * 使用form中的username查询，得到User user
	 */
	public User login(User form) throws LibraryException {// 该user对应的表单的user
		User user = userDao.findByUsername(form.getUsername());// _user对应数据库的user
		if (user == null)
			throw new LibraryException("用户名不存在！");
		if (!form.getPassword().equals(user.getPassword())) {
			throw new LibraryException("密码错误！");
		}if (!form.getIdentity().equals(user.getIdentity())&&form.getIdentity().equals("a")) {
			throw new LibraryException("你不是管理员！");
		}

		return user;
	}

	
}
