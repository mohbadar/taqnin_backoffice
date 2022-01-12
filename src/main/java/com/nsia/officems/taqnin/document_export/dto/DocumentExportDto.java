package com.nsia.officems.taqnin.document_export.dto;

import lombok.Data;

@Data
public class DocumentExportDto {
    private String export_number;
    private String export_date;
    private Long document_id;
    private String body;
}
