package com.nsia.officems.gop.complaint;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsType;
import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsTypeService;
import com.nsia.officems.gop.complaint.requests.CreateComplaintRequest;
import com.nsia.officems.gop.complaint.requests.EditComplaintRequest;
import com.nsia.officems.gop.complaint.requests.UploadComplaintDocumentRequest;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.file.FileDownloadUtil;
import com.nsia.officems.file.FileUploadUtil;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/complaints")
public class ComplaintController {

    @Value("${app.upload.complaint}")
    private String uploadDir;

    private final ModelMapper _modelMapper;
    private final ComplaintService _complaintService;
    private final ComplaintDocsTypeService _complaintDocsTypeService;
    private final FileDownloadUtil _fileDownloadUtil;
    private final ObjectMapper _objectMapper;

    @GetMapping()
    public List<Complaint> findAll() {
        return _complaintService.findAll();
    }

    @PostMapping("/list")
    public Object getComplaintsList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = _objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = _objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return _complaintService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(path = { "/{id}" })
    public Complaint findById(@PathVariable("id") Long id) {
        return _complaintService.findById(id);
    }

    @PostMapping()
    public Complaint create(@RequestBody CreateComplaintRequest createComplaintRequest) {
        System.out.println("Create Controller");
        return _complaintService.create(createComplaintRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_complaintService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public boolean updateComplaintById(@PathVariable(value = "id") Long id,
            @Valid @RequestBody EditComplaintRequest editComplaintRequest) {
        return _complaintService.update(id, editComplaintRequest);
    }

    @PostMapping(path = "/upload/complaint-documents/{id}/{fileType}")
    public String uploadComplaintDocuments(@PathVariable(value = "id") Long id,
            @PathVariable(value = "fileType") String fileType, @RequestBody MultipartFile file) throws IOException {
        Complaint complaint = _complaintService.findById(id);
        String uploadDirectory = uploadDir + "/" + complaint.getId().toString() + "/" + fileType;
        String fileLocation = _complaintService.saveAttachment(uploadDirectory, file);
        Boolean fieldUpdated = _complaintService.updateFileLocation(id, fileType, fileLocation);
        if (fieldUpdated) {
            return fileLocation;
        } else
            return null;
    }

    @GetMapping(path = "/filenames/{id}")
    public Map<String, String> findAllFileNames(@PathVariable("id") Long id) {
        Complaint complaint = _complaintService.findById(id);
        Map<String, String> fileNames = new HashMap<String, String>();
        fileNames.put("objectionAttachment", complaint.getObjectionAttachment());
        fileNames.put("complaintToResponsibleAuthorityAttachment",
                complaint.getComplaintToResponsibleAuthorityAttachment());
        fileNames.put("complaintToBoardAttachment", complaint.getComplaintToBoardAttachment());
        fileNames.put("courtDecreeAttachment", complaint.getCourtDecreeAttachment());
        fileNames.put("committeeDecisionAttachment", complaint.getCommitteeDecisionAttachment());
        fileNames.put("responsibleAuthorityResponseAttachment", complaint.getResponsibleAuthorityResponseAttachment());
        return fileNames;
    }

    @GetMapping(value = "/download-file/{id}/{fileType}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id,
            @PathVariable("fileType") String fileType, HttpServletResponse response) throws Exception {
        File file = _complaintService.downloadAttachment(id, fileType);
        _fileDownloadUtil.fileDownload(file, response);
    }

    @DeleteMapping(path = "/delete-file/{id}/{fileType}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable("id") Long id, @PathVariable("fileType") String fileType) {
        return ResponseEntity.ok(_complaintService.deleteAttachment(id, fileType));
    }

    @GetMapping(value = "/{id}/history", produces = MediaType.ALL_VALUE)
    public ResponseEntity<List<RevisionDTO>> getHistory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(_complaintService.getComplaintLog(id));
    }

}
