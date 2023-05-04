package com.example.Document.Management.System.repository;

import com.example.Document.Management.System.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // Add any additional methods for document retrieval or querying if needed
}
