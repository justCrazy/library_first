package com.web.library.dao.user;

import java.sql.Connection;
import java.sql.SQLException;

import com.web.library.JdbcUtils.JdbcUtils;
import com.web.library.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

	public void add(User form) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "INSERT INTO user VALUES(?,?)";
			pstmt = con.prepareStatement(sql);
			// 给pstmt中的？赋值
			pstmt.setString(1, form.getUsername());
			pstmt.setString(2, form.getPassword());
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

	public User findByUsername(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "SELECT * FROM user WHERE username=?";
			pstmt = con.prepareStatement(sql);
			// 给pstmt中的？赋值
			pstmt.setString(1, username);
			/*
			 * 执行
			 */
			rs = pstmt.executeQuery();

			/*
			 * 把rs转换成User类型；返回
			 */
			if (rs == null) {
				return null;
			}
			if (rs.next()) {
				// 转换成Uer对象并返回
				// ORM--->对象关系映射
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setIdentity(rs.getString("identity"));;

				return user;
			} else {
				return null;
			}

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
}
