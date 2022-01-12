package com.nsia.officems.gop.decree.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecreeDto {
    private Long id;
    private Long documentType;
    private String decreeNumber;
    private String decreeDate;
    private String subject;
    private String attachment;
    private Long ministry;
    private Long authority;
    private Long commission;
    private String proposalNo;

}