package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailId(String email);

	public User findByUserName(String username);
}
