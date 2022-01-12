package com.nsia.officems.gop.military_service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilitaryServiceRepository extends JpaRepository<MilitaryService, Long> {
    public List<MilitaryService> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(Long id);

    public MilitaryService findFirstByType(String type);

}
