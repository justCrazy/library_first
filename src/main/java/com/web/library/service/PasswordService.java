package com.web.library.service;

import com.web.library.dao.password.PasswordDao;
import com.web.library.dao.password.PasswordDaoImpl;
import com.web.library.dao.password.DaoFactory;
import com.web.library.domain.Password;

public class PasswordService {

	private PasswordDao passwordDao = DaoFactory.getPasswordDao();
	
	public Password update(Password form){
		
		return passwordDao.update(form);
	}
}
