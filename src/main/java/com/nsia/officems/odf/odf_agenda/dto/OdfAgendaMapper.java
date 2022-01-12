package com.nsia.officems.odf.odf_agenda.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;
import com.nsia.officems.odf.odf_presenters.OdfPresenterService;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;
import com.nsia.officems.odf.odf_agenda.OdfAgenda;
import com.nsia.officems.odf.odf_agenda.OdfAgendaService;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopic;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopicService;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto.OdfAgendaTopicDto;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto.OdfAgendaTopicMapper;

import org.hibernate.criterion.Example.PropertySelector;

public class OdfAgendaMapper {
    public static OdfAgenda MapAgendaDto(OdfAgenda agenda, OdfAgendaDto dto, OdfAgendaService agendaService,
            OdfAgendaTopicService agendaTopicService, OdfProposalService odfProposalService,
            OdfPresenterService odfPresenterService) {
        DateTimeChange changeDate = new DateTimeChange();

        try {
            agenda.setAgendaNo(dto.getAgendaNo());
            agenda.setMeetingLocation(dto.getMeetingLocation());
            agenda.setMeetingDuration(dto.getMeetingDuration());
            agenda.setMeetingDurationType(dto.getMeetingDurationType());
            agenda.setMeetingDate(dto.getMeetingDate() == null ? null
                    : changeDate.convertPersianDateToGregorianDate(dto.getMeetingDate()));
            agenda.setMeetingTime(dto.getMeetingTime());
            agenda.setMeetingStartDate(dto.getMeetingStartDate() == null ? null
                    : changeDate.convertPersianDateToGregorianDate(dto.getMeetingStartDate()));
            agenda.setMeetingStartTime(dto.getMeetingStartTime());

            return agenda;

        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
