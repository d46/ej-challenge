package com.ejchallange.badge.service.controller;

import com.ejchallange.badge.service.domain.Badge;
import com.ejchallange.badge.service.domain.BadgeProgress;
import com.ejchallange.badge.service.domain.User;
import com.ejchallange.badge.service.dto.BadgeDto;
import com.ejchallange.badge.service.repository.BadgeProgressRepository;
import com.ejchallange.badge.service.repository.BadgeRepository;
import com.ejchallange.badge.service.repository.UserRepository;
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
	private UserRepository userRepository;

	@RequestMapping(value = "/badges", method = RequestMethod.GET)
	@ResponseBody
	public List<BadgeDto> getBadges(Authentication authentication) {

		//Calculate stats
		List<BadgeDto> badgeDtos = new ArrayList<>();
		User user = this.userRepository.findByUsername(authentication.getName());
		Iterable<Badge> badges = this.badgeRepository.findAll();

		for (Badge badge : badges) {
			List<BadgeProgress> badgeProgresses = this.badgeProgressRepository.findByUserAndBadge(user, badge);
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
	public List<User> getTopList() {
		Pageable topTen = new PageRequest(0, 10, Sort.Direction.ASC, "totalScore");
		return this.userRepository.findAll(topTen);
	}

	//Create badge action
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity record(Authentication authentication, String action) {

		User user = this.userRepository.findByUsername(authentication.getName());
		Badge badge = this.badgeRepository.findByAction(action);

		//Update user totalScore
		user.setTotalScore(user.getTotalScore() + badge.getScore());
		this.userRepository.save(user);

		//Create new badge action
		BadgeProgress badgeProgress = new BadgeProgress(badge, user);
		this.badgeProgressRepository.save(badgeProgress);

		//Return 200
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
