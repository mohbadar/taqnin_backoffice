package com.nsia.officems.gop.academic_degree.impl;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.academic_degree.AcademicDegree;
import com.nsia.officems.gop.academic_degree.AcademicDegreeRepository;
import com.nsia.officems.gop.academic_degree.AcademicDegreeService;
import com.nsia.officems.gop.academic_degree.dto.AcademicDegreeDto;
import com.nsia.officems.gop.academic_degree.dto.AcademicDegreeMapperDto;
import com.nsia.officems.gop.academic_degree_type.AcademicDecreeTypeService;
import com.nsia.officems.gop.profile.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AcademicDegreeServiceImpl implements AcademicDegreeService{ 
    
    private ChecklistTitle titles = new ChecklistTitle();
    
    @Autowired
    private AcademicDegreeRepository academicDegreeRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private AcademicDecreeTypeService academicDecreeTypeService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Override
    public List<AcademicDegree> findAll() {
        return academicDegreeRepository.findAll();
    }

    @Override
    public List<AcademicDegree> findByProfile_id(Long id) {
        return academicDegreeRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(id);
    }

    @Override
    public AcademicDegree findById(Long id) {
        Optional<AcademicDegree> optionalObj = academicDegreeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public AcademicDegree create(AcademicDegreeDto dto) {
        AcademicDegree newAcademic = new AcademicDegree();
        AcademicDegree academicDegree = AcademicDegreeMapperDto.MapAcademicDto(newAcademic, dto, profileService, academicDecreeTypeService);
        academicDegree.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!academicDegree.equals(null)) {

            profileChecklistService.update(academicDegree.getProfile().getId(), titles.getAcademic_degree());
            return academicDegreeRepository.save(academicDegree);
        }
        return null;
    }

    public Boolean update(Long id, AcademicDegreeDto dto) {
        Optional<AcademicDegree> objection = academicDegreeRepository.findById(id);
        if (objection.isPresent()) {
            AcademicDegree academicDegree = AcademicDegreeMapperDto.MapAcademicDto(objection.get(), dto, profileService,academicDecreeTypeService);
            academicDegree.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if(!academicDegree.equals(null))
            {
                academicDegreeRepository.save(academicDegree);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<AcademicDegree> academicdegree = academicDegreeRepository.findById(id);

        if (academicdegree.isPresent()) {
            AcademicDegree academicDegree2 = academicdegree.get();
            academicDegree2.setDeleted(true);
            academicDegree2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            academicDegreeRepository.save(academicDegree2);
            return true;
        }

        return false;
    }
    
}
