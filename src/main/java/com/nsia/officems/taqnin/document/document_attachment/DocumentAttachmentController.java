package com.nsia.officems.taqnin.document.document_attachment;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.nsia.officems.file.FileDownloadUtil;
import com.nsia.officems.taqnin.document.Document;
import com.nsia.officems.taqnin.document.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/taqnin/document-attachments")
public class DocumentAttachmentController {
    

    @Value("${app.upload.taqnin.document}")
    private String uploadDir;

    @Autowired
    private DocumentAttachmentService documentAttachmentService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @GetMapping("document/{documentId}")
    public List<DocumentAttachment> findByDocumentId(@PathVariable("documentId") Long id) {
        return documentAttachmentService.findByDocumentId(id);
    }

    @PostMapping(path = "/upload-file/{documentId}/{fileName}")
    public String uploadDocumentAttachment(@PathVariable(value = "documentId") Long documentId,
            @PathVariable(value = "fileName") String fileName, @RequestBody MultipartFile file) throws IOException {
        Document document = documentService.findById(documentId);
        if (document != null) {
            String uploadDirectory = uploadDir + "/" + document.getId().toString();
            String fileLocation = documentAttachmentService.saveAttachment(uploadDirectory, file);
            DocumentAttachment updateDocumentAttachment = documentAttachmentService.create(documentId,
                    fileName, fileLocation);
            if (updateDocumentAttachment != null) {
                return fileLocation;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @DeleteMapping("delete/{documentAttachmentId}")
    public Boolean deleteDocumentAttachment(@PathVariable("documentAttachmentId") Long id) {
        return documentAttachmentService.deleteDocumentAttachmentById(id);
    }

    @GetMapping(value = "/downloadFile/{attachmentId}/{documentId}")
    public void downloadAttachment(@PathVariable(name = "attachmentId", required = true) Long attachmentId,@PathVariable(name = "documentId", required = true) Long documentId,
            HttpServletResponse response) throws Exception {
        File file = documentAttachmentService.downloadAttachment(attachmentId, documentId);
        fileDownloadUtil.fileDownload(file, response);
    }
}
