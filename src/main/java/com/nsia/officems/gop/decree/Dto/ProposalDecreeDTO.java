package com.nsia.officems.gop.decree.Dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalDecreeDTO {
    String proposalNumber;

    String proposalDate;

    // String organization;

    String incomingNumber;

    String incomingDate;

    Map<String, Object> suggestionStatus;

    Map<String, Object> suggestionType;

    Map<String, Object> documentType;

    private Map<String, Object> ministry;
    private Map<String, Object> authority;
    private Map<String, Object> commission;
}
