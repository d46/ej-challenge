package com.ejchallange.badge.service.controller;

import com.ejchallange.badge.service.domain.Badge;
import com.ejchallange.badge.service.domain.BadgeProgress;
import com.ejchallange.badge.service.domain.Manager;
import com.ejchallange.badge.service.dto.BadgeDto;
import com.ejchallange.badge.service.repository.BadgeProgressRepository;
import com.ejchallange.badge.service.repository.BadgeRepository;
import com.ejchallange.badge.service.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/reports")
public class BadgeProgressController {

	@Autowired
	private BadgeProgressRepository badgeProgressRepository;
	@Autowired
	private BadgeRepository badgeRepository;
	@Autowired
	private ManagerRepository managerRepository;

	@RequestMapping(value = "/badges", method = RequestMethod.GET)
	@ResponseBody
	public List<BadgeDto> getBadges(Authentication authentication) {

		//Calculate stats
		List<BadgeDto> badgeDtos = new ArrayList<>();
		Manager manager = this.managerRepository.findByUsername(authentication.getName());
		Iterable<Badge> badges = this.badgeRepository.findAll();

		for (Badge badge : badges) {
			List<BadgeProgress> badgeProgresses = this.badgeProgressRepository.findByManagerAndBadge(manager, badge);
			int actionSize = badgeProgresses.size();
			int score = actionSize * badge.getScore();
			if (score >= badge.getScoreForBadge()) {
				badgeDtos.add(new BadgeDto(score, badge.getName()));
			}
		}

		return badgeDtos;
	}

	@RequestMapping(value = "/topList", method = RequestMethod.GET)
	@ResponseBody
	public List<Manager> getTopList() {
		Pageable topTen = new PageRequest(0, 10, Sort.Direction.ASC, "totalScore");
		return this.managerRepository.findAll(topTen);
	}

	//Create badge action
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity record(Authentication authentication, String action) {

		Manager manager = this.managerRepository.findByUsername(authentication.getName());
		Badge badge = this.badgeRepository.findByAction(action);

		//Update user totalScore
		manager.setTotalScore(manager.getTotalScore() + badge.getScore());
		this.managerRepository.save(manager);

		//Create new badge action
		BadgeProgress badgeProgress = new BadgeProgress(badge, manager);
		this.badgeProgressRepository.save(badgeProgress);

		//Return 200
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
