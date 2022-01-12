package com.nsia.officems.odf.odf_agenda.odf_agenda_topic;

import java.util.List;

import com.nsia.officems.odf.odf_agenda.OdfAgenda;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfAgendaTopicRepository extends JpaRepository<OdfAgendaTopic, Long> {
    public List<OdfAgendaTopic> findByIdIn(List<Long> agendaTopics);

    public List<OdfAgendaTopic> findByAgenda_id(Long id);
}
