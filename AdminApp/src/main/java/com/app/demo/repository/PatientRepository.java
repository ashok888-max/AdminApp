package com.app.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.demo.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	List<Patient> findByNameContainingIgnoreCase(String name);
}
