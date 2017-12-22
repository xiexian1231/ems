package com.megalink.ems.security.service;

import java.util.Set;

import com.megalink.ems.model.Role;

public interface RoleService {
	public Role findRoleByPrimarykey(Integer id);

	public Set<Role> findAllRole();

	public Integer addRole(Role role);

	public void delRole(Role role);

	public void updateRole(Role role);
}
