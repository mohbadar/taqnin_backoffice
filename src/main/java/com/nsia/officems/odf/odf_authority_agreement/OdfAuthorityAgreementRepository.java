package com.nsia.officems.odf.odf_authority_agreement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfAuthorityAgreementRepository extends JpaRepository<OdfAuthorityAgreement, Long> {
    public List<OdfAuthorityAgreement> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 
}
