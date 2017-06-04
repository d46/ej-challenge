package com.ejchallange.badge.service.repository;


import com.ejchallange.badge.service.domain.Badge;
import com.ejchallange.badge.service.domain.BadgeProgress;
import com.ejchallange.badge.service.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BadgeProgressRepository extends PagingAndSortingRepository<BadgeProgress, Long> {
	BadgeProgress save(BadgeProgress badgeProgress);

	List<BadgeProgress> findByUserAndBadge(User user, Badge badge);
}
