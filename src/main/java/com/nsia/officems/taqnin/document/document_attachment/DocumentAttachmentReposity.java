package com.nsia.officems.taqnin.document.document_attachment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentAttachmentReposity extends JpaRepository<DocumentAttachment,Long>{
    public List<DocumentAttachment> findByDocument_idAndDeletedFalse(Long id);
}
