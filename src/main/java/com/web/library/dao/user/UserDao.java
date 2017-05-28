package com.web.library.dao.user;

import com.web.library.domain.User;

public interface UserDao {

	public void add(User form);
	public User findByUsername(String username);
}
