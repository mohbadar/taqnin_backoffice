package com.nsia.officems.gop.paneltyType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface paneltyTypeRepository extends JpaRepository<PaneltyType, Long> {
    public List<PaneltyType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
