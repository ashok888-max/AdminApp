package com.app.demo.exceptions;

public class HospitalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HospitalNotFoundException(String message) {
		super(message);

	}

	public HospitalNotFoundException(Long id) {
		super("Patient with ID " + id + " not found");
	}

}
