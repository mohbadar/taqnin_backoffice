package com.nsia.officems.taqnin.document_export.dto;
import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.document_export.DocumentExport;

public class DocumentExportMapper {
    public static DocumentExport MapDocCommentDto(DocumentExport documentExport,
            DocumentExportDto dto, DocumentService documentService) {
        try {
            documentExport.setExport_number(dto.getExport_number());;
            documentExport.setExport_date(dto.getExport_date());
            documentExport.setBody(dto.getBody());
            documentExport.setDocument(dto.getDocument_id() == null ? null : documentService.findById(dto.getDocument_id()));
            return documentExport;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
