package com.nsia.officems.odf.odf_proposal_followup.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;
import com.nsia.officems.odf.odf_proposal_followup.OdfProposalFollow;

public class OdfProposalFollowMapper {
    public static OdfProposalFollow MapFollowDto(OdfProposalFollow follow,OdfProposalFollowDto dto, OdfProposalService service){ 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            follow.setActive(true);
            follow.setDate(dto.getDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getDate()));
            follow.setType(dto.getType() == null? null: dto.getType());
            follow.setSummary(dto.getSummary() == null? null: dto.getSummary());
            follow.setProposal(dto.getProposal() == null? null: service.findById(dto.getProposal()));
            return follow;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    } 
}
