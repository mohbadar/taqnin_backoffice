package com.nsia.officems.odf.odf_presenters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfPresenterRepository extends JpaRepository<OdfPresenter, Long> {
    public List<OdfPresenter> findByAgendaTopic_id(Long id);

    public List<OdfPresenter> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
