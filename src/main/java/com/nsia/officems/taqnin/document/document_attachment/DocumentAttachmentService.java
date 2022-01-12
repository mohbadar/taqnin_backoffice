package com.nsia.officems.taqnin.document.document_attachment;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentAttachmentService {
    public DocumentAttachment findById(Long id);

    public DocumentAttachment create(Long documentId, String fileName, String attachmentName);

    public List<DocumentAttachment> findByDocumentId(Long id);

    public String saveAttachment(String uploadDirectory, MultipartFile file);

    public Boolean deleteDocumentAttachmentById(Long documentAttachmentId);
    public File downloadAttachment(Long attachmentId, Long documentId) throws Exception;
}
