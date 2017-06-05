package com.ejchallange.badge.service.controller;

import com.ejchallange.badge.service.domain.User;
import com.ejchallange.badge.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class AuthenticationController {

	@Autowired
	private UserRepository userRepository;

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public User Index(Authentication authentication) {
		return this.userRepository.findByUsername(authentication.getName());
	}

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User Register(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password,0,"ROLE_MANAGER");
		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken(username, "1",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
		this.userRepository.save(user);
		SecurityContextHolder.clearContext();
		return user;
	}

}
