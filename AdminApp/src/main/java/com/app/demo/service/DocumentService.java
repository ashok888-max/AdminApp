package com.app.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.demo.entity.Document;
import com.app.demo.entity.Users;
import com.app.demo.exceptions.UserNotFoundException;
import com.app.demo.repository.DocumentRepository;
import com.app.demo.repository.UserRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Document> getDocumentsByUserId(long userId) {
		return documentRepository.findByUserId(userId);
	}

	public void storeDocument(List<MultipartFile> files, long userId) throws IOException {
		// Fetch the patient, or throw an exception if not found
		Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

		List<Document> documents = new ArrayList<>();
		for (MultipartFile file : files) {
			// Check if the file is a PDF
			if (!"application/pdf".equals(file.getContentType()) || !file.getOriginalFilename().endsWith(".pdf")) {
				throw new IOException("Only PDF files are allowed: " + file.getOriginalFilename());
			}

			// Create a Document instance for each file
			Document document = new Document();
			document.setName(file.getOriginalFilename());
			document.setType(file.getContentType());
			document.setData(file.getBytes());
			document.setUser(user);

			// Save the document to the DocumentRepository
			documents.add(document);
		}

		// Save all documents at once
		documentRepository.saveAll(documents);

		// Optional: Set documents to the patient if needed
		user.setDocuments(documents);

		// Update patient in the PatientRepository if patient needs to reference
		// documents
		userRepository.save(user);
	}

	public Document getDocumentById(Long documentId) {
		return documentRepository.findById(documentId).orElse(null);
	}

	public boolean deleteDocument(Long documentId) {

		if (documentRepository.existsById(documentId)) {
			documentRepository.deleteById(documentId);
			return true;
		} else {
			return false;
		}
	}
}
