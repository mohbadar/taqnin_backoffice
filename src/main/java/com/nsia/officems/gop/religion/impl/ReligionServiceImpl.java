package com.nsia.officems.gop.religion.impl;

import com.nsia.officems.gop.religion.Religion;
import com.nsia.officems.gop.religion.ReligionRepository;
import com.nsia.officems.gop.religion.ReligionService;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReligionServiceImpl implements ReligionService{ 
    
    @Autowired
    private ReligionRepository religionRepository;
    
    @Override
    public List<Religion> findAll() {
        return religionRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Religion findById(Long id) {
        Optional<Religion> optionalObj = religionRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Religion create(Religion religion) {
        religion.setDeleted(false);
        return religionRepository.save(religion);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Religion> religion = religionRepository.findById(id);

        if (religion.isPresent()) {
            Religion religion2 = religion.get();
            religion2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            religion2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            religionRepository.save(religion2);
            return true;
        }

        return false;
    }

}
