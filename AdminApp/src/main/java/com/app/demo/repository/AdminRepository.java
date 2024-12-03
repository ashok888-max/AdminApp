package com.app.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.demo.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByAdminId(String adminId);
	
	Admin findByEmail(String email);
}
