package com.illan.ulearnvite.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.illan.ulearnvite.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	   public User findByEmail(String email);
	   boolean existsByEmail(String email);
	   boolean existsByUsername(String username);
	   public User findByUsername(String username);
	}
 


