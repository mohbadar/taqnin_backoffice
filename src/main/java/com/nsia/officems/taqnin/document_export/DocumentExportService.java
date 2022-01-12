package com.nsia.officems.taqnin.document_export;
import java.util.List;
public interface DocumentExportService {
    public DocumentExport findById(Long id);
    public DocumentExport create(DocumentExport export);
    public Boolean delete(Long id);
    public DocumentExport update(Long id, DocumentExport export);
    public List<DocumentExport> findByDocumentId(Long id);
}
