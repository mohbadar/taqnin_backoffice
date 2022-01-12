package com.nsia.officems.odf.odf_proposal_followup.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdfProposalFollowDto {
    private String date;
    private String type;
    private String summary;
    private Long proposal;
}
