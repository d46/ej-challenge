package com.ejchallange.badge.service.controller;

import com.ejchallange.badge.service.domain.User;
import com.ejchallange.badge.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity Logout(HttpServletRequest request, HttpServletResponse response) {
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		cookieClearingLogoutHandler.logout(request, response, null);
		securityContextLogoutHandler.logout(request, response, null);

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
