package com.app.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.demo.entity.Patient;
import com.app.demo.exceptions.PatientNotFoundException;
import com.app.demo.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public Patient getPatientDetailsById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));
	// Use Optional to handle not found case
	}

	public List<Patient> searchPatientsByName(String name) {
		return patientRepository.findByNameContainingIgnoreCase(name); // Assuming you have a method to search by
																				// name
	}

}
