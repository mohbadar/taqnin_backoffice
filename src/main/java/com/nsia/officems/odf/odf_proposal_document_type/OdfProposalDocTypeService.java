package com.nsia.officems.odf.odf_proposal_document_type;

import java.util.List;

public interface OdfProposalDocTypeService {
    public List<OdfProposalDocType> findAll();
    public OdfProposalDocType findById(Long id);
    public OdfProposalDocType create(OdfProposalDocType type);
    public Boolean delete(Long id);
}
