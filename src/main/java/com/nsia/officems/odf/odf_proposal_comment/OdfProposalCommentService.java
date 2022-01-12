package com.nsia.officems.odf.odf_proposal_comment;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface OdfProposalCommentService {
    public OdfProposalComment findById(Long id);
    public OdfProposalComment create(String data, MultipartFile file);
    public Boolean update(Long id, String data, MultipartFile file);
    public Boolean delete(Long id);
    public List<OdfProposalComment> findByProposal_id(Long id);
    public File downloadAttachment(Long id) throws Exception;
}
