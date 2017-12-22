package com.megalink.ems.security.service;

import java.util.Set;

import com.megalink.ems.model.Authority;
import com.megalink.ems.model.Role;
import com.megalink.ems.model.User;

public interface UserService {
	public User findUserByUserName(String username);

	public Set<Role> findRoles(String username);

	public Set<Authority> findPermissions(String username);

	public String login(User user, String force);

	public void logout();

	public Integer addUser(User user);

	public void updateUser(User user);

	public String changePassword(String username, String oldpwd, String newpwd, String confirm);
}
