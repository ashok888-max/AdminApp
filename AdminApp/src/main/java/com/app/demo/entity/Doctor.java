package com.app.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {

	@Id
	@Column(name = "doctor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "doctor_ name",nullable = false)
	private String name;

	@Column(nullable = false)
	private String specialization;

	@Column(nullable = false)
	private int yearsOfExperience;

	
	public Doctor(String name, String specialization, int yearsOfExperience) {
		super();
		this.name = name;
		this.specialization = specialization;
		this.yearsOfExperience = yearsOfExperience;
	}

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
}
