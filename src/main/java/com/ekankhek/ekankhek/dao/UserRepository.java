package com.ekankhek.ekankhek.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ekankhek.ekankhek.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Transactional
	@Query("from User cg where cg.email = ?1")
	User findByUsername(String email);
	
}
