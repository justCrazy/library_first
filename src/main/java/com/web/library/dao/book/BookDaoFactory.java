package com.web.library.dao.book;

import java.io.InputStream;
import java.util.Properties;

public class BookDaoFactory {

	private static Properties props = null;
	static {
		//加载配置文件内容
		try {
		InputStream in = BookDaoFactory.class
				.getClassLoader().getResourceAsStream("dao.properties");
		props = new Properties();
		props.load(in);
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}
	@SuppressWarnings("rawtypes")
	public static BookDaoImpl getBookDao(){
		
		String daoClassName = props.getProperty("com.web.library.dao.book.BookDao");
		/*
		 * 通过反射来创建类的对象
		 */ 
		try {
			Class clazz = Class.forName(daoClassName);
			return (BookDaoImpl) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

