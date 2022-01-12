package com.nsia.officems.gop.gender.impl;

import com.nsia.officems.gop.gender.Gender;
import com.nsia.officems.gop.gender.GenderRepository;
import com.nsia.officems.gop.gender.GenderService;


import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService{
    @Autowired
    private GenderRepository genderRepository;


    @Override
    public List<Gender> findAll() {
        return genderRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Gender findById(Long id) {
        Optional<Gender> optionalObj = genderRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Gender create(Gender gender) {
        return genderRepository.save(gender);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<Gender> gender = genderRepository.findById(id);

        if (gender.isPresent()) {
            Gender gender2 = gender.get();
            gender2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            gender2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            genderRepository.save(gender2);
            return true;
        }

        return false;
    } 
}
