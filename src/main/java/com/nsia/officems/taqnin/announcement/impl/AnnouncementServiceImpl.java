package com.nsia.officems.taqnin.announcement.impl;


import com.nsia.officems._admin.country.Country;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.file.FileUploadUtil;
import com.nsia.officems.taqnin.announcement.Announcement;
import com.nsia.officems.taqnin.announcement.AnnouncementRepository;
import com.nsia.officems.taqnin.announcement.AnnouncementService;
import com.nsia.officems.taqnin.announcement.dto.AnnouncementDto;
import com.nsia.officems.taqnin.announcement.dto.AnnouncementListDto;
import com.nsia.officems.taqnin.announcement.dto.AnnouncementMapper;
import com.nsia.officems.taqnin.announcement.main_announcement.MainAnnouncement;
import com.nsia.officems.taqnin.announcement.main_announcement.MainAnncouncementRepository;
import com.nsia.officems.taqnin.announcement.main_announcement.dto.CreateMainAnnouncementDto;
import com.nsia.officems.taqnin.announcement.main_announcement.dto.MainAnnouncementDto;
import com.nsia.officems.taqnin.announcement.main_announcement.dto.MainAnnouncementMapper;
import com.nsia.officems.taqnin.document.dto.DocumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Value("${app.upload.taqnin.announcement}")
    private String uploadDir;
    @Autowired
    private AnnouncementRepository repo;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private MainAnncouncementRepository mainAnncouncementRepository;


    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = " data.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.announcement data", null, joinClause, whereClause, groupByClause, input);
    }

    @Override
    public List<Announcement> findAll() {
        return repo.findAllOrderByIdDesc();
    }

    @Override
    public Announcement findById(Long id) {
        Optional<Announcement> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Announcement create(AnnouncementDto dto, MultipartFile file) {
        Announcement newAnnouncement = new Announcement();
        Announcement announcement = AnnouncementMapper.MapAnnouncementDto(newAnnouncement,dto, userService);
        Announcement announcement1 =  repo.save(announcement);
        String fileName = fileUploadUtil.saveAttachment(file, uploadDir, announcement1.getId().toString(), "taqnin_announcement");
        announcement1.setAttachment(fileName);
        return repo.save(announcement1);
    }

    @Override
    public Boolean update(Long id, AnnouncementDto dto, MultipartFile file) {
        Optional<Announcement> announcement = repo.findById(id);
        if (announcement.isPresent()) {
            Announcement announcementToUpdate = announcement.get();
            AnnouncementMapper.MapAnnouncementDto(announcementToUpdate, dto, userService);
            repo.save(announcementToUpdate);
            String fileName = fileUploadUtil.saveAttachment(file, uploadDir, announcementToUpdate.getId().toString(), "taqnin_announcement");
            announcementToUpdate.setAttachment(fileName);
            repo.save(announcementToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public List<Announcement> findbyIdIn(List<Long> ids) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Announcement> pOptional = repo.findById(id);
        if (pOptional.isPresent()) {
            Announcement announce = pOptional.get();
            announce.setDeleted(true);
            announce.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(announce);
            return true;
        }

        return false;
    }

    @Override
    public MainAnnouncement findMainAnnouncement(){
        MainAnnouncement model = mainAnncouncementRepository.getMainAnnouncement();
        if(model != null){
            return model;
        }
        return null;
    }

    @Override
    public MainAnnouncement createMainAnnouncement(CreateMainAnnouncementDto dto, MultipartFile file) {
        MainAnnouncement newAnnouncement = new MainAnnouncement();
        MainAnnouncement announcement = MainAnnouncementMapper.MapCreateMainAnnouncementDto(newAnnouncement,dto, userService);
        MainAnnouncement announcement1 =  mainAnncouncementRepository.save(announcement);
        String fileName = fileUploadUtil.saveAttachment(file, uploadDir, announcement1.getId().toString(), "taqnin_main_announcement");
        announcement1.setAttachment(fileName);
        return mainAnncouncementRepository.save(announcement1);
    }

    @Override
    public Boolean updateMainAnnouncement(CreateMainAnnouncementDto dto, MultipartFile file) {
        MainAnnouncement announcement = mainAnncouncementRepository.getMainAnnouncement();
        if (announcement != null) {
            MainAnnouncement announcementToUpdate = announcement;
            MainAnnouncementMapper.MapCreateMainAnnouncementDto(announcementToUpdate, dto, userService);
            MainAnnouncement announcement1 =  mainAnncouncementRepository.save(announcement);
            String fileName = fileUploadUtil.saveAttachment(file, uploadDir, announcement1.getId().toString(), "taqnin_main_announcement");
            announcement1.setAttachment(fileName);
            mainAnncouncementRepository.save(announcement1);
            return true;
        }
        return false;
    }

    public Integer getAnnouncementsCount(){
        Integer count = repo.getCount();
        if(count == null){
            return 0;
        }else{
            return count;
        }
    }

    public List<AnnouncementListDto> getFilteredAnnouncements(String query) {
        List<AnnouncementListDto> list = new ArrayList<AnnouncementListDto>();
        List<Announcement> announcements = repo.findAllFilteredAnnouncements(query);
        announcements.forEach((item) -> {
            AnnouncementListDto announcementDto = new AnnouncementListDto();
            AnnouncementMapper.MapAnnouncementListDto(item, announcementDto);
            list.add(announcementDto);
        });

        return list;
    }

    public List<AnnouncementListDto> getLastTenRecords(){
        List<AnnouncementListDto> list = new ArrayList<AnnouncementListDto>();
        List<Announcement> announcements = repo.findLastTenRecords();
        announcements.forEach((item) -> {
            AnnouncementListDto announcementDto = new AnnouncementListDto();
            AnnouncementMapper.MapAnnouncementListDto(item, announcementDto);
            list.add(announcementDto);
        });

        return list;
    }

    @Override
    public File downloadMainAnnouncementAttachment() throws Exception {
        MainAnnouncement documentUpload = mainAnncouncementRepository.getMainAnnouncement();
        if(documentUpload != null)        
       {
        String fileName = documentUpload.getAttachment();
        String saveDirectory = uploadDir + "/" + documentUpload.getId() + "/" + fileName;
        if (new File(saveDirectory).exists()) {
            return new File(saveDirectory);
        }
       }

        return null;
    }

    @Override
    public File downloadAttachment(Long id) throws Exception {
        Optional<Announcement> document = repo.findById(id);
        if (document.isPresent()) {

            String fileName =  document.get().getAttachment();

            String saveDirectory = uploadDir + "/" + id + "/" + fileName;
            if (new File(saveDirectory).exists()) {
                return new File(saveDirectory);
            }
        }

        return null;
    }

}
