package com.nsia.officems.taqnin.announcement.main_announcement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMainAnnouncementDto {
    private String title; 
    private String publisher;
    private String details;
    private String attachment;
}
