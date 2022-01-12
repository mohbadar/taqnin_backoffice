package com.nsia.officems.gop.hononary_service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HononaryServiceRepository extends JpaRepository<HononaryService, Long> {
    public List<HononaryService> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<HononaryService> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByEndDateDesc(Long id);

}
