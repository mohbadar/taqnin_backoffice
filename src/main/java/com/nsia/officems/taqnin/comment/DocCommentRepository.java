package com.nsia.officems.taqnin.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocCommentRepository extends JpaRepository<DocComment, Long> {
    public List<DocComment> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    public List<DocComment> findByDocument_idAndDeletedFalse(Long id);
}
