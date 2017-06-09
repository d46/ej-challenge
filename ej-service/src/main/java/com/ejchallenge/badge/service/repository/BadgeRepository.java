package com.ejchallenge.badge.service.repository;

import com.ejchallenge.badge.service.domain.Badge;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BadgeRepository extends PagingAndSortingRepository<Badge, Long> {
	Badge save(Badge badge);
	List<Badge> findAll();
}
