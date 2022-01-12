package com.nsia.officems.taqnin.doc_department;
import java.util.List;
public interface DocDepartmentService {
    public DocDepartment findById(Long id);
    public DocDepartment create(DocDepartment department);
    public Boolean delete(Long id);
    public DocDepartment update(Long id, DocDepartment department);
    public  List<DocDepartment> findByDocumentId(Long documentId);
}
