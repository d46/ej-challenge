package com.ejchallange.badge.service;

import com.ejchallange.badge.service.domain.Badge;
import com.ejchallange.badge.service.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BadgeSeed implements CommandLineRunner {

	private final BadgeRepository badgeRepository;

	@Autowired
	public BadgeSeed(BadgeRepository badgeRepository) {
		this.badgeRepository = badgeRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		Badge clickler = this.badgeRepository.save(new Badge("Clicker", 33));
	}
}
