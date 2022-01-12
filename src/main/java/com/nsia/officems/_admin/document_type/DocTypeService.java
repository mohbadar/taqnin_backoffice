package com.nsia.officems._admin.document_type;

import java.util.List;

public interface DocTypeService {
    public List<DocType> findAll();

    public DocType findById(Long id);

    public DocType create(DocType doctype);

    public Boolean delete(Long id);

    public boolean update(Long id, DocType doctype);

}
