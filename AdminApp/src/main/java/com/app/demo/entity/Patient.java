package com.app.demo.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity(name = "patients")
public class Patient {

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Name cannot be null")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@Min(value = 0, message = "Age cannot be negative")
	@Max(value = 120, message = "Age must be realistic")
	private int age;

	@NotNull(message = "Gender is required")
	@Pattern(regexp = "^(?i)(MALE|FEMALE)$", message = "Gender must be MALE or FEMALE (case insensitive)")
	private String gender;

	@Size(max = 500, message = "Medical history cannot exceed 500 characters")
	private String medicalHistory;

	@NotNull(message = "Preferred treatment date is required")
	@PastOrPresent(message = "Preferred treatment date must be in the past or present")
	@DateTimeFormat(pattern = "yyyy-MM-dd") // For request body
	@JsonFormat(pattern = "yyyy-MM-dd") // For response body
	private LocalDate preferredTreatmentDate;

	@Size(max = 255, message = "Current medication cannot exceed 255 characters")
	private String currentMedication;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital_id", nullable = false)
	@NotNull(message = "Hospital is required")
	private Hospital hospital;

	// Getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public LocalDate getPreferredTreatmentDate() {
		return preferredTreatmentDate;
	}

	public void setPreferredTreatmentDate(LocalDate preferredTreatmentDate) {
		this.preferredTreatmentDate = preferredTreatmentDate;
	}

	public String getCurrentMedication() {
		return currentMedication;
	}

	public Patient(
			@NotNull(message = "Name cannot be null") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name,
			@Min(value = 0, message = "Age cannot be negative") @Max(value = 120, message = "Age must be realistic") int age,
			@NotNull(message = "Gender is required") @Pattern(regexp = "^(?i)(MALE|FEMALE)$", message = "Gender must be MALE or FEMALE (case insensitive)") String gender,
			@Size(max = 500, message = "Medical history cannot exceed 500 characters") String medicalHistory,
			@NotNull(message = "Preferred treatment date is required") @PastOrPresent(message = "Preferred treatment date must be in the past or present") LocalDate preferredTreatmentDate,
			@Size(max = 255, message = "Current medication cannot exceed 255 characters") String currentMedication) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.medicalHistory = medicalHistory;
		this.preferredTreatmentDate = preferredTreatmentDate;
		this.currentMedication = currentMedication;
	}

	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
}
