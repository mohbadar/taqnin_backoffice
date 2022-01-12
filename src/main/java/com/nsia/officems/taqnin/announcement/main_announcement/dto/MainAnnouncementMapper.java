package com.nsia.officems.taqnin.announcement.main_announcement.dto;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.taqnin.announcement.AnnouncementService;
import com.nsia.officems.taqnin.announcement.main_announcement.MainAnnouncement;

public class MainAnnouncementMapper {
    public static MainAnnouncementDto MapMainAnnouncementDto(MainAnnouncementDto dto, MainAnnouncement model, AnnouncementService announcementService)
    {
        DateTimeChange changeDate = new DateTimeChange();
        try {
            dto.setId(model.getId());
            dto.setTitle(model.getTitle());
            dto.setPublisher(model.getPublisher());
            dto.setDetails(model.getDetails());
            dto.setCreated_date(model.getUpdatedAt() == null ? null 
            : changeDate.convertGregorianDateToPersianDate(java.sql.Timestamp.valueOf(model.getCreatedAt())));
            dto.setCreated_time(model.getUpdatedAt().getHour() +":"+model.getUpdatedAt().getMinute()+":"+model.getUpdatedAt().getSecond());
            dto.setCreated_by(model.getCreatedBy());
            dto.setAttachment(model.getAttachment());
            return dto;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }

    public  static MainAnnouncement MapCreateMainAnnouncementDto(MainAnnouncement announcement, CreateMainAnnouncementDto dto, UserService userService){
        announcement.setTitle(dto.getTitle());
        announcement.setPublisher(dto.getPublisher());
        announcement.setDetails(dto.getDetails());
        announcement.setAttachment(dto.getAttachment());
        announcement.setCreatedBy(userService.getLoggedInUser().getName().toString());
        announcement.setUpdatedBy(userService.getLoggedInUser().getName().toString());
        return announcement;
      }

    
}
