package com.megalink.ems.security.dao;

import java.util.Set;

import com.megalink.ems.model.Authority;
import com.megalink.ems.model.Role;
import com.megalink.ems.model.User;

public interface UserHelper {
	public User findUserByUserName(String username);

	public Set<Role> findRoles(String username);

	public Set<Authority> findPermissions(String username);

	public Integer addUser(User user);

	public void updateUser(User user);
}
