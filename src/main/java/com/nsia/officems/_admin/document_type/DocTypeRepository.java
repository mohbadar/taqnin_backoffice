package com.nsia.officems._admin.document_type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocTypeRepository extends JpaRepository<DocType, Long> {
    public List<DocType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
