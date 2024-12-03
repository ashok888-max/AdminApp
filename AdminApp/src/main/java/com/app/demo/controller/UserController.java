package com.app.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.demo.entity.Users;
import com.app.demo.exceptions.InvalidInputException;
import com.app.demo.exceptions.UserNotFoundException;
import com.app.demo.repository.UserRepository;
import com.app.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@GetMapping
	public ResponseEntity<List<Users>> getUsers(@RequestParam(required = false) String search) {
		if (search != null && !search.trim().isEmpty()) {
			if (isNumeric(search)) {
				try {
					Long id = Long.parseLong(search); // Parse search as ID
					Users user = userService.getUserDetailsById(id);
					if (user == null) {
						throw new UserNotFoundException(" with ID " + id + " not found");
					}
					return new ResponseEntity<>(List.of(user), HttpStatus.OK);
				} catch (NumberFormatException e) {
					throw new InvalidInputException("Invalid Patient ID format");
				}
			} else {
				return new ResponseEntity<>(userRepository.findByNameContainingIgnoreCase(search), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	private boolean isNumeric(String str) {
		return str.matches("\\d+");
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Users> getUser(@PathVariable("userId") Long id) {
		Users user = userService.getUserDetailsById(id);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody Users user) {
		if (user == null) {
			System.out.println("User object is null");
			return new ResponseEntity<>("Invalid user data", HttpStatus.BAD_REQUEST);
		}

		try {
			userRepository.save(user);
			System.out.println("User added: " + user.toString());
			return new ResponseEntity<>("User added successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error saving user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
