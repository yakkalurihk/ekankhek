package com.ekankhek.ekankhek.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ekankhek.ekankhek.domain.Datauploads;
import com.ekankhek.ekankhek.domain.User;

@Repository
@Transactional
public interface DatauploadsREpository extends JpaRepository<Datauploads, Long>{
	
	@Transactional
	@Query("from Datauploads cg where cg.user = ?1")
	List<Datauploads> findByUsername(User user);
	
}



/*
@Repository
@Transactional

*/