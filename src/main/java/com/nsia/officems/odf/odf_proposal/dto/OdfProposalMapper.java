package com.nsia.officems.odf.odf_proposal.dto;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.shura.ShuraService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;
import com.nsia.officems.odf.odf_proposal.OdfProposal;

import org.hibernate.criterion.Example.PropertySelector;

public class OdfProposalMapper {
    public static OdfProposal MapProposalDto(OdfProposal proposal,OdfProposalDto dto, OdfAuthorityAgreementService service,
     DepartmentService departmentService, ShuraService shuraService ){ 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            proposal.setActive(true);
            proposal.setSecretLevel(dto.getSecretLevel());
            proposal.setProposalNumber(dto.getProposalNumber());
            proposal.setProposalDate(dto.getProposalDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getProposalDate()));
            proposal.setSummary(dto.getSummary());
            proposal.setObjective(dto.getObjective());
            proposal.setPreviousLink(dto.getPreviousLink());
            proposal.setPreviousDecision(dto.getPreviousDecision());
            proposal.setLawArticle(dto.getLawArticle());
            proposal.setSubAuthorityShare(dto.getSubAuthorityShare());
            proposal.setAuthorityImplementation(dto.getAuthorityImplementation());
            proposal.setGovernmentBudget(dto.getGovernmentBudget());
            proposal.setForignBuget(dto.getForignBuget());
            proposal.setInternationalAgree(dto.getGovernmentBudget());
            proposal.setAuthorityAgreement(dto.getAuthorityAgreement() == null? null: service.findById(dto.getAuthorityAgreement()));
            proposal.setMinistriesImplementation(dto.getMinistriesImplementation());
            proposal.setImplementationScope(dto.getImplementationScope());
            proposal.setProposalPreResult(dto.getProposalPreResult());
            proposal.setPapers(dto.getPapers());
            proposal.setProgressBasedOnLaw(dto.getProgressBasedOnLaw());
            proposal.setNextFollowUp(dto.getNextFollowUp());
            proposal.setReason(dto.getReason());
            proposal.setProposalContent(dto.getProposalContent());
            //proposal.setEntity(dto.getEntity() == null? null: departmentService.findbyIdIn(dto.getEntity()));
            //proposal.setSubEntity(dto.getSubEntity() == null? null: departmentService.findbyIdIn(dto.getSubEntity()));
            proposal.setShura(dto.getShura() == null? null: shuraService.findById(dto.getShura()));

            return proposal;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
