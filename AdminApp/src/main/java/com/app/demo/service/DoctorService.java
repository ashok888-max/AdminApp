package com.app.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.demo.entity.Doctor;
import com.app.demo.repository.DoctorRepository;

@Service
public class DoctorService {

	
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	public ResponseEntity<String> addDoctor(Doctor doctor) {
		if (doctor != null) {
			doctorRepository.save(doctor);

			return new ResponseEntity<>("Doctor added successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
		}

	}
	
	
	public List<Doctor> getDoctorsByName(String name){
		
		List<Doctor> doctors = doctorRepository.findByName(name);
		
		return doctors;
	}
	
 }
