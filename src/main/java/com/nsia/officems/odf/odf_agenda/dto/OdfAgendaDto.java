package com.nsia.officems.odf.odf_agenda.dto;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto.OdfAgendaTopicDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdfAgendaDto {
    private String agendaNo;
    private String meetingLocation;
    private Long meetingDuration;
    private String meetingDurationType;
    private String meetingDate;
    private String meetingTime;
    private String meetingStartDate;
    private String meetingStartTime;

    private List<OdfAgendaTopicDto> agendaTopics;
}
