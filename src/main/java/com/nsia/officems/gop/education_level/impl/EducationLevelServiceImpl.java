package com.nsia.officems.gop.education_level.impl;
import com.nsia.officems.gop.education_level.EducationLevel;
import com.nsia.officems.gop.education_level.EducationLevelRepository;
import com.nsia.officems.gop.education_level.EducationLevelService;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationLevelServiceImpl implements EducationLevelService{
    @Autowired
    private EducationLevelRepository educationLevelRepository;

    @Override
    public List<EducationLevel> findAll() {
        return educationLevelRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public EducationLevel findById(Long id) {
        Optional<EducationLevel> optionalObj = educationLevelRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public EducationLevel create(EducationLevel level) {
        return educationLevelRepository.save(level);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<EducationLevel> level = educationLevelRepository.findById(id);

        if (level.isPresent()) {
            EducationLevel level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            educationLevelRepository.save(level2);
            return true;
        }

        return false;
    }
    
}
