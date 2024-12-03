package com.app.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String adminId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[\\w._%+-]+@(gmail|yahoo|veboticstechno)\\.com$", message = "Email must be from domain (gmail.com, yahoo.com, veboticstechno.com)")
	private String email;

	public Admin(String adminId, String password, String email) {
		this.adminId = adminId;
		this.password = password;
		this.email = email;

	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
