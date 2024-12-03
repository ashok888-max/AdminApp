package com.app.demo.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.demo.entity.Hospital;
import com.app.demo.entity.Patient;
import com.app.demo.exceptions.HospitalNotFoundException;
import com.app.demo.repository.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	public Hospital getHospitalById(Long id) {
		return hospitalRepository.findById(id)
				.orElseThrow(() -> new HospitalNotFoundException("Hospital not found with ID: " + id));
	}

	public List<Hospital> searchHospitalsByNameOrCity(String search) throws BadRequestException {
		if (search == null || search.trim().isEmpty()) {
			throw new BadRequestException("Search term cannot be null or empty.");
		}
		return hospitalRepository.findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(search, search);
	}

	public List<Patient> getPatientsByHospitalId(Long hospitalId) {
		
		return null;
	}

}
