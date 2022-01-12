package com.nsia.officems.odf.odf_agenda.odf_agenda_topic;

import java.util.List;
import java.util.Map;

import com.nsia.officems.odf.odf_agenda.OdfAgenda;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto.OdfAgendaTopicDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface OdfAgendaTopicService {
    public List<OdfAgendaTopic> findAll();

    public OdfAgendaTopic findById(Long id);

    public OdfAgendaTopic create(OdfAgendaTopic odfAgendaTopic);

    public Boolean delete(Long id);

    public Boolean update(Long id, OdfAgendaTopicDto dto);

    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<OdfAgendaTopic> findbyIdIn(List<Long> ids);

    public List<OdfAgendaTopic> findByAgendaId(Long agendaId);
}
