package com.web.library.dao.user;

import java.io.InputStream;
import java.util.Properties;

import com.web.library.dao.password.PasswordDaoImpl;

public class DaoFactory {

	private static Properties props = null;
	static {
		//加载配置文件内容
		try {
		InputStream in = DaoFactory.class
				.getClassLoader().getResourceAsStream("dao.properties");
		props = new Properties();
		props.load(in);
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}
	@SuppressWarnings("rawtypes")
	public static UserDaoImpl getUserDao(){
		
		String daoClassName = props.getProperty("com.web.library.dao.user.UserDao");
		/*
		 * 通过反射来创建类的对象
		 */ 
		try {
			Class clazz = Class.forName(daoClassName);
			return (UserDaoImpl) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}

