package com.nsia.officems.gop.proposal.Dto;

import java.io.File;
import java.util.Date;

import com.nsia.officems._admin.authority.Authority;
import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems.gop.documentType.DocumentType;
import com.nsia.officems._admin.ministry.Ministry;
import com.nsia.officems.gop.suggestionStatus.SuggestionStatus;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubject;
import com.nsia.officems.gop.suggestionType.SuggestionType;

import org.dom4j.Text;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalDto {
    private Long id;
    private DocumentType documentType;
    private String proposalNumber;
    private String proposalDate;
    private Boolean subject;
    private String incomingNumber;
    private String incomingDate;
    private String noteNumber;
    private String noteDate;
    private String remarks;
    private String details;
    private SuggestionStatus suggestionStatus;
    private SuggestionType suggestionType;
    private String content;
    private String attachment;
    private Commission commission;
    private Authority authority;
    private Ministry ministry;

    
}

