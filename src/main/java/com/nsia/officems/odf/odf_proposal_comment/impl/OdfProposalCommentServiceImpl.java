package com.nsia.officems.odf.odf_proposal_comment.impl;

import com.nsia.officems.file.FileUploadUtil;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;
import com.nsia.officems.odf.odf_proposal_comment.OdfProposalComment;
import com.nsia.officems.odf.odf_proposal_comment.OdfProposalCommentRepository;
import com.nsia.officems.odf.odf_proposal_comment.OdfProposalCommentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.io.File;
import java.time.ZoneId;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OdfProposalCommentServiceImpl implements OdfProposalCommentService{
    @Value("${app.upload.proposal.comment}")
    private String uploadDir;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private OdfProposalCommentRepository repo;

    @Autowired
    private OdfProposalService proposalService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Override
    public List<OdfProposalComment> findByProposal_id(Long id) {
        return repo.findByProposal_idAndDeletedIsFalseOrDeletedIsNull(id);
    }

    @Override
    public OdfProposalComment findById(Long id) {
        Optional<OdfProposalComment> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfProposalComment create(String data, MultipartFile file) {
        try{
            Date newDate = new Date();
            JsonNode root = mapper.readTree(data);
            OdfProposalComment upload = OdfProposalComment.builder()
                .proposal(proposalService.findById(root.get("proposal").asLong()))
                .title(root.get("title").asText())
                .content(root.get("content").asText())
                .date(newDate)
                .build();
            
            if(!upload.equals(null)){
                OdfProposalComment newObj = repo.save(upload);
                // String fileName = this.saveAttachment(file, newObj.getId());
                String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newObj.getId().toString(), "comment");
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
    public Boolean update(Long id, String data, MultipartFile file){
        try{
            JsonNode root = mapper.readTree(data);
            Optional <OdfProposalComment> upload = repo.findById(id);
            
            if(upload.isPresent()){
                OdfProposalComment newObj = upload.get();
                if(file != null)
                {
                    String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newObj.getId().toString(), "comment");
                    newObj.setFileName(fileName);
                }

                newObj.setProposal(proposalService.findById(root.get("proposal").asLong()));
                newObj.setTitle(root.get("title").asText());
                newObj.setContent(root.get("content").asText());
                repo.save(newObj);
                return true;
                
            }
            return false;
        }
        catch(Exception e){
            System.out.println("exception occured in creating documentUpload");
            return null;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfProposalComment> commentUpload = repo.findById(id);

        if (commentUpload.isPresent()) {
            OdfProposalComment newObj = commentUpload.get();
            newObj.setDeleted(true);
            newObj.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(newObj);
            return true;
        }
        return false;
    }



    @Override
    public File downloadAttachment(Long id) throws Exception {
        Optional<OdfProposalComment> documentUpload = repo.findById(id);
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
