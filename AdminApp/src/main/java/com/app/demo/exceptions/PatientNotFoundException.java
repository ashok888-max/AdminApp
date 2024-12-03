package com.app.demo.exceptions;

public class PatientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PatientNotFoundException(String message) {
		super(message);
	}

	public PatientNotFoundException(Long id) {
		super("Patient with ID " + id + " not found");
	}
}
