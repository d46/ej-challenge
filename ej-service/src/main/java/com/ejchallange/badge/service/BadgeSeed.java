package com.ejchallange.badge.service;

import com.ejchallange.badge.service.domain.Badge;
import com.ejchallange.badge.service.domain.BadgeProgress;
import com.ejchallange.badge.service.domain.Manager;
import com.ejchallange.badge.service.repository.BadgeProgressRepository;
import com.ejchallange.badge.service.repository.BadgeRepository;
import com.ejchallange.badge.service.repository.ManagerRepository;
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

	private final ManagerRepository managerRepository;

	@Autowired
	public BadgeSeed(BadgeRepository badgeRepository, BadgeProgressRepository badgeProgressRepository, ManagerRepository managerRepository) {
		this.badgeRepository = badgeRepository;
		this.badgeProgressRepository = badgeProgressRepository;
		this.managerRepository = managerRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		try{
			//Create dummy user
			Manager dummy = new Manager("hello","1",0,"ROLE_MANAGER");
			this.managerRepository.save(dummy);

			SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("hello", "1",
					AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

			//Create Badge
			Badge clicker = new Badge("Clicker", 33,"CLICKER",66);
			this.badgeRepository.save(clicker);

			Badge scroller = new Badge("Scroller", 60,"SCROLLER",60);
			this.badgeRepository.save(scroller);

			Badge explorer = new Badge("Explorer", 40,"EXPLORER",80);
			this.badgeRepository.save(explorer);

			Badge expectant = new Badge("Expectant",60,"EXPECTANT",120);
			this.badgeRepository.save(expectant);

			//	Create badgeAction
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

		}catch (Exception e){

		}

	}
}
