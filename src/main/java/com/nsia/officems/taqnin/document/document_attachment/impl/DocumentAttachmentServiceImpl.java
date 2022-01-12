package com.nsia.officems.taqnin.document.document_attachment.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.document.document_attachment.DocumentAttachment;
import com.nsia.officems.taqnin.document.document_attachment.DocumentAttachmentReposity;
import com.nsia.officems.taqnin.document.document_attachment.DocumentAttachmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentAttachmentServiceImpl implements DocumentAttachmentService{
    @Value("${app.upload.taqnin.document}")
    private String uploadDir;

    @Autowired
    DocumentAttachmentReposity repo;

    @Autowired
    private DocumentService documentService;

    @Override
    public DocumentAttachment findById(Long id) {
        Optional<DocumentAttachment> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentAttachment create(Long documentId, String fileName, String attachmentName) {
        DocumentAttachment documentAttachment = new DocumentAttachment();
        documentAttachment.setFileName(fileName);
        documentAttachment.setAttachmentName(attachmentName);
        documentAttachment.setDocument(documentService.findById(documentId));
        documentAttachment.setDeleted(false);
        return repo.save(documentAttachment);
    }

    @Override
    public List<DocumentAttachment> findByDocumentId(Long id) {
        return repo.findByDocument_idAndDeletedFalse(id);
    }

    public String saveAttachment(String uploadDirectory, MultipartFile file) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = null;
        if (file != null) {
            fileName = formatter.format(Calendar.getInstance().getTime()) + "_" + file.getOriginalFilename();
            String saveDirectory = uploadDirectory;
            File test = new File(saveDirectory);
            if (!test.exists()) {
                test.mkdirs();
            }
            try {
                File f = new File(saveDirectory + "/" + fileName);
                // create the file
                if (!f.exists()) {

                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(file.getBytes());
                fout.close();
            } catch (Exception e) {

            }
        }
        return fileName;
    }

    @Override
    public Boolean deleteDocumentAttachmentById(Long documentAttachmentId) {
        Optional<DocumentAttachment> documentAttachement = repo.findById(documentAttachmentId);
        if (documentAttachement.isPresent()) {
            DocumentAttachment documentAttachmentToBeUpdated = documentAttachement.get();
            documentAttachmentToBeUpdated.setDeleted(true);
            repo.save(documentAttachmentToBeUpdated);
            return true;
        }
        return false;
    }
    @Override
    public File downloadAttachment(Long attachmentId, Long documentId) throws Exception{
        Optional<DocumentAttachment> documentUpload = repo.findById(attachmentId);
        if(documentUpload.isPresent())        
       {
        String fileName = documentUpload.get().getAttachmentName();
        String saveDirectory = uploadDir + documentId + "/" + fileName;
        if (new File(saveDirectory).exists()) {
            return new File(saveDirectory);
        }
       }

        return null;
    }
}
