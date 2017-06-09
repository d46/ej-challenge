package com.ejchallenge.badge.service.repository;

import com.ejchallenge.badge.service.domain.Action;
import com.ejchallenge.badge.service.domain.Badge;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ActionRepository  extends PagingAndSortingRepository<Action, Long> {
	Action findByName(String name);
	Action save(Action action);
	List<Action> findByBadge(Badge badge);
}
