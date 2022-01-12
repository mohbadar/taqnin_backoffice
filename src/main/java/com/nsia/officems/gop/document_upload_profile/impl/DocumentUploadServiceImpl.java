package com.nsia.officems.gop.document_upload_profile.impl;

import com.nsia.officems.gop.document_upload_profile.DocumentUpload;
import com.nsia.officems.gop.document_upload_profile.DocumentUploadRepository;
import com.nsia.officems.gop.document_upload_profile.DocumentUploadService;
import com.nsia.officems.gop.document_upload_type_profile.DocumentUploadTypeService;
import com.nsia.officems.file.FileUploadUtil;

import java.util.List;
import java.util.Optional;
import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems.gop.profile.ProfileService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DocumentUploadServiceImpl implements DocumentUploadService{
    @Value("${app.profile.document}")
    private String uploadDir;

    private ChecklistTitle titles = new ChecklistTitle();
    ObjectMapper mapper = new ObjectMapper();


    @Autowired
    private DocumentUploadRepository documentUploadRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private DocumentUploadTypeService documentUploadTypeService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private FileUploadUtil fileUploadUtil;


    @Override
    public List<DocumentUpload> findByProfile_id(Long id) {
        return documentUploadRepository.findByProfile_idOrderByCreatedAtDesc(id);
    }

    @Override
    public DocumentUpload findById(Long id) {
        Optional<DocumentUpload> optionalObj = documentUploadRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }


    @Override
    public DocumentUpload create(String data, MultipartFile file) {
        try{
            JsonNode root = mapper.readTree(data);
            DocumentUpload upload = DocumentUpload.builder()
                .profile(profileService.findByIdWithoutRelation(root.get("profile").asLong()))
                .type(documentUploadTypeService.findById(root.get("type").asLong()))
                .build();
            
            if(!upload.equals(null)){
                DocumentUpload newObj = documentUploadRepository.save(upload);
                // String fileName = this.saveAttachment(file, newObj.getId());
                String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newObj.getId().toString(), "profile");
                if(newObj != null){
                    profileChecklistService.update(newObj.getProfile().getId(), titles.getDocument());
                    newObj.setFileName(fileName);
                    
                    return documentUploadRepository.save(newObj);
                }
                
            }
            return null;
        }
        catch(Exception e){
            System.out.println("exception occured in creating documentUpload");
            return null;
        }
    }
    

    @Override
    public Boolean delete(Long id) {
        Optional<DocumentUpload> documentUpload = documentUploadRepository.findById(id);

        if (documentUpload.isPresent()) {
            documentUploadRepository.delete(documentUpload.get());
            return true;
        }
        return false;
    }



    @Override
    public File downloadAttachment(Long id) throws Exception {
        Optional<DocumentUpload> documentUpload = documentUploadRepository.findById(id);
        if(documentUpload.isPresent())        
       {
        String fileName = documentUpload.get().getFileName();
        String saveDirectory = uploadDir + "/" + id + "/" + fileName;
        if (new File(saveDirectory).exists()) {
            return new File(saveDirectory);
        }
       }

        return null;
    }

    
}
