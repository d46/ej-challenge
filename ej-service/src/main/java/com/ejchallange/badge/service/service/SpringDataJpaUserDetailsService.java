package com.ejchallange.badge.service.service;

import com.ejchallange.badge.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public SpringDataJpaUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.ejchallange.badge.service.domain.User user = this.userRepository.findByUsername(username);
		return new User(user.getUsername(), user.getPassword(),
			AuthorityUtils.createAuthorityList(user.getRoles()));
	}
}
