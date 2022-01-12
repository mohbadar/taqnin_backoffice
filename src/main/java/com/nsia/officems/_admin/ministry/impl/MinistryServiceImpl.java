package com.nsia.officems._admin.ministry.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.ministry.Ministry;
import com.nsia.officems._admin.ministry.MinistryRepository;
import com.nsia.officems._admin.ministry.MinistryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinistryServiceImpl implements MinistryService {
    @Autowired
    private MinistryRepository ministryRepository;

    @Override
    public List<Ministry> findAll() {
        return ministryRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Ministry findById(Long id) {
        Optional<Ministry> optionalObj = ministryRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Ministry create(Ministry ministry) {
        ministry.setDeleted(false);
        return ministryRepository.save(ministry);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Ministry> ministry = ministryRepository.findById(id);

        if (ministry.isPresent()) {
            Ministry ministry2 = ministry.get();
            ministry2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            ministry2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            ministryRepository.save(ministry2);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, Ministry ministry) {
        Optional<Ministry> ministryToBeUpdated = ministryRepository.findById(id);
        if (ministryToBeUpdated.isEmpty()) {
            return false;
        }

        Ministry editedMinistry = ministryToBeUpdated.get();

        editedMinistry.setNameEn(ministry.getNameEn());
        editedMinistry.setNameDr(ministry.getNameDr());
        editedMinistry.setNamePs(ministry.getNamePs());

        ministryRepository.save(editedMinistry);
        return true;
    }

}
