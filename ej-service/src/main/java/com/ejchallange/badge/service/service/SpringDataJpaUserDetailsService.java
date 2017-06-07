package com.ejchallange.badge.service.service;

import com.ejchallange.badge.service.domain.Manager;
import com.ejchallange.badge.service.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final ManagerRepository managerRepository;

	@Autowired
	public SpringDataJpaUserDetailsService(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Manager manager = this.managerRepository.findByUsername(username);
		return new User(manager.getUsername(), manager.getPassword(),
			AuthorityUtils.createAuthorityList(manager.getRoles()));
	}
}
