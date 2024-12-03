package com.app.demo.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.demo.entity.Hospital;
import com.app.demo.service.HospitalService;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@GetMapping("/search")
	public ResponseEntity<List<Hospital>> getHospitals(@RequestParam String searchTerm) throws BadRequestException {
		if (searchTerm != null) {
			if (isNumeric(searchTerm)) {
				Long id = Long.parseLong(searchTerm);
				Hospital hospital = hospitalService.getHospitalById(id);
				return new ResponseEntity<>(List.of(hospital), HttpStatus.OK);
			} else {
				List<Hospital> hospitals = hospitalService.searchHospitalsByNameOrCity(searchTerm);
				return new ResponseEntity<>(hospitals, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 if no search term provided
	}

	private boolean isNumeric(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/*
	 * @GetMapping("/patients") public ResponseEntity<?>
	 * getPatientsByHospitalId(@RequestParam Long hospitalId) { List<Patient>
	 * patients = hospitalService.getPatientsByHospitalId(hospitalId); if
	 * (patients.isEmpty()) { return ResponseEntity.status(HttpStatus.NOT_FOUND).
	 * body("No patients found for this hospital"); } return
	 * ResponseEntity.ok(patients); }
	 */
}
