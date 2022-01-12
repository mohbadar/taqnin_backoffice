package com.nsia.officems.gop.decree.Dto;

import java.util.List;
import java.util.Map;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.decree.Decree;
import com.nsia.officems.gop.decree_document_type.DecreeDocumentTypeService;
import com.nsia.officems.gop.decree_individual.domain.DecreeIndividualDTO;
import com.nsia.officems.gop.language.LanguageService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.proposal.Proposal;

public class DecreeDtoMapper {
    public static Decree mapDecreeDTO(Decree decree, DecreeDto dto, LanguageService languageService,
            MinistryService ministryService, AuthorityService authorityService, CommissionService commissionService,
            DecreeDocumentTypeService decreeDocumentTypeService, Proposal proposal) {
        DateTimeChange changeDate = new DateTimeChange();
        try {
            if (dto.getId() != null) {
                decree.setId(dto.getId());
            }
            decree.setActive(true);
            decree.setDecreeDocumentType(
                    dto.getDocumentType() == null ? null : decreeDocumentTypeService.findById(dto.getDocumentType()));
            decree.setDecreeNumber(dto.getDecreeNumber());
            decree.setDecreeDate(dto.getDecreeDate() == null ? null
                    : changeDate.convertPersianDateToGregorianDate(dto.getDecreeDate()));
            decree.setMinistry(dto.getMinistry() == null ? null : ministryService.findById(dto.getMinistry()));
            decree.setAuthority(dto.getAuthority() == null ? null : authorityService.findById(dto.getAuthority()));
            decree.setCommission(dto.getCommission() == null ? null : commissionService.findById(dto.getCommission()));
            decree.setSubject(dto.getSubject());
            decree.setAttachment(dto.getAttachment());
            decree.setProposal(proposal);
            return decree;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping profile");
            return null;
        }
    }

    public static DecreeViewDto mapDecreeViewDTO(Decree decree, ProposalDecreeDTO proposalDecreeDTO,
            Map<String, Object> ministry, Map<String, Object> authority, Map<String, Object> commission,
            Map<String, Object> decreeDocumentType, List<DecreeIndividualDTO> profiles) {
        DecreeViewDto d = new DecreeViewDto();
        DateTimeChange changeDate = new DateTimeChange();

        try {
            d.setId(decree.getId());
            d.setDocumentType(decreeDocumentType);
            d.setDecreeNumber(decree.getDecreeNumber());
            d.setDecreeDate(decree.getDecreeDate() == null ? null
                    : changeDate.convertGregorianDateToPersianDateWithDash(decree.getDecreeDate()));
            d.setProposal(proposalDecreeDTO);
            d.setMinistry(ministry);
            d.setAuthority(authority);
            d.setCommission(commission);
            d.setProfiles(profiles);
            d.setSubject(decree.getSubject());
            d.setAttachment(decree.getAttachment());

            return d;

        } catch (Exception e) {
            System.out.println("Excaption in mappe class");
        }

        return null;
    }

    public static ProposalDecreeDTO mapProposalDecreeDTO(Proposal proposal, Map<String, Object> suggestionStatus,
            Map<String, Object> suggestionType, Map<String, Object> documentType, Map<String, Object> ministry,
            Map<String, Object> authority, Map<String, Object> commission) {
        try {
            ProposalDecreeDTO proposalDecreeDTO = new ProposalDecreeDTO();
            proposalDecreeDTO.setProposalNumber(proposal.getProposalNumber());
            proposalDecreeDTO.setProposalDate(proposal.getProposalDate().toString());

            proposalDecreeDTO.setIncomingNumber(proposal.getIncomingNumber());
            proposalDecreeDTO.setIncomingDate(proposal.getIncomingDate().toString());
            proposalDecreeDTO.setDocumentType(documentType);
            proposalDecreeDTO.setSuggestionStatus(suggestionStatus);
            proposalDecreeDTO.setSuggestionType(suggestionType);
            proposalDecreeDTO.setAuthority(authority);
            proposalDecreeDTO.setMinistry(ministry);
            proposalDecreeDTO.setCommission(commission);
            return proposalDecreeDTO;
        } catch (Exception e) {
            System.out.println("Excaption in mappe class");
        }
        return null;

    }

}
