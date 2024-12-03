package com.app.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.demo.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	// Ensure the parameter type matches the type in the Document entity
	List<Document> findByUserId(Long userId); // If patientId in Document is Long

	// List<Document> findByPatientId(String patientId);
}
