package com.nsia.officems.taqnin.comment;
import java.util.List;
public interface DocCommentService {
    public DocComment findById(Long id);
    public DocComment create(DocComment comment);
    public Boolean delete(Long id);
    public DocComment update(Long id, DocComment comment);
    public List<DocComment> findByDocumentId(Long id);
}
