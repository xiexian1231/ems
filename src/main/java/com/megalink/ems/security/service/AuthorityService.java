package com.megalink.ems.security.service;

import java.util.Set;

import com.megalink.ems.model.Authority;

public interface AuthorityService {
	public Authority findRoleByPrimarykey(Integer id);

	public Set<Authority> findAllAuthority();

	public Integer addAuthority(Authority authority);

	public void delAuthority(Authority authority);

	public void updateAuthority(Authority authority);
}
