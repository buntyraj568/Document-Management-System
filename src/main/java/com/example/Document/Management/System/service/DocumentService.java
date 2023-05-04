package com.example.Document.Management.System.service;

import com.example.Document.Management.System.model.Document;
import com.example.Document.Management.System.repository.DocumentRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document uploadDocument(MultipartFile file, String fileName) {
        try {


            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
            String filePath = "D:/pdf" + uniqueFileName;
            File destFile = new File(filePath);
            file.transferTo(destFile);

            Document document = new Document();
            document.setFileName(fileName);
            document.setFilePath(filePath);


            return documentRepository.save(document);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload document", e);
        }
    }

    public void deleteDocument(Long documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid document ID"));

        File fileToDelete = new File(document.getFilePath());
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }

        documentRepository.delete(document);
    }

    public List<Document> getAllDocuments() {

        return documentRepository.findAll();
    }
}

