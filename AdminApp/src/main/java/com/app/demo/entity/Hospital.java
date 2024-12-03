package com.app.demo.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity(name = "hospitals")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name cannot be blank")
	private String name;

	@NotBlank(message = "Address cannot be blank")
	private String address;

	@NotBlank(message = "City Name cannot be blank")
	private String city;

	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode must be exactly 6 digits")
	private String pincode;

	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Contact number must be 10 digits starting from 6 to 9")
	@Column(unique = true)
	private String contact;

	@Email(message = "Email must be valid")
	@Size(min = 8, message = "Email must be at least 8 characters long")
	@Column(unique = true)
	private String email;

	@Pattern(regexp = "^[a-zA-Z]{8,}$", message = "Username must be at least 8 characters long and contain only alphabetic characters")
	@Column(unique = true)
	private String username;

	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters long, contain one uppercase letter, and one special character")
	private String password;

	@PastOrPresent(message = "Established year must be the current year or a past year")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate establishedYear;

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Patient> patients; // Link to patients

	public Hospital() {
		super();

	}

	public Hospital(@NotBlank(message = "Name cannot be blank") String name,
			@NotBlank(message = "Address cannot be blank") String address,
			@NotBlank(message = "City Name cannot be blank") String city,
			@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode must be exactly 6 digits") String pincode,
			@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Contact number must be 10 digits starting from 6 to 9") String contact,
			@Email(message = "Email must be valid") @Size(min = 8, message = "Email must be at least 8 characters long") String email,
			@Pattern(regexp = "^[a-zA-Z]{8,}$", message = "Username must be at least 8 characters long and contain only alphabetic characters") String username,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters long, contain one uppercase letter, and one special character") String password,
			@PastOrPresent(message = "Established year must be the current year or a past year") LocalDate establishedYear,
			List<Patient> patients) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.contact = contact;
		this.email = email;
		this.username = username;
		this.password = password;
		this.establishedYear = establishedYear;
		this.patients = patients;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getEstablishedYear() {
		return establishedYear;
	}

	public void setEstablishedYear(LocalDate establishedYear) {
		this.establishedYear = establishedYear;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
