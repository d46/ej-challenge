package com.ejchallange.badge.service.repository;

import com.ejchallange.badge.service.domain.Badge;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BadgeRepository extends PagingAndSortingRepository<Badge, Long> {
	Badge save(Badge badge);
	Badge findByAction(String action);
}
