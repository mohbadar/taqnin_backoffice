package com.nsia.officems.odf.odf_agenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopic;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OdfAgenda")
@Table(name = "odf_agenda")
public class OdfAgenda extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_agenda_tbl_generator")
    @SequenceGenerator(name = "odf_agenda_tbl_generator", sequenceName = "odf_agenda_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String agendaNo;

    @Column
    private String meetingLocation;

    @Column
    private Long meetingDuration;

    @Column
    private String meetingDurationType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date meetingDate;

    @Column
    private String meetingTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date meetingStartDate;

    @Column
    private String meetingStartTime;

    @Column
    private String status;

}
