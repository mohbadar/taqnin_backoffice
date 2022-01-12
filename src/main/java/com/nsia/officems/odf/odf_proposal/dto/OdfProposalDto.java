package com.nsia.officems.odf.odf_proposal.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdfProposalDto {
    private Long id;

    private String secretLevel;
    private String proposalNumber;
    private String proposalDate;
    private String summary;
    private String objective;
    private Boolean previousLink;
    private String previousDecision;
    private String lawArticle;
    private Boolean subAuthorityShare;
    private String authorityImplementation;
    private Boolean governmentBudget;
    private Boolean forignBuget;
    private Boolean internationalAgree;
    private Long authorityAgreement;
    private String ministriesImplementation;
    private String implementationScope;
    private String proposalPreResult;
    private Integer papers;
    private String progressBasedOnLaw;
    private Boolean nextFollowUp;
    private String reason;
    private String proposalContent;
    private List<Long> entity;
    private List<Long> subEntity;
    private Long shura;
}
