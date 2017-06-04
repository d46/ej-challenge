package com.ejchallange.badge.service;

import com.ejchallange.badge.service.domain.Badge;
import com.ejchallange.badge.service.domain.BadgeProgress;
import com.ejchallange.badge.service.domain.User;
import com.ejchallange.badge.service.repository.BadgeProgressRepository;
import com.ejchallange.badge.service.repository.BadgeRepository;
import com.ejchallange.badge.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class BadgeSeed implements CommandLineRunner {

	private final BadgeRepository badgeRepository;

	private final BadgeProgressRepository badgeProgressRepository;

	private final UserRepository userRepository;

	@Autowired
	public BadgeSeed(BadgeRepository badgeRepository, BadgeProgressRepository badgeProgressRepository, UserRepository userRepository) {
		this.badgeRepository = badgeRepository;
		this.badgeProgressRepository = badgeProgressRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		//Create dummy user
		User dummy = new User("hello","1",0,"ROLE_MANAGER");
		this.userRepository.save(dummy);

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("hello", "1",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));



		//Create Badge
		Badge clicker = new Badge("Clicker", 33,"CLICKER",66);
		this.badgeRepository.save(clicker);


//		Create badgeAction
		BadgeProgress badgeProgress = new BadgeProgress(
			clicker,
			dummy);
		 this.badgeProgressRepository.save(badgeProgress);

		 Badge clicker1 = this.badgeRepository.findByAction("CLICKER");
		 BadgeProgress badgeProgress1 = new BadgeProgress(
			 clicker1,
			 dummy
		 );
		 this.badgeProgressRepository.save(badgeProgress1);

		SecurityContextHolder.clearContext();


	}
}
