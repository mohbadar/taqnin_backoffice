package com.nsia.officems.gop.proposal.Dto;

import java.util.List;

import com.nsia.officems.gop.proposalIndividual.ProposalIndividual;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProposalDto {
    private Long documentType;
    private String incomingNumber;
    private String incomingDate;
    private String proposalNumber;
    private String proposalDate;
    private String decreeNumber;
    private String decreeDate;
    private String relevantStaff;
    private String copyTo;
    private String thirdCopyDate;
    private String thirdCopyNumber;
    private String expert;
    private Long morsalMinistry;
    private Long morsalAuthority;
    private Long morsalCommission;
    private Long morsalAlaihaiMinistry;
    private Long morsalAlaihaiAuthority;
    private Long morsalAlaihaiCommission;
    private Long suggestionStatus;
    private String noteNumber;
    private String noteDate;
    private String remarks;
    private String details;
    private boolean tashkeel;
    private List<CreateProposalIndividualDto> profiles;
}
