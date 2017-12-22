package com.megalink.ems.security.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megalink.ems.model.Role;
import com.megalink.ems.security.dao.RoleHelper;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleHelper roleHelper;

	@Override
	public Role findRoleByPrimarykey(Integer id) {
		return roleHelper.findRoleByPrimarykey(id);
	}

	@Override
	public Set<Role> findAllRole() {
		return roleHelper.findAllRole();
	}

	@Override
	public Integer addRole(Role role) {
		return roleHelper.addRole(role);
	}

	@Override
	public void delRole(Role role) {
		roleHelper.delRole(role);
	}

	@Override
	public void updateRole(Role role) {
		roleHelper.updateRole(role);
	}

}
