package com.example.demo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MemberDAO extends CrudRepository<Member, Long>{
	 
	@Query(value = "select * from Member where memberid = ?1", nativeQuery = true)
	    Member findOne(Long id);
	
	@Query(value = "select * from Member where username = ?1 limit 1", nativeQuery = true)
	Member findbyUsername(String username);
}

