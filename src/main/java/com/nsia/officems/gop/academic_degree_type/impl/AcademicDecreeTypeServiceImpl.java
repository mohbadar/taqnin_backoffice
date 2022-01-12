package com.nsia.officems.gop.academic_degree_type.impl;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.nsia.officems.gop.academic_degree_type.AcademicDecreeType;
import com.nsia.officems.gop.academic_degree_type.AcademicDecreeTypeRepository;
import com.nsia.officems.gop.academic_degree_type.AcademicDecreeTypeService;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicDecreeTypeServiceImpl implements AcademicDecreeTypeService{
    @Autowired
    private AcademicDecreeTypeRepository academicDecreeTypeRepository;

    @Override
    public List<AcademicDecreeType> findAll() {
        return academicDecreeTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public AcademicDecreeType findById(Long id) {
        Optional<AcademicDecreeType> optionalObj = academicDecreeTypeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public AcademicDecreeType create(AcademicDecreeType type) {
        return academicDecreeTypeRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<AcademicDecreeType> type = academicDecreeTypeRepository.findById(id);

        if (type.isPresent()) {
            AcademicDecreeType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            academicDecreeTypeRepository.save(type2);
            return true;
        }

        return false;
    }
    
}
