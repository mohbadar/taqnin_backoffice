package com.nsia.officems.gop.decree_document_type;

import java.util.List;

import com.nsia.officems._util.LookupProjection;

public interface DecreeDocumentTypeService {
    public List<LookupProjection> findAll();

    public DecreeDocumentType findById(Long id);

}
