package com.nsia.officems.gop.comment;
import java.util.List;

public interface CommentService {
    public List<Comment> findAll();
    public Comment findById(Long id);
    public Comment create(Comment comment);
    public Boolean delete(Long id);
    public Comment update(Long id, Comment comment);

}
