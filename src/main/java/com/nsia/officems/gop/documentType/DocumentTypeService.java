package com.nsia.officems.gop.documentType;

import java.util.List;

import com.nsia.officems._util.LookupProjection;

public interface DocumentTypeService {
    public List<LookupProjection> findAll();

    public DocumentType findById(Long id);
}