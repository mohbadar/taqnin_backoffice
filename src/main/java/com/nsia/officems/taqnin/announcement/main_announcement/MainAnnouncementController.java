package com.nsia.officems.taqnin.announcement.main_announcement;
import com.google.gson.Gson;
import org.springframework.http.MediaType;

import com.nsia.officems.file.FileDownloadUtil;
import com.nsia.officems.taqnin.announcement.AnnouncementService;
import com.nsia.officems.taqnin.announcement.main_announcement.dto.CreateMainAnnouncementDto;
import com.nsia.officems.taqnin.announcement.main_announcement.dto.MainAnnouncementDto;
import com.nsia.officems.taqnin.announcement.main_announcement.dto.MainAnnouncementMapper;
import com.nsia.officems.taqnin.comment.dto.DocCommentDto;
import com.nsia.officems.taqnin.comment.dto.DocCommentMapper;
import com.nsia.officems.taqnin.document.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/taqnin/announcement/main-announcement")
public class MainAnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_VIEW')")
    @GetMapping()
    public MainAnnouncementDto findMainAnnouncement(){
        MainAnnouncement model = announcementService.findMainAnnouncement();
        MainAnnouncementDto dto = new MainAnnouncementDto();
        if (model != null){
            MainAnnouncementMapper.MapMainAnnouncementDto(dto, model, announcementService);
            return dto;
        }
        return null;
    }

    @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_CREATE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MainAnnouncement> create(@RequestParam(name = "avatar", required = true) MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) throws Exception
    {
        Gson g = new Gson();
        CreateMainAnnouncementDto dto = g.fromJson(data, CreateMainAnnouncementDto.class);
        return ResponseEntity.ok(announcementService.createMainAnnouncement(dto,file));
    }

    @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_EDIT')")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean update(@RequestParam(name = "avatar", required = true) MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) {
        Gson g = new Gson();
        CreateMainAnnouncementDto dto = g.fromJson(data, CreateMainAnnouncementDto.class);
        return announcementService.updateMainAnnouncement(dto, file);
    }

    // @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_VIEW')")
    @GetMapping(value = "/main-announcement-downloadFile")
    public void downloadMainAnnouncementAttachment(HttpServletResponse response) throws Exception {
        File file = announcementService.downloadMainAnnouncementAttachment();
        fileDownloadUtil.fileDownload(file, response);
    }

}
