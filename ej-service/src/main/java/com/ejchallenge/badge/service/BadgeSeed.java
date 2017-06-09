package com.ejchallenge.badge.service;

import com.ejchallenge.badge.service.domain.Action;
import com.ejchallenge.badge.service.domain.Badge;
import com.ejchallenge.badge.service.domain.Manager;
import com.ejchallenge.badge.service.domain.ManagerAction;
import com.ejchallenge.badge.service.repository.ActionRepository;
import com.ejchallenge.badge.service.repository.BadgeRepository;
import com.ejchallenge.badge.service.repository.ManagerActionRepository;
import com.ejchallenge.badge.service.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class BadgeSeed implements CommandLineRunner {

	private final BadgeRepository badgeRepository;


	private final ManagerRepository managerRepository;

	private final ManagerActionRepository managerActionRepository;

	private final ActionRepository actionRepository;

	@Autowired
	public BadgeSeed(BadgeRepository badgeRepository, ManagerRepository managerRepository, ManagerActionRepository managerActionRepository, ActionRepository actionRepository) {
		this.badgeRepository = badgeRepository;
		this.managerRepository = managerRepository;
		this.managerActionRepository = managerActionRepository;
		this.actionRepository = actionRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		try {
			//Create dummy user
			Manager dummy = new Manager("hello", "1", 0, "ROLE_MANAGER");
			this.managerRepository.save(dummy);

			SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("hello", "1",
					AuthorityUtils.createAuthorityList("ROLE_MANAGER")));


			Badge badge = new Badge("CLICKER");
			this.badgeRepository.save(badge);

			Action action1 = new Action("CLICKER_1", 30, badge);
			Action action2 = new Action("CLICKER_2", 20, badge);
			Action action3 = new Action("CLICKER_3", 20, badge);
			this.actionRepository.save(action1);
			this.actionRepository.save(action2);
			this.actionRepository.save(action3);

			Badge badge1 = new Badge("SCROLLER");
			this.badgeRepository.save(badge1);

			Action action4 = new Action("SCROLLER_1", 30, badge1);
			Action action5 = new Action("SCROLLER_2", 20, badge1);
			this.actionRepository.save(action4);
			this.actionRepository.save(action5);



			Badge badge2 = new Badge("EXPLORER");
			this.badgeRepository.save(badge2);

			Action action8 = new Action("EXPLORER_1", 30, badge2);
			Action action9 = new Action("EXPLORER_2", 20, badge2);
			Action action10 = new Action("EXPLORER_3", 20, badge2);
			Action action11 = new Action("EXPLORER_4", 20, badge2);
			this.actionRepository.save(action8);
			this.actionRepository.save(action9);
			this.actionRepository.save(action10);
			this.actionRepository.save(action11);


			Badge badge3 = new Badge("EXPECTANT");
			this.badgeRepository.save(badge3);

			Action action12 = new Action("EXPECTANT_1", 30, badge3);
			Action action13 = new Action("EXPECTANT_2", 20, badge3);
			this.actionRepository.save(action12);
			this.actionRepository.save(action13);




			ManagerAction managerAction1 = new ManagerAction(dummy, action1);
			ManagerAction managerAction2 = new ManagerAction(dummy, action2);
			ManagerAction managerAction3 = new ManagerAction(dummy, action3);

			this.managerActionRepository.save(managerAction1);
			this.managerActionRepository.save(managerAction2);
			this.managerActionRepository.save(managerAction3);

			SecurityContextHolder.clearContext();

		} catch (Exception e) {

		}

	}
}
