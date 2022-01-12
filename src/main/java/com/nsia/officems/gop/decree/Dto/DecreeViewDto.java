package com.nsia.officems.gop.decree.Dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nsia.officems.gop.decree_individual.domain.DecreeIndividualDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecreeViewDto {
    private Long id;
    private Map<String, Object> documentType;
    private String decreeNumber;
    private String decreeDate;
    private String subject;
    private String attachment;
    private ProposalDecreeDTO proposal;
    private Map<String, Object> ministry;
    private Map<String, Object> authority;
    private Map<String, Object> commission;
    private List<DecreeIndividualDTO> profiles;

}
