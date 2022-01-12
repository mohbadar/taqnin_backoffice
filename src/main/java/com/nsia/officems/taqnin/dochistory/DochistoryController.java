package com.nsia.officems.taqnin.dochistory;
import com.google.gson.Gson;
import com.nsia.officems._admin.organization.OrganizationService;
import com.nsia.officems.file.FileDownloadUtil;
import com.nsia.officems.taqnin.decision.DecisionService;
import com.nsia.officems.taqnin.dochistory.dto.DochistoryDto;
import com.nsia.officems.taqnin.dochistory.dto.DochistoryMapper;
import com.nsia.officems.taqnin.document.DocumentRepository;
import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.workflow.WorkflowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.io.File;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/taqnin/dochistory")
public class DochistoryController {

    @Autowired
    private DochistoryService service;
    @Autowired
    private DocumentService documentService;

    @Autowired
    private DecisionService decisionService;
    @Autowired
    private WorkflowService workflowService;
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    FileDownloadUtil fileDownloadUtil;

    @PreAuthorize("hasAuthority('DOCUMENT_VIEW_HISTORY')")
    @GetMapping()
    public List<Dochistory> findAll(){
        return service.findAll();
    }

    // @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_CREATE')")
    @GetMapping(path = {"/{id}"})
    public List<Dochistory> findByDocumentId(@PathVariable("id") Long id){
        return service.findByDocumentId(id);
    }

    // @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_CREATE')")
    @PostMapping()
    public Dochistory create(@RequestBody DochistoryDto dto ){

        return service.create(DochistoryMapper.MapDochistoryDto(new Dochistory(), dto, documentService, workflowService, documentRepository, decisionService));
    }

    // @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_CREATE')")
    @PutMapping("/{id}")
    public ResponseEntity<Dochistory> update(@PathVariable("id") Long id, @RequestBody String decision) {
        Gson g = new Gson();
        Dochistory c = g.fromJson(decision, Dochistory.class);
        if(c != null) {
            return ResponseEntity.ok(service.update(id, c));
        }
        return null;
    }

    // @PreAuthorize("hasAuthority('MAIN_ANNOUNCEMENT_CREATE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping(value = "/downloadFile/{id}/{docId}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id,
    @PathVariable(name = "docId", required = true) Long docId, HttpServletResponse response)
            throws Exception {
        File file = service.downloadAttachment(id, docId);
        fileDownloadUtil.fileDownload(file, response);
    }

}
