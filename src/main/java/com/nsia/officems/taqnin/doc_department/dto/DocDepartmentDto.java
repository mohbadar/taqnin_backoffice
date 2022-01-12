package com.nsia.officems.taqnin.doc_department.dto;

import lombok.Data;

@Data
public class DocDepartmentDto {
    private String receiveDate;
    private String description;
    private Long department_id;
    private Long user_id;
    private Long document_id;
}
