package com.ejchallange.badge.service;

import com.ejchallange.badge.service.domain.Badge;
import com.ejchallange.badge.service.domain.BadgeProgress;
import com.ejchallange.badge.service.domain.User;
import com.ejchallange.badge.service.repository.BadeProgressRepository;
import com.ejchallange.badge.service.repository.BadgeRepository;
import com.ejchallange.badge.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BadgeSeed implements CommandLineRunner {

	private final BadgeRepository badgeRepository;

	private final BadeProgressRepository badeProgressRepository;

	private final UserRepository userRepository;

	@Autowired
	public BadgeSeed(BadgeRepository badgeRepository, BadeProgressRepository badeProgressRepository,UserRepository userRepository) {
		this.badgeRepository = badgeRepository;
		this.badeProgressRepository = badeProgressRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		//Create dummy user
		User dummy = new User("hello","1");
		this.userRepository.save(dummy);


		//Create Badge
		Badge clicker = new Badge("Clicker", 33,"CLICKER");
		this.badgeRepository.save(clicker);


//		Create badgeAction
		BadgeProgress badgeProgress = new BadgeProgress(
			clicker,
			dummy);
		 this.badeProgressRepository.save(badgeProgress);

		 Badge clicker1 = this.badgeRepository.findByAction("CLICKER");
		 BadgeProgress badgeProgress1 = new BadgeProgress(
			 clicker1,
			 dummy
		 );
		 this.badeProgressRepository.save(badgeProgress1);


	}
}
