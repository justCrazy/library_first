package com.web.library.dao.password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.web.library.JdbcUtils.JdbcUtils;
import com.web.library.domain.Password;

public class PasswordDaoImpl implements PasswordDao {

	@SuppressWarnings("resource")
	public Password update(Password form) {

		Connection con = null;
		Password password = new Password();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "SELECT password FROM user WHERE username = ?";
			pstmt = con.prepareStatement(sql);
			// 给pstmt中的？赋值
			pstmt.setString(1, form.getUsername());
			/*
			 * 执行
			 */
			rs = pstmt.executeQuery();
			rs.next();
			String pw = rs.getString(1);
//			System.out.println(pw);
//			System.out.println(form.getOldPassword());
			if (pw.equals(form.getOldPassword())) {
				boolean same = true;
				password.setSame(same);
				sql = "UPDATE user SET password=? WHERE username=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, form.getNewPassword2());
				pstmt.setString(2, form.getUsername());
				pstmt.executeUpdate();

			} else {
				boolean same = false;
				password.setSame(same);
			}
			return password;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
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
