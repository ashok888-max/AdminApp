package com.app.demo.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Users {

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Name cannot be null")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@Min(value = 0, message = "Age cannot be negative")
	@Max(value = 120, message = "Age must be realistic")
	private int age;

	@NotNull(message = "Gender is required")
	@Pattern(regexp = "MALE|FEMALE", message = "Gender must be MALE or FEMALE")
	private String gender;

	@Size(max = 500, message = "Medical history cannot exceed 500 characters")
	private String medicalHistory;

	@NotNull(message = "Preferred treatment date is required")
	@FutureOrPresent(message = "Preferred treatment date must be in the future or present")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate preferredTreatmentDate;

	@Size(max = 255, message = "Current medication cannot exceed 255 characters")
	private String currentMedication;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	private String email;
	private String contactNumber;
	private String address;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String paymentStatus;
	private String paymentMode;
	private Double amount;
	private String passportStatus;
	private String emergencyContact;
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Document> documents;

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	// Constructors, Getters, and Setters
	public Users() {
	}

	public Users(
			@NotNull(message = "Name cannot be null") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name,
			@Min(value = 0, message = "Age cannot be negative") @Max(value = 120, message = "Age must be realistic") int age,
			@NotNull(message = "Gender is required") @Pattern(regexp = "MALE|FEMALE", message = "Gender must be MALE or FEMALE") String gender,
			@Size(max = 500, message = "Medical history cannot exceed 500 characters") String medicalHistory,
			@NotNull(message = "Preferred treatment date is required") @PastOrPresent(message = "Preferred treatment date must be in the past or present") LocalDate preferredTreatmentDate,
			@Size(max = 255, message = "Current medication cannot exceed 255 characters") String currentMedication,
			Date dateOfBirth, String email, String contactNumber, String address, String city, String state,
			String country, String postalCode, String paymentStatus, String paymentMode, Double amount,
			String passportStatus, String emergencyContact, String password) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.medicalHistory = medicalHistory;
		this.preferredTreatmentDate = preferredTreatmentDate;
		this.currentMedication = currentMedication;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.contactNumber = contactNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.paymentStatus = paymentStatus;
		this.paymentMode = paymentMode;
		this.amount = amount;
		this.passportStatus = passportStatus;
		this.emergencyContact = emergencyContact;
		this.password = password;
	}

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

	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPassportStatus() {
		return passportStatus;
	}

	public void setPassportStatus(String passportStatus) {
		this.passportStatus = passportStatus;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
