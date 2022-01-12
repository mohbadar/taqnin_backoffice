package com.nsia.officems.taqnin.document;

import com.nsia.officems.taqnin.document.dto.DocumentDto;
import com.nsia.officems.taqnin.document.dto.WelcomeDocumentDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.io.File;

public interface DocumentService {

    List<Document> findAll();

    Document create(String data,MultipartFile file);

    Object getList(DataTablesInput input, Map<String, String> filters);

    public Document findById(long id);

    public boolean approveDocument(Long id, DocumentDto dto);
    public boolean assignDocument(Long id, String data, MultipartFile file);
    public boolean documentCompletion(Long id, DocumentDto dto);

    public boolean delete(long id);

    public Document update(Long id, String data, MultipartFile file);
    public Document assignDepartment(Long doc_id, Long dep_id);
    
    List<WelcomeDocumentDto> getApprovedDocuments();
    List<WelcomeDocumentDto> getFilteredApprovedDocuments(String query);
    
    public File downloadAttachment(Long id) throws Exception;

    public long countTotalDocuments();
    public long countApprovedDocuments();
    public long countNotApprovedDocuments();
    // public long countInProgressDocuments();
}
