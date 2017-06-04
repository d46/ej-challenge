package com.ejchallange.badge.service.repository;


import com.ejchallange.badge.service.domain.BadgeProgress;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BadeProgressRepository extends PagingAndSortingRepository<BadgeProgress, Long> {
	BadgeProgress save(BadgeProgress badgeProgress);
}
