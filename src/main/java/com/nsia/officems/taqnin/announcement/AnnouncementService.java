package com.nsia.officems.taqnin.announcement;

import com.nsia.officems.taqnin.announcement.dto.AnnouncementDto;
import com.nsia.officems.taqnin.announcement.dto.AnnouncementListDto;
import com.nsia.officems.taqnin.announcement.main_announcement.MainAnnouncement;
import com.nsia.officems.taqnin.announcement.main_announcement.dto.CreateMainAnnouncementDto;
import com.nsia.officems.taqnin.document.Document;
import com.nsia.officems.taqnin.document.dto.DocumentDto;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface AnnouncementService {
    public List<Announcement> findAll();

    public Announcement findById(Long id);

    Announcement create(AnnouncementDto dto, MultipartFile file);

    Object getList(DataTablesInput input, Map<String, String> filters);

    public Boolean delete(Long id);

    public Boolean update(Long id, AnnouncementDto dto, MultipartFile file);

    public List<Announcement> findbyIdIn(List<Long> ids);

    public MainAnnouncement findMainAnnouncement();
    public MainAnnouncement createMainAnnouncement(CreateMainAnnouncementDto dto, MultipartFile file);
    public Boolean updateMainAnnouncement(CreateMainAnnouncementDto dto,  MultipartFile file);
    Integer getAnnouncementsCount();
    public List<AnnouncementListDto> getFilteredAnnouncements(String query);
    public List<AnnouncementListDto> getLastTenRecords();
    public File downloadMainAnnouncementAttachment() throws Exception;
    public File downloadAttachment(Long id) throws Exception;
}
