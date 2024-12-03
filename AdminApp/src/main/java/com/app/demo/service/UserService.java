package com.app.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.demo.entity.Users;
import com.app.demo.exceptions.UserNotFoundException;
import com.app.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Users getUserDetailsById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Patient with ID " + id + " not found"));
	// Use Optional to handle not found case
	}

	public List<Users> searchUsersByName(String name) {
		return userRepository.findByNameContainingIgnoreCase(name); // Assuming you have a method to search by
																				// name
	}

}
