package com.nsia.officems.gop.Complaints_Docs_type;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintDocsTypeRepository extends JpaRepository<ComplaintDocsType, Long> {
        public List<ComplaintDocsType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
