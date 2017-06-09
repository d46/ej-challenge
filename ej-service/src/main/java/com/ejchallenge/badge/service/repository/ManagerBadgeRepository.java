package com.ejchallenge.badge.service.repository;

import com.ejchallenge.badge.service.domain.Badge;
import com.ejchallenge.badge.service.domain.Manager;
import com.ejchallenge.badge.service.domain.ManagerBadge;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ManagerBadgeRepository extends PagingAndSortingRepository<ManagerBadge, Long>{
	ManagerBadge save(ManagerBadge managerBadge);
	ManagerBadge findByBadge(Badge badge);
	List<ManagerBadge> findAllByManager(Manager manager);
}
