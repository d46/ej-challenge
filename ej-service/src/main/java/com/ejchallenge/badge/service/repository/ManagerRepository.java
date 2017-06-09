package com.ejchallenge.badge.service.repository;

import com.ejchallenge.badge.service.domain.Manager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ManagerRepository extends Repository<Manager, Long> {
	Manager save(Manager manager);
	Manager findByUsername(String username);
	List<Manager> findAll(Pageable pageable);
}
