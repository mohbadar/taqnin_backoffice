package com.nsia.officems.taqnin.announcement.dto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnnouncementListDto {
private Long id;
private String title;
private String publisher;
private String body;
private String attachment;
private String createdBy;
private String createdAt;
private String createdAtTime;
}
