package com.example.Document.Management.System.controller;

import com.example.Document.Management.System.model.Document;
import com.example.Document.Management.System.service.DocumentService;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<Document> uploadDocument(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("fileName") String fileName) {
        Document document = documentService.uploadDocument(file, fileName);
        return ResponseEntity.ok(document);
    }

    @DeleteMapping("/{documentId}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long documentId) {
        documentService.deleteDocument(documentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

}
