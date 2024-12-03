package com.app.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.demo.entity.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	List<Hospital> findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(String name, String city);

}
