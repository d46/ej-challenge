package com.ejchallenge.badge.service.controller;

import com.ejchallenge.badge.service.domain.*;
import com.ejchallenge.badge.service.repository.ActionRepository;
import com.ejchallenge.badge.service.repository.ManagerActionRepository;
import com.ejchallenge.badge.service.repository.ManagerBadgeRepository;
import com.ejchallenge.badge.service.repository.ManagerRepository;
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
	private ManagerRepository managerRepository;
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private ManagerBadgeRepository managerBadgeRepository;
	@Autowired
	private ManagerActionRepository managerActionRepository;

	@RequestMapping(value = "/badges", method = RequestMethod.GET)
	@ResponseBody
	public List<Badge> getBadges(Authentication authentication) {

		//Find user
		Manager manager = this.managerRepository.findByUsername(authentication.getName());
		List<Badge> badgeList = new ArrayList<>();
		List<ManagerBadge> managerBadgeList = this.managerBadgeRepository.findAllByManager(manager);
		for(ManagerBadge managerBadge : managerBadgeList){
			badgeList.add(managerBadge.getBadge());
		}
		return badgeList;
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
	public ResponseEntity record(Authentication authentication, String actionName) {

		Manager manager = this.managerRepository.findByUsername(authentication.getName());

		Action action = this.actionRepository.findByName(actionName);

		this.managerActionRepository.save(new ManagerAction(manager, action));

		manager.setTotalScore(manager.getTotalScore() + action.getScore());
		this.managerRepository.save(manager);

		Badge badge = action.getBadge();
		List<Action> actionList = this.actionRepository.findByBadge(badge);
		int actionSize = 0;
		for (Action action1 : actionList){
			if(this.managerActionRepository.findByManagerAndAction(manager, action1).size() > 0){
				actionSize++;
			}
		}
		if(actionSize == actionList.size() && this.managerBadgeRepository.findByBadge(badge) == null){
			this.managerBadgeRepository.save(new ManagerBadge(manager, badge));
		}

		//Return 200
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
