package com.nsia.officems.gop.proposal.Dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.proposal.Proposal;

public class ProposalDtoMapper {
    public static Proposal MapProposalDto(ProposalDto dto, Proposal proposal, MinistryService ministryService,
            AuthorityService authorityService, CommissionService commissionService) {
         DateTimeChange changeDate = new DateTimeChange(); 

        proposal.setActive(true);
        proposal.setDocumentType(dto.getDocumentType());
        proposal.setProposalNumber(dto.getProposalNumber());
        proposal.setProposalDate(dto.getProposalDate()== null? null: changeDate.convertPersianDateToGregorianDate(dto.getProposalDate()));
        proposal.setSubject(dto.getSubject());
        proposal.setIncomingNumber(dto.getIncomingNumber());
        proposal.setIncomingDate(dto.getIncomingDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getIncomingDate()));
        proposal.setSuggestionStatus(dto.getSuggestionStatus());
        proposal.setSuggestionType(dto.getSuggestionType());
        proposal.setContent(dto.getContent());
        proposal.setDetails(dto.getDetails());
        proposal.setNoteDate(dto.getNoteDate()== null? null: changeDate.convertPersianDateToGregorianDate(dto.getNoteDate()));
        proposal.setNoteNumber(dto.getNoteNumber());
        proposal.setRemarks(dto.getRemarks());

        proposal.setMorsalCommission(
                dto.getCommission() != null ? commissionService.findById(dto.getCommission().getId()) : null);
        proposal.setMorsalAuthority(
                dto.getAuthority() != null ? authorityService.findById(dto.getAuthority().getId()) : null);
        proposal.setMorsalMinistry(
                dto.getMinistry() != null ? ministryService.findById(dto.getMinistry().getId()) : null);
        if (!dto.getAttachment().equals(""))
            proposal.setAttachment(dto.getAttachment());
        return proposal;
    }

}
