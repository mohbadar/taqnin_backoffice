package com.nsia.officems.gop.complaint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface ComplaintRepository
        extends JpaRepository<Complaint, Long>, RevisionRepository<Complaint, Long, Integer> {
    public List<Complaint> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    public List<Complaint> findAll();

    @Query(value = "select count(*) from public.complaints where deleted is not true", nativeQuery = true)
    public long count();
}
