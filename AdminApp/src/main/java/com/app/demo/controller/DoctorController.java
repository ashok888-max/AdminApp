package com.app.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.demo.entity.Doctor;
import com.app.demo.repository.DoctorRepository;
import com.app.demo.service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private DoctorRepository doctorRepository;

	/*
	 * @PostMapping("/addDoctor") public ResponseEntity<String>
	 * addDoctor(@RequestBody Doctor doctor) { if (doctor != null) {
	 * doctorRepository.save(doctor);
	 * 
	 * return new ResponseEntity<>("Doctor added successfully", HttpStatus.OK); }
	 * else { return new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
	 * }
	 * 
	 * }
	 */

	@GetMapping("/search/{name}")
	public ResponseEntity<List<Doctor>> getDoctors(@PathVariable String name) {

		List<Doctor> doctors = doctorService.getDoctorsByName(name);

		if (!doctors.isEmpty()) {

			return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Doctor>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}
}
