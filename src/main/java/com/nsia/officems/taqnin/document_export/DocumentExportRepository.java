package com.nsia.officems.taqnin.document_export;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentExportRepository extends JpaRepository<DocumentExport, Long> {
    public List<DocumentExport> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    public List<DocumentExport> findByDocument_idAndDeletedFalse(Long id);
}
