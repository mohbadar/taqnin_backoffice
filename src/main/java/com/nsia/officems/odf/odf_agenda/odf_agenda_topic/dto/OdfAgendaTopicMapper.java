package com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.odf.odf_agenda.OdfAgenda;
import com.nsia.officems.odf.odf_agenda.OdfAgendaService;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopic;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;
import com.nsia.officems.odf.odf_presenters.OdfPresenter;
import com.nsia.officems.odf.odf_presenters.OdfPresenterService;
import com.nsia.officems.odf.odf_presenters.dto.OdfPresenterMapper;
import com.nsia.officems.odf.odf_proposal.OdfProposal;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;
import com.nsia.officems.odf.odf_proposal.dto.OdfProposalDto;

import org.hibernate.criterion.Example.PropertySelector;

public class OdfAgendaTopicMapper {
    public static OdfAgendaTopic MapAgendaTopicDto(OdfAgendaTopic agendaTopic, OdfAgendaTopicDto dto,
            OdfAgendaService odfAgendaService, OdfProposalService proposalService, Long agendaId) {
        try {
            agendaTopic.setSubject(dto.getSubject());
            agendaTopic.setDetails(dto.getDetails());

            agendaTopic.setPresentDuration(dto.getPresentDuration());
            agendaTopic.setPresentDurationType(dto.getPresentDurationType());
            agendaTopic.setDiscussionDuration(dto.getDiscussionDuration());
            agendaTopic.setDiscussionDurationType(dto.getDiscussionDurationType());
            agendaTopic.setInclusionReason(dto.getInclusionReason());
            OdfAgenda agenda = odfAgendaService.findById(agendaId);

            if (agenda != null) {
                agendaTopic.setAgenda(agenda);
            }

            if (dto.getProposals() != null) {
                dto.getProposals().forEach((proposal) -> {
                    OdfProposal getProposal = proposalService.findById(proposal.getId());
                    if (proposal != null) {
                        agendaTopic.setOdfProposal(getProposal);
                    }
                });
            }

            return agendaTopic;

        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }

    }
}
