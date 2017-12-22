package com.megalink.ems.security.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megalink.ems.model.Authority;
import com.megalink.ems.security.dao.AuthorityHelper;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityHelper authorityHelper;

	@Override
	public Authority findRoleByPrimarykey(Integer id) {
		return authorityHelper.findRoleByPrimarykey(id);
	}

	@Override
	public Set<Authority> findAllAuthority() {
		return authorityHelper.findAllAuthority();
	}

	@Override
	public Integer addAuthority(Authority authority) {
		return authorityHelper.addAuthority(authority);
	}

	@Override
	public void delAuthority(Authority authority) {
		authorityHelper.delAuthority(authority);
	}

	@Override
	public void updateAuthority(Authority authority) {
		authorityHelper.updateAuthority(authority);
	}

}
