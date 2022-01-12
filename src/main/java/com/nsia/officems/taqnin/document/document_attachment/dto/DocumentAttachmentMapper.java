package com.nsia.officems.taqnin.document.document_attachment.dto;

import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.document.document_attachment.DocumentAttachment;

public class DocumentAttachmentMapper {
    public static DocumentAttachment MapAgendaTopicCommentDto(DocumentAttachment documentAttachment,
            DocumentAttachmentDto dto, DocumentService documentService) {
        try {
            documentAttachment.setFileName(dto.getFileName());
            documentAttachment
                    .setDocument(dto.getDocumentId() == null ? null : documentService.findById(dto.getDocumentId()));
            return documentAttachment;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
