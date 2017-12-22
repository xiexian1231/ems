package com.megalink.ems.security.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megalink.ems.model.Authority;
import com.megalink.ems.model.Role;
import com.megalink.ems.model.User;

@Repository
public class UserHelperImpl implements UserHelper {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public User findUserByUserName(String username) {
		return (User) this.getCurrentSession().createQuery("from User where account = ?").setParameter(0, username)
				.uniqueResult();
	}

	@Override
	public Set<Role> findRoles(String username) {
		User user = findUserByUserName(username);
		Hibernate.initialize(user.getRoles());
		return user.getRoles();
	}

	@Override
	public Set<Authority> findPermissions(String username) {
		Set<Role> roles = findRoles(username);
		Set<Authority> authorities = new HashSet<>();
		for (Role role : roles) {
			Hibernate.initialize(role.getAuthorities());
			for (Authority authority : role.getAuthorities()) {
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public Integer addUser(User user) {
		return (Integer) this.getCurrentSession().save(user);
	}

	@Override
	public void updateUser(User user) {
		this.getCurrentSession().update(user);
	}

}
