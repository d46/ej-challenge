package com.ejchallange.badge.service.repository;

import com.ejchallange.badge.service.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserRepository extends Repository<User, Long> {
	User save(User user);
	User findByUsername(String username);
	List<User> findFirstByTotalScore(int size);
	List<User> findAll(Pageable pageable);
}
