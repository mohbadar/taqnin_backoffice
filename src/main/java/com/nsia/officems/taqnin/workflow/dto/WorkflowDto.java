package com.nsia.officems.taqnin.workflow.dto;

import lombok.Data;


@Data
public class WorkflowDto {
    private String namePs;
    private String nameDr;
    private String nameEn;
    private Integer serialNo;
    private Integer processDays;
    // private List<Long> stepsIds;
}
