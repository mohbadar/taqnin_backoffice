package com.nsia.officems.taqnin.document.dto;

import lombok.Data;

@Data
public class DocumentDto {
    String title;
    boolean approved;
    String number;
    String body;
    Long workflow_id;
    Long decision_id;
    String date;
    boolean receivedByMoJ;
    String remarks;
    Long doctype_id;
    String workflowAssignDate;
    boolean isCompleted;
    String processStartDate;
    String processEndDate;
}
