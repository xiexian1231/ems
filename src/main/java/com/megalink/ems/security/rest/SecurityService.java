package com.megalink.ems.security.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.megalink.ems.model.User;
import com.megalink.ems.security.service.UserService;

@Controller
@RequestMapping("/user")
public class SecurityService {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public @ResponseBody String login(HttpServletRequest request) {
		String force = request.getParameter("force");
		String userInfo = request.getParameter("user");
		JSONObject jsonObj = JSONObject.parseObject(userInfo);
		User user = new User();
		user.setAccount(jsonObj.getString("username"));
		user.setPassword(jsonObj.getString("password"));
		String msg = userService.login(user, force);
		return msg;
	}

	@RequestMapping(value = "/logout")
	public void logout() {
		userService.logout();
	}

	@RequestMapping("/changePassword")
	public @ResponseBody String changePassword(HttpServletRequest request) {
		String userInfo = request.getParameter("user");
		JSONObject jsonObj = JSONObject.parseObject(userInfo);
		String username = jsonObj.getString("username").trim();
		String oldpwd = jsonObj.getString("oldpwd").trim();
		String newpwd = jsonObj.getString("newpwd").trim();
		String confirm = jsonObj.getString("confirm").trim();
		String msg = userService.changePassword(username, oldpwd, newpwd, confirm);
		return msg;
	}

}