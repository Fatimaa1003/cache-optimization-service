package com.spring.redis.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.redis.api.entities.User;

public interface UserRepository extends JpaRepository<User, UUID>{

	@Query("SELECT u FROM User u where u.isActive='Y'")
	List<User> getAllService();


}
