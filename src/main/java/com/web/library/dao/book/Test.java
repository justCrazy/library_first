package com.web.library.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.library.JdbcUtils.JdbcUtils;

public class Test {
	
		
		
	
public static void main(String[] args) throws Exception {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		con = JdbcUtils.getConnection();
		String sql = "DELETE FROM book WHERE bid = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "abc");
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
