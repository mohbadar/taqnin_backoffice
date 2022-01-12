package com.nsia.officems.odf.odf_presenters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.odf.odf_agenda.OdfAgenda;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odf_agenda_topic_presenter")
@Entity(name = "OdfAgendaTopicPresenter")
public class OdfPresenter extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_agenda_topic_presenter_tbl_generator")
    @SequenceGenerator(name = "odf_agenda_topic_presenter_tbl_generator", sequenceName = "odf_agenda_topic_presenter_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "odf_agenda_topic_id", nullable = true, referencedColumnName = "id")
    private OdfAgendaTopic agendaTopic;
}
