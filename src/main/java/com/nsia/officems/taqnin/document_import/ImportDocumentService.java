package com.nsia.officems.taqnin.document_import;
import java.util.List;
public interface ImportDocumentService {
    public ImportDocument findById(Long id);
    public ImportDocument create(ImportDocument export);
    public Boolean delete(Long id);
    public ImportDocument update(Long id, ImportDocument export);
    public List<ImportDocument> findByDocumentId(Long id);
}
