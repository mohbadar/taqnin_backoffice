package com.nsia.officems.gop.panelty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaneltyRepository extends JpaRepository<Panelty, Long> {
    public List<Panelty> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<Panelty> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByDecreeDateDesc(Long id);

}
