package com.nsia.officems.taqnin.dochistory.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DochistoryDto {
    private String date;
    private String number;
    private Long document_Id;
    private Long workflow_id;
    private Long decision_id;
    private String remarks;
}
