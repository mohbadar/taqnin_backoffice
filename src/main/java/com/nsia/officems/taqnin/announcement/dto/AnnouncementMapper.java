package com.nsia.officems.taqnin.announcement.dto;
import java.util.Date;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.taqnin.announcement.Announcement;

import org.joda.time.LocalDateTime;


public class AnnouncementMapper {
    public  static Announcement MapAnnouncementDto(Announcement announcement, AnnouncementDto dto, UserService userService){
      announcement.setAttachment(dto.getAttachment());
      announcement.setPublisher(dto.getPublisher());
      announcement.setTitle(dto.getTitle());
      announcement.setBody(dto.getBody());
      announcement.setCreatedBy(userService.getLoggedInUser().getName().toString());
      announcement.setUpdatedBy(userService.getLoggedInUser().getName().toString());
        return announcement;
    }

    public static AnnouncementListDto MapAnnouncementListDto(Announcement announcement, AnnouncementListDto dto){
      DateTimeChange changeDate = new DateTimeChange();
        try {
            dto.setId(announcement.getId());
            dto.setTitle(announcement.getTitle());
            dto.setPublisher(announcement.getPublisher());
            dto.setBody(announcement.getBody());
            dto.setCreatedAt(announcement.getCreatedAt() == null ? null 
            : changeDate.convertGregorianDateToPersianDate(java.sql.Timestamp.valueOf(announcement.getCreatedAt())));
            dto.setCreatedAtTime(announcement.getUpdatedAt().getHour() +":"+announcement.getUpdatedAt().getMinute()+":"+announcement.getUpdatedAt().getSecond());
            dto.setCreatedBy(announcement.getCreatedBy());
            dto.setAttachment(announcement.getAttachment());
            return dto;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }

}
