package com.nsia.officems.odf.odf_authority_agreement.impl;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreement;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementRepository;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OdfAuthorityAgreementServiceImpl implements OdfAuthorityAgreementService{
    @Autowired
    private OdfAuthorityAgreementRepository odfAuthorityAgreementRepository;

    @Override
    public List<OdfAuthorityAgreement> findAll() {
        return odfAuthorityAgreementRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public OdfAuthorityAgreement findById(Long id) {
        Optional<OdfAuthorityAgreement> optionalObj = odfAuthorityAgreementRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfAuthorityAgreement create(OdfAuthorityAgreement level) {
        return odfAuthorityAgreementRepository.save(level);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<OdfAuthorityAgreement> level = odfAuthorityAgreementRepository.findById(id);

        if (level.isPresent()) {
            OdfAuthorityAgreement level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfAuthorityAgreementRepository.save(level2);
            return true;
        }

        return false;
    } 
}
