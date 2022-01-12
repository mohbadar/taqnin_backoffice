package com.nsia.officems.gop.Complaints_Docs_type;

import java.util.List;

public interface ComplaintDocsTypeService {
    public List<ComplaintDocsType> findAll();

    public ComplaintDocsType findById(Long id);

    public ComplaintDocsType create(ComplaintDocsType type);

    public Boolean delete(Long id);
}
