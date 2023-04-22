package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.hms.entities.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailId(String email);

	public User findByUserName(String username);
	
	@Modifying
	@Query("UPDATE User U set U.isActive = false where U.userId = ?1 ")
	Integer inactivateUser(Long userId);
	
	@Modifying
	@Query("UPDATE User U set U.isActive = true where U.userId = ?1 ")
	Integer activateUser(Long userId);
	
}
