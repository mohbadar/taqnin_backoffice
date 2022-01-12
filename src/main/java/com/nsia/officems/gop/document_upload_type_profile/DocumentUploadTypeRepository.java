package com.nsia.officems.gop.document_upload_type_profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentUploadTypeRepository extends JpaRepository<DocumentUploadType, Long> {
    public List<DocumentUploadType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    @Query(value = "SELECT b.* from public.document_upload_type b WHERE b.id not in (select document_type_id from public.document_upload where profile_id  =:proId)", nativeQuery = true)
    public List<DocumentUploadType> documentTypeByProfile(@Param("proId") long proId);
}

