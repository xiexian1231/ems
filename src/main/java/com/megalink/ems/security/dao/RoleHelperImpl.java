package com.megalink.ems.security.dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megalink.ems.model.Role;

@Repository
public class RoleHelperImpl implements RoleHelper {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Role findRoleByPrimarykey(Integer id) {
		return (Role) this.getCurrentSession().get(Role.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Role> findAllRole() {
		return (Set<Role>) this.getCurrentSession().createQuery("from Role").list();
	}

	@Override
	public Integer addRole(Role role) {
		return (Integer) this.getCurrentSession().save(role);
	}

	@Override
	public void delRole(Role role) {
		this.getCurrentSession().delete(role);
	}

	@Override
	public void updateRole(Role role) {
		this.getCurrentSession().update(role);
	}

}
