package com.nsia.officems.taqnin.announcement.main_announcement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainAnnouncementDto {
    private Long id;
    private String title; 
    private String publisher;
    private String details;
    private String created_date;
    private String created_time;
    private String created_by;
    private String attachment;
}
