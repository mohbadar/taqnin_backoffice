package com.nsia.officems.odf.odf_order.dto;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdfOrderDto {
    private String orderNumber;
    private String orderDate;
    private String summary;
    private String implementationEndDate;
    private List<Long> implementingDepartmentsIds;
    private List<Long> assistingDepartmentsIds;
    private Long subjectId;
}
