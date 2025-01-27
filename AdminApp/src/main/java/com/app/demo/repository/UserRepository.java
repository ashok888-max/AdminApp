
package com.app.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.demo.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	List<Users> findByNameContainingIgnoreCase(String name);
}
