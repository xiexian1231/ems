package com.megalink.ems.security.dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megalink.ems.model.Authority;

@Repository
public class AuthorityHelperImpl implements AuthorityHelper {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Authority findRoleByPrimarykey(Integer id) {
		return (Authority) this.getCurrentSession().get(Authority.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Authority> findAllAuthority() {
		return (Set<Authority>) this.getCurrentSession().createQuery("from Authority").list();
	}

	@Override
	public Integer addAuthority(Authority authority) {
		return (Integer) this.getCurrentSession().save(authority);
	}

	@Override
	public void delAuthority(Authority authority) {
		this.getCurrentSession().delete(authority);
	}

	@Override
	public void updateAuthority(Authority authority) {
		this.getCurrentSession().update(authority);
	}

}
