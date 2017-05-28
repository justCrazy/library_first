package com.web.library.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.library.JdbcUtils.JdbcUtils;
import com.web.library.domain.Book;
import com.web.library.domain.PageBean;

public class BookDaoImpl implements BookDao {

	public void addBook(Book form) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "INSERT INTO book VALUES(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// 给pstmt中的？赋值
			pstmt.setString(1, form.getBid());
			pstmt.setString(2, form.getBname());
			pstmt.setString(3, form.getPublisher());
			pstmt.setString(4, form.getPublishDate());
			pstmt.setString(5, form.getAuthor());
			pstmt.setString(6, form.getDescription());
			/*
			 * 执行
			 */
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
	}

	/**
	 * 查询所有图书
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public List findAllbook() throws Exception {

		List<Map<String, Object>> bookList = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtils.getConnection();
			String sql = "SELECT * FROM book";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= count; i++) {
					String label = rsmd.getColumnName(i);
					Object object = rs.getObject(i);
					rowData.put(label, object);
				}
				bookList.add(rowData);
			}

			return bookList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}

		}

	}

	/**
	 * 查询所有图书(带页码)
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public PageBean<Book> findAll(int pc, int ps) {

		List<Map<String, Object>> beanList = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			/*
			 * 1.创建PageBean对象pb 2.设置pb的pc和ps 3.设置tr，设置给pb 4.得到beanList,设置给pb
			 * 5.返回给pb
			 */
			PageBean<Book> pb = new PageBean<Book>();
			pb.setPc(pc);
			pb.setPs(ps);
			/*
			 * 得到tr
			 */
			String sql = "SELECT COUNT(*) FROM book ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int tr = rs.getInt(1);
			pb.setTr(tr);
			/*
			 * 得到beanList
			 */
			sql = "SELECT * FROM book limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (pc - 1) * ps);
			pstmt.setInt(2, ps);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= count; i++) {
					String label = rsmd.getColumnName(i);
					Object object = rs.getObject(i);
					rowData.put(label, object);
				}
				beanList.add(rowData);
			}
			pb.setBeanList(beanList);
			return pb;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}

		}
		

	}

	/**
	 * 高级搜索图书(带页码)
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public PageBean<Book> queryBook(Book url , int pc, int ps) {

		List<Map<String, Object>> beanList = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			/*
			 * 1.创建PageBean对象pb 2.设置pb的pc和ps 3.设置tr，设置给pb 4.得到beanList,设置给pb
			 * 5.返回给pb
			 */
			PageBean<Book> pb = new PageBean<Book>();
			pb.setPc(pc);
			pb.setPs(ps);
			/*
			 * 得到tr
			 */
			
			/*
			 * 1.得到sql语句前半部分
			 */
			StringBuilder baseSql = new StringBuilder("SELECT COUNT(*) FROM book ");
			StringBuilder whereSql = new StringBuilder(" WHERE 1=1 ");
			/*
			 * 2.判断条件，完成向sql语句中追加sql子句
			 */
			/*
			 * 3.创建一个ArrayList，用来装在参数
			 */
			List<Object> params = new ArrayList<Object>();
			//书名查询
			String bname = url.getBname();
			if (bname!=null&&!bname.trim().isEmpty()) {
				whereSql.append(" AND bname LIKE "+"'%"+bname+"%' ");
			}
			//出版社查询
			String publisher = url.getPublisher();
			if (publisher!=null&&!publisher.trim().isEmpty()) {
				whereSql.append(" AND publisher LIKE "+"'%"+publisher+"%' ");
			}
			//作者查询
			String author = url.getAuthor();
			if (author!=null&&!author.trim().isEmpty()) {
				whereSql.append(" AND author LIKE "+"'%"+author+"%' ");
			}
			String trSql = baseSql.append(whereSql).toString();
			pstmt = con.prepareStatement(trSql);
			//System.out.println(trSql);
			rs = pstmt.executeQuery();
			rs.next();
			int tr = rs.getInt(1);
			pb.setTr(tr);
			/*
			 * 得到beanList
			 */
			StringBuilder sql  = new StringBuilder("SELECT * FROM book ");
			StringBuilder limitSql = new StringBuilder(" limit "+(pc-1)*ps+", "+ps);
			String sql1 = sql.append(whereSql).append(limitSql).toString();
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= count; i++) {
					String label = rsmd.getColumnName(i);
					Object object = rs.getObject(i);
					rowData.put(label, object);
				}
				beanList.add(rowData);
			}
			pb.setBeanList(beanList);
			return pb;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}

		}
		

	}
	/**
	 * 查询id图书
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public List selectBook(String id) throws Exception {

		List<Map<String, Object>> bookList1 = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtils.getConnection();
			String sql = "SELECT * FROM book WHERE bid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= count; i++) {
					String label = rsmd.getColumnName(i);
					Object object = rs.getObject(i);
					rowData.put(label, object);
				}
				bookList1.add(rowData);
			}

			return bookList1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}

		}

	}

	/**
	 * 更新图书
	 */
	public void updateBook(Book form) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "UPDATE book SET bid=?," + "bname=?,publisher=?," + "publishDate=?,author=?,"
					+ "description=? WHERE bid=?";
			pstmt = con.prepareStatement(sql);
			// 给pstmt中的？赋值
			pstmt.setString(1, form.getBid());
			pstmt.setString(2, form.getBname());
			pstmt.setString(3, form.getPublisher());
			pstmt.setString(4, form.getPublishDate());
			pstmt.setString(5, form.getAuthor());
			pstmt.setString(6, form.getDescription());
			pstmt.setString(7, form.getBid());

			/*
			 * 执行
			 */
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}

	}

	/**
	 * 删除id图书
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public void deleteBook(String id) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtils.getConnection();
			String sql = "DELETE FROM book WHERE bid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}

		}

	}
}
