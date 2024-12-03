package com.app.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.demo.entity.Document;
import com.app.demo.service.DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@PostMapping("/upload/{id}")
	public ResponseEntity<String> uploadUserWithPDFs(@PathVariable("id") long userId,
			@RequestParam("files") List<MultipartFile> files) {
		try {
			documentService.storeDocument(files, userId);
			return ResponseEntity.status(HttpStatus.CREATED).body("Documents uploaded successfully.");
		} catch (IOException e) {
			// Log the error and return a specific message
			// logger.error("IOException occurred while uploading files: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload files: " + e.getMessage());
		} catch (Exception e) {
			// Log any other exceptions
			// logger.error("An unexpected error occurred: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An unexpected error occurred: " + e.getMessage());
		}
	}

	@GetMapping("/getDocument/{documentId}")
	public ResponseEntity<byte[]> viewPDF(@PathVariable Long documentId) {
		Document document = documentService.getDocumentById(documentId); // Fixed method name
		if (document != null) {
			return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + document.getName() + "\"") 
																																																			// in
																														
					.contentType(MediaType.APPLICATION_PDF) // Use the predefined constant for PDF
					.body(document.getData());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/deleteDocument/{documentId}")
	public ResponseEntity<String> deletePDF(@PathVariable Long documentId) {
		try {
			boolean deleted = documentService.deleteDocument(documentId);
			if (deleted) {
				return ResponseEntity.ok("File deleted successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to delete file: " + e.getMessage());
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Document>> getDocumentsByUserId(@PathVariable Long userId) {

		List<Document> documents = documentService.getDocumentsByUserId(userId);

		return ResponseEntity.ok(documents);
	}

}
