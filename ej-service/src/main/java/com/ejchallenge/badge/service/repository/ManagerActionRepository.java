package com.ejchallenge.badge.service.repository;

import com.ejchallenge.badge.service.domain.Action;
import com.ejchallenge.badge.service.domain.Manager;
import com.ejchallenge.badge.service.domain.ManagerAction;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ManagerActionRepository extends PagingAndSortingRepository<ManagerAction, Long> {
	ManagerAction save(ManagerAction managerAction);
	List<ManagerAction> findByManagerAndAction(Manager manager, Action action);
}
