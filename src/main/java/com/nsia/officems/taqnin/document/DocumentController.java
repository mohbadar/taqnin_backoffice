package com.nsia.officems.taqnin.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems.taqnin.document.dto.DocumentDto;
import com.nsia.officems.taqnin.document.dto.DocumentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import com.nsia.officems.taqnin.document.dto.WelcomeDocumentDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nsia.officems.file.FileDownloadUtil;
import com.nsia.officems.file.FileUploadUtil;

import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/taqnin/document")
public class DocumentController {

    @Value("${app.upload.taqnin.document}")
    private String uploadDir;

    @Value("${app.upload.taqnin.documentversions}")
    private String uploadDirV;
    
    @Autowired
    DocumentService documentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    FileDownloadUtil fileDownloadUtil;

    @Autowired
    FileUploadUtil fileUploadUtil;


    @PreAuthorize("hasAuthority('DOCUMENT_CREATE')")
    @PostMapping(value = "/create")
    public ResponseEntity<Map<String, Object>> create(
            @RequestParam(name = "avatar", required = false) MultipartFile file, @RequestParam("data") String data,
            HttpServletRequest request) throws Exception {
                Document obj = documentService.create(data, file);
        Map<String, Object> uploadObj = new HashMap<String, Object>();
        if (!obj.equals(null)) {
            uploadObj.put("objection", obj);
            return ResponseEntity.ok(uploadObj);
        }

        return null;
    }

    @PreAuthorize("hasAuthority('DOCUMENT_LIST')")
    @GetMapping()
    public List<Document> findAll(){
        return documentService.findAll();
    }

    @PreAuthorize("hasAuthority('DOCUMENT_LIST')")
    @PostMapping("/list")
    public Object getFilesList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);
        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside GetFileList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return documentService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('DOCUMENT_EDIT')")
    @PutMapping(value = "/update/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> update(@PathVariable(name = "Id", required = true) long id,
    @RequestParam(name = "avatar", required = false) MultipartFile file, @RequestParam("data") String data,
    HttpServletRequest request) {
        Document obj = documentService.update(id, data, file);
        Map<String, Object> uploadObj = new HashMap<String, Object>();
        if (!obj.equals(null)) {
            uploadObj.put("objection", obj);
            return ResponseEntity.ok(uploadObj);
        }
        return null;
        //return ResponseEntity.ok(documentService.update(id, data));
    }    
    
    // @PreAuthorize("hasAuthority('DOCUMENT_VIEW_HISTORY')")
    @PutMapping("dep/{doc_id}/{dep_id}")
    public ResponseEntity<Document> department(@PathVariable(name = "doc_id", required = true) long doc_id,@PathVariable(name = "dep_id", required = true) long dep_id) {
        return ResponseEntity.ok(documentService.assignDepartment(doc_id, dep_id));
    }

    @PreAuthorize("hasAuthority('DOCUMENT_VIEW')")
    @GetMapping(value = "/{Id}")
    public ResponseEntity<Document> findById(@PathVariable(name = "Id", required = true) long id) {
        Document objection = documentService.findById(id);
        return ResponseEntity.ok(objection);
    }

    // @PreAuthorize("hasAuthority('DOCUMENT_VIEW_HISTORY')")
    @GetMapping("/document-dto/{Id}")
    public ResponseEntity<WelcomeDocumentDto> findWelcomeDocumentDtoById(@PathVariable(name = "Id", required = true) long id) {
        Document objection = documentService.findById(id);
        WelcomeDocumentDto dto = new WelcomeDocumentDto();
        DocumentMapper.MapDocumentToWelcomeDocumentDto(objection, dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('DOCUMENT_APPROVE')")
    @PutMapping("approve/{id}")
    public ResponseEntity<Boolean> approve(@PathVariable("id") Long id, @RequestBody DocumentDto dto) {
        return ResponseEntity.ok(documentService.approveDocument(id, dto));
    }
    
    @PreAuthorize("hasAuthority('DOCUMENT_ASSIGN')")
    @PostMapping(value = "assign/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> assign(@PathVariable("id") Long id,
        @RequestParam(name = "avatar", required = false) MultipartFile file, @RequestParam("data") String data,
        HttpServletRequest request) {

        boolean obj =  documentService.assignDocument(id, data, file);
        Map<String, Object> uploadObj = new HashMap<String, Object>();
        if (!data.equals(null)) {
            uploadObj.put("objection", data);
            return ResponseEntity.ok(uploadObj);
        }
        return null;
    }

    @PreAuthorize("hasAuthority('MARK_DOCUMENT_AS_COMPLETED')")
    @PutMapping("document-completion/{id}")
    public ResponseEntity<Boolean> complete(@PathVariable("id") Long id, @RequestBody DocumentDto dto) {
        return ResponseEntity.ok(documentService.documentCompletion(id, dto));
    }

    @PreAuthorize("hasAuthority('DOCUMENT_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
      return ResponseEntity.ok(documentService.delete(id));  
    }

    // @PreAuthorize("hasAuthority('DOCUMENT_VIEW_HISTORY')")
    @GetMapping("/get-approved-list")
    public ResponseEntity<List<WelcomeDocumentDto>> getApprovedDocuments() {
        List<WelcomeDocumentDto> list = documentService.getApprovedDocuments();
        return ResponseEntity.ok(list);
    }

    // @PreAuthorize("hasAuthority('DOCUMENT_VIEW_HISTORY')")
    @GetMapping("/get-approved-list/{query}")
    public ResponseEntity<List<WelcomeDocumentDto>> getFilteredApprovedDocuments(@PathVariable("query") String query) {
        List<WelcomeDocumentDto> list = documentService.getFilteredApprovedDocuments(query);
        return ResponseEntity.ok(list);
    }


    @PreAuthorize("hasAuthority('DOCUMENT_ATTACHMENT_VIEW')")
    @GetMapping(value = "/downloadFile/{id}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id, HttpServletResponse response)
            throws Exception {
        File file = documentService.downloadAttachment(id);
        fileDownloadUtil.fileDownload(file, response);
    }

}
