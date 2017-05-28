package com.web.library.dao.book;

import java.util.List;

import com.web.library.domain.Book;
import com.web.library.domain.PageBean;

public interface BookDao {

	public void addBook(Book form);

	public List<Book> findAllbook() throws Exception;

	public PageBean<Book> findAll(int pc, int ps) throws Exception;

	public List<Book> selectBook(String id) throws Exception;

	public void updateBook(Book form);

	public void deleteBook(String id) throws Exception;

	public PageBean<Book> queryBook(Book url, int pc, int ps) throws Exception;
}
