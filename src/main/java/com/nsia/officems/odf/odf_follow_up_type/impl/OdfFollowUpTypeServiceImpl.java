package com.nsia.officems.odf.odf_follow_up_type.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import com.nsia.officems.odf.odf_follow_up_type.OdfFollowUpType;
import com.nsia.officems.odf.odf_follow_up_type.OdfFollowUpTypeRepository;
import com.nsia.officems.odf.odf_follow_up_type.OdfFollowUpTypeService;

import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class OdfFollowUpTypeServiceImpl implements OdfFollowUpTypeService{
    @Autowired
    private OdfFollowUpTypeRepository repo;

    @Override
    public List<OdfFollowUpType> findAll() {
        return repo.findAll();
    }

    @Override
    public OdfFollowUpType findById(Long id) {
        Optional<OdfFollowUpType> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfFollowUpType create(OdfFollowUpType type) {
        return repo.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<OdfFollowUpType> type = repo.findById(id);

        if (type.isPresent()) {
            OdfFollowUpType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(type2);
            return true;
        }

        return false;
    }
}
