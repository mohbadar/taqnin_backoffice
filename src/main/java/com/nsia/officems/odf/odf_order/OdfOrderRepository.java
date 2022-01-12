package com.nsia.officems.odf.odf_order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfOrderRepository extends JpaRepository<OdfOrder, Long> {
    public List<OdfOrder> findBySubject_id(Long id);

    public List<OdfOrder> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
