package com.nsia.officems.taqnin.document_import.dto;

import lombok.Data;

@Data
public class ImportDocumentDto {
    private String import_number;
    private String import_date;
    private String body;
    private Long document_id;
}
