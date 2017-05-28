package com.web.library.service;

import java.util.List;

import com.web.library.dao.book.BookDao;
import com.web.library.dao.book.BookDaoFactory;
import com.web.library.domain.Book;
import com.web.library.domain.PageBean;

public class BookService {

	private BookDao bookDao = BookDaoFactory.getBookDao();

	public void addBook(Book book) throws LibraryException {

		bookDao.addBook(book);
	}

	public List<Book> findAllbook() throws Exception {
		return bookDao.findAllbook();
	}

	public PageBean<Book> findAll(int pc, int ps) throws Exception {
		return bookDao.findAll(pc, ps);
	}

	public List<Book> selectBook(String id) throws Exception {
		return bookDao.selectBook(id);
	}

	public void updateBook(Book book) throws LibraryException {

		bookDao.updateBook(book);
	}

	public void deleteBook(String id) throws Exception {

		bookDao.deleteBook(id);
	}
	public PageBean<Book> queryBook(Book url,int pc, int ps) throws Exception {

		return bookDao.queryBook(url,pc, ps);
	}
}
