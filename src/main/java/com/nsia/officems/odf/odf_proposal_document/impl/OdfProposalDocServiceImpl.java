package com.nsia.officems.odf.odf_proposal_document.impl;

import com.nsia.officems.file.FileUploadUtil;

import java.util.List;
import java.util.Optional;

import javax.validation.ReportAsSingleViolation;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.odf.odf_proposal.OdfProposal;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;
import com.nsia.officems.odf.odf_proposal_document.OdfProposalDoc;
import com.nsia.officems.odf.odf_proposal_document.OdfProposalDocRepository;
import com.nsia.officems.odf.odf_proposal_document.OdfProposalDocService;
import com.nsia.officems.odf.odf_proposal_document_type.OdfProposalDocTypeService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OdfProposalDocServiceImpl implements OdfProposalDocService{
    @Value("${app.upload.proposal}")
    private String uploadDir;

    ObjectMapper mapper = new ObjectMapper();


    @Autowired
    private OdfProposalDocRepository repo;

    @Autowired
    private OdfProposalService proposalService;

    @Autowired
    private OdfProposalDocTypeService typeService;


    @Autowired
    private FileUploadUtil fileUploadUtil;


    @Override
    public List<OdfProposalDoc> findByProposal_id(Long id) {
        return repo.findByProposal_id(id);
    }

    @Override
    public OdfProposalDoc findById(Long id) {
        Optional<OdfProposalDoc> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }


    @Override
    public OdfProposalDoc create(String data, MultipartFile file) {
        try{
            JsonNode root = mapper.readTree(data);
            OdfProposalDoc upload = OdfProposalDoc.builder()
                .proposal(proposalService.findById(root.get("proposal").asLong()))
                .type(typeService.findById(root.get("type").asLong()))
                .build();
            
            if(!upload.equals(null)){
                OdfProposalDoc newObj = repo.save(upload);
                // String fileName = this.saveAttachment(file, newObj.getId());
                String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newObj.getId().toString(), "proposal");
                if(newObj != null){
                    newObj.setFileName(fileName);
                    
                    return repo.save(newObj);
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
        Optional<OdfProposalDoc> documentUpload = repo.findById(id);

        if (documentUpload.isPresent()) {
            repo.delete(documentUpload.get());
            return true;
        }
        return false;
    }



    @Override
    public File downloadAttachment(Long id) throws Exception {
        Optional<OdfProposalDoc> documentUpload = repo.findById(id);
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
