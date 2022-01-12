package com.nsia.officems.odf.odf_presenters.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.odf.odf_agenda.OdfAgendaService;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopicService;
import com.nsia.officems.odf.odf_presenters.OdfPresenter;

public class OdfPresenterMapper {
    public static OdfPresenter MapPresentDto(OdfPresenter presenter, Long agendaTopicId, String presenterName,
            OdfAgendaTopicService odfAgendaTopicService) {
        try {
            presenter.setName(presenterName);
            presenter.setAgendaTopic(agendaTopicId == null ? null : odfAgendaTopicService.findById(agendaTopicId));
            return presenter;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
