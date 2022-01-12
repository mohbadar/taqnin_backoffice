package com.nsia.officems.taqnin.document_import.dto;
import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.document_import.ImportDocument;



public class ImportDocumentMapper {

    public static ImportDocument MapImportDocumentDto(ImportDocument importDocument,
            ImportDocumentDto dto, DocumentService documentService) {
        try {
            importDocument.setImport_number(dto.getImport_number());;
            importDocument.setImport_date(dto.getImport_date());
            importDocument.setBody(dto.getBody());
            importDocument.setDocument(dto.getDocument_id()==null?null:documentService.findById(dto.getDocument_id()));
            return importDocument;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
