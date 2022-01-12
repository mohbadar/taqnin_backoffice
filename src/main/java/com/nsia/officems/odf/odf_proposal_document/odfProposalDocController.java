package com.nsia.officems.odf.odf_proposal_document;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nsia.officems.file.FileDownloadUtil;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/odf/proposaldocument")
public class odfProposalDocController {
    @Autowired
    private OdfProposalDocService service;
    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @GetMapping("/proposal")
    public List<OdfProposalDoc> getDocumentsByProposal(@RequestParam("pId") Long pId) {
        return service.findByProposal_id(pId);
    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "Id", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        OdfProposalDoc objection = service.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }

    @PostMapping(value = "/document")
    public ResponseEntity<Map<String, Object>> photo(
            @RequestParam(name = "avatar", required = false) MultipartFile file, @RequestParam("data") String data,
            HttpServletRequest request) throws Exception {
                OdfProposalDoc obj = service.create(data, file);
        Map<String, Object> uploadObj = new HashMap<String, Object>();
        if (!obj.equals(null)) {
            uploadObj.put("objection", obj);
            return ResponseEntity.ok(uploadObj);
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    
    @GetMapping(value = "/downloadFile/{id}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id,
            HttpServletResponse response) throws Exception {
        File file = service.downloadAttachment(id);
        fileDownloadUtil.fileDownload(file, response);
    }
}
