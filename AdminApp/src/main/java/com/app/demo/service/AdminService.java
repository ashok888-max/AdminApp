package com.app.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.demo.entity.Admin;
import com.app.demo.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Admin findUserByAdminId(String adminId) {
		// Assuming you are using JPA or Hibernate
		return adminRepository.findByAdminId(adminId);
	}

	public Admin findAdminByEmail(String email) {
		// Assuming you have a method in your repository to find by email
		return adminRepository.findByEmail(email);
	}

	public boolean validatePassword(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
