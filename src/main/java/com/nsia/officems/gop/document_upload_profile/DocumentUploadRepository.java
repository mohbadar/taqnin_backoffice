package com.nsia.officems.gop.document_upload_profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentUploadRepository extends JpaRepository<DocumentUpload, Long> {
    public List<DocumentUpload> findByProfile_idOrderByCreatedAtDesc(Long id);

}
