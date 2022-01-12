package com.nsia.officems.odf.odf_proposal_document;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
public interface OdfProposalDocService {
    public OdfProposalDoc findById(Long id);
    public OdfProposalDoc create(String data, MultipartFile file);
    public Boolean delete(Long id);
    public List<OdfProposalDoc> findByProposal_id(Long id);
    public File downloadAttachment(Long id) throws Exception;
}
