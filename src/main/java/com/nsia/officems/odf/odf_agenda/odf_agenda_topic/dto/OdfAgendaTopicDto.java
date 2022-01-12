package com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto;

import java.util.List;

import com.nsia.officems.gop.proposal.Dto.ProposalDto;
import com.nsia.officems.odf.odf_proposal.dto.OdfProposalDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdfAgendaTopicDto {
    private String subject;
    private String details;
    private List<String> presenters;
    private String presentDuration;
    private String presentDurationType;
    private String discussionDuration;
    private String discussionDurationType;
    private String inclusionReason;
    private List<ProposalDto> proposals;
}
