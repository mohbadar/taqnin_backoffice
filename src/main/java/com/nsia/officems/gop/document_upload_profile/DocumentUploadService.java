package com.nsia.officems.gop.document_upload_profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface DocumentUploadService {
    public DocumentUpload findById(Long id);
    public DocumentUpload create(String data, MultipartFile file);
    public Boolean delete(Long id);
    public List<DocumentUpload> findByProfile_id(Long id);
    public File downloadAttachment(Long id) throws Exception;
}
