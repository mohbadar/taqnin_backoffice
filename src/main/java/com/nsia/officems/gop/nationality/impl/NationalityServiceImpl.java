package com.nsia.officems.gop.nationality.impl;

import com.nsia.officems.gop.nationality.Nationality;
import com.nsia.officems.gop.nationality.NationalityRepository;
import com.nsia.officems.gop.nationality.NationalityService;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NationalityServiceImpl implements NationalityService{

    @Autowired
    private NationalityRepository nationalityRepository;
    
    @Override
    public List<Nationality> findAll() {
        return nationalityRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Nationality findById(Long id) {
        Optional<Nationality> optionalObj = nationalityRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Nationality create(Nationality nationality) {
        nationality.setDeleted(false);
        return nationalityRepository.save(nationality);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Nationality> nationality = nationalityRepository.findById(id);

        if (nationality.isPresent()) {
            Nationality nationality2 = nationality.get();
            nationality2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            nationality2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            nationalityRepository.save(nationality2);
            return true;
        }

        return false;
    }
    
}
