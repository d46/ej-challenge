package com.ejchallange.badge.service.repository;


import com.ejchallange.badge.service.domain.Badge;
import com.ejchallange.badge.service.domain.BadgeProgress;
import com.ejchallange.badge.service.domain.Manager;
import com.ejchallange.badge.service.domain.Manager;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BadgeProgressRepository extends PagingAndSortingRepository<BadgeProgress, Long> {
	BadgeProgress save(BadgeProgress badgeProgress);

	List<BadgeProgress> findByManagerAndBadge(Manager manager, Badge badge);
}
