package com.nsia.officems.taqnin.announcement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems.file.FileDownloadUtil;
import com.nsia.officems.taqnin.announcement.dto.AnnouncementDto;
import com.nsia.officems.taqnin.announcement.dto.AnnouncementListDto;
import com.nsia.officems.taqnin.announcement.dto.AnnouncementMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/taqnin/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    FileDownloadUtil fileDownloadUtil;


    @PreAuthorize("hasAuthority('ANNOUNCEMENT_ADMIN_LIST')")
    @GetMapping()
    public ArrayList<AnnouncementListDto> findAll() {
        List<Announcement> announcements = announcementService.findAll();
        ArrayList<AnnouncementListDto> list = new ArrayList<AnnouncementListDto>();

        if(announcements.isEmpty()){
            return new ArrayList<AnnouncementListDto>();
        }else{
            announcements.forEach((item) -> {
                AnnouncementListDto dto = new AnnouncementListDto();
                AnnouncementMapper.MapAnnouncementListDto(item, dto);
                list.add(dto);
            });

            return list;
        }
    }

    @PreAuthorize("hasAuthority('ANNOUNCEMENT_ADMIN_LIST')")
    @PostMapping("/list")
    public Object getFilesList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return announcementService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('ANNOUNCEMENT_ADMIN_VIEW')")
    @GetMapping(path = { "/{id}" })
    public AnnouncementListDto findById(@PathVariable("id") Long id) {
        Announcement announcement = announcementService.findById(id);
        AnnouncementListDto dto = new AnnouncementListDto();
        AnnouncementMapper.MapAnnouncementListDto(announcement, dto);
        return dto;
    }

    @PreAuthorize("hasAuthority('ANNOUNCEMENT_ADMIN_EDIT')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean update(@PathVariable("id") Long id, @RequestParam(name = "avatar", required = true) MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) {
        Gson g = new Gson();
        AnnouncementDto dto = g.fromJson(data, AnnouncementDto.class);
        return announcementService.update(id, dto, file);
    }

    @PreAuthorize("hasAuthority('ANNOUNCEMENT_ADMIN_CREATE')")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Announcement> create(@RequestParam(name = "avatar", required = true) MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) throws Exception
    {
        Gson g = new Gson();
        AnnouncementDto dto = g.fromJson(data, AnnouncementDto.class);
        return ResponseEntity.ok(announcementService.create(dto,file));
    }

    @PreAuthorize("hasAuthority('ANNOUNCEMENT_ADMIN_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(announcementService.delete(id));
    }

    // @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_VIEW')")
    @GetMapping(value = "/announcement-count")
    public ResponseEntity<Integer> announcementsCount(){
        return ResponseEntity.ok(announcementService.getAnnouncementsCount());
    }

    // @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_VIEW')")
    @GetMapping("/get-filtered-list/{query}")
    public ResponseEntity<List<AnnouncementListDto>> getFilteredApprovedDocuments(@PathVariable("query") String query) {
        List<AnnouncementListDto> list = announcementService.getFilteredAnnouncements(query);
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAuthority('ANNOUNCEMENT_VIEW')")
    @GetMapping("/get-announcement-details/{id}")
    public Map<String, Object> getAnnouncementDetails(@PathVariable("id") Long id){
        Map<String, Object> data = new HashMap<String, Object>();

        AnnouncementListDto announcement = this.findById(id);
        List<AnnouncementListDto> list = announcementService.getLastTenRecords();

        data.put("announcement", announcement);
        data.put("list", list);

        return data;
    }

    // @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_VIEW')")
    @GetMapping(value = "/downloadFile/{id}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id, HttpServletResponse response)
            throws Exception {
        File file = announcementService.downloadAttachment(id);
        fileDownloadUtil.fileDownload(file, response);
    }

}
