package com.nsia.officems.taqnin.dochistory;
import java.io.File;
import java.util.List;
public interface DochistoryService {
    public List<Dochistory> findAll();
    public Dochistory findById(Long id);
    public Dochistory create(Dochistory decision);
    public Boolean delete(Long id);
    public Dochistory update(Long id, Dochistory decision);
    public List<Dochistory> findByDocumentId(Long document_id);
    public File downloadAttachment(Long id, Long docId) throws Exception;
}
