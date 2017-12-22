package com.megalink.ems.security.service;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megalink.ems.model.Authority;
import com.megalink.ems.model.Role;
import com.megalink.ems.model.User;
import com.megalink.ems.security.dao.UserHelper;
import com.megalink.ems.util.EncryptUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private SessionDAO sessionDAO;

	@Override
	public User findUserByUserName(String username) {
		return userHelper.findUserByUserName(username);
	}

	@Override
	public Set<Role> findRoles(String username) {
		return userHelper.findRoles(username);
	}

	@Override
	public Set<Authority> findPermissions(String username) {
		return userHelper.findPermissions(username);
	}

	@Override
	public Integer addUser(User user) {
		EncryptUtils.md5Password(user);
		return userHelper.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		userHelper.updateUser(user);
	}

	@Override
	public String changePassword(String username, String oldpwd, String newpwd, String confirm) {
		User user = findUserByUserName(username);
		String pwd = user.getPassword();
		String MDoldpwd = new Md5Hash(oldpwd, user.getSalt(), 3).toHex();
		String msg = "";
		if (MDoldpwd.equals(pwd)) {
			if (newpwd.equals(confirm)) {
				if (!(new Md5Hash(newpwd, user.getSalt(), 3).toHex().equals(pwd))) {
					user.setPassword(newpwd);
					EncryptUtils.md5Password(user);
					userHelper.updateUser(user);
					msg = "密码修改成功";
				} else {
					msg = "密码没有改动";
				}
			} else {
				msg = "抱歉，密码输入不一致";
			}
		} else {
			msg = "旧密码输入错误";
		}
		return msg;
	}

	@Override
	public String login(User user, String force) {
		Subject subject = SecurityUtils.getSubject();
		// 如果认证通过，重复登陆时处理逻辑
		if (subject.isAuthenticated()) {
			return "验证通过";
		}
		// 判断该账号是否已在其他地方登陆
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		for (Session session : sessions) {
			String loginUsername = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
			if (user.getAccount().equals(loginUsername)) {
				if (force.equals("no")) {
					return "该账号已登陆";
				} else {
					session.setTimeout(0);
				}
			}
		}
		// end
		Session session = subject.getSession();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
		String msg = "";
		try {
			subject.login(token);
			msg = "验证通过";
		} catch (UnknownAccountException ex) {
			msg = "用户不存在";
			log.error("用户不存在", ex);
		} catch (IncorrectCredentialsException ex) {
			msg = "密码错误";
			log.error("密码错误", ex);
		} catch (LockedAccountException ex) {
			msg = "账户被锁定，请联系管理员";
			log.error("账户被锁定，请联系管理员", ex);
		} catch (ExcessiveAttemptsException ex) {
			msg = "登录失败多次，账户锁定5分钟";
			log.error("登录失败多次，账户锁定5分钟", ex);
		} catch (AuthenticationException ex) {
			msg = "用户验证失败";
			log.error("用户验证失败", ex);
		} catch (Exception ex) {
			msg = "其他错误";
			log.error("其他错误", ex);
		}
		// 验证成功后保存用户信息到session
		if (subject.isAuthenticated()) {
			session.setAttribute("user", findUserByUserName(user.getAccount()));
			session.setAttribute("today", new Date());
		}
		return msg;
	}

	@Override
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
	}

}
