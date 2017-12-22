package com.megalink.ems.security.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.megalink.ems.model.Authority;
import com.megalink.ems.model.Role;
import com.megalink.ems.model.User;
import com.megalink.ems.security.service.UserService;

public class UserAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 根据用户名查询当前用户拥有的角色
		Set<Role> roles = userService.findRoles(username);
		Set<String> roleNames = new HashSet<String>();
		for (Role role : roles) {
			roleNames.add(role.getName());
		}
		// 将角色名称提供给info
		authorizationInfo.setRoles(roleNames);
		// 根据用户名查询当前用户权限
		Set<Authority> permissions = userService.findPermissions(username);
		Set<String> permissionNames = new HashSet<String>();
		for (Authority authority : permissions) {
			permissionNames.add(authority.getName());
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);

		return authorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		User user = userService.findUserByUserName(username);
		if (user == null) {
			// 用户名不存在抛出异常
			throw new UnknownAccountException();
		}
		if (user.getLocked() == 0) {
			// 用户被管理员锁定抛出异常
			throw new LockedAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getAccount(),
				user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		return authenticationInfo;
	}

}
