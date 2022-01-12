package com.nsia.officems.taqnin.document_import;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportDocumentRepository extends JpaRepository<ImportDocument, Long> {
    public List<ImportDocument> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    public List<ImportDocument> findByDocument_idAndDeletedFalse(Long id);
}
