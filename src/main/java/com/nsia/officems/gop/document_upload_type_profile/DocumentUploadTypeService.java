package com.nsia.officems.gop.document_upload_type_profile;

import java.util.List;

public interface DocumentUploadTypeService {
    public List<DocumentUploadType> findAll();
    public DocumentUploadType findById(Long id);
    public DocumentUploadType create(DocumentUploadType type);
    public Boolean delete(Long id);
}
