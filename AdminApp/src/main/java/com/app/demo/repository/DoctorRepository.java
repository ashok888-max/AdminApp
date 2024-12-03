package com.app.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.demo.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	List<Doctor> findByName(String name);
 
}
