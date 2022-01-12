package com.nsia.officems.odf.odf_authority_agreement;

import java.util.List;

public interface OdfAuthorityAgreementService {
    public List<OdfAuthorityAgreement> findAll();
    public OdfAuthorityAgreement findById(Long id);
    public OdfAuthorityAgreement create(OdfAuthorityAgreement level);
    public Boolean delete(Long id);
}
